package com.c446.ars_trinkets.datagen;

import com.c446.ars_trinkets.ArsNouveauRegistry;
import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.glyphs.effect_glyph.*;

import com.c446.ars_trinkets.glyphs.forms.AuraForm;
import com.c446.ars_trinkets.glyphs.forms.FormMissile;
import com.c446.ars_trinkets.glyphs.forms.FormOverhead;
import com.c446.ars_trinkets.glyphs.propagators.PropagateMissile;
import com.c446.ars_trinkets.glyphs.propagators.PropagateOverhead;
import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.api.familiar.AbstractFamiliarHolder;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.spell.AbstractCastMethod;
import com.hollingsworth.arsnouveau.api.spell.AbstractEffect;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.common.crafting.recipes.GlyphRecipe;
import com.hollingsworth.arsnouveau.common.crafting.recipes.ImbuementRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.GlyphRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.ImbuementRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.*;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.function.Consumer;

import static com.c446.ars_trinkets.registry.ModRegistry.*;
import static com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry.MANIPULATION_ESSENCE;
import static com.hollingsworth.arsnouveau.setup.registry.RegistryHelper.getRegistryName;
import static net.minecraft.world.item.Items.*;

public class ArsProviders {

    static String root = ArsTrinkets.MOD_ID;

    public static class CraftingTableProvider extends RecipeProvider {
        public CraftingTableProvider(DataGenerator pGenerator) {
            super(pGenerator.getPackOutput());
        }

        public Item getRitualItem(ResourceLocation id) {
            return RitualRegistry.getRitualItemMap().get(id);
        }

        public ShapelessRecipeBuilder shapelessBuilder(ItemLike result) {
            return shapelessBuilder(result, 1);
        }

        public ShapelessRecipeBuilder shapelessBuilder(ItemLike result, int resultCount) {
            return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultCount).unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK));
        }

        @Override
        protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
