package com.c446.ars_trinkets.item;

import com.google.common.collect.Multimap;
import com.hollingsworth.arsnouveau.api.mana.IManaEquipment;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.HashMap;
import java.util.UUID;

public class GenericAttributeCurio extends RegularItems implements ICurioItem, IManaEquipment {
    private final HashMap<Attribute, ForgeConfigSpec.LongValue> configVal;
    private final HashMap<Attribute, AttributeModifier.Operation> operations;
    private final HashMap<Attribute, UUID> uuids;
    private final String name;


    public GenericAttributeCurio(Properties properties, HashMap<Attribute, ForgeConfigSpec.LongValue> stats, HashMap<Attribute, UUID> uuids, HashMap<Attribute, AttributeModifier.Operation> operations, String name) {
        super(properties);
        configVal = stats;
        this.operations = operations;
        this.uuids = uuids;
        this.name = name;
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        for (Attribute attributeKey : configVal.keySet().stream().toList()) {
            String modifier_name = attributeKey.toString() + "_" + name + "curio_modifier";
            UUID modifierUUID = uuids.get(attributeKey);
            Long value = configVal.get(attributeKey).get();
            AttributeModifier.Operation operation = operations.get(attributeKey);

            AttributeModifier modifier = new AttributeModifier(modifierUUID, modifier_name, value, operation);
            attributes.put(attributeKey, modifier);
        }
        return attributes;
    }
}


