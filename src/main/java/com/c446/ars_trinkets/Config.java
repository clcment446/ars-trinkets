package com.c446.ars_trinkets;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ArsTrinkets.MOD_ID)
public class Config {


    public static ForgeConfigSpec.BooleanValue IS_LEVELING_ENABLED;
    public static ForgeConfigSpec.BooleanValue ARE_OMEGA_GLYPHS_ENABLED;

    public static ForgeConfigSpec SERVER_CONFIG;
    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

        SERVER_BUILDER.comment("SERVER CONFIGURATION");
        IS_LEVELING_ENABLED = SERVER_BUILDER.comment("can players level up").define("LevelingSystemEnabled", false);
        ARE_OMEGA_GLYPHS_ENABLED = SERVER_BUILDER.comment("are the ars omega forms/propagators enabled").define("ArsOmegaFormsEnabled", false);
        SERVER_CONFIG = SERVER_BUILDER.build();
    }

//    @SubscribeEvent
//    public static void onLoad(final ModConfigEvent.Loading configEvent) {
//    }
//
//    @SubscribeEvent
//    public static void onReload(final ModConfigEvent.Reloading configEvent) {
//    }
}
