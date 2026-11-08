package com.c446.ars_trinkets.item.runes;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL;

public class LifeRune extends AbstractRune {
    public LifeRune(Properties pProperties, int level, ResourceLocation registeredName) {
        super(pProperties, level, registeredName);
    }

    @Override
    public void addDefaultModifiers(ResourceLocation id, Multimap<Holder<Attribute>, AttributeModifier> map) {

        //TODO : add apoth bonus regen
        map.put(Attributes.MAX_HEALTH, new AttributeModifier(id, getMult(), ADD_MULTIPLIED_TOTAL));
        map.put(Attributes.MOVEMENT_EFFICIENCY, new AttributeModifier(id, getMult()/2f, ADD_MULTIPLIED_TOTAL));
    }
}
