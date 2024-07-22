package com.c446.ars_trinkets.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;

public class ThatItemToChangeClassLol extends FoodItem {
    public boolean turnsPlayerProfane;
    boolean show_ench = false;

    public ThatItemToChangeClassLol(Properties p, FoodProperties foodProperties, boolean profane) {
        super(p, foodProperties);
    this.turnsPlayerProfane = profane;}

    @Override
    public boolean isFoil(ItemStack s) {
        if (show_ench) {
            return true;
        } else {
            return super.isFoil(s);
        }
    }


}
