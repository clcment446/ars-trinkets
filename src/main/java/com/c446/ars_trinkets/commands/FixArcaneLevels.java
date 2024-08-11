package com.c446.ars_trinkets.commands;

import com.c446.ars_trinkets.capabilities.ArcaneLevels;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import java.util.Objects;

public class FixArcaneLevels {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("fix-arcane-levels").
                requires(sender -> sender.hasPermission(0))
                .executes(context -> setProgression(context.getSource())));
    }

    private static int setProgression(CommandSourceStack source) {
            source.getPlayer().getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(ArcaneLevels::fixArcaneLevels);
            return 1;
    }
}