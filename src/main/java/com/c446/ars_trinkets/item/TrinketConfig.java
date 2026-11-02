package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.Config;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Loads, hot-reloads and auto-generates the per-trinket attribute JSON.
 *
 * <pre>
 * {
 *   "ars_trinkets:death_rune": {
 *     "enabled": true,                 // false = trinket gives no attributes at all
 *     "mode": "OVERRIDE",              // OVERRIDE: only this list. MERGE: built-in stats + this list
 *     "attributes": {
 *       "minecraft:generic.armor": [   // an object is accepted instead of a one-element array
 *         { "id": "auto", "amount": 2.0, "operation": "add_multiplied_total" }
 *       ]
 *     }
 *   }
 * }
 * </pre>
 * Missing trinkets are appended on server start (existing entries are never touched), the file is re-read
 * when it changes on disk (re-equip the trinket to apply), and one bad entry never breaks the others.
 */
@EventBusSubscriber
public final class TrinketConfig {
    public enum Mode {MERGE, OVERRIDE}

    private record Spec(Holder<Attribute> attribute, String id, double amount, AttributeModifier.Operation op) {}

    private record Entry(Mode mode, boolean enabled, List<Spec> specs) {}

    private record Loaded(long modified, Map<ResourceLocation, Entry> entries) {}

    private static final String DEFAULT_PATH = "ars_trinkets/curios_config.json";
    private static final long CHECK_INTERVAL_MS = 2000L;
    private static final Gson PRETTY = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private static Loaded loaded = new Loaded(Long.MIN_VALUE, Map.of());
    private static long lastCheck = 0L;

    private TrinketConfig() {}

    // ---------------------------------------------------------------- public API

