package com.c446.ars_trinkets.events;

import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.capabilities.LevelingCapability;
import com.c446.ars_trinkets.registry.CapabilityRegistry;
import com.c446.ars_trinkets.registry.ItemRegistry;
import com.c446.ars_trinkets.registry.ModGameRules;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

import java.util.UUID;

/**
 * Mobs killed by a player drop an essence whose tier is the mob's level, plus extra copies per core.
 * A mob hit by Devour Soul and then killed by the same player yields far more (and always drops).
 */
@EventBusSubscriber
public final class EssenceDropEvents {
    private static final String TAG = "ars_trinkets_devoured";
    private static final long MARK_DURATION_TICKS = 20L * 60L;

    private EssenceDropEvents() {
    }

    /** Called by Devour Soul: remember who devoured this mob, how strongly, and until when. */
    public static void markDevoured(LivingEntity target, ServerPlayer caster, double amplify) {
        CompoundTag tag = new CompoundTag();
        tag.putUUID("by", caster.getUUID());
        tag.putDouble("amp", amplify);
        tag.putLong("until", target.level().getGameTime() + MARK_DURATION_TICKS);
        target.getPersistentData().put(TAG, tag);
    }

    /** Yield multiplier from Devour Soul for this killer, or 1 when not marked / expired / someone else. */
    static double devourMultiplier(LivingEntity mob, UUID killer) {
        CompoundTag data = mob.getPersistentData();
        if (!data.contains(TAG)) return 1.0;
        CompoundTag tag = data.getCompound(TAG);
        if (!tag.hasUUID("by") || !tag.getUUID("by").equals(killer)) return 1.0;
        if (mob.level().getGameTime() > tag.getLong("until")) return 1.0;
        return 1.0 + (Config.Common.DEVOUR_SOUL_ESSENCE_YIELD_MULTIPLIER.get() - 1.0) * Math.max(1.0, tag.getDouble("amp"));
    }

    /** Pure helper: stochastic rounding so 0.25 extra per core really averages out. */
    static int dropCount(int cores, double extraPerCore, double devourMultiplier, double roll) {
        double base = 1.0 + Math.max(0, cores) * Math.max(0.0, extraPerCore);
        double scaled = base * Math.max(1.0, devourMultiplier);
        int whole = (int) Math.floor(scaled);
        return whole + (roll < scaled - whole ? 1 : 0);
    }

    @SubscribeEvent
    public static void onDrops(LivingDropsEvent event) {
        LivingEntity mob = event.getEntity();
        if (mob.level().isClientSide() || mob instanceof Player || !Config.Common.ESSENCE_DROPS_ENABLED.get()
                || !mob.level().getGameRules().getBoolean(ModGameRules.ESSENCE_DROPS)) return;
        if (Config.Common.ESSENCE_DROPS_HOSTILE_ONLY.get() && !(mob instanceof Enemy)) return;

        Entity killer = event.getSource().getEntity();
        if (!(killer instanceof ServerPlayer player) || killer == mob) return;

        int killerLevel = player.hasData(CapabilityRegistry.LEVEL_CAP) ? LevelingCapability.get(player).level : 0;
        double devour = devourMultiplier(mob, player.getUUID());
        if (devour <= 1.0 && mob.getRandom().nextDouble() >= Config.Common.ESSENCE_DROP_CHANCE.get()) return;

        int level = 1;
        int cores = 0;
        if (mob.hasData(CapabilityRegistry.LEVEL_CAP)) {
            LevelingCapability cap = mob.getData(CapabilityRegistry.LEVEL_CAP);
            level = Math.max(1, cap.level);
            cores = cap.cores;
        }

        // An essence only works while player level <= tier, so anything weaker is dead weight.
        if (Config.Common.ESSENCE_DROP_ONLY_USABLE.get() && killerLevel > Math.clamp(level, 1, 10)) return;

        int count = dropCount(cores, Config.Common.ESSENCE_DROP_EXTRA_PER_CORE.get(), devour, mob.getRandom().nextDouble());
        count = Math.min(count, Config.Common.ESSENCE_DROP_MAX_COUNT.get());
        if (count <= 0) return;

        ItemStack stack = new ItemStack(ItemRegistry.essenceForLevel(level), count);
        event.getDrops().add(new ItemEntity(mob.level(), mob.getX(), mob.getY(), mob.getZ(), stack));
    }
}
