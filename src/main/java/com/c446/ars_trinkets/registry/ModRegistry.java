package com.c446.ars_trinkets.registry;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.item.*;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.sound.SpellSound;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;


public class ModRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ArsTrinkets.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ArsTrinkets.MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ArsTrinkets.MODID);
    /*
     * A
     *
     *
     *
     *
     *
     */
    public static final HashMap<Integer, Player> MANA_CRYSTAL_MAP = new HashMap<Integer, Player>();
//    public static final DeferredRegister<Attribute> MANA_CRYSTAL = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, ArsTrinkets.MODID);

    public static void registerRegistries(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        SOUNDS.register(bus);
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
    //    public static final List<Item> ESSENCE_LOTUS_LIST = new ArrayList<Item>();
    public static final RegistryObject<Item> SILVER_ESSENCE;
    public static final RegistryObject<Item> GOLD_ESSENCE;
    public static final RegistryObject<Item> CRYSTAL_ESSENCE;
    public static final RegistryObject<Item> GREEN_ESSENCE;
    public static final RegistryObject<Item> RED_ESSENCE;
    public static final RegistryObject<Item> WHITE_ESSENCE;
    public static final RegistryObject<Item> YELLOW_ESSENCE;
    public static final RegistryObject<Item> PURPLE_ESSENCE;


    //    public static final RegistryObject<Item> MANA_CRYSTAL;
    //this is an example of how to register a sound. You also need to add the sound to the sound.json file, referencing your ogg files, and a texture for the button under textures/sounds.
    //this example will use one of the existing sounds randomly
    public static RegistryObject<SoundEvent> EXAMPLE_FAMILY = SOUNDS.register("example_sound", () -> makeSound("example_sound"));
    public static SpellSound EXAMPLE_SPELL_SOUND;

    static {
        //trinkets
        ESSENCE_LOTUS_3 = ITEMS.register("essence_lotus_3", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).tab(ArsNouveau.itemGroup).stacksTo(1), 15, 10));
        ESSENCE_LOTUS_4 = ITEMS.register("essence_lotus_4", () -> new MagicItems(new Item.Properties().rarity(Rarity.COMMON).tab(ArsNouveau.itemGroup).stacksTo(1), 30, 20));
        ESSENCE_LOTUS_5 = ITEMS.register("essence_lotus_5", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).tab(ArsNouveau.itemGroup).stacksTo(1), 45, 40));
        ESSENCE_LOTUS_6 = ITEMS.register("essence_lotus_6", () -> new MagicItems(new Item.Properties().rarity(Rarity.UNCOMMON).tab(ArsNouveau.itemGroup).stacksTo(1), 60, 80));
        ESSENCE_LOTUS_7 = ITEMS.register("essence_lotus_7", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).tab(ArsNouveau.itemGroup).stacksTo(1), 75, 160));
        ESSENCE_LOTUS_8 = ITEMS.register("essence_lotus_8", () -> new MagicItems(new Item.Properties().rarity(Rarity.RARE).tab(ArsNouveau.itemGroup).stacksTo(1), 90, 320));
        ESSENCE_LOTUS_9 = ITEMS.register("essence_lotus_9", () -> new MagicItems(new Item.Properties().rarity(Rarity.EPIC).tab(ArsNouveau.itemGroup).stacksTo(1), 105, 640));
        ESSENCE_LOTUS_10 = ITEMS.register("essence_lotus_10", () -> new MagicItems(new Item.Properties().rarity(Rarity.EPIC).tab(ArsNouveau.itemGroup).stacksTo(1), 250, 1250));
        //ressources
        SILVER_ESSENCE = ITEMS.register("silver_essence", () -> new EssenceItem(new Item.Properties().tab(ArsNouveau.itemGroup).stacksTo(64).rarity(Rarity.COMMON)));
        GOLD_ESSENCE = ITEMS.register("gold_essence", () -> new EssenceItem(new Item.Properties().tab(ArsNouveau.itemGroup).stacksTo(64).rarity(Rarity.COMMON)));
        CRYSTAL_ESSENCE = ITEMS.register("crystal_essence", () -> new EssenceItem(new Item.Properties().tab(ArsNouveau.itemGroup).stacksTo(64).rarity(Rarity.UNCOMMON)));
        GREEN_ESSENCE = ITEMS.register("green_essence", () -> new EssenceItem(new Item.Properties().tab(ArsNouveau.itemGroup).stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON),true));
        RED_ESSENCE = ITEMS.register("red_essence", () -> new EssenceItem(new Item.Properties().tab(ArsNouveau.itemGroup).stacksTo(64).fireResistant().rarity(Rarity.RARE),true));
        WHITE_ESSENCE = ITEMS.register("white_essence", () -> new EssenceItem(new Item.Properties().tab(ArsNouveau.itemGroup).stacksTo(64).fireResistant().rarity(Rarity.RARE),true));
        YELLOW_ESSENCE = ITEMS.register("yellow_essence", () -> new EssenceItem(new Item.Properties().tab(ArsNouveau.itemGroup).stacksTo(64).fireResistant().rarity(Rarity.EPIC),true));
        PURPLE_ESSENCE = ITEMS.register("purple_essence", () -> new EssenceItem(new Item.Properties().tab(ArsNouveau.itemGroup).stacksTo(64).fireResistant().rarity(Rarity.EPIC),true));
    }



    static SoundEvent makeSound(String name) {
        return new SoundEvent(new ResourceLocation(ArsTrinkets.MODID, name));
    }

}
