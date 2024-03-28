package com.c446.ars_trinkets;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ArsTrinkets.MOD_ID)
public class Config {


    public static ForgeConfigSpec.BooleanValue IS_LEVELING_ENABLED;
    public static ForgeConfigSpec.BooleanValue ARE_OMEGA_GLYPHS_ENABLED;
    public static ForgeConfigSpec.IntValue MAX_PLAYER_LEVEL;
    public static ForgeConfigSpec.IntValue MAX_PLAYER_CORE;

    public static ForgeConfigSpec SERVER_CONFIG;
    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

        SERVER_BUILDER.comment("SERVER CONFIGURATION");
        IS_LEVELING_ENABLED = SERVER_BUILDER.comment("can players level up").define("LevelingSystemEnabled", false);
        ARE_OMEGA_GLYPHS_ENABLED = SERVER_BUILDER.comment("(/! DEPRECATED /!) are the ars omega forms/propagators enabled (/!DEPRECATED /!)").define("ArsOmegaFormsEnabled", false);
        MAX_PLAYER_LEVEL = SERVER_BUILDER.comment("the maximum level that a player can get to.").defineInRange("LevelingMaxLevel",8,1,9);
        MAX_PLAYER_CORE = SERVER_BUILDER.comment("the maximum number of cores that a player can have.").defineInRange("LevelingMaxCore",9,1,777);

        SERVER_CONFIG = SERVER_BUILDER.build();
    }
}
