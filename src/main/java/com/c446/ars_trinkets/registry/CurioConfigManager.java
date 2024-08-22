package com.c446.ars_trinkets.registry;

import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.perks.PerkAttributes;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;

import static com.hollingsworth.arsnouveau.api.perk.PerkAttributes.MANA_REGEN_BONUS;
import static com.hollingsworth.arsnouveau.api.perk.PerkAttributes.MAX_MANA;

public class CurioConfigManager {
    /**
     * why are we still here... Just to suffer?
     */
    public static final int[] defaultMonocleMana = {0, 0, 5, 10, 15, 25, 40, 60, 80, 100};
    public static final int[] defaultMonocleRegen = {0, 0, 0, 5, 10, 15, 25, 50, 75, 100};
    public static final int[] defaultMonocleDamage = {0, 5, 10, 15, 20, 25, 30, 35, 40, 50};

    public static final int[] defaultLotusRegen = {5, 10, 25, 50, 75, 100, 150, 250, 500, 1000};
    public static final int[] defaultLotusMana = {0, 0, 10, 15, 25, 50, 75, 125, 250, 500};

    public static final int[] defaultRingMana = {5, 10, 25, 50, 100, 150, 250, 500, 1000, 2000};
    public static final int[] defaultRingRegen = {-0, -5, -10, -15, -20, -25, -50, -100, -150, -200};


    public static final Item.Properties EPIC_CURIO;
    public static final Item.Properties RARE_CURIO;
    public static final Item.Properties UNCOMMON_CURIO;
    public static final Item.Properties COMMON_CURIO;

    static {
        EPIC_CURIO = new Item.Properties().rarity(Rarity.EPIC).fireResistant().stacksTo(1);
        RARE_CURIO = new Item.Properties().rarity(Rarity.RARE).fireResistant().stacksTo(1);
        UNCOMMON_CURIO = new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant().stacksTo(1);
        COMMON_CURIO = new Item.Properties().rarity(Rarity.COMMON).fireResistant().stacksTo(1);
    }

    public static HashMap<Integer, HashMap<Attribute, ForgeConfigSpec.IntValue>> lotuses_maps = new HashMap<>();
    public static HashMap<Integer, HashMap<Attribute, ForgeConfigSpec.IntValue>> monocle_maps = new HashMap<>();
    public static HashMap<Integer, HashMap<Attribute, ForgeConfigSpec.IntValue>> ring_maps = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_lotus_3 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_lotus_4 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_lotus_5 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_lotus_6 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_lotus_7 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_lotus_8 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_lotus_9 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_lotus_10 = new HashMap<>();

    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_ring_3 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_ring_4 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_ring_5 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_ring_6 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_ring_7 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_ring_8 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_ring_9 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_ring_10 = new HashMap<>();

    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_monocle_3 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_monocle_4 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_monocle_5 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_monocle_6 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_monocle_7 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_monocle_8 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_monocle_9 = new HashMap<>();
    public static HashMap<Attribute, ForgeConfigSpec.IntValue> mana_monocle_10 = new HashMap<>();

