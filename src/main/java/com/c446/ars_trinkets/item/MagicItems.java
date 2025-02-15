package com.c446.ars_trinkets.item;


import com.google.common.collect.Multimap;
import com.hollingsworth.arsnouveau.api.mana.IManaEquipment;
//import com.hollingsworth.arsnouveau.api.mana.
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.swing.text.DefaultStyledDocument;
import java.util.UUID;

public class MagicItems extends RegularItems implements ICurioItem, IManaEquipment {
    int boost;
    int regen;
    int damage_boost;
    int max_boost;
    public MagicItems(Properties properties, int boost, int regen, int max_boost, int damage_boost){
        super(properties);
        this.boost = boost;
        this.regen = regen;
        this.max_boost = max_boost;
        this.damage_boost =damage_boost;
    }

    @Override
    public int getManaRegenBonus(ItemStack stack) {
        return regen;
    }
    @Override
    public int getMaxManaBoost(ItemStack stack) {
        return boost;
    }
    public int getSpellDamageBoost(ItemStack stack) {return damage_boost;}
    public int getMaxManaBoostPct(ItemStack stack) {return max_boost;}
    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        attributes.put(PerkAttributes.FLAT_MANA_BONUS.get(), new AttributeModifier(uuid, "flat_max_mana_modifier_curio", this.getMaxManaBoost(stack), AttributeModifier.Operation.ADDITION));
        attributes.put(PerkAttributes.MANA_REGEN_BONUS.get(), new AttributeModifier(uuid, "mana_regen_modifier_curio", this.getManaRegenBonus(stack), AttributeModifier.Operation.ADDITION));
        attributes.put(com.c446.ars_trinkets.perks.PerkAttributes.SPELL_DAMAGE_PCT.get(), new AttributeModifier(uuid, "spell_damage_bonus_modifier_curio", (double) this.getSpellDamageBoost(stack)/100, AttributeModifier.Operation.ADDITION));
        attributes.put(PerkAttributes.MAX_MANA_BONUS.get(), new AttributeModifier(uuid, "max_mana_bonus_modifier_curio", this.getMaxManaBoostPct(stack),AttributeModifier.Operation.MULTIPLY_TOTAL));

        return attributes;
    }
}
