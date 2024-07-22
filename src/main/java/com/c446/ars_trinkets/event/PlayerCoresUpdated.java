package com.c446.ars_trinkets.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

public class PlayerCoresUpdated extends Event {
    int playerCores;
    Player player;
    PlayerCoresUpdated(int playerCores, Player player) {
        this.playerCores = playerCores;
        this.player = player;
    }
    public static class Post extends PlayerCoresUpdated{
        public Post(int playerCores, Player player){super(playerCores, player);}

    }
}