    public static Multimap<Holder<Attribute>, AttributeModifier> resolve(ITrinket trinket, ResourceLocation slotId) {
        Entry entry = entry(trinket.trinketId());
        if (entry == null) return trinket.defaultModifiers(slotId);
        if (!entry.enabled()) return HashMultimap.create();

        Multimap<Holder<Attribute>, AttributeModifier> result =
                entry.mode() == Mode.OVERRIDE ? HashMultimap.create() : trinket.defaultModifiers(slotId);
        ResourceLocation item = trinket.trinketId();
        int index = 0;
        for (Spec spec : entry.specs()) {
            result.put(spec.attribute(), new AttributeModifier(modifierId(spec, item, slotId, index++), spec.amount(), spec.op()));
        }
        return result;
    }

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        generateMissing();
        synchronized (TrinketConfig.class) {
            loaded = new Loaded(Long.MIN_VALUE, Map.of());
            lastCheck = 0L;
        }
    }

    /** Appends a default entry for every trinket that is not in the file yet. */
    public static synchronized void generateMissing() {
        Path file = path();
        JsonObject root = new JsonObject();
        try {
            if (Files.exists(file)) {
                try (Reader r = Files.newBufferedReader(file)) {
                    JsonElement parsed = JsonParser.parseReader(r);
                    if (parsed.isJsonObject()) root = parsed.getAsJsonObject();
                }
            }
        } catch (Exception e) {
            // Never overwrite a file the user may be in the middle of fixing.
            ArsTrinkets.LOGGER.error("[ArsTrinkets] cannot read {}, not generating defaults: {}", file, e.toString());
            return;
        }

        boolean changed = false;
        if (!root.has("_readme")) {
            JsonArray readme = new JsonArray();
            for (String line : List.of(
                    "One entry per trinket. Entries are generated automatically; existing ones are never overwritten.",
                    "mode OVERRIDE: only 'attributes' below apply. mode MERGE: built-in stats + 'attributes'.",
                    "enabled false: the trinket gives no attributes. Delete an entry to restore the built-in stats.",
                    "operation: add_value | add_multiplied_base | add_multiplied_total. id: 'auto' or a unique name.",
                    "The file is re-read when it changes; re-equip the trinket to apply it.")) {
                readme.add(line);
            }
            JsonObject withReadme = new JsonObject();
            withReadme.add("_readme", readme);
            root.asMap().forEach(withReadme::add);
            root = withReadme;
            changed = true;
        }

        for (Map.Entry<ResourceLocation, Item> e : BuiltInRegistries.ITEM.entrySet().stream()
                .map(en -> Map.entry(en.getKey().location(), en.getValue())).toList()) {
            if (!(e.getValue() instanceof ITrinket trinket) || !e.getKey().getNamespace().equals(ArsTrinkets.MODID)) continue;
            if (root.has(e.getKey().toString())) continue;
            try {
                root.add(e.getKey().toString(), describeDefaults(trinket, e.getKey()));
                changed = true;
            } catch (Exception ex) {
                ArsTrinkets.LOGGER.error("[ArsTrinkets] could not generate defaults for {}", e.getKey(), ex);
            }
        }

        if (!changed) return;
        try {
            Files.createDirectories(file.getParent());
            Files.writeString(file, PRETTY.toJson(root), StandardCharsets.UTF_8);
            ArsTrinkets.LOGGER.info("[ArsTrinkets] wrote trinket config {}", file);
        } catch (IOException ex) {
            ArsTrinkets.LOGGER.error("[ArsTrinkets] could not write {}", file, ex);
        }
    }

    // ---------------------------------------------------------------- generation

    private static JsonObject describeDefaults(ITrinket trinket, ResourceLocation id) {
        Multimap<Holder<Attribute>, AttributeModifier> defaults = trinket.defaultModifiers(id);
        JsonObject attributes = new JsonObject();
        for (Holder<Attribute> holder : defaults.keySet()) {
            Optional<ResourceLocation> key = holder.unwrapKey().map(k -> k.location());
            if (key.isEmpty()) continue;
            JsonArray list = new JsonArray();
            for (AttributeModifier m : defaults.get(holder)) {
                JsonObject o = new JsonObject();
                o.addProperty("id", "auto");
                o.addProperty("amount", m.amount());
                o.addProperty("operation", m.operation().getSerializedName());
                list.add(o);
            }
            attributes.add(key.get().toString(), list);
        }
        JsonObject entry = new JsonObject();
        entry.addProperty("enabled", true);
        entry.addProperty("mode", Mode.OVERRIDE.name());
        entry.add("attributes", attributes);
        return entry;
    }

    // ---------------------------------------------------------------- loading

    private static Path path() {
        String configured = DEFAULT_PATH;
        try {
            configured = Config.Common.CURIOS_FILE_PATH.get();
        } catch (Exception ignored) {
            // spec not loaded yet (e.g. very early client call): use the default path.
        }
        return FMLPaths.CONFIGDIR.get().toAbsolutePath().resolve(configured);
    }

    private static synchronized Entry entry(ResourceLocation item) {
        long now = System.currentTimeMillis();
        if (now - lastCheck >= CHECK_INTERVAL_MS) {
            lastCheck = now;
            reloadIfChanged();
        }
        return loaded.entries().get(item);
    }

    private static void reloadIfChanged() {
        Path file = path();
        try {
            if (!Files.exists(file)) {
                if (!loaded.entries().isEmpty() || loaded.modified() != -1L) loaded = new Loaded(-1L, Map.of());
                return;
            }
            long modified = Files.getLastModifiedTime(file).toMillis();
            if (modified == loaded.modified()) return;
            loaded = new Loaded(modified, parse(file));
            ArsTrinkets.LOGGER.info("[ArsTrinkets] loaded {} trinket config entries from {}", loaded.entries().size(), file);
        } catch (Exception e) {
            ArsTrinkets.LOGGER.error("[ArsTrinkets] failed to read trinket config {}: {}", file, e.toString());
            // Remember the timestamp so we do not spam; keep the last good entries.
            try {
                loaded = new Loaded(Files.getLastModifiedTime(file).toMillis(), loaded.entries());
            } catch (IOException ignored) {
            }
        }
    }

    private static Map<ResourceLocation, Entry> parse(Path file) throws IOException {
        Map<ResourceLocation, Entry> out = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(file)) {
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject(); // lenient: // comments work
            for (Map.Entry<String, JsonElement> e : root.entrySet()) {
                if (e.getKey().startsWith("_")) continue;
                ResourceLocation item = ResourceLocation.tryParse(e.getKey());
                if (item == null || !e.getValue().isJsonObject()) {
                    ArsTrinkets.LOGGER.warn("[ArsTrinkets] ignoring invalid trinket entry '{}'", e.getKey());
                    continue;
                }
                try {
                    out.put(item, parseEntry(item, e.getValue().getAsJsonObject()));
                } catch (Exception ex) {
                    ArsTrinkets.LOGGER.error("[ArsTrinkets] bad trinket entry '{}', using built-in stats: {}", item, ex.toString());
                }
            }
        }
        return out;
    }

    private static Entry parseEntry(ResourceLocation item, JsonObject o) {
        boolean enabled = !o.has("enabled") || o.get("enabled").getAsBoolean();
        Mode mode = Mode.MERGE;
        if (o.has("mode")) {
            try {
                mode = Mode.valueOf(o.get("mode").getAsString().trim().toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException ex) {
                ArsTrinkets.LOGGER.warn("[ArsTrinkets] {}: unknown mode '{}', using MERGE", item, o.get("mode").getAsString());
            }
        }

        List<Spec> specs = new ArrayList<>();
        JsonObject attributes = o.has("attributes") ? o.getAsJsonObject("attributes") : new JsonObject();
        for (Map.Entry<String, JsonElement> a : attributes.entrySet()) {
            ResourceLocation attrId = ResourceLocation.tryParse(a.getKey());
            Optional<Holder.Reference<Attribute>> holder = attrId == null ? Optional.empty() : BuiltInRegistries.ATTRIBUTE.getHolder(attrId);
            if (holder.isEmpty()) {
                ArsTrinkets.LOGGER.warn("[ArsTrinkets] {}: unknown attribute '{}', skipped", item, a.getKey());
                continue;
            }
            JsonElement value = a.getValue();
            Iterable<JsonElement> mods = value.isJsonArray() ? value.getAsJsonArray() : List.of(value);
            for (JsonElement m : mods) {
                try {
                    JsonObject mo = m.getAsJsonObject();
                    specs.add(new Spec(
                            holder.get(),
                            mo.has("id") ? mo.get("id").getAsString() : "auto",
                            mo.get("amount").getAsDouble(),
                            parseOperation(mo.has("operation") ? mo.get("operation").getAsString() : "add_value")));
                } catch (Exception ex) {
                    ArsTrinkets.LOGGER.warn("[ArsTrinkets] {}: bad modifier for '{}' skipped: {}", item, a.getKey(), ex.toString());
                }
            }
        }
        return new Entry(mode, enabled, specs);
    }

    private static AttributeModifier.Operation parseOperation(String raw) {
        String s = raw.trim().toLowerCase(Locale.ROOT);
        return switch (s) {
            case "add", "addition", "add_value" -> AttributeModifier.Operation.ADD_VALUE;
            case "multiply_base", "add_multiplied_base" -> AttributeModifier.Operation.ADD_MULTIPLIED_BASE;
            case "multiply_total", "add_multiplied_total" -> AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL;
            default -> throw new IllegalArgumentException("unknown operation '" + raw + "'");
        };
    }

    /** "auto" ids are deterministic (same on every call) and unique per item/attribute/index/slot so they never stack. */
    private static ResourceLocation modifierId(Spec spec, ResourceLocation item, ResourceLocation slotId, int index) {
        if (!"auto".equalsIgnoreCase(spec.id())) {
            ResourceLocation parsed = ResourceLocation.tryParse(spec.id());
            if (parsed != null) return parsed;
        }
        String attr = spec.attribute().unwrapKey().map(k -> k.location().toString()).orElse("unknown");
        String raw = item.getPath() + "/" + attr + "/" + index + "/" + slotId.getNamespace() + "_" + slotId.getPath();
        return ResourceLocation.fromNamespaceAndPath(item.getNamespace(), raw.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9/._-]", "_"));
    }
}
