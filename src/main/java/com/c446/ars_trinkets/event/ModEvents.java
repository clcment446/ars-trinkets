package com.c446.ars_trinkets.event;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.item.EssenceItem;
import com.c446.ars_trinkets.manaStorage.ManaStone;
import com.c446.ars_trinkets.manaStorage.ManaStoneProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = ArsTrinkets.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Player> event) {
        if (event.getObject() != null) {
            if (!event.getObject().getCapability(ManaStoneProvider.PLAYER_MANA).isPresent()) {
                event.addCapability(new ResourceLocation(ArsTrinkets.MODID, "properties"), new ManaStoneProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(ManaStoneProvider.PLAYER_MANA).ifPresent(oldStore -> event.getOriginal().getCapability(ManaStoneProvider.PLAYER_MANA).ifPresent(newStore -> newStore.CopyFrom(oldStore)));
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ManaStone.class);
    }


    /*
     * Algorithm description
     * Check if side is server
     * Get player capability
     * If MANA CAPABILITY is available then set current mana as max mana
     * Then
     * If MANA STONE available, if STONE SUB LEGAL then substract.
     */
//    @SubscribeEvent
//    public static void onTickPasses(TickEvent.PlayerTickEvent event) {
//        if (event.side == LogicalSide.SERVER) {
//            if (event.player.getCapability(CapabilityRegistry.MANA_CAPABILITY).isPresent() && event.player.getCapability(ManaStoneProvider.PLAYER_MANA).isPresent()) {
//                event.player.getCapability(ManaStoneProvider.PLAYER_MANA).ifPresent(manaStone -> event.player.getCapability(CapabilityRegistry.MANA_CAPABILITY).ifPresent(playerMana -> {
//                    int missingMana = (int) (playerMana.getMaxMana() - playerMana.getCurrentMana());
//                    if (missingMana > 0) {
//                        if (ManaStone.isSubLegal(missingMana, manaStone.getCrystalMana())) {
//                            manaStone.subCrystalMana(missingMana);
//                            playerMana.addMana(missingMana);
//                        }
//                    }
//                }));
//            }
//        }
//    }

    /*
     * Algorithm description;
     * Check if the offhand item is included in the essence list
     * Get player capability
     * If 'ManaStoneProvider' is present, attempt to add the item's worth of mana into the player's reserve.
     */
    public static void essenceIsConsumed(Player pPlayer) {
        if (EssenceItem.getManaValue(pPlayer.getItemBySlot(EquipmentSlot.OFFHAND).getItem()) != 0) {
            pPlayer.getCapability(ManaStoneProvider.PLAYER_MANA).ifPresent(manaStone -> {
                int count = pPlayer.getItemBySlot(EquipmentSlot.OFFHAND).getCount();
                int k = EssenceItem.getManaValue(pPlayer.getItemBySlot(EquipmentSlot.OFFHAND).getItem()) * count;
                pPlayer.getInventory().removeItem(Inventory.SLOT_OFFHAND, count);
                manaStone.addCrystalMana(k);
//                pPlayer.displayClientMessage(net.minecraft.network.chat.Component.);
            });
        }
    }
}
