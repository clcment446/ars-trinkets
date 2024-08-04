package com.c446.ars_trinkets;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ArsTrinkets.MOD_ID)
public class Config {


    public static ForgeConfigSpec.BooleanValue IS_LEVELING_ENABLED;
    public static ForgeConfigSpec.BooleanValue PLAYER_GAINS_MANA_LEVELING;
    public static ForgeConfigSpec.BooleanValue PLAYER_GAINS_MANA_REGEN_LEVELING;
    public static ForgeConfigSpec.BooleanValue PLAYER_GAINS_DAMAGE_LEVELING;
    public static ForgeConfigSpec.BooleanValue PLAYER_GAINS_DEFENSE_LEVELING;
    public static ForgeConfigSpec.BooleanValue ARE_OMEGA_GLYPHS_ENABLED;
    public static ForgeConfigSpec.IntValue WITHER_SHIELD_TOUGHNESS;
    public static ForgeConfigSpec.IntValue WITHER_SHIELD_ARMOR;
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
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec SERVER_CONFIG;

//    // ESSENCE_LOTUS_3
//    public static ForgeConfigSpec.IntValue essence_lotus_3_mana_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_3_regen_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_3_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_3_damage_total_boost = null;
//
//    // ESSENCE_LOTUS_4
//    public static ForgeConfigSpec.IntValue essence_lotus_4_mana_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_4_regen_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_4_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_4_damage_total_boost = null;
//
//    // ESSENCE_LOTUS_5
//    public static ForgeConfigSpec.IntValue essence_lotus_5_mana_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_5_regen_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_5_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_5_damage_total_boost = null;
//
//    // ESSENCE_LOTUS_6
//    public static ForgeConfigSpec.IntValue essence_lotus_6_mana_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_6_regen_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_6_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_6_damage_total_boost = null;
//
//    // ESSENCE_LOTUS_7
//    public static ForgeConfigSpec.IntValue essence_lotus_7_mana_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_7_regen_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_7_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_7_damage_total_boost = null;
//
//    // ESSENCE_LOTUS_8
//    public static ForgeConfigSpec.IntValue essence_lotus_8_mana_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_8_regen_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_8_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_8_damage_total_boost = null;
//
//    // ESSENCE_LOTUS_9
//    public static ForgeConfigSpec.IntValue essence_lotus_9_mana_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_9_regen_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_9_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_9_damage_total_boost = null;
//
//    // ESSENCE_LOTUS_10
//    public static ForgeConfigSpec.IntValue essence_lotus_10_mana_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_10_regen_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_10_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue essence_lotus_10_damage_total_boost = null;
//
//    // MANA_RING_3
//    public static ForgeConfigSpec.IntValue mana_ring_3_mana_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_3_regen_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_3_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_3_damage_total_boost = null;
//
//    // MANA_RING_4
//    public static ForgeConfigSpec.IntValue mana_ring_4_mana_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_4_regen_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_4_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_4_damage_total_boost = null;
//
//    // MANA_RING_5
//    public static ForgeConfigSpec.IntValue mana_ring_5_mana_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_5_regen_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_5_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_5_damage_total_boost = null;
//
//    // MANA_RING_6
//    public static ForgeConfigSpec.IntValue mana_ring_6_mana_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_6_regen_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_6_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_6_damage_total_boost = null;
//
//    // MANA_RING_7
//    public static ForgeConfigSpec.IntValue mana_ring_7_mana_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_7_regen_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_7_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_7_damage_total_boost = null;
//
//    // MANA_RING_8
//    public static ForgeConfigSpec.IntValue mana_ring_8_mana_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_8_regen_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_8_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_8_damage_total_boost = null;
//
//    // MANA_RING_9
//    public static ForgeConfigSpec.IntValue mana_ring_9_mana_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_9_regen_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_9_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_9_damage_total_boost = null;
//
//    // MANA_RING_10
//    public static ForgeConfigSpec.IntValue mana_ring_10_mana_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_10_regen_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_10_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue mana_ring_10_damage_total_boost = null;
//
//    // MONOCLE_1
//    public static ForgeConfigSpec.IntValue monocle_1_mana_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_1_regen_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_1_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_1_damage_total_boost = null;
//
//    // MONOCLE_2
//    public static ForgeConfigSpec.IntValue monocle_2_mana_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_2_regen_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_2_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_2_damage_total_boost = null;
//
//    // MONOCLE_3
//    public static ForgeConfigSpec.IntValue monocle_3_mana_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_3_regen_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_3_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_3_damage_total_boost = null;
//
//    // MONOCLE_4
//    public static ForgeConfigSpec.IntValue monocle_4_mana_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_4_regen_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_4_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_4_damage_total_boost = null;
//
//    // MONOCLE_5
//    public static ForgeConfigSpec.IntValue monocle_5_mana_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_5_regen_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_5_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_5_damage_total_boost = null;
//
//    // MONOCLE_6
//    public static ForgeConfigSpec.IntValue monocle_6_mana_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_6_regen_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_6_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_6_damage_total_boost = null;
//
//    // MONOCLE_7
//    public static ForgeConfigSpec.IntValue monocle_7_mana_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_7_regen_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_7_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_7_damage_total_boost = null;
//
//    // MONOCLE_8
//    public static ForgeConfigSpec.IntValue monocle_8_mana_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_8_regen_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_8_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_8_damage_total_boost = null;
//
//    // MONOCLE_9
//
//
//    public static ForgeConfigSpec.IntValue monocle_9_regen_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_9_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_9_mana_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_9_damage_total_boost = null;
//
//    public static ForgeConfigSpec.IntValue monocle_10_mana_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_10_regen_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_10_mana_total_boost = null;
//    public static ForgeConfigSpec.IntValue monocle_10_damage_total_boost = null;
    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

