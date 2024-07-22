package com.c446.ars_trinkets.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;

public class PlayerLevelUpdated extends Event {
    int player_level;
    Player player;
    PlayerLevelUpdated(int playerLevel, Player player) {
        this.player_level = playerLevel;
        this.player = player;
    }
    public static class Post extends PlayerLevelUpdated{
        public Post(int player_level, Player player){super(player_level, player);}

    }
}
