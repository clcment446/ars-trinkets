package com.c446.ars_trinkets;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import static com.ibm.icu.lang.UCharacter.GraphemeClusterBreak.T;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ArsTrinkets.MOD_ID)
public class Config {


    public static ForgeConfigSpec.BooleanValue IS_LEVELING_ENABLED;
    public static ForgeConfigSpec.BooleanValue PLAYER_GAINS_MANA_LEVELING;
    public static ForgeConfigSpec.BooleanValue PLAYER_GAINS_MANA_REGEN_LEVELING;
    public static ForgeConfigSpec.BooleanValue PLAYER_GAINS_DAMAGE_LEVELING;
    public static ForgeConfigSpec.BooleanValue PLAYER_GAINS_DEFENSE_LEVELING;
    public static ForgeConfigSpec.BooleanValue ARE_OMEGA_GLYPHS_ENABLED;
    public static ForgeConfigSpec.IntValue MAX_PLAYER_LEVEL;
    public static ForgeConfigSpec.IntValue MAX_PLAYER_CORE;
    public static ForgeConfigSpec.IntValue AURA_BASE_DURATION;
    public static ForgeConfigSpec.IntValue AURA_BASE_REACH;
    public static ForgeConfigSpec.IntValue AURA_BASE_DELAY;
    public static ForgeConfigSpec.IntValue AURA_AUG_ACCELERATE;
    public static ForgeConfigSpec.IntValue AURA_AUG_EXTEND_TIME;
    public static ForgeConfigSpec.IntValue AURA_AUG_AOE;

    public static ForgeConfigSpec.IntValue EXP_TO_LEVEL_1;
    public static ForgeConfigSpec.IntValue EXP_TO_LEVEL_2;
    public static ForgeConfigSpec.IntValue EXP_TO_LEVEL_3;
    public static ForgeConfigSpec.IntValue EXP_TO_LEVEL_4;
    public static ForgeConfigSpec.IntValue EXP_TO_LEVEL_5;
    public static ForgeConfigSpec.IntValue EXP_TO_LEVEL_6;
    public static ForgeConfigSpec.IntValue EXP_TO_LEVEL_7;
    public static ForgeConfigSpec.IntValue EXP_TO_LEVEL_8;
    public static ForgeConfigSpec.IntValue EXP_TO_LEVEL_9;

    public static ForgeConfigSpec.IntValue MANA_REGEN_BONUS_LVL_0;
    public static ForgeConfigSpec.IntValue MANA_REGEN_BONUS_LVL_1;
    public static ForgeConfigSpec.IntValue MANA_REGEN_BONUS_LVL_2;
    public static ForgeConfigSpec.IntValue MANA_REGEN_BONUS_LVL_3;
    public static ForgeConfigSpec.IntValue MANA_REGEN_BONUS_LVL_4;
    public static ForgeConfigSpec.IntValue MANA_REGEN_BONUS_LVL_5;
    public static ForgeConfigSpec.IntValue MANA_REGEN_BONUS_LVL_6;
    public static ForgeConfigSpec.IntValue MANA_REGEN_BONUS_LVL_7;
    public static ForgeConfigSpec.IntValue MANA_REGEN_BONUS_LVL_8;
    public static ForgeConfigSpec.IntValue MANA_REGEN_BONUS_LVL_9;
    public static ForgeConfigSpec.IntValue MANA_BONUS_FLAT_LEVEL_0;
    public static ForgeConfigSpec.IntValue MANA_BONUS_FLAT_LEVEL_1;
    public static ForgeConfigSpec.IntValue MANA_BONUS_FLAT_LEVEL_2;
    public static ForgeConfigSpec.IntValue MANA_BONUS_FLAT_LEVEL_3;
    public static ForgeConfigSpec.IntValue MANA_BONUS_FLAT_LEVEL_4;
    public static ForgeConfigSpec.IntValue MANA_BONUS_FLAT_LEVEL_5;
    public static ForgeConfigSpec.IntValue MANA_BONUS_FLAT_LEVEL_6;
    public static ForgeConfigSpec.IntValue MANA_BONUS_FLAT_LEVEL_7;
    public static ForgeConfigSpec.IntValue MANA_BONUS_FLAT_LEVEL_8;
    public static ForgeConfigSpec.IntValue MANA_BONUS_FLAT_LEVEL_9;
    public static ForgeConfigSpec.IntValue SPELL_DMG_0;
    public static ForgeConfigSpec.IntValue SPELL_DMG_1;
    public static ForgeConfigSpec.IntValue SPELL_DMG_2;
    public static ForgeConfigSpec.IntValue SPELL_DMG_3;
    public static ForgeConfigSpec.IntValue SPELL_DMG_4;
    public static ForgeConfigSpec.IntValue SPELL_DMG_5;
    public static ForgeConfigSpec.IntValue SPELL_DMG_6;
    public static ForgeConfigSpec.IntValue SPELL_DMG_7;
    public static ForgeConfigSpec.IntValue SPELL_DMG_8;
    public static ForgeConfigSpec.IntValue SPELL_DMG_9;

