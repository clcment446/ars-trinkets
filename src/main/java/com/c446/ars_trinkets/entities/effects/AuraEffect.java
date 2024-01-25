package com.c446.ars_trinkets.entities.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AuraEffect extends MobEffect {
    public AuraEffect() {
        super(MobEffectCategory.NEUTRAL, 2039587);
    }

    @Override
    public List<ItemStack> getCurativeItems(){
        return new ArrayList<>();
    }

}