package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.Config;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;

public class EssenceLotus extends Item implements ITrinket, IManaBonusItem {
    final double manaRegen;
    final double manaBonus;
    final int id;

    public EssenceLotus(Properties p, Double manaRegen, double manaBonus, int id) {
        super(p);
        this.manaRegen = manaRegen;
        this.manaBonus = manaBonus;
        this.id=id;
    }

    @Override
    public void addDefaultModifiers(ResourceLocation id, Multimap<Holder<Attribute>, AttributeModifier> map) {
        doManaOp(id, map);
    }

    public double getMult() {
        try {
            var values = Config.Common.LOTUS_VALUES.get();
            if (this.id >= 0 && this.id < values.size()) return values.get(this.id);
        } catch (IllegalStateException ignored) {
            // config not loaded yet
        }
        return 1d;
    }

    @Override
    public double getManaBonus() {
        return this.manaBonus * getMult();
    }

    @Override
    public double getManaRegen() {
        return this.manaRegen * getMult();
    }
}
