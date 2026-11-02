package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.Config;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class EssenceRing extends Item implements ITrinket, IManaBonusItem {
    final float manaBonus ;
    final float manaRegen;
    final int id;

    public EssenceRing(Item.Properties p, float manaBonus, float manaRegen, int id) {
        super(p);
        this.manaBonus = manaBonus;
        this.manaRegen = manaRegen;
        this.id = id;
    }

    @Override
    public void addDefaultModifiers(ResourceLocation id, Multimap<Holder<Attribute>, AttributeModifier> map) {
        doManaOp(id, map);
    }

    public double getMult() {
        try {
            var values = Config.Common.RING_VALUES.get();
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
