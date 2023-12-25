package com.c446.ars_trinkets.datagen;

import com.c446.ars_trinkets.ArsNouveauRegistry;
import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.glyphs.spell_glyphs.*;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.dkmk100.arsomega.util.RegistryHandler;
import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.api.familiar.AbstractFamiliarHolder;
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
import com.hollingsworth.arsnouveau.setup.BlockRegistry;
import com.hollingsworth.arsnouveau.setup.ItemsRegistry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;

import java.io.IOException;
import java.nio.file.Path;

import static com.c446.ars_trinkets.registry.ModRegistry.*;
import static com.hollingsworth.arsnouveau.api.RegistryHelper.getRegistryName;
import static net.minecraft.world.item.Items.*;

public class ArsProviders {

    static String root = ArsTrinkets.MODID;

    public static class GlyphProvider extends GlyphRecipeProvider {

        public GlyphProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void run(CachedOutput cache) throws IOException {

            Path output = this.generator.getOutputFolder();

            recipes.add(get(WaterSpear.INSTANCE).withItem(ItemsRegistry.WATER_ESSENCE, 4).withItem(PURPLE_ESSENCE.get(), 4).withItem(RegistryHandler.ENCHANTED_DIAMOND_BLOCK_ITEM.get(), 1));
            recipes.add(get(AirSword.INSTANCE).withItem(ItemsRegistry.AIR_ESSENCE, 4).withItem(PURPLE_ESSENCE.get(), 4).withItem(RegistryHandler.ENCHANTED_DIAMOND_BLOCK_ITEM.get(), 1));
            recipes.add(get(SacrificeHealth.INSTANCE).withItem(Items.DIAMOND_SWORD).withItem(RED_ESSENCE.get(), 4).withItem(NETHERITE_INGOT, 1));
            recipes.add(get(SacrificeExperience.INSTANCE).withItem(RegistryHandler.ENCHANTED_DEMONIC_GEM.get()).withItem(WHITE_ESSENCE.get(), 3).withItem(RegistryHandler.ENCHANTED_DIAMOND_BLOCK_ITEM.get(), 2).withItem(ENCHANTED_BOOK).withItem(EXPERIENCE_BOTTLE));
            recipes.add(get(ShadowVeil.INSTANCE).withItem(RegistryHandler.ENCHANTED_DEMONIC_GEM.get()).withItem(WHITE_ESSENCE.get(),4).withItem(SCULK_CATALYST,2));
            recipes.add(get(SonicBoom.INSTANCE).withItem(RegistryHandler.ENCHANTED_DIAMOND_BLOCK_ITEM.get()).withItem(ItemsRegistry.AIR_ESSENCE,4).withItem(SCULK_CATALYST,2).withItem(PURPLE_ESSENCE.get(),4));
            for (GlyphRecipe recipe : recipes) {
                Path path = getScribeGlyphPath(output, recipe.output.getItem());
                DataProvider.saveStable(cache, recipe.asRecipe(), path);
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
        public void run(CachedOutput cache) throws IOException {
            // # TRINKETS RECIPES
            recipes.add(builder()
                    .withSourceCost(1000)
                    .withPedestalItem(4, SILVER_ESSENCE)
                    .withReagent(RegistryHandler.ARCANE_REGEN_AMULET)
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
                    .withSourceCost(10000)
                    .withPedestalItem(4, YELLOW_ESSENCE)
                    .withReagent(ESSENCE_LOTUS_8)
                    .withResult(ESSENCE_LOTUS_9)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(15000)
                    .withPedestalItem(4, ESSENCE_LOTUS_9)
                    .withPedestalItem(4, PURPLE_ESSENCE)
                    .withReagent(ESSENCE_LOTUS_9)
                    .withResult(ESSENCE_LOTUS_10)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(20000)
                    .withPedestalItem(8, YELLOW_ESSENCE)
                    .withResult(BlockRegistry.CREATIVE_SOURCE_JAR)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(1000)
                    .withPedestalItem(4, SILVER_ESSENCE)
                    .withReagent(RegistryHandler.RING_BOOST)
                    .withResult(MANA_CONDENSOR_3)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(1500)
                    .withPedestalItem(4, GOLD_ESSENCE)
                    .withReagent(MANA_CONDENSOR_3)
                    .withResult(MANA_CONDENSOR_4)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(2000)
                    .withPedestalItem(4, CRYSTAL_ESSENCE)
                    .withReagent(MANA_CONDENSOR_4)
                    .withResult(MANA_CONDENSOR_5)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(2500)
                    .withPedestalItem(4, GREEN_ESSENCE)
                    .withReagent(MANA_CONDENSOR_5)
                    .withResult(MANA_CONDENSOR_6)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(4000)
                    .withPedestalItem(4, RED_ESSENCE)
                    .withReagent(MANA_CONDENSOR_6)
                    .withResult(MANA_CONDENSOR_7)
                    .build()
            );
            recipes.add(builder()

                    .withSourceCost(6000)
                    .withPedestalItem(4, WHITE_ESSENCE)
                    .withReagent(MANA_CONDENSOR_7)
                    .withResult(MANA_CONDENSOR_8)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(10000)
                    .withPedestalItem(4, YELLOW_ESSENCE)
                    .withPedestalItem(2, MANA_CONDENSOR_8)
                    .withReagent(MANA_CONDENSOR_8)
                    .withResult(MANA_CONDENSOR_9)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(15000)
                    .withPedestalItem(4, PURPLE_ESSENCE)
                    .withPedestalItem(4, MANA_CONDENSOR_9)
                    .withReagent(MANA_CONDENSOR_9)
                    .withResult(MANA_CONDENSOR_10)
                    .build()
            );
            recipes.add(builder()
                    .withSourceCost(20000)
                    .withPedestalItem(4, PURPLE_ESSENCE.get())
                    .withPedestalItem(BlockRegistry.SOURCE_JAR.asItem())
                    .withReagent(RegistryHandler.ARCANE_APPLE_ITEM.get())
                    .withResult(BlockRegistry.CREATIVE_SOURCE_JAR.asItem())
                    .build()
            );
            /*
            * recipes.add(new ImbuementRecipe("mana_jar_inf", Ingredient.of(), new ItemStack(10000)
                    .
                    .withPedestalItem(PURPLE_ESSENCE.get())
                    .withPedestalItem(PURPLE_ESSENCE.get())
                    .withPedestalItem(PURPLE_ESSENCE.get())

                    .withPedestalItem(BlockRegistry.SOURCE_JAR.asItem())
                    .withPedestalItem(BlockRegistry.SOURCE_JAR.asItem())
                    .withPedestalItem(BlockRegistry.SOURCE_JAR.asItem())
            );
            *
            *
            * */

            Path output = this.generator.getOutputFolder();
            for (EnchantingApparatusRecipe g : recipes) {
                if (g != null) {
                    Path path = getRecipePath(output, g.getId().getPath());
                    DataProvider.saveStable(cache, g.asRecipe(), path);
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
        public void run(CachedOutput cache) throws IOException {
            recipes.add(new ImbuementRecipe("essence_silver", Ingredient.of(Items.IRON_BLOCK), new ItemStack(SILVER_ESSENCE.get()), 10)
                    .withPedestalItem(Items.IRON_INGOT)
                    .withPedestalItem(Items.IRON_INGOT)
            );
            recipes.add(new ImbuementRecipe("essence_gold", Ingredient.of(Items.GOLD_BLOCK), new ItemStack(GOLD_ESSENCE.get()), 20)
                    .withPedestalItem(SILVER_ESSENCE.get())
                    .withPedestalItem(SILVER_ESSENCE.get())
                    .withPedestalItem(SILVER_ESSENCE.get())
                    .withPedestalItem(SILVER_ESSENCE.get())
            );
            recipes.add(new ImbuementRecipe("essence_crystal", Ingredient.of(RegistryHandler.INFUSED_DIAMOND.get()), new ItemStack(CRYSTAL_ESSENCE.get()), 40)
                    .withPedestalItem(GOLD_ESSENCE.get())
                    .withPedestalItem(GOLD_ESSENCE.get())
                    .withPedestalItem(GOLD_ESSENCE.get())
                    .withPedestalItem(GOLD_ESSENCE.get())
                    .withPedestalItem(GOLD_ESSENCE.get())
                    .withPedestalItem(GOLD_ESSENCE.get())
            );
            recipes.add(new ImbuementRecipe("essence_green", Ingredient.of(RegistryHandler.INFUSED_DIAMOND_BLOCK_ITEM.get()), new ItemStack(GREEN_ESSENCE.get()), 100)
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
                    .withPedestalItem(CRYSTAL_ESSENCE.get())
            );
            recipes.add(new ImbuementRecipe("essence_red", Ingredient.of(RegistryHandler.ENCHANTED_DIAMOND.get()), new ItemStack(RED_ESSENCE.get()), 300)
                    .withPedestalItem(GREEN_ESSENCE.get())
                    .withPedestalItem(GREEN_ESSENCE.get())
            );
            recipes.add(new ImbuementRecipe("essence_white", Ingredient.of(RegistryHandler.ENCHANTED_DIAMOND_BLOCK_ITEM.get()), new ItemStack(WHITE_ESSENCE.get()), 900)
                    .withPedestalItem(RED_ESSENCE.get())
                    .withPedestalItem(RED_ESSENCE.get())
                    .withPedestalItem(RED_ESSENCE.get())
                    .withPedestalItem(RED_ESSENCE.get())
            );
            recipes.add(new ImbuementRecipe("essence_yellow", Ingredient.of(RegistryHandler.ARCANE_DIAMOND.get()), new ItemStack(YELLOW_ESSENCE.get()), 900)
                    .withPedestalItem(WHITE_ESSENCE.get())
                    .withPedestalItem(WHITE_ESSENCE.get())
                    .withPedestalItem(WHITE_ESSENCE.get())
                    .withPedestalItem(WHITE_ESSENCE.get())
                    .withPedestalItem(WHITE_ESSENCE.get())
                    .withPedestalItem(WHITE_ESSENCE.get())
            );
            recipes.add(new ImbuementRecipe("essence_purple", Ingredient.of(RegistryHandler.ARCANE_DIAMOND_BLOCK_ITEM.get()), new ItemStack(PURPLE_ESSENCE.get()), 1500)
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
                    .withPedestalItem(YELLOW_ESSENCE.get())
            );

            Path output = generator.getOutputFolder();
            for (ImbuementRecipe g : recipes) {
                Path path = getRecipePath(output, g.getId().getPath());
                DataProvider.saveStable(cache, g.asRecipe(), path);
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
        public void run(CachedOutput cache) throws IOException {

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
            return this.generator.getOutputFolder().resolve("data/" + root + "/patchouli_books/example/en_us/entries/" + category.getPath() + "/" + fileName + ".json");
        }

        ImbuementPage ImbuementPage(ItemLike item) {
            return new ImbuementPage(root + ":imbuement_" + getRegistryName(item.asItem()).getPath());
        }

    }

}