//            shapelessBuilder(getRitualItem(new ResourceLocation(ArsNouveau.MODID,LevelingRitual.ID)))
//                    .requires(GOLD_ESSENCE.get(),2)
//                    .requires(NETHERITE_INGOT,2)
//                    .requires(NETHER_STAR)
//                    .save(consumer, prefix("tablet_"+LevelingRitual.ID))
//            ;
        }
    }


    public static class GlyphProvider extends GlyphRecipeProvider {

        public GlyphProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void collectJsons(CachedOutput cache) {

            Path output = this.generator.getPackOutput().getOutputFolder();
            recipes.add(get(FilterIsShifting.INSTANCE).withItem(MANIPULATION_ESSENCE).withItem(FilterIsSelf.INSTANCE.glyphItem));
            recipes.add(get(FilterIsShifting.INSTANCE).withItem(MANIPULATION_ESSENCE).withItem(FilterIsShifting.INSTANCE.glyphItem));
            recipes.add(get(AuraForm.INSTANCE).withItem(MANIPULATION_ESSENCE).withItem(Items.DIAMOND_BLOCK).withItem(Items.NETHERITE_BLOCK).withItem(Items.NETHER_STAR, 7));
            recipes.add(get(WaterSpear.INSTANCE).withItem(ItemsRegistry.WATER_ESSENCE, 4).withItem(PURPLE_ESSENCE.get(), 4).withItem(DIAMOND_BLOCK, 1));
            recipes.add(get(AirSword.INSTANCE).withItem(ItemsRegistry.AIR_ESSENCE, 4).withItem(PURPLE_ESSENCE.get(), 4).withItem(NETHERITE_BLOCK, 1));
            recipes.add(get(SacrificeHealth.INSTANCE).withItem(Items.DIAMOND_SWORD).withItem(RED_ESSENCE.get(), 4).withItem(NETHERITE_INGOT, 1));
            recipes.add(get(SacrificeExperience.INSTANCE).withItem(DIAMOND_BLOCK).withItem(WHITE_ESSENCE.get(), 3).withItem(ENCHANTED_BOOK, 3).withItem(EXPERIENCE_BOTTLE, 2));
            recipes.add(get(ShadowVeil.INSTANCE).withItem(NETHERITE_INGOT).withItem(WHITE_ESSENCE.get(), 4).withItem(SCULK, 2));
            recipes.add(get(SonicBoom.INSTANCE).withItem(DIAMOND_BLOCK).withItem(ItemsRegistry.AIR_ESSENCE, 2).withItem(SCULK, 1).withItem(PURPLE_ESSENCE.get(), 2).withItem(TNT, 2));
            recipes.add(get(SunFlare.INSTANCE).withItem(NETHERITE_INGOT, 2).withItem(ItemsRegistry.FIRE_ESSENCE).withItem(GLOWSTONE.asItem()).withItem(PURPLE_ESSENCE.get(), 4));
            recipes.add(get(FilterIsNotSelf.INSTANCE).withItem(FilterIsSelf.INSTANCE.glyphItem.asItem()).withItem(MANIPULATION_ESSENCE, 4));
            recipes.add(get(FilterIsSelf.INSTANCE).withItem(GOLDEN_CARROT).withItem(MANIPULATION_ESSENCE, 4));
            recipes.add(get(ManaBomb.INSTANCE).withItem(ItemsRegistry.CONJURATION_ESSENCE, 3).withItem(MANIPULATION_ESSENCE, 2).withItem(PURPLE_ESSENCE.get(), 3));
            recipes.add(get(Devour.INSTANCE).withItem(NETHER_STAR, 3).withItem(ROTTEN_FLESH, 2).withItem(SacrificeHealth.INSTANCE.glyphItem.asItem()));
            recipes.add(get(DevourSoul.INSTANCE).withItem(NETHER_STAR, 3).withItem(WITHER_SKELETON_SKULL, 2).withItem(SacrificeExperience.INSTANCE.glyphItem.asItem()));
            recipes.add(get(InspectSoul.INSTANCE).withItem(NETHER_STAR).withItem(WITHER_SKELETON_SKULL, 1).withItem(SKELETON_SKULL, 1).withItem(DRAGON_HEAD, 1).withItem(ZOMBIE_HEAD, 1).withItem(MANA_CORE_1));

            recipes.add(get(FormMissile.INSTANCE).withItem(FIRE_CHARGE).withItem(ItemsRegistry.FIRE_ESSENCE, 2).withItem(ItemsRegistry.AIR_ESSENCE).withItem(GUNPOWDER, 2).withItem(ARROW));
            recipes.add(get(FormOverhead.INSTANCE).withItem(IRON_HELMET).withItem(STONE_PRESSURE_PLATE));
            recipes.add(get(PropagateOverhead.INSTANCE).withItem(MANIPULATION_ESSENCE).withItem(FormOverhead.INSTANCE.getGlyph().asItem()));
            recipes.add(get(PropagateMissile.INSTANCE).withItem(MANIPULATION_ESSENCE).withItem(FormMissile.INSTANCE.getGlyph().asItem()));


            for (GlyphRecipe recipe : recipes) {
                Path path = getScribeGlyphPath(output, recipe.output.getItem());
                saveStable(cache, recipe.asRecipe(), path);
            }

        }

        protected static Path getScribeGlyphPath(Path pathIn, Item glyph) {
            return pathIn.resolve("data/" + root + "/recipes/" + getRegistryName(glyph).getPath() + ".json");
        }

        @Override
        public String getName() {
            return "Example Glyph Recipes";
        }
    }

    public static class EnchantingAppProvider extends ApparatusRecipeProvider {

        public EnchantingAppProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void collectJsons(CachedOutput cache) {
            recipes.add(builder()
                            .withPedestalItem(MANA_CORE_1)
                            .withPedestalItem(ENCHANTED_GOLDEN_APPLE)
                    .withResult(HOLY_HEART).build()
            );
            recipes.add(builder()
                    .withPedestalItem(MANA_CORE_1)
                    .withPedestalItem(WITHER_SKELETON_SKULL)
                    .withResult(PUTRID_HEART).build()
            );
            recipes.add(builder()
                    .withReagent(NETHER_STAR)
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withResult(MANA_CORE_1)
                    .build());
            recipes.add(builder()
                    .withReagent(MANA_CORE_1)
                    .withPedestalItem(PURPLE_ESSENCE.get())
                    .withPedestalItem(PURPLE_ESSENCE.get())
                    .withPedestalItem(PURPLE_ESSENCE.get())
                    .withPedestalItem(PURPLE_ESSENCE.get())
                    .withResult(MANA_CORE_2)
                    .build());
            recipes.add(builder()
                    .withReagent(MANA_CORE_2)
                    .withPedestalItem(MANA_CORE_1)
                    .withPedestalItem(MANA_CORE_1)
                    .withResult(MANA_CORE_3)
                    .build());
            // # TRINKETS RECIPES
            recipes.add(builder()
                    .withSourceCost(250)
                    .withPedestalItem(4, COPPER_ESSENCE)
                    .withReagent(GLASS_PANE)
                    .withResult(MONOCLE_1)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(500)
                    .withPedestalItem(4, IRON_ESSENCE)
                    .withReagent(MONOCLE_1)
                    .withResult(MONOCLE_2)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(1000)
                    .withPedestalItem(4, SILVER_ESSENCE)
                    .withReagent(MONOCLE_2)
                    .withResult(MONOCLE_3)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(1500)
                    .withPedestalItem(4, GOLD_ESSENCE)
                    .withReagent(MONOCLE_3)
                    .withResult(MONOCLE_4)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(2000)
                    .withPedestalItem(4, CRYSTAL_ESSENCE)
                    .withReagent(MONOCLE_4)
                    .withResult(MONOCLE_5)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(2500)
                    .withPedestalItem(4, GREEN_ESSENCE)
                    .withReagent(MONOCLE_5)
                    .withResult(MONOCLE_6)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(4000)
                    .withPedestalItem(4, RED_ESSENCE)
                    .withReagent(MONOCLE_6)
                    .withResult(MONOCLE_7)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(6000)
                    .withPedestalItem(4, WHITE_ESSENCE)
                    .withReagent(MONOCLE_7)
                    .withResult(MONOCLE_8)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(8000)
                    .withPedestalItem(4, YELLOW_ESSENCE)
                    .withReagent(MONOCLE_8)
                    .withResult(MONOCLE_9)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withPedestalItem(4, PURPLE_ESSENCE)
                    .withReagent(MONOCLE_9)
                    .withResult(MONOCLE_10)
                    .build()
            );

            recipes.add(builder()
                    .withSourceCost(1000)
                    .withPedestalItem(4, SILVER_ESSENCE)
                    .withReagent(ItemsRegistry.AMULET_OF_MANA_REGEN)
                    .withResult(ESSENCE_LOTUS_3)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(1500)
                    .withPedestalItem(4, GOLD_ESSENCE)
                    .withReagent(ESSENCE_LOTUS_3)
                    .withResult(ESSENCE_LOTUS_4)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(2000)
                    .withPedestalItem(4, CRYSTAL_ESSENCE)
                    .withReagent(ESSENCE_LOTUS_4)
                    .withResult(ESSENCE_LOTUS_5)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(2500)
                    .withPedestalItem(4, GREEN_ESSENCE)
                    .withReagent(ESSENCE_LOTUS_5)
                    .withResult(ESSENCE_LOTUS_6)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(4000)
                    .withPedestalItem(4, RED_ESSENCE)
                    .withReagent(ESSENCE_LOTUS_6)
                    .withResult(ESSENCE_LOTUS_7)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(6000)
                    .withPedestalItem(4, WHITE_ESSENCE)
                    .withReagent(ESSENCE_LOTUS_7)
                    .withResult(ESSENCE_LOTUS_8)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(8000)
                    .withPedestalItem(4, YELLOW_ESSENCE)
                    .withReagent(ESSENCE_LOTUS_8)
                    .withResult(ESSENCE_LOTUS_9)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withPedestalItem(4, ESSENCE_LOTUS_9)
                    .withPedestalItem(4, PURPLE_ESSENCE)
                    .withReagent(ESSENCE_LOTUS_9)
                    .withResult(ESSENCE_LOTUS_10)
                    .build()
            );
//            recipes.add(builder()
//                    .withSourceCost(16000)
//                    .withPedestalItem(8, YELLOW_ESSENCE)
//                    .withResult(BlockRegistry.CREATIVE_SOURCE_JAR.asItem())
//                    .build()
//            );
            recipes.add(builder()
                    .withSourceCost(1000)
                    .withPedestalItem(4, SILVER_ESSENCE)
                    .withReagent(ItemsRegistry.RING_OF_POTENTIAL)
                    .withResult(MANA_RING_3)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(1500)
                    .withPedestalItem(4, GOLD_ESSENCE)
                    .withReagent(MANA_RING_3)
                    .withResult(MANA_RING_4)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(2000)
                    .withPedestalItem(4, CRYSTAL_ESSENCE)
                    .withReagent(MANA_RING_4)
                    .withResult(MANA_RING_5)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(2500)
                    .withPedestalItem(4, GREEN_ESSENCE)
                    .withReagent(MANA_RING_5)
                    .withResult(MANA_RING_6)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(4000)
                    .withPedestalItem(4, RED_ESSENCE)
                    .withReagent(MANA_RING_6)
                    .withResult(MANA_RING_7)
                    .build()
            );
            recipes.add(builder()

                    .withSourceCost(6000)
                    .withPedestalItem(4, WHITE_ESSENCE)
                    .withReagent(MANA_RING_7)
                    .withResult(MANA_RING_8)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(8000)
                    .withPedestalItem(4, YELLOW_ESSENCE)
                    .withPedestalItem(2, MANA_RING_8)
                    .withReagent(MANA_RING_8)
                    .withResult(MANA_RING_9)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withPedestalItem(4, PURPLE_ESSENCE)
                    .withPedestalItem(4, MANA_RING_9)
                    .withReagent(MANA_RING_9)
                    .withResult(MANA_RING_10)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_1)
                    .withPedestalItem(2, PURPLE_ESSENCE)
                    .withPedestalItem(SacrificeHealth.INSTANCE.glyphItem)
                    .withPedestalItem(ENCHANTED_GOLDEN_APPLE)
                    .withResult(HOLY_RUNE_LESSER)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_1)
                    .withPedestalItem(2, PURPLE_ESSENCE)
                    .withPedestalItem(InspectSoul.INSTANCE.glyphItem)
                    .withPedestalItem(DevourSoul.INSTANCE.glyphItem)
                    .withResult(SOUL_STEALER_RUNE_LESSER)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_1)
                    .withPedestalItem(2, PURPLE_ESSENCE)
                    .withPedestalItem(SunFlare.INSTANCE.glyphItem)
                    .withPedestalItem(AirSword.INSTANCE.glyphItem)
                    .withResult(WARRIOR_RUNE_LESSER)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_1)
                    .withPedestalItem(2, PURPLE_ESSENCE)
                    .withPedestalItem(SacrificeExperience.INSTANCE.glyphItem)
                    .withPedestalItem(MONOCLE_10)
                    .withPedestalItem(MANA_RING_10)
                    .withPedestalItem(ESSENCE_LOTUS_10)
                    .withResult(MAGE_RUNE_LESSER)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_2)
                    .withPedestalItem(4, HOLY_RUNE_LESSER)
                    .withResult(HOLY_RUNE)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_2)
                    .withPedestalItem(4, SOUL_STEALER_RUNE_LESSER)
                    .withResult(SOUL_STEALER_RUNE)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_2)
                    .withPedestalItem(4, WARRIOR_RUNE_LESSER)
                    .withResult(WARRIOR_RUNE)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_3)
                    .withPedestalItem(4, MAGE_RUNE_LESSER)
                    .withResult(MAGE_RUNE)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_3)
                    .withPedestalItem(4, HOLY_RUNE)
                    .withResult(HOLY_RUNE_GREATER)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_3)
                    .withPedestalItem(4, SOUL_STEALER_RUNE)
                    .withResult(SOUL_STEALER_RUNE_GREATER)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_3)
                    .withPedestalItem(4, WARRIOR_RUNE)
                    .withResult(WARRIOR_RUNE_GREATER)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withReagent(MANA_CORE_3)
                    .withPedestalItem(4, MAGE_RUNE)
                    .withResult(MAGE_RUNE_GREATER)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(0)
                            .withReagent(CRYING_OBSIDIAN)
                            .withPedestalItem(MANA_CORE_3)
                            .withPedestalItem(MANA_CORE_3)
                            .withPedestalItem(MANA_CORE_3)
                            .withPedestalItem(MANA_CORE_3)
                            .withPedestalItem(MAGE_RUNE_GREATER)
                            .withPedestalItem(HOLY_RUNE_GREATER)
                            .withPedestalItem(WARRIOR_RUNE_GREATER)
                            .withPedestalItem(SOUL_STEALER_RUNE_GREATER)
                            .withResult(ETERNAL_RUNE)
                    .build()
            );

            Path output = this.generator.getPackOutput().getOutputFolder();
            for (EnchantingApparatusRecipe g : recipes) {
                if (g != null) {
                    Path path = getRecipePath(output, g.getId().getPath());
                    saveStable(cache, g.asRecipe(), path);
                }
            }
        }

        protected static Path getRecipePath(Path pathIn, String str) {
            return pathIn.resolve("data/" + root + "/recipes/" + str + ".json");
        }

        @Override
        public String getName() {
            return "Example Apparatus";
        }
    }

    public static class ImbuementProvider extends ImbuementRecipeProvider {

        public ImbuementProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void collectJsons(CachedOutput cache) {
            System.out.println("started Imbument>collect jsons");

            recipes.add(new ImbuementRecipe("gold_to_copper", Ingredient.of(GOLD_BLOCK), new ItemStack(COPPER_BLOCK), 10000)
                    .withPedestalItem(COPPER_ESSENCE)
                    .withPedestalItem(COPPER_ESSENCE)
                    .withPedestalItem(GOLD_ESSENCE)
                    .withPedestalItem(GOLD_ESSENCE)
                    .withPedestalItem(MANIPULATION_ESSENCE)
            );

            recipes.add(new ImbuementRecipe("essence_copper", Ingredient.of(COPPER_BLOCK), new ItemStack(COPPER_ESSENCE.get()), 10)
                    .withPedestalItem(COPPER_INGOT)
                    .withPedestalItem(COPPER_INGOT)
            );
            recipes.add(new ImbuementRecipe("essence_iron", Ingredient.of(COPPER_ESSENCE.get()), new ItemStack(IRON_ESSENCE.get()), 20)
                    .withPedestalItem(IRON_INGOT)
                    .withPedestalItem(IRON_INGOT)

            );
            recipes.add(new ImbuementRecipe("essence_silver", Ingredient.of(IRON_ESSENCE.get()), new ItemStack(SILVER_ESSENCE.get()), 40)
                    .withPedestalItem(IRON_ESSENCE.get())
                    .withPedestalItem(IRON_ESSENCE.get())
                    .withPedestalItem(IRON_ESSENCE.get())
                    .withPedestalItem(IRON_ESSENCE.get())
            );
            recipes.add(new ImbuementRecipe("essence_gold", Ingredient.of(SILVER_ESSENCE.get()), new ItemStack(GOLD_ESSENCE.get()), 80)
                    .withPedestalItem(SILVER_ESSENCE.get())
                    .withPedestalItem(SILVER_ESSENCE.get())
                    .withPedestalItem(SILVER_ESSENCE.get())
                    .withPedestalItem(SILVER_ESSENCE.get())
                    .withPedestalItem(GOLD_INGOT)
                    .withPedestalItem(GOLD_INGOT)
            );
            recipes.add(new ImbuementRecipe("essence_crystal", Ingredient.of(GOLD_ESSENCE.get()), new ItemStack(CRYSTAL_ESSENCE.get()), 160)
                    .withPedestalItem(GOLD_ESSENCE.get())
                    .withPedestalItem(GOLD_ESSENCE.get())
                    .withPedestalItem(GOLD_ESSENCE.get())
                    .withPedestalItem(GOLD_ESSENCE.get())
                    .withPedestalItem(GOLD_ESSENCE.get())
                    .withPedestalItem(GOLD_ESSENCE.get())
                    .withPedestalItem(AMETHYST_BLOCK)
                    .withPedestalItem(AMETHYST_BLOCK)
            );
            recipes.add(new ImbuementRecipe("essence_green", Ingredient.of(CRYSTAL_ESSENCE.get()), new ItemStack(GREEN_ESSENCE.get()), 150)
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
            );
            recipes.add(new ImbuementRecipe("essence_red", Ingredient.of(GREEN_ESSENCE.get()), new ItemStack(RED_ESSENCE.get()), 300)
                    .withPedestalItem(GREEN_ESSENCE.get())
                    .withPedestalItem(GREEN_ESSENCE.get())
                    .withPedestalItem(GREEN_ESSENCE.get())
            );
            recipes.add(new ImbuementRecipe("essence_white", Ingredient.of(RED_ESSENCE.get()), new ItemStack(WHITE_ESSENCE.get()), 900)
                    .withPedestalItem(RED_ESSENCE.get())
                    .withPedestalItem(RED_ESSENCE.get())
                    .withPedestalItem(RED_ESSENCE.get())
                    .withPedestalItem(RED_ESSENCE.get())
            );
            recipes.add(new ImbuementRecipe("essence_yellow", Ingredient.of(WHITE_ESSENCE.get()), new ItemStack(YELLOW_ESSENCE.get()), 900)
                    .withPedestalItem(WHITE_ESSENCE.get())
                    .withPedestalItem(WHITE_ESSENCE.get())
                    .withPedestalItem(WHITE_ESSENCE.get())
                    .withPedestalItem(WHITE_ESSENCE.get())
                    .withPedestalItem(WHITE_ESSENCE.get())
                    .withPedestalItem(WHITE_ESSENCE.get())
            );
            recipes.add(new ImbuementRecipe("essence_purple", Ingredient.of(YELLOW_ESSENCE.get()), new ItemStack(PURPLE_ESSENCE.get()), 1500)
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
            );

            Path output = generator.getPackOutput().getOutputFolder();
            for (ImbuementRecipe g : recipes) {
                Path path = getRecipePath(output, g.getId().getPath());
                saveStable(cache, g.asRecipe(), path);
            }

        }

        protected Path getRecipePath(Path pathIn, String str) {
            return pathIn.resolve("data/" + root + "/recipes/" + str + ".json");
        }

        @Override
        public String getName() {
            return "Example Imbuement";
        }

    }

    public static class PatchouliProvider extends com.hollingsworth.arsnouveau.common.datagen.PatchouliProvider {

        public PatchouliProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void collectJsons(CachedOutput cache) {

            for (AbstractSpellPart spell : ArsNouveauRegistry.registeredSpells) {
                addGlyphPage(spell);
            }

            //check the superclass for examples

            for (PatchouliPage patchouliPage : pages) {
                DataProvider.saveStable(cache, patchouliPage.build(), patchouliPage.path());
            }

        }

        @Override
        public PatchouliPage addBasicItem(ItemLike item, ResourceLocation category, IPatchouliPage recipePage) {
            PatchouliBuilder builder = new PatchouliBuilder(category, item.asItem().getDescriptionId())
                    .withIcon(item.asItem())
                    .withPage(new TextPage(root + ".page." + getRegistryName(item.asItem()).getPath()))
                    .withPage(recipePage);
            var page = new PatchouliPage(builder, getPath(category, getRegistryName(item.asItem()).getPath()));
            this.pages.add(page);
            return page;
        }

        public void addFamiliarPage(AbstractFamiliarHolder familiarHolder) {
            PatchouliBuilder builder = new PatchouliBuilder(FAMILIARS, "entity." + root + "." + familiarHolder.getRegistryName().getPath())
                    .withIcon(root + ":" + familiarHolder.getRegistryName().getPath())
                    .withTextPage(root + ".familiar_desc." + familiarHolder.getRegistryName().getPath())
                    .withPage(new EntityPage(familiarHolder.getRegistryName().toString()));
            this.pages.add(new PatchouliPage(builder, getPath(FAMILIARS, familiarHolder.getRegistryName().getPath())));
        }

        public void addRitualPage(AbstractRitual ritual) {
            PatchouliBuilder builder = new PatchouliBuilder(RITUALS, "item." + root + '.' + ritual.getRegistryName().getPath())
                    .withIcon(ritual.getRegistryName().toString())
                    .withTextPage(ritual.getDescriptionKey())
                    .withPage(new CraftingPage(root + ":tablet_" + ritual.getRegistryName().getPath()));

            this.pages.add(new PatchouliPage(builder, getPath(RITUALS, ritual.getRegistryName().getPath())));
        }

        public void addEnchantmentPage(Enchantment enchantment) {
            PatchouliBuilder builder = new PatchouliBuilder(ENCHANTMENTS, enchantment.getDescriptionId())
                    .withIcon(getRegistryName(Items.ENCHANTED_BOOK).toString())
                    .withTextPage(root + ".enchantment_desc." + getRegistryName(enchantment).getPath());

            for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); i++) {
                builder.withPage(new EnchantingPage("ars_nouveau:" + getRegistryName(enchantment).getPath() + "_" + i));
            }
            this.pages.add(new PatchouliPage(builder, getPath(ENCHANTMENTS, getRegistryName(enchantment).getPath())));
        }

        public void addGlyphPage(AbstractSpellPart spellPart) {
            ResourceLocation category = switch (spellPart.defaultTier().value) {
                case 1 -> GLYPHS_1;
                case 2 -> GLYPHS_2;
                default -> GLYPHS_3;
            };
            PatchouliBuilder builder = new PatchouliBuilder(category, spellPart.getName())
                    .withName(root + ".glyph_name." + spellPart.getRegistryName().getPath())
                    .withIcon(spellPart.getRegistryName().toString())
                    .withSortNum(spellPart instanceof AbstractCastMethod ? 1 : spellPart instanceof AbstractEffect ? 2 : 3)
                    .withPage(new TextPage(root + ".glyph_desc." + spellPart.getRegistryName().getPath()))
                    .withPage(new GlyphScribePage(spellPart));
            this.pages.add(new PatchouliPage(builder, getPath(category, spellPart.getRegistryName().getPath())));
        }

        /**
         * Gets a name for this provider, to use in logging.
         */
        @Override
        public String getName() {
            return "Example Patchouli Datagen";
        }

        @Override
        public Path getPath(ResourceLocation category, String fileName) {
            return this.generator.getPackOutput().getOutputFolder().resolve("data/" + root + "/patchouli_books/example/en_us/entries/" + category.getPath() + "/" + fileName + ".json");
        }

        ImbuementPage ImbuementPage(ItemLike item) {
            return new ImbuementPage(root + ":imbuement_" + getRegistryName(item.asItem()).getPath());
        }

    }

}
