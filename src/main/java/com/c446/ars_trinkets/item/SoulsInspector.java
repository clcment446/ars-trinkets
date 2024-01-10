package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SoulsInspector extends RegularItems {

    public SoulsInspector(Properties p) {
        super(p);
        this.showEnch = true;
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
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if (pPlayer.isCrouching()) {
            pPlayer.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
                pPlayer.displayClientMessage(a.getPlayerTitle(),false);
                pPlayer.displayClientMessage(a.getPlayerTitle(),false);
            });
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