    static {




        COMMON_BUILDER.comment("SERVER CONFIGURATION");

        WITHER_SHIELD_TOUGHNESS = SERVER_BUILDER.comment("HOW MUCH EACH LEVEL OF WITHER SHIELD INCREASE TOUGHNESS  ").defineInRange("WITHER_SHIELD_TOUGHNESS_PER_AMP", 5,1,100);
        WITHER_SHIELD_ARMOR = SERVER_BUILDER.comment("HOW MUCH EACH LEVEL OF WITHER SHIELD INCREASE ARMOR").defineInRange("WITHER_SHIELD_ARMOR_PER_AMP", 5,1,100);
        IS_LEVELING_ENABLED = SERVER_BUILDER.comment("can players level up").define("LevelingSystemEnabled", true);
        MAX_PLAYER_LEVEL = SERVER_BUILDER.comment("the maximum level that a player can get to.").defineInRange("LevelingMaxLevel", 6, 1, 9);
        MAX_PLAYER_CORE = SERVER_BUILDER.comment("the maximum number of cores that a player can have.").defineInRange("LevelingMaxCore", 9, 1, 777);
        AURA_AUG_AOE = COMMON_BUILDER.comment("the maximum number of cores that a player can have.").defineInRange("AURA_AUG_AOE", 5, 0, 1000);
        AURA_AUG_EXTEND_TIME = COMMON_BUILDER.comment("the maximum number of cores that a player can have.").defineInRange("AURA_AUG_TIME_PLUS", 5, 0, 100000);
        AURA_AUG_ACCELERATE = COMMON_BUILDER.comment("The delay reduction that each \"Accelerate\" will give.").defineInRange("AURA_AUG_ACCELERATE", 4, 0, 4);
        AURA_BASE_DURATION = COMMON_BUILDER.comment("The base Aura duration.").defineInRange("AURA_BASE_REACH", 5, 0, 100000);
        AURA_BASE_REACH = COMMON_BUILDER.comment("The base Aura Reach.").defineInRange("AURA_BASE_TIME", 20, 1, 100000);
        AURA_BASE_DELAY = COMMON_BUILDER.comment("The base Aura Delay.").defineInRange("AURA_BASE_DELAY", 20, 1, 100000);
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
// AUTO-GENERATED CONFIGURATION DEFINITIONS
//        essence_lotus_3_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for essence_lotus_3.")
//                .defineInRange("essence_lotus_3_mana_boost", 15, 0, 1000000000);
//        essence_lotus_3_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for essence_lotus_3.")
//                .defineInRange("essence_lotus_3_regen_boost", 10, 0, 1000000000);
//        essence_lotus_3_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for essence_lotus_3.")
//                .defineInRange("essence_lotus_3_mana_total_boost", 0, 0, 1000000000);
//        essence_lotus_3_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for essence_lotus_3.")
//                .defineInRange("essence_lotus_3_damage_total_boost", 0, 0, 1000000000);
//        essence_lotus_4_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for essence_lotus_4.")
//                .defineInRange("essence_lotus_4_mana_boost", 30, 0, 1000000000);
//        essence_lotus_4_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for essence_lotus_4.")
//                .defineInRange("essence_lotus_4_regen_boost", 20, 0, 1000000000);
//        essence_lotus_4_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for essence_lotus_4.")
//                .defineInRange("essence_lotus_4_mana_total_boost", 0, 0, 1000000000);
//        essence_lotus_4_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for essence_lotus_4.")
//                .defineInRange("essence_lotus_4_damage_total_boost", 0, 0, 1000000000);
//        essence_lotus_5_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for essence_lotus_5.")
//                .defineInRange("essence_lotus_5_mana_boost", 45, 0, 1000000000);
//        essence_lotus_5_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for essence_lotus_5.")
//                .defineInRange("essence_lotus_5_regen_boost", 40, 0, 1000000000);
//        essence_lotus_5_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for essence_lotus_5.")
//                .defineInRange("essence_lotus_5_mana_total_boost", 0, 0, 1000000000);
//        essence_lotus_5_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for essence_lotus_5.")
//                .defineInRange("essence_lotus_5_damage_total_boost", 0, 0, 1000000000);
//        essence_lotus_6_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for essence_lotus_6.")
//                .defineInRange("essence_lotus_6_mana_boost", 60, 0, 1000000000);
//        essence_lotus_6_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for essence_lotus_6.")
//                .defineInRange("essence_lotus_6_regen_boost", 80, 0, 1000000000);
//        essence_lotus_6_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for essence_lotus_6.")
//                .defineInRange("essence_lotus_6_mana_total_boost", 0, 0, 1000000000);
//        essence_lotus_6_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for essence_lotus_6.")
//                .defineInRange("essence_lotus_6_damage_total_boost", 0, 0, 1000000000);
//        essence_lotus_7_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for essence_lotus_7.")
//                .defineInRange("essence_lotus_7_mana_boost", 75, 0, 1000000000);
//        essence_lotus_7_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for essence_lotus_7.")
//                .defineInRange("essence_lotus_7_regen_boost", 160, 0, 1000000000);
//        essence_lotus_7_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for essence_lotus_7.")
//                .defineInRange("essence_lotus_7_mana_total_boost", 0, 0, 1000000000);
//        essence_lotus_7_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for essence_lotus_7.")
//                .defineInRange("essence_lotus_7_damage_total_boost", 0, 0, 1000000000);
//        essence_lotus_8_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for essence_lotus_8.")
//                .defineInRange("essence_lotus_8_mana_boost", 90, 0, 1000000000);
//        essence_lotus_8_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for essence_lotus_8.")
//                .defineInRange("essence_lotus_8_regen_boost", 320, 0, 1000000000);
//        essence_lotus_8_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for essence_lotus_8.")
//                .defineInRange("essence_lotus_8_mana_total_boost", 0, 0, 1000000000);
//        essence_lotus_8_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for essence_lotus_8.")
//                .defineInRange("essence_lotus_8_damage_total_boost", 0, 0, 1000000000);
//        essence_lotus_9_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for essence_lotus_9.")
//                .defineInRange("essence_lotus_9_mana_boost", 105, 0, 1000000000);
//        essence_lotus_9_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for essence_lotus_9.")
//                .defineInRange("essence_lotus_9_regen_boost", 640, 0, 1000000000);
//        essence_lotus_9_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for essence_lotus_9.")
//                .defineInRange("essence_lotus_9_mana_total_boost", 0, 0, 1000000000);
//        essence_lotus_9_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for essence_lotus_9.")
//                .defineInRange("essence_lotus_9_damage_total_boost", 0, 0, 1000000000);
//        essence_lotus_10_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for essence_lotus_10.")
//                .defineInRange("essence_lotus_10_mana_boost", 250, 0, 1000000000);
//        essence_lotus_10_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for essence_lotus_10.")
//                .defineInRange("essence_lotus_10_regen_boost", 1250, 0, 1000000000);
//        essence_lotus_10_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for essence_lotus_10.")
//                .defineInRange("essence_lotus_10_mana_total_boost", 0, 0, 1000000000);
//        essence_lotus_10_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for essence_lotus_10.")
//                .defineInRange("essence_lotus_10_damage_total_boost", 0, 0, 1000000000);
//        mana_ring_3_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for mana_ring_3.")
//                .defineInRange("mana_ring_3_mana_boost", 25, 0, 1000000000);
//        mana_ring_3_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for mana_ring_3.")
//                .defineInRange("mana_ring_3_regen_boost", -5, 0, 1000000000);
//        mana_ring_3_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for mana_ring_3.")
//                .defineInRange("mana_ring_3_mana_total_boost", 0, 0, 1000000000);
//        mana_ring_3_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for mana_ring_3.")
//                .defineInRange("mana_ring_3_damage_total_boost", 0, 0, 1000000000);
//        mana_ring_4_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for mana_ring_4.")
//                .defineInRange("mana_ring_4_mana_boost", 50, 0, 1000000000);
//        mana_ring_4_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for mana_ring_4.")
//                .defineInRange("mana_ring_4_regen_boost", -10, 0, 1000000000);
//        mana_ring_4_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for mana_ring_4.")
//                .defineInRange("mana_ring_4_mana_total_boost", 0, 0, 1000000000);
//        mana_ring_4_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for mana_ring_4.")
//                .defineInRange("mana_ring_4_damage_total_boost", 0, 0, 1000000000);
//        mana_ring_5_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for mana_ring_5.")
//                .defineInRange("mana_ring_5_mana_boost", 75, 0, 1000000000);
//        mana_ring_5_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for mana_ring_5.")
//                .defineInRange("mana_ring_5_regen_boost", -15, 0, 1000000000);
//        mana_ring_5_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for mana_ring_5.")
//                .defineInRange("mana_ring_5_mana_total_boost", 0, 0, 1000000000);
//        mana_ring_5_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for mana_ring_5.")
//                .defineInRange("mana_ring_5_damage_total_boost", 0, 0, 1000000000);
//        mana_ring_6_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for mana_ring_6.")
//                .defineInRange("mana_ring_6_mana_boost", 100, 0, 1000000000);
//        mana_ring_6_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for mana_ring_6.")
//                .defineInRange("mana_ring_6_regen_boost", -20, 0, 1000000000);
//        mana_ring_6_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for mana_ring_6.")
//                .defineInRange("mana_ring_6_mana_total_boost", 0, 0, 1000000000);
//        mana_ring_6_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for mana_ring_6.")
//                .defineInRange("mana_ring_6_damage_total_boost", 0, 0, 1000000000);
//        mana_ring_7_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for mana_ring_7.")
//                .defineInRange("mana_ring_7_mana_boost", 250, 0, 1000000000);
//        mana_ring_7_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for mana_ring_7.")
//                .defineInRange("mana_ring_7_regen_boost", -30, 0, 1000000000);
//        mana_ring_7_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for mana_ring_7.")
//                .defineInRange("mana_ring_7_mana_total_boost", 0, 0, 1000000000);
//        mana_ring_7_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for mana_ring_7.")
//                .defineInRange("mana_ring_7_damage_total_boost", 0, 0, 1000000000);
//        mana_ring_8_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for mana_ring_8.")
//                .defineInRange("mana_ring_8_mana_boost", 500, 0, 1000000000);
//        mana_ring_8_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for mana_ring_8.")
//                .defineInRange("mana_ring_8_regen_boost", -40, 0, 1000000000);
//        mana_ring_8_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for mana_ring_8.")
//                .defineInRange("mana_ring_8_mana_total_boost", 0, 0, 1000000000);
//        mana_ring_8_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for mana_ring_8.")
//                .defineInRange("mana_ring_8_damage_total_boost", 0, 0, 1000000000);
//        mana_ring_9_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for mana_ring_9.")
//                .defineInRange("mana_ring_9_mana_boost", 1000, 0, 1000000000);
//        mana_ring_9_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for mana_ring_9.")
//                .defineInRange("mana_ring_9_regen_boost", -50, 0, 1000000000);
//        mana_ring_9_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for mana_ring_9.")
//                .defineInRange("mana_ring_9_mana_total_boost", 0, 0, 1000000000);
//        mana_ring_9_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for mana_ring_9.")
//                .defineInRange("mana_ring_9_damage_total_boost", 0, 0, 1000000000);
//        mana_ring_10_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for mana_ring_10.")
//                .defineInRange("mana_ring_10_mana_boost", 2500, 0, 1000000000);
//        mana_ring_10_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for mana_ring_10.")
//                .defineInRange("mana_ring_10_regen_boost", -60, 0, 1000000000);
//        mana_ring_10_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for mana_ring_10.")
//                .defineInRange("mana_ring_10_mana_total_boost", 0, 0, 1000000000);
//        mana_ring_10_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for mana_ring_10.")
//                .defineInRange("mana_ring_10_damage_total_boost", 0, 0, 1000000000);
//        monocle_1_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for monocle_1.")
//                .defineInRange("monocle_1_mana_boost", 0, 0, 1000000000);
//        monocle_1_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for monocle_1.")
//                .defineInRange("monocle_1_regen_boost", 0, 0, 1000000000);
//        monocle_1_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for monocle_1.")
//                .defineInRange("monocle_1_mana_total_boost", 0, 0, 1000000000);
//        monocle_1_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for monocle_1.")
//                .defineInRange("monocle_1_damage_total_boost", 1, 0, 1000000000);
//        monocle_2_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for monocle_2.")
//                .defineInRange("monocle_2_mana_boost", 0, 0, 1000000000);
//        monocle_2_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for monocle_2.")
//                .defineInRange("monocle_2_regen_boost", 0, 0, 1000000000);
//        monocle_2_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for monocle_2.")
//                .defineInRange("monocle_2_mana_total_boost", 0, 0, 1000000000);
//        monocle_2_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for monocle_2.")
//                .defineInRange("monocle_2_damage_total_boost", 2, 0, 1000000000);
//        monocle_3_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for monocle_3.")
//                .defineInRange("monocle_3_mana_boost", 10, 0, 1000000000);
//        monocle_3_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for monocle_3.")
//                .defineInRange("monocle_3_regen_boost", 1, 0, 1000000000);
//        monocle_3_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for monocle_3.")
//                .defineInRange("monocle_3_mana_total_boost", 0, 0, 1000000000);
//        monocle_3_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for monocle_3.")
//                .defineInRange("monocle_3_damage_total_boost", 4, 0, 1000000000);
//        monocle_4_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for monocle_4.")
//                .defineInRange("monocle_4_mana_boost", 20, 0, 1000000000);
//        monocle_4_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for monocle_4.")
//                .defineInRange("monocle_4_regen_boost", 2, 0, 1000000000);
//        monocle_4_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for monocle_4.")
//                .defineInRange("monocle_4_mana_total_boost", 0, 0, 1000000000);
//        monocle_4_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for monocle_4.")
//                .defineInRange("monocle_4_damage_total_boost", 5, 0, 1000000000);
//        monocle_5_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for monocle_5.")
//                .defineInRange("monocle_5_mana_boost", 50, 0, 1000000000);
//        monocle_5_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for monocle_5.")
//                .defineInRange("monocle_5_regen_boost", 3, 0, 1000000000);
//        monocle_5_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for monocle_5.")
//                .defineInRange("monocle_5_mana_total_boost", 0, 0, 1000000000);
//        monocle_5_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for monocle_5.")
//                .defineInRange("monocle_5_damage_total_boost", 10, 0, 1000000000);
//        monocle_6_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for monocle_6.")
//                .defineInRange("monocle_6_mana_boost", 100, 0, 1000000000);
//        monocle_6_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for monocle_6.")
//                .defineInRange("monocle_6_regen_boost", 10, 0, 1000000000);
//        monocle_6_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for monocle_6.")
//                .defineInRange("monocle_6_mana_total_boost", 0, 0, 1000000000);
//        monocle_6_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for monocle_6.")
//                .defineInRange("monocle_6_damage_total_boost", 15, 0, 1000000000);
//        monocle_7_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for monocle_7.")
//                .defineInRange("monocle_7_mana_boost", 200, 0, 1000000000);
//        monocle_7_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for monocle_7.")
//                .defineInRange("monocle_7_regen_boost", 20, 0, 1000000000);
//        monocle_7_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for monocle_7.")
//                .defineInRange("monocle_7_mana_total_boost", 0, 0, 1000000000);
//        monocle_7_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for monocle_7.")
//                .defineInRange("monocle_7_damage_total_boost", 20, 0, 1000000000);
//        monocle_8_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for monocle_8.")
//                .defineInRange("monocle_8_mana_boost", 400, 0, 1000000000);
//        monocle_8_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for monocle_8.")
//                .defineInRange("monocle_8_regen_boost", 40, 0, 1000000000);
//        monocle_8_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for monocle_8.")
//                .defineInRange("monocle_8_mana_total_boost", 0, 0, 1000000000);
//        monocle_8_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for monocle_8.")
//                .defineInRange("monocle_8_damage_total_boost", 25, 0, 1000000000);
//        monocle_9_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for monocle_9.")
//                .defineInRange("monocle_9_mana_boost", 500, 0, 1000000000);
//        monocle_9_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for monocle_9.")
//                .defineInRange("monocle_9_regen_boost", 50, 0, 1000000000);
//        monocle_9_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for monocle_9.")
//                .defineInRange("monocle_9_mana_total_boost", 0, 0, 1000000000);
//        monocle_9_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for monocle_9.")
//                .defineInRange("monocle_9_damage_total_boost", 35, 0, 1000000000);
//        monocle_10_mana_boost = COMMON_BUILDER.comment("Specifies the mana boost for monocle_10.")
//                .defineInRange("monocle_10_mana_boost", 1000, 0, 1000000000);
//        monocle_10_regen_boost = COMMON_BUILDER.comment("Specifies the regen boost for monocle_10.")
//                .defineInRange("monocle_10_regen_boost", 100, 0, 1000000000);
//        monocle_10_mana_total_boost = COMMON_BUILDER.comment("Specifies the total mana boost for monocle_10.")
//                .defineInRange("monocle_10_mana_total_boost", 0, 0, 1000000000);
//        monocle_10_damage_total_boost = COMMON_BUILDER.comment("Specifies the total damage boost for monocle_10.")
//                .defineInRange("monocle_10_damage_total_boost", 50, 0, 1000000000);



//        MANA_REGEN_BONUS_LVL_1 = SERVER_BUILDER.comment("The base Aura Delay.").defineInRange("AURA_BASE_DELAY", );

        COMMON_CONFIG = COMMON_BUILDER.build();
        SERVER_CONFIG = SERVER_BUILDER.build();

    }
}
