package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ManaCore extends RegularItems {
    private int core_level;
    boolean showEnch = false;

    public ManaCore(Properties p) {
        super(p);
    }

    public ManaCore(Properties p, boolean showEnch, int core_level) {
        super(p);
        this.showEnch = showEnch;
        this.core_level = core_level;
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
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player player, @NotNull InteractionHand pUsedHand) {
        if (Config.IS_LEVELING_ENABLED.get()) {
            if (!pLevel.isClientSide && pUsedHand != InteractionHand.OFF_HAND) {
                player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
                    if (a.getPlayerArcaneLevel() <= this.core_level) {
                        if (a.nextCore(player)) {
                            for (int i = 0; i < 35; i++) {
                                if (player.getMainHandItem() == player.getInventory().getItem(i)) {
                                    System.out.println("consuming in main Hand");
                                    player.getInventory().removeItem(i, 1);
                                }
                            }
                        }
                    } else {
                        player.displayClientMessage(Component.literal("This core's level is too low !"), false);
                    }
                });
            }
        }return super.use(pLevel, player, pUsedHand);
    }
}
