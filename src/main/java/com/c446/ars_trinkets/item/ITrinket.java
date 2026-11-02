package com.c446.ars_trinkets.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

/**
 * Every curio item of the mod implements this. Items only describe their built-in stats through
 * {@link #addDefaultModifiers}; {@link TrinketConfig} then applies the (auto-generated) JSON config on top,
 * so every trinket is configurable the same way.
 */
public interface ITrinket extends ICurioItem {

    /** Built-in stats of the trinket, ignoring the JSON config. */
    void addDefaultModifiers(ResourceLocation slotId, Multimap<Holder<Attribute>, AttributeModifier> map);

    /** Registry name of this trinket, used as the key in the JSON config. */
    default ResourceLocation trinketId() {
        return BuiltInRegistries.ITEM.getKey((Item) this);
    }

    default Multimap<Holder<Attribute>, AttributeModifier> defaultModifiers(ResourceLocation slotId) {
        Multimap<Holder<Attribute>, AttributeModifier> map = HashMultimap.create();
        addDefaultModifiers(slotId, map);
        return map;
    }

    @Override
    default Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        return TrinketConfig.resolve(this, id);
    }
}
