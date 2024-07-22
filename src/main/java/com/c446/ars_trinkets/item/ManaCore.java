package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import org.jetbrains.annotations.NotNull;

public class ManaCore extends FoodItem {
    public int core_level;
    boolean show_ench = false;

    public ManaCore(Properties p,FoodProperties foodProperties) {
        super(p,foodProperties);
    }

    public ManaCore(Properties p, FoodProperties foodProperties, boolean showEnch, int core_level) {
        super(p,foodProperties);
        this.show_ench = showEnch;
        this.core_level = core_level;

    }

    @Override
    public boolean isFoil(ItemStack s) {
        if (show_ench) {
            return true;
        } else {
            return super.isFoil(s);
        }
    }
}
