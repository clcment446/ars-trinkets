package com.c446.ars_trinkets.event;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.capabilities.ArcaneLevels;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher.ArcaneLevelsProvider;
import com.hollingsworth.arsnouveau.api.event.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.c446.ars_trinkets.capabilities.CapabilityRegistry;

import static com.c446.ars_trinkets.capabilities.CapabilityRegistry.getArcaneLevels;

@Mod.EventBusSubscriber(modid = ArsTrinkets.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
        System.out.println("attach Cap Entity triggered");
        if (event.getObject() instanceof Player) {
            ArcaneLevelsAttacher.attach(event);
            System.out.println("Arcane Level Capability Created");
        }
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ArcaneLevels.class);

        System.out.println("Arcane Cap registered");
    }

    @SubscribeEvent
    public static void playerCloned(PlayerEvent.Clone event) {
        Player oldPlayer = event.getOriginal();
        oldPlayer.revive();
        getArcaneLevels(oldPlayer).ifPresent(oldArcane -> getArcaneLevels(event.getEntity()).ifPresent(newArcaneLevels -> {
            newArcaneLevels.setPlayerSoulRefinement(oldArcane.getPlayerSoulRefinement());
            newArcaneLevels.setPlayerArcaneLevel(oldArcane.getPlayerArcaneLevel());
            newArcaneLevels.setProfane(oldArcane.getProfane());
            newArcaneLevels.setCollectedSouls(oldArcane.getPlayerCollectedSouls());
        }));
        event.getOriginal().invalidateCaps();
        System.out.println("Arcane Cap Cloned");
    }




//    public static void syncPlayerCaps(Player player){
//        ArcaneLevels cap = CapabilityRegistry.(player).orElse(new ArcaneLevels())
//        CompoundTag tag = cap.ser
//    }

    @SubscribeEvent
    public static void MaxManaCalcEvent(MaxManaCalcEvent event) {
        if (event.getEntity() instanceof Player player && player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).isPresent()) {
            player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(
                    arcaneLevels -> event.setMax(event.getMax() + arcaneLevels.getPlayerManaBonus()));

            System.out.println("max mana altered");
        }
    }

    @SubscribeEvent
    public static void ManaRegenCalcEvent(ManaRegenCalcEvent event) {
        if (event.getEntity() instanceof Player player && player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).isPresent()) {
            player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(
                    a -> event.setRegen(event.getRegen() + a.getPlayerRegenBonus()));
            System.out.println("mana regen altered");
        }
    }

    @SubscribeEvent
    public static void PlayerKillRefineSoul(net.minecraftforge.event.entity.living.LivingDeathEvent deathEvent) {
        if (deathEvent.getSource().getEntity() instanceof Player player) {
            player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> a.updateSoulEssence((int) (deathEvent.getEntity().getMaxHealth() / 10), true, player));
            System.out.println(player.getName() + " killed " + deathEvent.getEntity().getName() + " for " + (int) deathEvent.getEntity().getMaxHealth() / 10);
        }
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
//    public static void essenceIsConsumed(Player pPlayer) {
//        if (EssenceItem.getManaValue(pPlayer.getItemBySlot(EquipmentSlot.OFFHAND).getItem()) != 0) {
//            pPlayer.getCapability(ManaStoneProvider.PLAYER_MANA).ifPresent(manaStone -> {
//                int count = pPlayer.getItemBySlot(EquipmentSlot.OFFHAND).getCount();
//                int k = EssenceItem.getManaValue(pPlayer.getItemBySlot(EquipmentSlot.OFFHAND).getItem()) * count;
//                pPlayer.getInventory().removeItem(Inventory.SLOT_OFFHAND, count);
//                manaStone.addCrystalMana(k);
////                pPlayer.displayClientMessage(net.minecraft.network.chat.Component.);
//            });
//        }
//    }
}