    public static ForgeConfigSpec.IntValue DEFENSE_0;
    public static ForgeConfigSpec.IntValue DEFENSE_1;
    public static ForgeConfigSpec.IntValue DEFENSE_2;
    public static ForgeConfigSpec.IntValue DEFENSE_3;
    public static ForgeConfigSpec.IntValue DEFENSE_4;
    public static ForgeConfigSpec.IntValue DEFENSE_5;
    public static ForgeConfigSpec.IntValue DEFENSE_6;
    public static ForgeConfigSpec.IntValue DEFENSE_7;
    public static ForgeConfigSpec.IntValue DEFENSE_8;
    public static ForgeConfigSpec.IntValue DEFENSE_9;
    public static ForgeConfigSpec.IntValue PHYS_DAMAGE_0;
    public static ForgeConfigSpec.IntValue PHYS_DAMAGE_1;
    public static ForgeConfigSpec.IntValue PHYS_DAMAGE_2;
    public static ForgeConfigSpec.IntValue PHYS_DAMAGE_3;
    public static ForgeConfigSpec.IntValue PHYS_DAMAGE_4;
    public static ForgeConfigSpec.IntValue PHYS_DAMAGE_5;
    public static ForgeConfigSpec.IntValue PHYS_DAMAGE_6;
    public static ForgeConfigSpec.IntValue PHYS_DAMAGE_7;
    public static ForgeConfigSpec.IntValue PHYS_DAMAGE_8;
    public static ForgeConfigSpec.IntValue PHYS_DAMAGE_9;
    public static ForgeConfigSpec.IntValue CORE_FLAT_BONUS;
    public static ForgeConfigSpec.IntValue CORE_REGEN_BONUS;
    public static ForgeConfigSpec SERVER_CONFIG;

    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

