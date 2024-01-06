package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher.*;
import com.c446.ars_trinkets.registry.ModRegistry;
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

    public static int getExperienceValue(Item item) {
        if (ESSENCE_LIST.contains(item)) {
            return ESSENCE_VALUE.get(item);
        }
        return 0;
    }

    public void ConsumeMana(Player player, boolean slot/* is off hand*/, ItemStack stack) {
        if (slot) {
            player.getInventory().removeItem(Inventory.SLOT_OFFHAND, 1);
        } else {
            for (int i = 0; i < 35; i++) {
                if (player.getInventory().getItem(i) == stack) {
                    player.getInventory().removeItem(i, 1);
                }
            }
        }
        player.getInventory().removeItem(player.getMainHandItem());
        player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(arcaneLevels -> arcaneLevels.updateSoulEssence(getExperienceValue(stack.getItem()), false, player));

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

        ESSENCE_VALUE.clear();
        ESSENCE_VALUE.put(ModRegistry.IRON_ESSENCE.get(), 25);
        ESSENCE_VALUE.put(ModRegistry.COPPER_ESSENCE.get(), 50);
        ESSENCE_VALUE.put(ModRegistry.SILVER_ESSENCE.get(), 100);
        ESSENCE_VALUE.put(ModRegistry.GOLD_ESSENCE.get(), 200);
        ESSENCE_VALUE.put(ModRegistry.CRYSTAL_ESSENCE.get(), 300);
        ESSENCE_VALUE.put(ModRegistry.GREEN_ESSENCE.get(), 1000);
        ESSENCE_VALUE.put(ModRegistry.RED_ESSENCE.get(), 2500);
        ESSENCE_VALUE.put(ModRegistry.WHITE_ESSENCE.get(), 7000);
        ESSENCE_VALUE.put(ModRegistry.YELLOW_ESSENCE.get(), 14000);
        ESSENCE_VALUE.put(ModRegistry.PURPLE_ESSENCE.get(), 27000);
    }
}



