package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.perks.PerkAttributes;
import com.google.common.collect.Multimap;
import com.hollingsworth.arsnouveau.api.mana.IManaEquipment;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class AlchemistBelt extends RegularItems implements ICurioItem, IManaEquipment {
    private final double strength;
    private final double length;
    public AlchemistBelt(Properties properties, int length, int strenght){
        super(properties);
        this.strength = strenght;
        this.length = length;
    }
    public double getLength() {return this.length;}
    public double getStrength() {return this.strength;}
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        attributes.put(PerkAttributes.POTION_LENGTH.get(), new AttributeModifier("alchemist_belt", (double) this.getLength(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(PerkAttributes.POTION_STRENGTH.get(), new AttributeModifier("alchemist_belt", (double) this.getStrength(), AttributeModifier.Operation.MULTIPLY_TOTAL));

        return attributes;
    }
}
