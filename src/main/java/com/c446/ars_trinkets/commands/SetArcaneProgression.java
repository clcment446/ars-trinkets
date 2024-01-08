package com.c446.ars_trinkets.commands;

import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import java.util.Objects;

public class SetArcaneProgression {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("set-arcane-levels").
                requires(sender -> sender.hasPermission(2))
                        .then(Commands.argument("level", IntegerArgumentType.integer(0,7)))
                .executes(context -> setProgression(context.getSource(), IntegerArgumentType.getInteger(context, "level"))));
    }

    private static int setProgression(CommandSourceStack source, int level) {
            Objects.requireNonNull(source.getPlayer()).getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a->a.setPlayerArcaneLevel(level));
            return 1;
    }
}