        SERVER_BUILDER.comment("SERVER CONFIGURATION");
        IS_LEVELING_ENABLED = SERVER_BUILDER.comment("can players level up").define("LevelingSystemEnabled", true);
        ARE_OMEGA_GLYPHS_ENABLED = SERVER_BUILDER.comment("(/! DEPRECATED /!) are the ars omega forms/propagators enabled (/!DEPRECATED /!)").define("ArsOmegaFormsEnabled", false);
        MAX_PLAYER_LEVEL = SERVER_BUILDER.comment("the maximum level that a player can get to.").defineInRange("LevelingMaxLevel", 6, 1, 9);
        MAX_PLAYER_CORE = SERVER_BUILDER.comment("the maximum number of cores that a player can have.").defineInRange("LevelingMaxCore", 9, 1, 777);
        AURA_AUG_AOE = SERVER_BUILDER.comment("the maximum number of cores that a player can have.").defineInRange("AURA_AUG_AOE", 5, 0, 1000);
        AURA_AUG_EXTEND_TIME = SERVER_BUILDER.comment("the maximum number of cores that a player can have.").defineInRange("AURA_AUG_TIME_PLUS", 5, 0, 100000);
        AURA_AUG_ACCELERATE = SERVER_BUILDER.comment("The delay reduction that each \"Accelerate\" will give.").defineInRange("AURA_AUG_ACCELERATE", 4, 0, 4);
        AURA_BASE_DURATION = SERVER_BUILDER.comment("The base Aura duration.").defineInRange("AURA_BASE_REACH", 5, 0, 100000);
        AURA_BASE_REACH = SERVER_BUILDER.comment("The base Aura Reach.").defineInRange("AURA_BASE_TIME", 20, 1, 100000);
        AURA_BASE_DELAY = SERVER_BUILDER.comment("The base Aura Delay.").defineInRange("AURA_BASE_DELAY", 20, 1, 100000);
        EXP_TO_LEVEL_1 = SERVER_BUILDER.comment("Souls to get to level 1").defineInRange("SOUL_QTY_LEVEL_1", 1000, 1, 1000000000);
        EXP_TO_LEVEL_2 = SERVER_BUILDER.comment("Souls to get to level 2").defineInRange("SOUL_QTY_LEVEL_2", 5000, 1, 1000000000);
        EXP_TO_LEVEL_3 = SERVER_BUILDER.comment("Souls to get to level 3").defineInRange("SOUL_QTY_LEVEL_3", 10000, 1, 1000000000);
        EXP_TO_LEVEL_4 = SERVER_BUILDER.comment("Souls to get to level 4").defineInRange("SOUL_QTY_LEVEL_4", 50000, 1, 1000000000);
        EXP_TO_LEVEL_5 = SERVER_BUILDER.comment("Souls to get to level 5").defineInRange("SOUL_QTY_LEVEL_5", 250000, 1, 1000000000);
        EXP_TO_LEVEL_6 = SERVER_BUILDER.comment("Souls to get to level 6").defineInRange("SOUL_QTY_LEVEL_6", 1000000, 1, 1000000000);
        EXP_TO_LEVEL_7 = SERVER_BUILDER.comment("Souls to get to level 7").defineInRange("SOUL_QTY_LEVEL_7", 2500000, 1, 1000000000);
        EXP_TO_LEVEL_8 = SERVER_BUILDER.comment("Souls to get to level 8").defineInRange("SOUL_QTY_LEVEL_8", 10000000, 1, 1000000000);
        EXP_TO_LEVEL_9 = SERVER_BUILDER.comment("Souls to get to level 9").defineInRange("SOUL_QTY_LEVEL_9", 25000000, 1, 1000000000);

        MANA_BONUS_FLAT_LEVEL_0 = SERVER_BUILDER.comment("The mana bonus that being to level 0 brings.").defineInRange("MANA_FLAT_BONUS_0", 0, 1, 1000000000);
        MANA_BONUS_FLAT_LEVEL_1 = SERVER_BUILDER.comment("The mana bonus that being to level 1 brings.").defineInRange("MANA_FLAT_BONUS_1", 1000, 1, 1000000000);
        MANA_BONUS_FLAT_LEVEL_2 = SERVER_BUILDER.comment("The mana bonus that being to level 2 brings.").defineInRange("MANA_FLAT_BONUS_2", 2500, 1, 1000000000);
        MANA_BONUS_FLAT_LEVEL_3 = SERVER_BUILDER.comment("The mana bonus that being to level 3 brings.").defineInRange("MANA_FLAT_BONUS_3", 5000, 1, 1000000000);
        MANA_BONUS_FLAT_LEVEL_4 = SERVER_BUILDER.comment("The mana bonus that being to level 4 brings.").defineInRange("MANA_FLAT_BONUS_4", 12500, 1, 1000000000);
        MANA_BONUS_FLAT_LEVEL_5 = SERVER_BUILDER.comment("The mana bonus that being to level 5 brings.").defineInRange("MANA_FLAT_BONUS_5", 18750, 1, 1000000000);
        MANA_BONUS_FLAT_LEVEL_6 = SERVER_BUILDER.comment("The mana bonus that being to level 6 brings.").defineInRange("MANA_FLAT_BONUS_6", 25000, 1, 1000000000);
        MANA_BONUS_FLAT_LEVEL_7 = SERVER_BUILDER.comment("The mana bonus that being to level 7 brings.").defineInRange("MANA_FLAT_BONUS_7", 50000, 1, 1000000000);
        MANA_BONUS_FLAT_LEVEL_8 = SERVER_BUILDER.comment("The mana bonus that being to level 8 brings.").defineInRange("MANA_FLAT_BONUS_8", 125000, 1, 1000000000);
        MANA_BONUS_FLAT_LEVEL_9 = SERVER_BUILDER.comment("The mana bonus that being to level 9 brings.").defineInRange("MANA_FLAT_BONUS_9", 250000, 1, 1000000000);

