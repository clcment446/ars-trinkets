package com.c446.ars_trinkets.item.runes;

import com.c446.ars_trinkets.item.ITrinket;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;

/**
 * Base of all runes. Subclasses only describe their built-in stats in {@link #addDefaultModifiers};
 * the JSON config (see {@link com.c446.ars_trinkets.item.TrinketConfig}) is applied by {@link ITrinket}.
 */
public abstract class AbstractRune extends Item implements ITrinket {
    protected final int level;
    protected final ResourceLocation registeredName;

    public AbstractRune(Properties pProperties, int level, ResourceLocation registeredName) {
        super(pProperties);
        this.level = level;
        this.registeredName = registeredName;
    }

    @Override
    public ResourceLocation trinketId() {
        return registeredName;
    }

    @Override
    public void addDefaultModifiers(ResourceLocation id, Multimap<Holder<Attribute>, AttributeModifier> map) {
    }

    float getMult() {
        return ((float) (this.level * 0.5));
    }
}
