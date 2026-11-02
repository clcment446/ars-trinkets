package com.c446.ars_trinkets.item.runes;

import com.c446.ars_trinkets.registry.ItemRegistry;
import com.google.common.collect.Multimap;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class EternityRune extends AbstractRune {
    public EternityRune(Properties pProperties, int level, ResourceLocation registeredName) {
        super(pProperties, level, registeredName);
    }

    @Override
    public void addDefaultModifiers(ResourceLocation id, Multimap<Holder<Attribute>, AttributeModifier> map) {
        // Built-in stats only (not the sub-runes' JSON overrides): Eternity has its own config entry.
        map.putAll(ItemRegistry.LIFE_RUNE_2.get().defaultModifiers(id));
        map.putAll(ItemRegistry.MAGE_RUNE_2.get().defaultModifiers(id));
        map.putAll(ItemRegistry.DEATH_RUNE_2.get().defaultModifiers(id));
        map.putAll(ItemRegistry.WARRIOR_RUNE_2.get().defaultModifiers(id));
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        super.onEquip(slotContext, prevStack, stack);

        if (slotContext.entity() instanceof Player p) {

        }
    }
}
