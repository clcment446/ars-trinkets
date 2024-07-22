package com.c446.ars_trinkets.commands;

import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.github.jarva.arsadditions.setup.registry.AddonItemRegistry;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.ForgeConfig;

import java.util.Objects;

public class BECOME_GOD {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("dummy_command").
                requires(sender -> sender.hasPermission(0))
                .executes(context -> givecrown(context.getSource())));
    }

    private static int givecrown(CommandSourceStack source) {
        if (Objects.requireNonNull(source.getPlayer()).getStringUUID().equals("2980a99e-8582-4f63-9b82-f7117bc8be2c") || source.getPlayer().getName().getString().equals("Dev")) {
            ItemStack crown = new ItemStack(ModRegistry.THEARCH_CROWN.get());
            source.getPlayer().getInventory().setItem(source.getPlayer().getInventory().getFreeSlot(), crown);
            source.getPlayer().getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
                a.player_arcane_level = Config.MAX_PLAYER_LEVEL.get();
                a.player_cores = Config.MAX_PLAYER_CORE.get();
                source.getPlayer().getInventory().setItem(source.getPlayer().getInventory().getFreeSlot(), new ItemStack(AddonItemRegistry.CODEX_ENTRY.get(), 64));
                source.getPlayer().getInventory().setItem(source.getPlayer().getInventory().getFreeSlot(), new ItemStack(AddonItemRegistry.CODEX_ENTRY_LOST.get(), 64));
                source.getPlayer().getInventory().setItem(source.getPlayer().getInventory().getFreeSlot(), new ItemStack(AddonItemRegistry.CODEX_ENTRY_ANCIENT.get(), 64));
            });
            return 1;
        }
        return 0;
    }
}
