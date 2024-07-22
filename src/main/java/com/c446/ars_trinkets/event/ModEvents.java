package com.c446.ars_trinkets.event;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.capabilities.ArcaneLevels;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher.ArcaneLevelsProvider;
import com.c446.ars_trinkets.commands.BECOME_GOD;
import com.c446.ars_trinkets.commands.CommandResetArcaneProgression;
import com.c446.ars_trinkets.commands.SetArcaneProgression;
import com.c446.ars_trinkets.item.ManaCore;
import com.c446.ars_trinkets.item.ThatItemToChangeClassLol;
import com.c446.ars_trinkets.perks.PerkAttributes;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.event.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Objects;

import static com.c446.ars_trinkets.capabilities.CapabilityRegistry.getArcaneLevels;

@Mod.EventBusSubscriber(modid = ArsTrinkets.MOD_ID)
public class ModEvents {


    @SubscribeEvent
    public static void commandRegister(RegisterCommandsEvent event) {
        CommandResetArcaneProgression.register(event.getDispatcher());
        BECOME_GOD.register(event.getDispatcher());
        SetArcaneProgression.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
//        System.out.println("attach Cap Entity triggered");
        if (event.getObject() instanceof Player) {
            ArcaneLevelsAttacher.attach(event);
//            System.out.println("Arcane Level Capability Created");
        }
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ArcaneLevels.class);

//        System.out.println("Arcane Cap registered");
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
            newArcaneLevels.setCores(oldArcane.getPlayerCollectedSouls());
        }));
        event.getOriginal().invalidateCaps();
//        System.out.println("Arcane Cap Cloned");
    }

    @SubscribeEvent
    public static void MaxManaCalcEvent(MaxManaCalcEvent event) {
        if (!Config.IS_LEVELING_ENABLED.get()) {
            return;
        }
        if (event.getEntity() instanceof Player player && player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).isPresent()) {
            player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(
                    a -> {
                        event.setMax((int) ((event.getMax() + a.getPlayerManaBonus()) * Objects.requireNonNull(event.getEntity().getAttribute(PerkAttributes.TOTAL_MANA_BOOST.get())).getValue())
                        );
                    });
        }
    }

//    @SubscribeEvent
//    public static void ManaReductionEvent(SpellCostCalcEvent event) {
//        if (!Config.IS_LEVELING_ENABLED.get()) {
//            return;
//        }
//        if (event.context.getCaster() instanceof Player player && player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).isPresent()) {
//            player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(
//                    a -> {
//                        if (a.getProfane()) {
//                            event.currentCost *= (3 * a.getCores()) / (Math.pow(a.getPlayerArcaneLevel() / 1.3, 1.5));
//                        } else {
//                            event.currentCost /= a.getPlayerArcaneLevel() * 1.5;
//                        }
//                    }
//            );
//        }
//    }

    @SubscribeEvent
    public static void ManaRegenCalcEvent(ManaRegenCalcEvent event) {
        if (!Config.IS_LEVELING_ENABLED.get()) {
            return;
        }
        if (event.getEntity() instanceof Player player && player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).isPresent()) {
            player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(
                    a -> {
//
                        event.setRegen((event.getRegen() + a.getPlayerRegenBonus() * Objects.requireNonNull(event.getEntity().getAttribute(PerkAttributes.TOTAL_MANA_REGEN_BOOST.get())).getValue()));
                    });
        }
    }

    @SubscribeEvent
    public static void SpellDamageApplied(SpellDamageEvent event) {
        if (!Config.IS_LEVELING_ENABLED.get()) {
            return;
        }
        event.caster.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> event.damage *= a.getSpellDmg());
        //        p.displayClientMessage(Component.literal("spell damage applied"), false);
    }

    @SubscribeEvent
    public static void PlayerKillRefineSoul(net.minecraftforge.event.entity.living.LivingDeathEvent deathEvent) {
        if (!Config.IS_LEVELING_ENABLED.get()) {
            return;
        }
        if (deathEvent.getSource().getEntity() instanceof Player player) {
            player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
                a.updateSoulEssence((int) (Objects.requireNonNull(player.getAttribute(PerkAttributes.SOUL_STEALER.get())).getValue() * deathEvent.getEntity().getMaxHealth() / 2), true, player);
                a.updatePlayerCores(player);
            });
