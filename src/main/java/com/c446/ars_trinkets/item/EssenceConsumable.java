package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.capabilities.LevelingCapability;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Consumable essence that feeds souls to the player.
 * <ul>
 *     <li>Always edible (no hunger requirement) and eaten fast.</li>
 *     <li>Refuses to start being used if it would do nothing (player level above the essence level),
 *     so it is never wasted, and tells the player why.</li>
 *     <li>Sneaking while eating consumes the whole stack at once.</li>
 * </ul>
 */
public class EssenceConsumable extends Item {
    final int level;
    final int soulQuantity;

    public EssenceConsumable(Properties pProperties, int minLevel, int soulQuantity) {
        super(pProperties.food(
                new FoodProperties.Builder()
                        .nutrition(0)
                        .saturationModifier(0f)
                        .alwaysEdible()
                        .fast()
                        .build()
        ));
        this.level = minLevel;
        this.soulQuantity = soulQuantity;
    }

    private boolean isUseful(Player player) {
        return LevelingCapability.get(player).level <= this.level;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!isUseful(player)) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.translatable("message.ars_trinkets.essence.too_weak", this.level), true);
            }
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }
        return super.use(level, player, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        // Grab the amount before super shrinks the stack.
        int consumed = pLivingEntity.isShiftKeyDown() ? pStack.getCount() : 1;
        ItemStack result = super.finishUsingItem(pStack, pLevel, pLivingEntity);

        if (pLevel instanceof ServerLevel && pLivingEntity instanceof ServerPlayer p) {
            // super already consumed one; consume the rest of the batch.
            if (consumed > 1 && !p.getAbilities().instabuild) {
                result.shrink(consumed - 1);
            }

            var cap = LevelingCapability.get(p);
            long before = cap.souls;
            int levelBefore = cap.level;
            for (int i = 0; i < consumed && cap.level <= this.level; i++) {
                cap.addSoul(this.soulQuantity, p);
            }
            if (cap.level > levelBefore) {
                p.displayClientMessage(Component.translatable("message.ars_trinkets.essence.level_up", cap.level), true);
            } else {
                p.displayClientMessage(Component.translatable("message.ars_trinkets.essence.souls", cap.souls - before, cap.souls), true);
            }
        }

        return result;
    }
}
