package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher.*;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.c446.ars_trinkets.util.Util;
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

    public EssenceItem(Properties p) {
        super(p);
    }

    public EssenceItem(Properties p, boolean showEnch) {
        super(p);
        this.showEnch = showEnch;
    }

    @Override
    public boolean isFoil(ItemStack s) {
        if (showEnch) {
            return true;
        } else {
            return super.isFoil(s);
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (!pLevel.isClientSide && pUsedHand == InteractionHand.OFF_HAND) {
            System.out.println("OFF HAND USE");
            ConsumeMana(pPlayer, true, pPlayer.getOffhandItem(), pPlayer.getOffhandItem().getCount());
        } else if (!pLevel.isClientSide) {
            ItemStack held = pPlayer.getMainHandItem();
            System.out.println("MAIN HAND USE");
            ConsumeMana(pPlayer, false, held, held.getCount());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public static int getExperienceValue(Item item) {
        System.out.println("getExperienceValue triggered");
        if (Util.getAllEssences().contains(item)) {
            System.out.println(Util.getAllEssencesValues().get(item));
            return Util.getAllEssencesValues().get(item);
        }
        return 0;
    }

    public void ConsumeMana(Player player, boolean slot_is_off_hand/* is offhand*/, ItemStack stack,int number) {
        int a = getExperienceValue(stack.getItem());
        System.out.println("ArcaneLevelCap Called");
        player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(arcaneLevels -> arcaneLevels.updateSoulEssence(a*number, false, player));
        System.out.println("ConsumeMana triggered");
        if (slot_is_off_hand) {
            System.out.println("consuming in off hand");
            player.getInventory().removeItem(Inventory.SLOT_OFFHAND, 1);
        } else {
            for (int i = 0; i < 35; i++) {
                if (player.getInventory().getItem(i) == stack) {
                    System.out.println("consuming in main Hand");
                    player.getInventory().removeItem(i, 1);
                }
            }
        }
        player.getInventory().removeItem(player.getMainHandItem());
    }
}




