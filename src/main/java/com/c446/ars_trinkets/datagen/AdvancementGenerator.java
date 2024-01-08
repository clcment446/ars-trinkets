//package com.c446.ars_trinkets.datagen;
//
//import alexthw.ars_elemental.ArsElemental;
//import alexthw.ars_elemental.registry.ModAdvTriggers;
//import alexthw.ars_elemental.registry.ModEntities;
//import alexthw.ars_elemental.registry.ModItems;
//import com.c446.ars_trinkets.ArsTrinkets;
//import com.c446.ars_trinkets.registry.ModRegistry;
//import com.google.common.collect.ImmutableMap;
//import com.hollingsworth.arsnouveau.ArsNouveau;
//import com.hollingsworth.arsnouveau.common.datagen.advancement.ANAdvancementBuilder;
//import com.hollingsworth.arsnouveau.common.datagen.advancement.ANAdvancements;
//import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
//import net.minecraft.advancements.Advancement;
//import net.minecraft.advancements.AdvancementRewards;
//import net.minecraft.advancements.FrameType;
//import net.minecraft.advancements.critereon.EntityPredicate;
//import net.minecraft.advancements.critereon.PlayerTrigger;
//import net.minecraft.advancements.critereon.SummonedEntityTrigger;
//import net.minecraft.core.HolderLookup;
//import net.minecraft.data.PackOutput;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.ItemLike;
//import net.minecraftforge.common.data.ExistingFileHelper;
//import net.minecraftforge.common.data.ForgeAdvancementProvider;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.function.Consumer;
//
//public class AdvancementGenerator extends ForgeAdvancementProvider {
//
//    /**
//     * Code stolen from the ars elemental project
//     * */
//    public AdvancementGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
//        super(output, registries, existingFileHelper, List.of(new ArsTrinketsAdvancement()));
//    }
//
//    public String getOldName() {
//        return "Ars Trinkets Advancement Datagen";
//    }
//
//    public static class ArsTrinketsAdvancement extends ANAdvancements {
//
//        static Consumer<Advancement> advancementConsumer;
//
//        static Advancement dummy(String name) {
//            return new Advancement(new ResourceLocation(ArsTrinkets.MOD_ID, name), null, null, AdvancementRewards.EMPTY, ImmutableMap.of(), null, false);
//        }
//
//        @Override
//        public void generate(HolderLookup.Provider registries, Consumer<Advancement> con, ExistingFileHelper existingFileHelper) {
//            advancementConsumer = con;
//
//            saveBasicItem(ModRegistry.COPPER_ESSENCE.get(), dummy("essence_1"));
//            saveBasicItem(ModRegistry.IRON_ESSENCE.get(), dummy("essence_2"));
//            saveBasicItem(ModRegistry.SILVER_ESSENCE.get(), dummy("essence_3"));
//            saveBasicItem(ModRegistry.GOLD_ESSENCE.get(), dummy("essence_4"));
//            saveBasicItem(ModRegistry.CRYSTAL_ESSENCE.get(), dummy("essence_5"));
//            saveBasicItem(ModRegistry.GREEN_ESSENCE.get(), dummy("essence_6"));
//            saveBasicItem(ModRegistry.RED_ESSENCE.get(), dummy("essence_7"));
//            saveBasicItem(ModRegistry.WHITE_ESSENCE.get(), dummy("essence_8"));
//            saveBasicItem(ModRegistry.YELLOW_ESSENCE.get(), dummy("essence_9"));
//            saveBasicItem(ModRegistry.PURPLE_ESSENCE.get(), dummy("essence_10"));
//
////            builder("essence_1")
//
//            Advancement curioBag = saveBasicItem(ModItems.CURIO_BAG.get(), dummy("magebloom_crop"));
//
//            Advancement air = saveBasicItem(ModItems.LESSER_AIR_FOCUS.get(), curioBag);
//
//            buildBasicItem(ModItems.AIR_FOCUS.get(),"air_focus", FrameType.CHALLENGE, air).save(con);
//
//
//
//
///*
//    "ars_elemental.adv.title.lesser_earth_focus": "The way of Earth",
//    "ars_elemental.adv.desc.lesser_earth_focus": "Acquire a lesser focus of Earth",
//* */
//
//
//
//        }
//
//        public Advancement saveBasicItem(ItemLike item, Advancement parent) {
//            return buildBasicItem(item, ForgeRegistries.ITEMS.getKey(item.asItem()).getPath(), FrameType.TASK, parent).save(advancementConsumer);
//        }
//
//        public ANAdvancementBuilder buildBasicItem(ItemLike item, String id, FrameType frame,Advancement parent) {
//            return builder(id).display(item, frame).requireItem(item).parent(parent);
//        }
//
//        public ANAdvancementBuilder builder(String key) {
//            return ANAdvancementBuilder.builder(ArsElemental.MODID, key);
//        }
//
//    }
//
//}