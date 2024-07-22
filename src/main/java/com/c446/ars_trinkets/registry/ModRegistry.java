package com.c446.ars_trinkets.registry;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.entities.EntityMissileSpell;
import com.c446.ars_trinkets.entities.effects.AuraEffect;
import com.c446.ars_trinkets.item.*;
import com.c446.ars_trinkets.perks.PerkAttributes;
import com.hollingsworth.arsnouveau.api.perk.IPerk;
import com.hollingsworth.arsnouveau.api.registry.PerkRegistry;
import com.hollingsworth.arsnouveau.api.sound.SpellSound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ArsTrinkets.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ArsTrinkets.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ArsTrinkets.MOD_ID);

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ArsTrinkets.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ArsTrinkets.MOD_ID);
    public static final DeferredRegister<Attribute> PERKS = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, ArsTrinkets.MOD_ID);
    public static void registerRegistries(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        SOUNDS.register(bus);
        ENTITIES.register(bus);
        EFFECTS.register(bus);
        PerkAttributes.ATTRIBUTES.register(bus);

    }

    //    public static final RegistryObject<Item> EXAMPLE;
    public static final RegistryObject<Item> ESSENCE_LOTUS_3;
    public static final RegistryObject<Item> ESSENCE_LOTUS_4;
    public static final RegistryObject<Item> ESSENCE_LOTUS_5;
    public static final RegistryObject<Item> ESSENCE_LOTUS_6;
    public static final RegistryObject<Item> ESSENCE_LOTUS_7;
    public static final RegistryObject<Item> ESSENCE_LOTUS_8;
    public static final RegistryObject<Item> ESSENCE_LOTUS_9;
    public static final RegistryObject<Item> ESSENCE_LOTUS_10;
    public static final RegistryObject<Item> MANA_RING_3;
    public static final RegistryObject<Item> MANA_RING_4;
    public static final RegistryObject<Item> MANA_RING_5;
    public static final RegistryObject<Item> MANA_RING_6;
    public static final RegistryObject<Item> MANA_RING_7;
    public static final RegistryObject<Item> MANA_RING_8;
    public static final RegistryObject<Item> MANA_RING_9;
    public static final RegistryObject<Item> MANA_RING_10;
    public static final RegistryObject<Item> MONOCLE_1;
    public static final RegistryObject<Item> MONOCLE_2;
    public static final RegistryObject<Item> MONOCLE_3;
    public static final RegistryObject<Item> MONOCLE_4;
    public static final RegistryObject<Item> MONOCLE_5;
    public static final RegistryObject<Item> MONOCLE_6;
    public static final RegistryObject<Item> MONOCLE_7;
    public static final RegistryObject<Item> MONOCLE_8;
    public static final RegistryObject<Item> MONOCLE_9;
    public static final RegistryObject<Item> MONOCLE_10;
    public static final RegistryObject<Item> MANA_CORE_1;
    public static final RegistryObject<Item> MANA_CORE_2;
    public static final RegistryObject<Item> MANA_CORE_3;
    public static final RegistryObject<Item> COPPER_ESSENCE;
    public static final RegistryObject<Item> IRON_ESSENCE;
    public static final RegistryObject<Item> SILVER_ESSENCE;
    public static final RegistryObject<Item> GOLD_ESSENCE;
    public static final RegistryObject<Item> CRYSTAL_ESSENCE;
    public static final RegistryObject<Item> GREEN_ESSENCE;
    public static final RegistryObject<Item> RED_ESSENCE;
    public static final RegistryObject<Item> WHITE_ESSENCE;
    public static final RegistryObject<Item> YELLOW_ESSENCE;
    public static final RegistryObject<Item> PURPLE_ESSENCE;


    public static final RegistryObject<Item> ETERNAL_RUNE;
    public static RegistryObject<Item> WARRIOR_RUNE = null;
    public static RegistryObject<Item> SOUL_STEALER_RUNE = null;
    public static RegistryObject<Item> HOLY_RUNE = null;
    public static RegistryObject<Item> MAGE_RUNE = null;
    public static RegistryObject<Item> WARRIOR_RUNE_LESSER = null;
    public static RegistryObject<Item> SOUL_STEALER_RUNE_LESSER = null;
    public static RegistryObject<Item> HOLY_RUNE_LESSER = null;
    public static RegistryObject<Item> MAGE_RUNE_LESSER = null;
    public static RegistryObject<Item> WARRIOR_RUNE_GREATER = null;
    public static RegistryObject<Item> SOUL_STEALER_RUNE_GREATER = null;
    public static RegistryObject<Item> HOLY_RUNE_GREATER = null;
    public static RegistryObject<Item> MAGE_RUNE_GREATER = null;
    public static RegistryObject<Item> HOLY_HEART = null;
    public static RegistryObject<Item> PUTRID_HEART = null;
    public static RegistryObject<Item> THEARCH_CROWN = null;
    public static final RegistryObject<EntityType<? extends EntityMissileSpell>> ENTITY_MISSILE = ENTITIES.register("missile_spell_proj", () -> EntityType.Builder.<EntityMissileSpell>of(EntityMissileSpell::new, MobCategory.MISC).sized(0.9F, 3.0F).build(new ResourceLocation(ArsTrinkets.MOD_ID, "missile_spell_proj").toString()));


    public static ResourceLocation getGlyphName(String name) {
        return new ResourceLocation(ArsTrinkets.MOD_ID, ("glyph_" + name));
    }

    public static final RegistryObject<MobEffect> AURA_EFFECT = EFFECTS.register("glyph_aura", AuraEffect::new);

    public static void registerPerk(IPerk perk) {
        PerkRegistry.registerPerk(perk);
    }

    public static SpellSound EXAMPLE_SPELL_SOUND;

    static {
        //trinkets
        ESSENCE_LOTUS_3 = ITEMS.register("essence_lotus_3", () -> new MagicItems(new Item.Properties()/*.tab(...)*/.rarity(Rarity.COMMON).stacksTo(1), 15, 10, 0, 0));
        ESSENCE_LOTUS_4 = ITEMS.register("essence_lotus_4", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 30, 20, 0, 0));
        ESSENCE_LOTUS_5 = ITEMS.register("essence_lotus_5", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1), 45, 40, 0, 0));
        ESSENCE_LOTUS_6 = ITEMS.register("essence_lotus_6", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1), 60, 80, 0, 0));
        ESSENCE_LOTUS_7 = ITEMS.register("essence_lotus_7", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 75, 160, 0, 0));
        ESSENCE_LOTUS_8 = ITEMS.register("essence_lotus_8", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 90, 320, 0, 0));
        ESSENCE_LOTUS_9 = ITEMS.register("essence_lotus_9", () -> new MagicItems(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 105, 640, 0, 0));
        ESSENCE_LOTUS_10 = ITEMS.register("essence_lotus_10", () -> new MagicItems(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 250, 1250, 0, 0));

        MANA_RING_3 = ITEMS.register("mana_stone_3", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 25, -5, 0, 0));
        MANA_RING_4 = ITEMS.register("mana_stone_4", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 50, -10, 0, 0));
        MANA_RING_5 = ITEMS.register("mana_stone_5", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1), 75, -15, 0, 0));
        MANA_RING_6 = ITEMS.register("mana_stone_6", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1), 100, -20, 0, 0));
        MANA_RING_7 = ITEMS.register("mana_stone_7", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 250, -30, 0, 0));
        MANA_RING_8 = ITEMS.register("mana_stone_8", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 500, -40, 0, 0));
        MANA_RING_9 = ITEMS.register("mana_stone_9", () -> new MagicItems(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 1000, -50, 0, 0));
        MANA_RING_10 = ITEMS.register("mana_stone_10", () -> new MagicItems(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 2500, -60, 0, 0));

        MONOCLE_1 = ITEMS.register("monocle_1", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 0, 0, 0, 1));
        MONOCLE_2 = ITEMS.register("monocle_2", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 0, 0, 0, 2));
        MONOCLE_3 = ITEMS.register("monocle_3", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 10, 1, 0, 4));
        MONOCLE_4 = ITEMS.register("monocle_4", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 20, 2, 0, 5));
        MONOCLE_5 = ITEMS.register("monocle_5", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 50, 3, 0, 10));
        MONOCLE_6 = ITEMS.register("monocle_6", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1), 100, 10, 0, 15));
        MONOCLE_7 = ITEMS.register("monocle_7", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1), 200, 20, 0, 20));
        MONOCLE_8 = ITEMS.register("monocle_8", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 400, 40, 0, 25));
        MONOCLE_9 = ITEMS.register("monocle_9", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 500, 50, 0, 35));
        MONOCLE_10 = ITEMS.register("monocle_10", () -> new MagicItems(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 1000, 100, 0, 50));

        MANA_CORE_1 = ITEMS.register("mana_core_1", () -> new ManaCore(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16), new FoodProperties.Builder().fast().saturationMod(50).nutrition(50).alwaysEat().build(), true, 1));
        MANA_CORE_2 = ITEMS.register("mana_core_2", () -> new ManaCore(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16), new FoodProperties.Builder().fast().saturationMod(100).nutrition(100).alwaysEat().build(), true, 2));
        MANA_CORE_3 = ITEMS.register("mana_core_3", () -> new ManaCore(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16), new FoodProperties.Builder().fast().saturationMod(150).nutrition(150).alwaysEat().build(), true, 3));

        COPPER_ESSENCE = ITEMS.register("copper_essence", () -> new EssenceItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
        IRON_ESSENCE = ITEMS.register("iron_essence", () -> new EssenceItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
        SILVER_ESSENCE = ITEMS.register("silver_essence", () -> new EssenceItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
        GOLD_ESSENCE = ITEMS.register("gold_essence", () -> new EssenceItem(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
        CRYSTAL_ESSENCE = ITEMS.register("crystal_essence", () -> new EssenceItem(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON)));
        GREEN_ESSENCE = ITEMS.register("green_essence", () -> new EssenceItem(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON), true));
        RED_ESSENCE = ITEMS.register("red_essence", () -> new EssenceItem(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.RARE), true));
        WHITE_ESSENCE = ITEMS.register("white_essence", () -> new EssenceItem(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.RARE), true));
        YELLOW_ESSENCE = ITEMS.register("yellow_essence", () -> new EssenceItem(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC), true));
        PURPLE_ESSENCE = ITEMS.register("purple_essence", () -> new EssenceItem(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC), true));

        ETERNAL_RUNE = ITEMS.register("eternal_rune", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 50, 125, 60, 50, 50, 0, 60, 60, 60, 1));


        HOLY_HEART = ITEMS.register("holy_heart",() -> new ThatItemToChangeClassLol(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), new FoodProperties.Builder().alwaysEat().nutrition(20).saturationMod(20).build() ,false));
        PUTRID_HEART = ITEMS.register("putrid_heart",() -> new ThatItemToChangeClassLol(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),new FoodProperties.Builder().alwaysEat().nutrition(0).saturationMod(0).build(), true));

        HOLY_RUNE_GREATER = ITEMS.register("life_rune_greater", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 0, 0, 50, 0, 30, 0, 0, 0, 0, 0));
        SOUL_STEALER_RUNE_GREATER = ITEMS.register("death_rune_greater", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 35, 100, 0, 0, 0, 0, 0, 0, 0, 0));
        WARRIOR_RUNE_GREATER = ITEMS.register("warrior_rune_greater", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 0, 0, 0, 40, 0, 0, 0, 0, 0, 0.75));
        MAGE_RUNE_GREATER = ITEMS.register("mage_rune_greater", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 0, 0, 0, 0, 0, 0, 50, 50, 50, 0));

        HOLY_RUNE = ITEMS.register("life_rune", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant(), 0, 0, 40, 0, 20, 0, 0, 0, 0, 0));
        SOUL_STEALER_RUNE = ITEMS.register("death_rune", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant(), 25, 75, 0, 0, 0, 0, 0, 0, 0, 0));
        WARRIOR_RUNE = ITEMS.register("warrior_rune", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant(), 0, 0, 0, 30, 0, 0, 0, 0, 0, 0.5));
        MAGE_RUNE = ITEMS.register("mage_rune", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant(), 0, 0, 0, 0, 0, 0, 40, 40, 40, 0));

        HOLY_RUNE_LESSER = ITEMS.register("life_rune_lesser", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), 0, 0, 20, 0, 10, 0, 0, 0, 0, 0));
        SOUL_STEALER_RUNE_LESSER = ITEMS.register("death_rune_lesser", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), 15, 50, 0, 0, 0, 0, 0, 0, 0, 0));
        WARRIOR_RUNE_LESSER = ITEMS.register("warrior_rune_lesser", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), 0, 0, 0, 15, 0, 0, 0, 0, 0, 0.25));
        MAGE_RUNE_LESSER = ITEMS.register("mage_rune_lesser", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), 0, 0, 0, 0, 0, 0, 20, 20, 20, 0));

        THEARCH_CROWN = ITEMS.register("thearch_crown", () -> new ArcaneRunes(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 250, 150, 250, 500, 500, 250, 250, 250, 250, 2.5));

    }
}
