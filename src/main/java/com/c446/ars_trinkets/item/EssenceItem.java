package com.c446.ars_trinkets.item;

import com.hollingsworth.arsnouveau.common.capability.CapabilityRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static com.c446.ars_trinkets.ArsTrinkets.ESSENCE_LIST;
import static com.c446.ars_trinkets.ArsTrinkets.ESSENCE_VALUE;
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
            ConsumeMana(pPlayer, true, pPlayer.getMainHandItem());
        } else if (!pLevel.isClientSide) {
            ItemStack held = pPlayer.getMainHandItem();
            ConsumeMana(pPlayer, false, held);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public static int getManaValue(Item item) {
        if (ESSENCE_LIST.contains(item)) {
            return ESSENCE_VALUE.get(item);
        }
        return 0;
    }

    public void ConsumeMana(Player player, boolean slot/* is off hand*/, ItemStack stack) {
        player.getCapability(CapabilityRegistry.MANA_CAPABILITY).ifPresent(iManaCap -> {
            iManaCap.addMana(getManaValue(stack.getItem()));
            if (slot) {
                player.getInventory().removeItem(Inventory.SLOT_OFFHAND, 1);
            } else {
                for (int i = 0; i < 35; i++) {
                    if (player.getInventory().getItem(i) == stack) {
                        player.getInventory().removeItem(i, 1);
                    }
                }
                player.getInventory().removeItem(player.getMainHandItem());
            }
        });
    }

    public static void setEssenceLists() {
        ESSENCE_LIST.clear();
        ESSENCE_LIST.add(SILVER_ESSENCE.get());
        ESSENCE_LIST.add(GOLD_ESSENCE.get());
        ESSENCE_LIST.add(CRYSTAL_ESSENCE.get());
        ESSENCE_LIST.add(GREEN_ESSENCE.get());
        ESSENCE_LIST.add(RED_ESSENCE.get());
        ESSENCE_LIST.add(WHITE_ESSENCE.get());
        ESSENCE_LIST.add(YELLOW_ESSENCE.get());

        ESSENCE_VALUE.put(SILVER_ESSENCE.get(), 50);
        ESSENCE_VALUE.put(GOLD_ESSENCE.get(), 150);
        ESSENCE_VALUE.put(CRYSTAL_ESSENCE.get(), 450);
        ESSENCE_VALUE.put(GREEN_ESSENCE.get(), 1000);
        ESSENCE_VALUE.put(RED_ESSENCE.get(), 2500);
        ESSENCE_VALUE.put(WHITE_ESSENCE.get(), 10000);
        ESSENCE_VALUE.put(YELLOW_ESSENCE.get(), 50000);
        ESSENCE_VALUE.put(PURPLE_ESSENCE.get(), 2000000);
    }
}



