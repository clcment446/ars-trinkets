package com.c446.ars_trinkets.item.runes;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL;

public class DeathRune extends AbstractRune {
    public DeathRune(Properties pProperties, int level, ResourceLocation registeredName) {
        super(pProperties, level, registeredName);
    }

    @Override
    public void addDefaultModifiers(ResourceLocation id, Multimap<Holder<Attribute>, AttributeModifier> map) {

        //TODO : add apoth life steal
        map.put(Attributes.ATTACK_SPEED, new AttributeModifier(id, getMult(), ADD_MULTIPLIED_TOTAL));
        map.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(id, getMult(), ADD_MULTIPLIED_TOTAL));
    }
}
