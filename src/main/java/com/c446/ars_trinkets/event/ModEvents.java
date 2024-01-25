package com.c446.ars_trinkets.event;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.capabilities.ArcaneLevels;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher.ArcaneLevelsProvider;
import com.c446.ars_trinkets.commands.CommandResetArcaneProgression;
import com.c446.ars_trinkets.commands.SetArcaneProgression;
import com.hollingsworth.arsnouveau.api.event.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.c446.ars_trinkets.capabilities.CapabilityRegistry.getArcaneLevels;

@Mod.EventBusSubscriber(modid = ArsTrinkets.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void commandRegister(RegisterCommandsEvent event) {
        CommandResetArcaneProgression.register(event.getDispatcher());
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
//                        if (a.getProfane()) {
//                            if (a.getLastFed() > a.getFeedingTime()) {
//                                event.setMax(event.getMax() + a.getPlayerManaBonus()/5);
//                            } else {
//                                event.setMax(event.getMax() + a.getPlayerManaBonus());
//                            }
////                            System.out.println("MANA ALTERED : irregular");
//                        } else {
                            event.setMax(event.getMax() + a.getPlayerManaBonus());
//                            System.out.println("MANA ALTERED : regular");
//                        }
                    });
//            System.out.println("max mana altered");
//            player.displayClientMessage(Component.literal("mana regen altered"), false);
        }
    }

    @SubscribeEvent
    public static void ManaRegenCalcEvent(ManaRegenCalcEvent event) {
        if (!Config.IS_LEVELING_ENABLED.get()) {
            return;
        }
        if (event.getEntity() instanceof Player player && player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).isPresent()) {
            player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(
                    a -> {
//                        if (a.getProfane()) {
//                            if (a.getLastFed() > a.getFeedingTime()) {
//                                event.setRegen(event.getRegen() + a.getPlayerRegenBonus()/5);
//                            } else {
//                                event.setRegen(event.getRegen() + a.getPlayerRegenBonus());
//                            }
////                            System.out.println("MANA REGEN ALTERED : irregular");
//                        } else {
                            event.setRegen(event.getRegen() + a.getPlayerRegenBonus());
//                            System.out.println("MANA REGEN ALTERED : regular");
//                        }
                    });
//            System.out.println("mana regen altered");
//            player.displayClientMessage(Component.literal("mana regen altered"), false);
        }
    }

    @SubscribeEvent
    public static void SpellDamageApplied(SpellDamageEvent event) {
        if (!Config.IS_LEVELING_ENABLED.get()) {
            return;
        }
        event.caster.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {event.damage *= Math.pow(1.7, a.getPlayerArcaneLevel() / 2);

        });
        Player p = (Player) event.caster;
//        p.displayClientMessage(Component.literal("spell damage applied"), false);
    }

    @SubscribeEvent
    public static void PlayerKillRefineSoul(net.minecraftforge.event.entity.living.LivingDeathEvent deathEvent) {
        if (!Config.IS_LEVELING_ENABLED.get()) {

            return;
        }
        if (deathEvent.getSource().getEntity() instanceof Player player) {
            player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
                a.updateSoulEssence((int) (deathEvent.getEntity().getMaxHealth() / 2), true, player);
                a.updatePlayerCores(player);
            });
//            System.out.println(player.getName() + " killed " + deathEvent.getEntity().getName() + " for " + (int) deathEvent.getEntity().getMaxHealth() / 10);
//            player.displayClientMessage(Component.literal("kill event"), false);
        }
    }

    @SubscribeEvent
    public static void onPlayerReceiveDamage(net.minecraftforge.event.entity.living.LivingDamageEvent event) {
        event.getEntity().getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
            event.setAmount((float) (event.getAmount() / Math.pow(1.9, a.getPlayerArcaneLevel() / 1.2)));
            if (a.getProfane()) {
                event.setAmount((float) (event.getAmount() * 1.5));
            }
        });
    }

    @SubscribeEvent
    public static void expPickupEvent(PlayerXpEvent.PickupXp event) {
        event.getEntity().getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
            if (a.getProfane()) {
                event.getOrb().value = event.getOrb().value / 2;
            }
        });
    }

//    @SubscribeEvent
//    public static void tickEntity(TickEvent.PlayerTickEvent event) {
//        event.player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
//            if (a.getProfane()) {
//                Player player = event.player;
//                if (a.getLastFed() > a.getFeedingTime() / 2) {
//                    player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.hunger1"), true);
//                    a.warned1 = true;
//                } else if (a.getLastFed() > a.getFeedingTime()) {
//                    a.warned2 = true;
//                    player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.hunger2"), true);
//                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5, 3));
//                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 5, 3));
//                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 5, 1));
//                    player.addEffect(new MobEffectInstance(MobEffects.WITHER, 5, 1));
//                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,5,1));
//                                    System.out.println("PLAYER FAMISHED !");
//                }
//                a.setLastFed(a.getLastFed() + 1);
//            }
//        });
//    }
}
