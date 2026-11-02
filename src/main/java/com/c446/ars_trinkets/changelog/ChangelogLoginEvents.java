package com.c446.ars_trinkets.changelog;

import com.c446.ars_trinkets.ArsTrinkets;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/** Shows the current version's changelog once per player, the first time they log in after an update. */
@EventBusSubscriber
public final class ChangelogLoginEvents {
    private static final String SEEN_KEY = "ars_trinkets_changelog_seen";

    private ChangelogLoginEvents() {
    }

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        String version = ModList.get().getModContainerById(ArsTrinkets.MODID)
                .map(c -> c.getModInfo().getVersion().toString()).orElse(null);
        Changelog.Release release = version == null ? null : Changelog.forVersion(version);
        if (release == null) return;

        // The persisted tag survives death/respawn.
        CompoundTag persisted = player.getPersistentData().getCompound(Player.PERSISTED_NBT_TAG);
        if (release.version().equals(persisted.getString(SEEN_KEY))) return;
        persisted.putString(SEEN_KEY, release.version());
        player.getPersistentData().put(Player.PERSISTED_NBT_TAG, persisted);

        player.sendSystemMessage(Component.translatable("message.ars_trinkets.changelog.header", release.version())
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
        for (int i = 1; i <= release.lines(); i++) {
            player.sendSystemMessage(Component.literal("- ").withStyle(ChatFormatting.GRAY)
                    .append(Component.translatable(release.lineKey(i)).withStyle(ChatFormatting.WHITE)));
        }
        player.sendSystemMessage(Component.translatable("message.ars_trinkets.changelog.footer").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }
}