        MANA_REGEN_BONUS_LVL_0 = SERVER_BUILDER.comment("The mana regen bonus that being to level 0 brings.").defineInRange("MANA_REGEN_BONUS_0", 0, 1, 1000000000);
        MANA_REGEN_BONUS_LVL_1 = SERVER_BUILDER.comment("The mana regen bonus that being to level 1 brings.").defineInRange("MANA_REGEN_BONUS_1", 20, 1, 1000000000);
        MANA_REGEN_BONUS_LVL_2 = SERVER_BUILDER.comment("The mana regen bonus that being to level 2 brings.").defineInRange("MANA_REGEN_BONUS_2", 100, 1, 1000000000);
        MANA_REGEN_BONUS_LVL_3 = SERVER_BUILDER.comment("The mana regen bonus that being to level 3 brings.").defineInRange("MANA_REGEN_BONUS_3", 200, 1, 1000000000);
        MANA_REGEN_BONUS_LVL_4 = SERVER_BUILDER.comment("The mana regen bonus that being to level 4 brings.").defineInRange("MANA_REGEN_BONUS_4", 500, 1, 1000000000);
        MANA_REGEN_BONUS_LVL_5 = SERVER_BUILDER.comment("The mana regen bonus that being to level 5 brings.").defineInRange("MANA_REGEN_BONUS_5", 750, 1, 1000000000);
        MANA_REGEN_BONUS_LVL_6 = SERVER_BUILDER.comment("The mana regen bonus that being to level 6 brings.").defineInRange("MANA_REGEN_BONUS_6", 1000, 1, 1000000000);
        MANA_REGEN_BONUS_LVL_7 = SERVER_BUILDER.comment("The mana regen bonus that being to level 7 brings.").defineInRange("MANA_REGEN_BONUS_7", 2000, 1, 1000000000);
        MANA_REGEN_BONUS_LVL_8 = SERVER_BUILDER.comment("The mana regen bonus that being to level 8 brings.").defineInRange("MANA_REGEN_BONUS_8", 5000, 1, 1000000000);
        MANA_REGEN_BONUS_LVL_9 = SERVER_BUILDER.comment("The mana regen bonus that being to level 9 brings.").defineInRange("MANA_REGEN_BONUS_9", 7500, 1, 1000000000);

        DEFENSE_0 = SERVER_BUILDER.comment("The total % of damage reduction that being at level 0 brings.").defineInRange("DEFENSE_PCT_0", 0, 0, 100);
        DEFENSE_1 = SERVER_BUILDER.comment("The total % of damage reduction that being at level 0 brings.").defineInRange("DEFENSE_PCT_1", 5, 0, 100);
        DEFENSE_2 = SERVER_BUILDER.comment("The total % of damage reduction that being at level 0 brings.").defineInRange("DEFENSE_PCT_2", 10, 0, 100);
        DEFENSE_3 = SERVER_BUILDER.comment("The total % of damage reduction that being at level 0 brings.").defineInRange("DEFENSE_PCT_3", 15, 0, 100);
        DEFENSE_4 = SERVER_BUILDER.comment("The total % of damage reduction that being at level 0 brings.").defineInRange("DEFENSE_PCT_4", 25, 0, 100);
        DEFENSE_5 = SERVER_BUILDER.comment("The total % of damage reduction that being at level 0 brings.").defineInRange("DEFENSE_PCT_5", 35, 0, 100);
        DEFENSE_6 = SERVER_BUILDER.comment("The total % of damage reduction that being at level 0 brings.").defineInRange("DEFENSE_PCT_6", 40, 0, 100);
        DEFENSE_7 = SERVER_BUILDER.comment("The total % of damage reduction that being at level 0 brings.").defineInRange("DEFENSE_PCT_7", 50, 0, 100);
        DEFENSE_8 = SERVER_BUILDER.comment("The total % of damage reduction that being at level 0 brings.").defineInRange("DEFENSE_PCT_8", 60, 0, 100);
        DEFENSE_9 = SERVER_BUILDER.comment("The total % of damage reduction that being at level 0 brings.").defineInRange("DEFENSE_PCT_9", 75, 0, 100);

