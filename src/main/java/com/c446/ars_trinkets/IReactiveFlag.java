package com.c446.ars_trinkets;

import net.minecraft.world.item.ItemStack;

public interface IReactiveFlag {
    void setReactive(boolean val);
    boolean getValue();

    void setTrueItem(ItemStack stack);
    ItemStack getTrueItem();
}