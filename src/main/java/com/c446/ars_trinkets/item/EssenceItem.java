package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher.*;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.c446.ars_trinkets.util.Util;
import com.hollingsworth.arsnouveau.setup.registry.CapabilityRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

import static com.c446.ars_trinkets.registry.ModRegistry.*;


//unused for the moment
public class EssenceItem extends RegularItems {
    boolean showEnch = false;

    int manaGiven = 0;

    public EssenceItem(Properties p, int manaGiven) {
        super(p);
        this.manaGiven = manaGiven;
    }

    public EssenceItem(Properties p, int manaGiven, boolean showEnch) {
        super(p);
        this.showEnch = showEnch;
        this.manaGiven = manaGiven;
    }

    @Override
    public boolean isFoil(ItemStack s) {
        if (showEnch) {
            return true;
        } else {
            return super.isFoil(s);
        }
    }

    public int getMana() {
        return this.manaGiven;
    }
    public void setMana(int i) {
        this.manaGiven = i;
    }

    public static int getExperienceValue(Item item) {
        if (Util.getAllEssences().contains(item)) {
            System.out.println(Util.getAllEssencesValues().get(item));
            return Util.getAllEssencesValues().get(item);
        }
        return 0;
    }


}




