package com.c446.ars_trinkets.item;

import com.hollingsworth.arsnouveau.api.item.ArsNouveauCurio;
import com.hollingsworth.arsnouveau.api.item.ISpellModifierItem;
import com.hollingsworth.arsnouveau.api.spell.SpellStats;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

public class UnholyFocus extends ArsNouveauCurio implements ISpellModifierItem {
    public UnholyFocus(Item.Properties properties) {
        super(properties);
        this.withTooltip(Component.translatable("ars_trinkets.item.unholy_focus.tooltip"));
    }


}

