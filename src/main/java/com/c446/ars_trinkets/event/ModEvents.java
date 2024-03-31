package com.c446.ars_trinkets.event;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.capabilities.ArcaneLevels;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher.ArcaneLevelsProvider;
import com.c446.ars_trinkets.commands.CommandResetArcaneProgression;
import com.c446.ars_trinkets.commands.SetArcaneProgression;
import com.c446.ars_trinkets.perks.PerkAttributes;
import com.hollingsworth.arsnouveau.api.event.*;
import com.hollingsworth.arsnouveau.api.util.PerkUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

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
                        event.setMax((int) ((event.getMax() + a.getPlayerManaBonus()) * Objects.requireNonNull(event.getEntity().getAttribute(PerkAttributes.TOTAL_MANA_BOOST.get())).getValue())

                        );
                    });
//                            System.out.println("MANA ALTERED : regular");
//                        }
//            System.out.println("max mana altered");
//            player.displayClientMessage(Component.literal("mana regen altered"), false);
        }
    }

    @SubscribeEvent
    public static void ManaReductionEvent(SpellCostCalcEvent event) {
        if (!Config.IS_LEVELING_ENABLED.get()) {
            return;
        }
        if (event.context.getCaster() instanceof Player player && player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).isPresent()) {
            player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(
                    a -> {
                        if (a.getProfane()) {
                            event.currentCost *= (3 * a.getCores()) / (Math.pow(a.getPlayerArcaneLevel() / 1.3, 1.5));
                        } else {
                            event.currentCost /= a.getPlayerArcaneLevel() * 1.5;
                        }
                    }
            );
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
                        event.setRegen(
                                (event.getRegen() + a.getPlayerRegenBonus())
                                        *
                                        Objects.requireNonNull(event.getEntity().getAttribute(PerkAttributes.TOTAL_MANA_REGEN_BOOST.get())).getValue());
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
        event.caster.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> event.damage *= Objects.requireNonNull(event.caster.getAttribute(PerkAttributes.SPELL_DAMAGE_PCT.get())).getValue() * Math.pow(1.7, a.getPlayerArcaneLevel() / 2));
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
//    @SubscribeEvent
//    public static void potionEvent(MobEffectEvent. event) {
//        LivingEntity target = event.getEntity();
//        Entity applier = event.getEffectSource();
//        if(target.level.isClientSide)
//            return;
//        double bonus_len = 0.0;
//        double bonus_str = 0.0;
//        if(event.getEffectInstance().getEffect().isBeneficial()){
//            bonus_len = PerkUtil.valueOrZero(target, PerkAttributes.POTION_LENGTH.get());
//            bonus_str = PerkUtil.valueOrZero(target, PerkAttributes.POTION_STRENGTH.get());
//        }else if(applier instanceof LivingEntity living){
//            bonus_len = PerkUtil.valueOrZero(target, PerkAttributes.POTION_LENGTH.get());
//            bonus_str = PerkUtil.valueOrZero(target, PerkAttributes.POTION_STRENGTH.get());
//        }
//
//        if(bonus_len > 0.0){
//            event.getEffectInstance().duration *= bonus_len;
//            event.getEffectInstance().strength *= bonus_len;
//        }
//    }
}