//            System.out.println(player.getName() + " killed " + deathEvent.getEntity().getName() + " for " + (int) deathEvent.getEntity().getMaxHealth() / 10);
//            player.displayClientMessage(Component.literal("kill event"), false);
        }
    }

    @SubscribeEvent
    public static void playerDeathEvent(PlayerEvent.PlayerRespawnEvent event) {
        Player p = event.getEntity();
        if (Objects.equals(p.getUUID().toString(), "2980a99e-8582-4f63-9b82-f7117bc8be2c") && !CuriosApi.getCurio(new ItemStack(ModRegistry.THEARCH_CROWN.get())).isPresent()) {
            p.getInventory().setItem(p.getInventory().getFreeSlot(), new ItemStack(ModRegistry.THEARCH_CROWN.get()));


        }
    }

    @SubscribeEvent
    public static void onPlayerReceiveDamage(net.minecraftforge.event.entity.living.LivingDamageEvent event) {
        event.getEntity().getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
            event.setAmount((float) (event.getAmount() * a.getPlayerDefense()));
            if (a.getProfane()) {
                event.setAmount((float) (event.getAmount() * 1.5));
            }
        });
    }

    @SubscribeEvent
    public static void onPlayerFinishEating(LivingEntityUseItemEvent.Finish event) {
        if (event.getItem().getItem() instanceof ManaCore core && event.getEntity() instanceof Player player) {
            player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
                if (a.getPlayerArcaneLevel() / 3 <= core.core_level) {
                    System.out.println("gate 1")    ;
                    a.nextCore(player);
                }
            });
        }
        if (event.getItem().getItem() instanceof ThatItemToChangeClassLol relic && event.getEntity() instanceof Player player) {
            player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
                if (a.getProfane() != relic.turnsPlayerProfane) {
                    if (relic.turnsPlayerProfane) {
                        player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.going_to_madness_item"), false);
                    } else {
                        player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.back_from_madness"), false);
                    }
                    a.setProfane(relic.turnsPlayerProfane);
                }
            });
        }
    }

//    @SubscribeEvent
//    public static void playerEquipCurio(CurioEquipEvent event){
//        if (event.getStack().getItem().asItem() == ModRegistry.THEARCH_CROWN.get() && event.getEntity() instanceof Player player && player.getStringUUID().equals("2980a99e-8582-4f63-9b82-f7117bc8be2c")) {
//            return;
//        } else{
//            Event.Result.DENY;
//        }
//    }

    @SubscribeEvent
    public static void expPickupEvent(PlayerXpEvent.PickupXp event) {
        event.getEntity().getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
            if (a.getProfane()) {
                event.getOrb().value = event.getOrb().value / 2;
            }
        });
    }

    @SubscribeEvent
    public static void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        event.getEntity().getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
            if (a.getLoginFirst()) {
                if (Objects.equals(event.getEntity().getUUID().toString(), "2980a99e-8582-4f63-9b82-f7117bc8be2c")) {
                    event.getEntity().getInventory().setItem(event.getEntity().getInventory().getFreeSlot(), new ItemStack(ModRegistry.MANA_RING_10.get(), 2));
                    event.getEntity().getInventory().setItem(event.getEntity().getInventory().getFreeSlot(), new ItemStack(ModRegistry.ESSENCE_LOTUS_10.get(), 1));
                    event.getEntity().getInventory().setItem(event.getEntity().getInventory().getFreeSlot(), new ItemStack(ModRegistry.MONOCLE_10.get(), 1));
                    event.getEntity().getInventory().setItem(event.getEntity().getInventory().getFreeSlot(), new ItemStack(ModRegistry.ETERNAL_RUNE.get(), 5));

                    a.player_arcane_level = 9;
                    a.player_cores = Config.MAX_PLAYER_CORE.get() * 5;
                } else {
                    a.setLoginFirst(false);
                }
            }

        });
    }

    @SubscribeEvent
    public static void coresUpdated(PlayerCoresUpdated event) {
//        ArcaneLevels.updateModifiers(event.player);

    }

    @SubscribeEvent
    public static void levelsUpdated(PlayerLevelUpdated event) {
//        ArcaneLevels.updateModifiers(event.player);
    }
}


