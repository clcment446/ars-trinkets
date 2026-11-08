package com.c446.ars_trinkets.item.runes;

import com.c446.ars_trinkets.registry.AttributeRegistry;
import com.google.common.collect.Multimap;
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class MageRune extends AbstractRune {
    public MageRune(Properties pProperties, int level, ResourceLocation registeredName) {
        super(pProperties, level, registeredName);
    }

    @Override
    public void addDefaultModifiers(ResourceLocation id, Multimap<Holder<Attribute>, AttributeModifier> map) {

        map.put(PerkAttributes.MAX_MANA, new AttributeModifier(id, getMult(), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        map.put(PerkAttributes.MANA_REGEN_BONUS, new AttributeModifier(id, getMult(), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        map.put(AttributeRegistry.SPELL_DAMAGE_ABSOLUTE, new AttributeModifier(id, getMult(), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }
}
