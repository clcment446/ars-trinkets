package com.c446.ars_trinkets.client;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.changelog.Changelog;
import com.c446.ars_trinkets.registry.ItemRegistry;
import com.hollingsworth.arsnouveau.api.documentation.DocCategory;
import com.hollingsworth.arsnouveau.api.documentation.ReloadDocumentationEvent;
import com.hollingsworth.arsnouveau.api.documentation.builder.DocEntryBuilder;
import com.hollingsworth.arsnouveau.api.registry.DocumentationRegistry;
import com.hollingsworth.arsnouveau.setup.registry.Documentation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

/**
 * Adds an "Ars Trinkets Changelog" section to Ars Nouveau's native in-game book (not Patchouli).
 * Ars fires AddEntries on every world join / reload, so this must stay idempotent.
 */
@EventBusSubscriber(modid = ArsTrinkets.MODID, value = Dist.CLIENT)
public final class ArsTrinketsDocs {
    private ArsTrinketsDocs() {
    }

    @SubscribeEvent
    public static void onAddEntries(ReloadDocumentationEvent.AddEntries event) {
        // Section title comes from the lang key "ars_trinkets.section.changelog".
        DocCategory category = new DocCategory(ArsTrinkets.prefix("changelog"), new ItemStack(ItemRegistry.Essence10.get()), 1100);
        DocumentationRegistry.registerMainCategory(category);

        int sort = 1;
        for (Changelog.Release release : Changelog.RELEASES) {
            MutableComponent body = Component.empty();
            for (int i = 1; i <= release.lines(); i++) {
                if (i > 1) body.append("\n\n");
                body.append(Component.literal("- ")).append(Component.translatable(release.lineKey(i)));
            }

            // Title key: ars_trinkets.page.changelog_<version>
            Documentation.addPage(new DocEntryBuilder(ArsTrinkets.MODID, category, release.entryName())
                    .withIcon(ItemRegistry.Essence10.get())
                    .withSortNum(sort++)
                    .withHeaderPage(body, Component.translatable("ars_trinkets.page." + release.entryName())));
        }
    }
}
