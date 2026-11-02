package com.c446.ars_trinkets.item.runes;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.*;

public class WarriorRune extends AbstractRune {
    public WarriorRune(Properties pProperties, int level, ResourceLocation registeredName) {
        super(pProperties, level, registeredName);
    }

    @Override
    public void addDefaultModifiers(ResourceLocation id, Multimap<Holder<Attribute>, AttributeModifier> map) {

        map.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(id, getMult(), ADD_MULTIPLIED_TOTAL));
        map.put(Attributes.ARMOR, new AttributeModifier(id, getMult(), ADD_MULTIPLIED_TOTAL));
        map.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(id, getMult(), ADD_MULTIPLIED_TOTAL));
    }
}
