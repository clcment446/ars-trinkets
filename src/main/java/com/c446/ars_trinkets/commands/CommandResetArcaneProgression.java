package com.c446.ars_trinkets.commands;

import com.c446.ars_trinkets.capabilities.ArcaneLevels;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.google.common.collect.ImmutableList;
import com.hollingsworth.arsnouveau.common.network.Networking;
import com.hollingsworth.arsnouveau.common.network.PacketTogglePathing;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

import java.util.Objects;

public class CommandResetArcaneProgression {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("reset-arcane-levels").
                requires(sender -> sender.hasPermission(2))
                .executes(context -> resetsProgression(context.getSource())));
    }

    private static int resetsProgression(CommandSourceStack source) {
        if (Objects.requireNonNull(source.getPlayer()).getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).isPresent()) {
            Objects.requireNonNull(source.getPlayer()).getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(ArcaneLevels::RESET_PLAYER);
            return 1;
        }
        return 0;
    }
}