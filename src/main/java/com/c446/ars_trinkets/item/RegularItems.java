package com.c446.ars_trinkets.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

//credits for code go to: https://github.com/dkmk100/ArsOmega/blob/37c9ba1d9c76ccde893ca12a8987bf4bdc9e4ffa/src/main/java/com/dkmk100/arsomega/items/BasicItem.java
public class RegularItems extends Item {
    boolean showEnch = false;
    String name;

    public RegularItems(Properties p) {
        super(p);
    }

    public RegularItems(Properties p, boolean showEnch){
        super(p);
        this.showEnch = showEnch;
    }
    @Override
    public boolean isFoil(ItemStack s){
        if(showEnch){return true;}else{return super.isFoil(s);}
    }
}