        SPELL_DMG_0 = SERVER_BUILDER.comment("The total % of bonus spell damage that being at level 0 brings.").defineInRange("SPELL_DMG_0", 0, 0, 10000000);
        SPELL_DMG_1 = SERVER_BUILDER.comment("The total % of bonus spell damage that being at level 1 brings.").defineInRange("SPELL_DMG_1", 0, 0, 10000000);
        SPELL_DMG_2 = SERVER_BUILDER.comment("The total % of bonus spell damage that being at level 2 brings.").defineInRange("SPELL_DMG_2",5 , 0, 10000000);
        SPELL_DMG_3 = SERVER_BUILDER.comment("The total % of bonus spell damage that being at level 3 brings.").defineInRange("SPELL_DMG_3",10 , 0, 10000000);
        SPELL_DMG_4 = SERVER_BUILDER.comment("The total % of bonus spell damage that being at level 4 brings.").defineInRange("SPELL_DMG_4",15 , 0, 10000000);
        SPELL_DMG_5 = SERVER_BUILDER.comment("The total % of bonus spell damage that being at level 5 brings.").defineInRange("SPELL_DMG_5",20 , 0, 10000000);
        SPELL_DMG_6 = SERVER_BUILDER.comment("The total % of bonus spell damage that being at level 6 brings.").defineInRange("SPELL_DMG_6",25 , 0, 10000000);
        SPELL_DMG_7 = SERVER_BUILDER.comment("The total % of bonus spell damage that being at level 7 brings.").defineInRange("SPELL_DMG_7",35 , 0, 10000000);
        SPELL_DMG_8 = SERVER_BUILDER.comment("The total % of bonus spell damage that being at level 8 brings.").defineInRange("SPELL_DMG_8", 50, 0, 10000000);
        SPELL_DMG_9 = SERVER_BUILDER.comment("The total % of bonus spell damage that being at level 9 brings.").defineInRange("SPELL_DMG_9", 70, 0, 10000000);

        PHYS_DAMAGE_0 = SERVER_BUILDER.comment("The total % of bonus physical damage that being at level 0 brings.").defineInRange("DEFENSE_PCT_0", 0, 0, 10000000);
        PHYS_DAMAGE_1 = SERVER_BUILDER.comment("The total % of bonus physical damage that being at level 1 brings.").defineInRange("DEFENSE_PCT_1",0 , 0, 10000000);
        PHYS_DAMAGE_2 = SERVER_BUILDER.comment("The total % of bonus physical damage that being at level 2 brings.").defineInRange("DEFENSE_PCT_2",0 , 0, 10000000);
        PHYS_DAMAGE_3 = SERVER_BUILDER.comment("The total % of bonus physical damage that being at level 3 brings.").defineInRange("DEFENSE_PCT_3",5 , 0, 10000000);
        PHYS_DAMAGE_4 = SERVER_BUILDER.comment("The total % of bonus physical damage that being at level 4 brings.").defineInRange("DEFENSE_PCT_4",10 , 0, 10000000);
        PHYS_DAMAGE_5 = SERVER_BUILDER.comment("The total % of bonus physical damage that being at level 5 brings.").defineInRange("DEFENSE_PCT_5",15 , 0, 10000000);
        PHYS_DAMAGE_6 = SERVER_BUILDER.comment("The total % of bonus physical damage that being at level 6 brings.").defineInRange("DEFENSE_PCT_6", 20, 0, 10000000);
        PHYS_DAMAGE_7 = SERVER_BUILDER.comment("The total % of bonus physical damage that being at level 7 brings.").defineInRange("DEFENSE_PCT_7", 30, 0, 10000000);
        PHYS_DAMAGE_8 = SERVER_BUILDER.comment("The total % of bonus physical damage that being at level 8 brings.").defineInRange("DEFENSE_PCT_8", 40, 0, 10000000);
        PHYS_DAMAGE_9 = SERVER_BUILDER.comment("The total % of bonus physical damage that being at level 9 brings.").defineInRange("DEFENSE_PCT_9", 50, 0, 10000000);

        CORE_FLAT_BONUS = SERVER_BUILDER.comment("How much each core increases your flat mana stat.").defineInRange("CORE_FLAT", 1, 0, 1000000000);
        CORE_REGEN_BONUS = SERVER_BUILDER.comment("How much each core increases your mana regen stat.").defineInRange("CORE_REGEN", 1, 0, 1000000000);


//        MANA_REGEN_BONUS_LVL_1 = SERVER_BUILDER.comment("The base Aura Delay.").defineInRange("AURA_BASE_DELAY", );

        SERVER_CONFIG = SERVER_BUILDER.build();
    }
}
