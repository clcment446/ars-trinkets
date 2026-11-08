package com.c446.ars_trinkets.capabilities;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.registry.CapabilityRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;

@EventBusSubscriber
public final class MobLevelHandling {
    private static final ResourceLocation HEALTH_SCALING_ID = ArsTrinkets.prefix("mob_health_scaling");
    private static final ResourceLocation ATTACK_SCALING_ID = ArsTrinkets.prefix("mob_attack_scaling");
    private static final ResourceLocation ARMOR_SCALING_ID = ArsTrinkets.prefix("mob_armor_scaling");

    private MobLevelHandling() {
    }

    @SubscribeEvent
    public static void onMobJoin(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()
                || !(event.getEntity() instanceof Mob mob)
                || mob.hasData(CapabilityRegistry.LEVEL_CAP)) {
            return;
        }

        LevelingCapability capability = mob.getData(CapabilityRegistry.LEVEL_CAP);
        if (!Config.Common.MOB_SCALING_ENABLED.get()) return;

        int minimumLevel = Config.Common.MOB_LEVEL_MIN.get();
        int maximumLevel = Math.max(minimumLevel, Config.Common.MOB_LEVEL_MAX.get());
        int minimumCores = Config.Common.MOB_CORE_MIN.get();
        int maximumCores = Math.max(minimumCores, Config.Common.MOB_CORE_MAX.get());
        Player nearbyPlayer = referencePlayer(mob);
        if (nearbyPlayer != null) {
            // getData attaches a level-0 default, so players who never levelled still cap nearby mobs.
            LevelingCapability playerCapability = nearbyPlayer.getData(CapabilityRegistry.LEVEL_CAP);
            maximumLevel = MobScalingMath.mobLevelCapForPlayer(
                    playerCapability.level, maximumLevel, minimumLevel,
                    Config.Common.MOB_PLAYER_LEVEL_OFFSET.get());
            maximumCores = MobScalingMath.mobCoreCapForPlayer(
                    playerCapability.cores, maximumCores, minimumCores,
                    Config.Common.MOB_PLAYER_CORE_OFFSET.get());
        }

        double baseHealth = baseValue(mob, Attributes.MAX_HEALTH);
        double baseAttack = baseValue(mob, Attributes.ATTACK_DAMAGE);
        double baseArmor = baseValue(mob, Attributes.ARMOR);
        capability.level = (short) calculateLevel(baseHealth, baseAttack, baseArmor,
                Config.Common.MOB_LEVEL_SCORE_FLOOR.get(), Config.Common.MOB_LEVEL_SCORE_BASE.get(),
                minimumLevel, maximumLevel);
        capability.cores = MobScalingMath.rollCores(capability.level, mob.getRandom().nextDouble(),
                minimumLevel, maximumLevel, minimumCores, maximumCores,
                Config.Common.MOB_CORE_DECAY.get(), Config.Common.MOB_CORE_LEVEL_BIAS_DIVISOR.get());

        applyScaling(mob, capability.level, capability.cores, baseHealth, baseAttack, baseArmor,
                minimumLevel, maximumLevel, minimumCores, maximumCores);
    }

    /** Which nearby player's level caps a spawning mob. */
    public enum PlayerCapMode { NEAREST, LOWEST, HIGHEST }

    /**
     * Picks the player whose level caps this mob, among players within {@code player_cap_radius} blocks
     * (spectators ignored), per {@code player_cap_mode}. Null when nobody is in range.
     */
    public static Player referencePlayer(Mob mob) {
        double radius = Config.Common.MOB_PLAYER_CAP_RADIUS.get();
        PlayerCapMode mode = Config.Common.MOB_PLAYER_CAP_MODE.get();
        Player best = null;
        double bestDistance = Double.MAX_VALUE;
        int bestLevel = 0;
        for (Player player : mob.level().players()) {
            if (player.isSpectator()) continue;
            double distance = player.distanceToSqr(mob);
            if (distance > radius * radius) continue;
            int level = player.getData(CapabilityRegistry.LEVEL_CAP).level;
            boolean better = best == null || switch (mode) {
                case NEAREST -> distance < bestDistance;
                case LOWEST -> level < bestLevel || (level == bestLevel && distance < bestDistance);
                case HIGHEST -> level > bestLevel || (level == bestLevel && distance < bestDistance);
            };
            if (better) {
                best = player;
                bestDistance = distance;
                bestLevel = level;
            }
        }
        return best;
    }

    public static int calculateLevel(LivingEntity mob) {
        return calculateLevel(
                baseValue(mob, Attributes.MAX_HEALTH),
                baseValue(mob, Attributes.ATTACK_DAMAGE),
                baseValue(mob, Attributes.ARMOR));
    }

    static int calculateLevel(double health, double attack, double armor) {
        return MobScalingMath.levelForBaseStats(health, attack, armor);
    }

    private static int calculateLevel(double health, double attack, double armor,
                                      double scoreFloor, double scoreBase,
                                      int minimumLevel, int maximumLevel) {
        return MobScalingMath.levelForBaseStats(
                health, attack, armor, scoreFloor, scoreBase, minimumLevel, maximumLevel);
    }

    private static void applyScaling(Mob mob, int level, int cores,
                                     double baseHealth, double baseAttack, double baseArmor,
                                     int minimumLevel, int maximumLevel,
                                     int minimumCores, int maximumCores) {
        double combatMultiplier = MobScalingMath.combatStatMultiplier(
                level, cores, minimumLevel, maximumLevel, minimumCores, maximumCores);
        double armorTarget = MobScalingMath.scaledArmor(
                baseArmor, level, cores, Config.Common.MOB_ARMOR_SCALING_CAP.get(),
                minimumLevel, maximumLevel, minimumCores, maximumCores);

        if (Config.Common.MOB_HEALTH_SCALING_ENABLED.get()) {
            double healthMultiplier = Math.min(
                    MobScalingMath.healthMultiplier(level, cores,
                            Config.Common.MOB_HEALTH_GROWTH_BASE.get(),
                            minimumLevel, maximumLevel, minimumCores, maximumCores),
                    Math.max(1.0, Config.Common.MOB_HEALTH_SCALING_MAX.get()));
            addMultiplier(mob, Attributes.MAX_HEALTH, HEALTH_SCALING_ID, healthMultiplier - 1.0);
        }
        addMultiplier(mob, Attributes.ATTACK_DAMAGE, ATTACK_SCALING_ID, combatMultiplier - 1.0);
        if (baseArmor > 0.0) {
            addMultiplier(mob, Attributes.ARMOR, ARMOR_SCALING_ID, armorTarget / baseArmor - 1.0);
        }
        mob.setHealth(mob.getMaxHealth());
        ArsTrinkets.LOGGER.debug("Scaled mob {}: level={}, cores={}, baseHealth={}, baseAttack={}, baseArmor={}",
                mob.getType(), level, cores, baseHealth, baseAttack, baseArmor);
    }

    private static void addMultiplier(Mob mob,
                                      net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute,
                                      ResourceLocation id, double amount) {
        AttributeInstance instance = mob.getAttribute(attribute);
        if (instance == null || amount == 0.0) return;
        instance.addPermanentModifier(new AttributeModifier(id, amount, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }

    private static double baseValue(LivingEntity entity,
                                    net.minecraft.core.Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute) {
        AttributeInstance instance = entity.getAttribute(attribute);
        return instance == null ? 0.0 : instance.getBaseValue();
    }
}