    // LOTUSES
    static {
        mana_lotus_3.put(MAX_MANA.get(), Config.essence_lotus_3_mana_boost);
        mana_lotus_3.put(MANA_REGEN_BONUS.get(), Config.essence_lotus_3_regen_boost);
        mana_lotus_4.put(MAX_MANA.get(), Config.essence_lotus_4_mana_boost);
        mana_lotus_4.put(MANA_REGEN_BONUS.get(), Config.essence_lotus_4_regen_boost);
        mana_lotus_5.put(MAX_MANA.get(), Config.essence_lotus_5_mana_boost);
        mana_lotus_5.put(MANA_REGEN_BONUS.get(), Config.essence_lotus_5_regen_boost);
        mana_lotus_6.put(MAX_MANA.get(), Config.essence_lotus_6_mana_boost);
        mana_lotus_6.put(MANA_REGEN_BONUS.get(), Config.essence_lotus_6_regen_boost);
        mana_lotus_7.put(MAX_MANA.get(), Config.essence_lotus_7_mana_boost);
        mana_lotus_7.put(MANA_REGEN_BONUS.get(), Config.essence_lotus_7_regen_boost);
        mana_lotus_8.put(MAX_MANA.get(), Config.essence_lotus_8_mana_boost);
        mana_lotus_8.put(MANA_REGEN_BONUS.get(), Config.essence_lotus_8_regen_boost);
        mana_lotus_9.put(MAX_MANA.get(), Config.essence_lotus_9_mana_boost);
        mana_lotus_9.put(MANA_REGEN_BONUS.get(), Config.essence_lotus_9_regen_boost);
        mana_lotus_10.put(MAX_MANA.get(), Config.essence_lotus_10_mana_boost);
        mana_lotus_10.put(MANA_REGEN_BONUS.get(), Config.essence_lotus_10_regen_boost);

        lotuses_maps.put(3, mana_lotus_3);
        lotuses_maps.put(4, mana_lotus_4);
        lotuses_maps.put(5, mana_lotus_5);
        lotuses_maps.put(6, mana_lotus_6);
        lotuses_maps.put(7, mana_lotus_7);
        lotuses_maps.put(8, mana_lotus_8);
        lotuses_maps.put(9, mana_lotus_9);
        lotuses_maps.put(10, mana_lotus_10);

        mana_ring_3.put(MAX_MANA.get(), Config.mana_ring_3_mana_boost);
        mana_ring_3.put(MANA_REGEN_BONUS.get(), Config.mana_ring_3_mana_boost);
        mana_ring_4.put(MAX_MANA.get(), Config.mana_ring_4_mana_boost);
        mana_ring_4.put(MANA_REGEN_BONUS.get(), Config.mana_ring_4_mana_boost);
        mana_ring_5.put(MAX_MANA.get(), Config.mana_ring_5_mana_boost);
        mana_ring_5.put(MANA_REGEN_BONUS.get(), Config.mana_ring_5_mana_boost);
        mana_ring_6.put(MAX_MANA.get(), Config.mana_ring_6_mana_boost);
        mana_ring_6.put(MANA_REGEN_BONUS.get(), Config.mana_ring_6_mana_boost);
        mana_ring_7.put(MAX_MANA.get(), Config.mana_ring_7_mana_boost);
        mana_ring_7.put(MANA_REGEN_BONUS.get(), Config.mana_ring_7_mana_boost);
        mana_ring_8.put(MAX_MANA.get(), Config.mana_ring_8_mana_boost);
        mana_ring_8.put(MANA_REGEN_BONUS.get(), Config.mana_ring_8_mana_boost);
        mana_ring_9.put(MAX_MANA.get(), Config.mana_ring_9_mana_boost);
        mana_ring_9.put(MANA_REGEN_BONUS.get(), Config.mana_ring_9_mana_boost);
        mana_ring_10.put(MAX_MANA.get(), Config.mana_ring_10_mana_boost);
        mana_ring_10.put(MANA_REGEN_BONUS.get(), Config.mana_ring_10_mana_boost);

        ring_maps.put(3, mana_ring_3);
        ring_maps.put(4, mana_ring_4);
        ring_maps.put(5, mana_ring_5);
        ring_maps.put(6, mana_ring_6);
        ring_maps.put(7, mana_ring_7);
        ring_maps.put(8, mana_ring_8);
        ring_maps.put(9, mana_ring_9);
        ring_maps.put(10, mana_ring_10);

        mana_monocle_3.put(MAX_MANA.get(), Config.monocle_3_mana_boost);
        mana_monocle_3.put(MANA_REGEN_BONUS.get(), Config.monocle_3_damage_total_boost);
        mana_monocle_3.put(PerkAttributes.SPELL_DAMAGE_PCT.get(), Config.monocle_3_regen_boost);
        mana_monocle_4.put(MAX_MANA.get(), Config.monocle_4_mana_boost);
        mana_monocle_4.put(MANA_REGEN_BONUS.get(), Config.monocle_4_damage_total_boost);
        mana_monocle_4.put(PerkAttributes.SPELL_DAMAGE_PCT.get(), Config.monocle_4_regen_boost);
        mana_monocle_5.put(MAX_MANA.get(), Config.monocle_5_mana_boost);
        mana_monocle_5.put(MANA_REGEN_BONUS.get(), Config.monocle_5_damage_total_boost);
        mana_monocle_5.put(PerkAttributes.SPELL_DAMAGE_PCT.get(), Config.monocle_5_regen_boost);
        mana_monocle_6.put(MAX_MANA.get(), Config.monocle_6_mana_boost);
        mana_monocle_6.put(MANA_REGEN_BONUS.get(), Config.monocle_6_damage_total_boost);
        mana_monocle_6.put(PerkAttributes.SPELL_DAMAGE_PCT.get(), Config.monocle_6_regen_boost);
        mana_monocle_7.put(MAX_MANA.get(), Config.monocle_7_mana_boost);
        mana_monocle_7.put(MANA_REGEN_BONUS.get(), Config.monocle_7_damage_total_boost);
        mana_monocle_7.put(PerkAttributes.SPELL_DAMAGE_PCT.get(), Config.monocle_7_regen_boost);
        mana_monocle_8.put(MAX_MANA.get(), Config.monocle_8_mana_boost);
        mana_monocle_8.put(MANA_REGEN_BONUS.get(), Config.monocle_8_damage_total_boost);
        mana_monocle_8.put(PerkAttributes.SPELL_DAMAGE_PCT.get(), Config.monocle_8_regen_boost);
        mana_monocle_9.put(MAX_MANA.get(), Config.monocle_9_mana_boost);
        mana_monocle_9.put(MANA_REGEN_BONUS.get(), Config.monocle_9_damage_total_boost);
        mana_monocle_9.put(PerkAttributes.SPELL_DAMAGE_PCT.get(), Config.monocle_9_regen_boost);
        mana_monocle_10.put(MAX_MANA.get(), Config.monocle_10_mana_boost);
        mana_monocle_10.put(MANA_REGEN_BONUS.get(), Config.monocle_10_damage_total_boost);
        mana_monocle_10.put(PerkAttributes.SPELL_DAMAGE_PCT.get(), Config.monocle_10_regen_boost);

        monocle_maps.put(3, mana_monocle_3);
        monocle_maps.put(4, mana_monocle_4);
        monocle_maps.put(5, mana_monocle_5);
        monocle_maps.put(6, mana_monocle_6);
        monocle_maps.put(7, mana_monocle_7);
        monocle_maps.put(8, mana_monocle_8);
        monocle_maps.put(9, mana_monocle_9);
        monocle_maps.put(10, mana_monocle_10);
    }


}
