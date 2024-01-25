package com.c446.ars_trinkets.registry;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.entities.EntityMissileSpell;
import com.c446.ars_trinkets.entities.effects.AuraEffect;
import com.c446.ars_trinkets.item.*;
import com.hollingsworth.arsnouveau.api.sound.SpellSound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
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



    public static void registerRegistries(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        SOUNDS.register(bus);
        ENTITIES.register(bus);
        EFFECTS.register(bus);
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
    public static final RegistryObject<Item> MANA_CONDENSOR_3;
    public static final RegistryObject<Item> MANA_CONDENSOR_4;
    public static final RegistryObject<Item> MANA_CONDENSOR_5;
    public static final RegistryObject<Item> MANA_CONDENSOR_6;
    public static final RegistryObject<Item> MANA_CONDENSOR_7;
    public static final RegistryObject<Item> MANA_CONDENSOR_8;
    public static final RegistryObject<Item> MANA_CONDENSOR_9;
    public static final RegistryObject<Item> MANA_CONDENSOR_10;
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
    public static final RegistryObject<Item> MANA_CORE_4;
    public static final RegistryObject<Item> MANA_CORE_5;
    public static final RegistryObject<Item> MANA_CORE_6;
    public static final RegistryObject<Item> MANA_CORE_7;
    public static final RegistryObject<Item> MANA_CORE_8;

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
    public static final RegistryObject<EntityType<? extends EntityMissileSpell>> ENTITY_MISSILE = ENTITIES.register("missile_spell_proj", () -> EntityType.Builder.<EntityMissileSpell>of(EntityMissileSpell::new, MobCategory.MISC).sized(0.9F, 3.0F).build(new ResourceLocation(ArsTrinkets.MOD_ID, "missile_spell_proj").toString()));
    public static ResourceLocation getGlyphName(String name) {
        return new ResourceLocation(ArsTrinkets.MOD_ID, ("glyph_"+name));
    }

    public static final RegistryObject<MobEffect> AURA_EFFECT = EFFECTS.register("glyph_aura", AuraEffect::new);

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

        MANA_CONDENSOR_3 = ITEMS.register("mana_stone_3", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 20, -3, 0, 0));
        MANA_CONDENSOR_4 = ITEMS.register("mana_stone_4", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 40, -6, 0, 0));
        MANA_CONDENSOR_5 = ITEMS.register("mana_stone_5", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1), 80, -12, 0, 0));
        MANA_CONDENSOR_6 = ITEMS.register("mana_stone_6", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1), 160, -24, 0, 0));
        MANA_CONDENSOR_7 = ITEMS.register("mana_stone_7", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 320, -48, 0, 0));
        MANA_CONDENSOR_8 = ITEMS.register("mana_stone_8", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 640, -96, 0, 0));
        MANA_CONDENSOR_9 = ITEMS.register("mana_stone_9", () -> new MagicItems(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 1280, -192, 0, 0));
        MANA_CONDENSOR_10 = ITEMS.register("mana_stone_10", () -> new MagicItems(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 2560, -384, 0, 0));

        MONOCLE_1 = ITEMS.register("monocle_1", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 0, 0, 0, 1));
        MONOCLE_2 = ITEMS.register("monocle_2", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 0, 0, 0, 2));
        MONOCLE_3 = ITEMS.register("monocle_3", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 10, 1, 0, 3));
        MONOCLE_4 = ITEMS.register("monocle_4", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 20, 2, 0, 4));
        MONOCLE_5 = ITEMS.register("monocle_5", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1), 30, 3, 0, 6));
        MONOCLE_6 = ITEMS.register("monocle_6", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1), 100, 10, 0, 10));
        MONOCLE_7 = ITEMS.register("monocle_7", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1), 200, 20, 0, 20));
        MONOCLE_8 = ITEMS.register("monocle_8", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 400, 40, 0, 30));
        MONOCLE_9 = ITEMS.register("monocle_9", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 500, 50, 0, 40));
        MONOCLE_10 = ITEMS.register("monocle_10", () -> new MagicItems(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), 1000, 100, 0, 80));

        MANA_CORE_1 = ITEMS.register("mana_core_1", () -> new ManaCore(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1),true,1));
        MANA_CORE_2 = ITEMS.register("mana_core_2", () -> new ManaCore(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1),true,2));
        MANA_CORE_3 = ITEMS.register("mana_core_3", () -> new ManaCore(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1),true,3));
        MANA_CORE_4 = ITEMS.register("mana_core_4", () -> new ManaCore(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1),true,4));
        MANA_CORE_5 = ITEMS.register("mana_core_5", () -> new ManaCore(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1),true,5));
        MANA_CORE_6 = ITEMS.register("mana_core_6", () -> new ManaCore(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1),true,6));
        MANA_CORE_7 = ITEMS.register("mana_core_7", () -> new ManaCore(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1),true,7));
        MANA_CORE_8 = ITEMS.register("mana_core_8", () -> new ManaCore(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1),true,8));

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
    }
}
