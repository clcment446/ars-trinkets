package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.c446.ars_trinkets.capabilities.CapabilityRegistry;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.api.util.DamageUtil;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;


import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;


public class BlackFlame extends AbstractEffect implements IDamageEffect {
    public BlackFlame(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final BlackFlame INSTANCE = new BlackFlame(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_cursed_flame"), "Cursed Flames");


    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (resolver.hasFocus(ModRegistry.UNHOLY_FOCUS.get())) {
            spellStats.setAmpMultiplier(spellStats.getAmpMultiplier() + 2);
            spellStats.setDurationMultiplier(spellStats.getDurationMultiplier() + 2);
        }
        AtomicBoolean is_profane = new AtomicBoolean(false);
         shooter.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a->{
             is_profane.set(a.getProfane());
         });

         if (!(is_profane.get()) && shooter instanceof Player player){
            player.displayClientMessage(Component.translatable("player_not_cursed.glyph_stopped"), true);
            return;
         }

        DamageSource source = DamageUtil.source(world, DamageTypes.WITHER, shooter);

        if (rayTraceResult.getEntity() instanceof LivingEntity living && shooter instanceof Player player) {
            ServerLevel level = (ServerLevel) world;
            level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, living.getX(), living.getY(), living.getZ(), 10, 0, 0, 0, 0);
            float range = (float) (spellStats.getAoeMultiplier() + 3);

            for (Entity e : world.getEntities(shooter, new AABB(living.position().add(range, range, range), living.position().subtract(range, range, range)))) {

                AtomicLong bonus = new AtomicLong(5);
                boolean prof = false;
                if (living.hasEffect(ModPotions.FREEZING_EFFECT.get())) {
                    int s = Objects.requireNonNull(living.getEffect(ModPotions.FREEZING_EFFECT.get())).getAmplifier();
                    s /= 2;
                    bonus.addAndGet(s);
                }
                if (living.hasEffect(ModPotions.HEX_EFFECT.get())) {
                    int s = Objects.requireNonNull(living.getEffect(ModPotions.HEX_EFFECT.get())).getAmplifier();
                    s /= 2.5;
                    bonus.addAndGet(s);
                }
                if (living.isOnFire()) {
                    bonus.updateAndGet(v -> (long) (v * 1.2));
                }
                if (living instanceof Player player1) {
                    player1.getCapability(CapabilityRegistry.arcane_cap).ifPresent(arcaneLevels -> {
                        if (!arcaneLevels.getProfane()) bonus.addAndGet(2);
                    });
                }
                float damage = (float) (this.DAMAGE.get() + (6 * (1 + bonus.get())) + (this.AMP_VALUE.get() * spellStats.getAmpMultiplier()));

                attemptDamage(level, shooter, spellStats, spellContext, resolver, living, source, damage);
            }
        }
    }




    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE);
    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.THREE;
    }

    @Override
    public int getDefaultManaCost() {
        return 3000;
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(ModRegistry.UNHOLY);
    }

    @Override
    public void addDefaultAugmentLimits(Map<ResourceLocation, Integer> defaults) {
        defaults.put(AugmentAmplify.INSTANCE.getRegistryName(), 4);
        defaults.put(AugmentAOE.INSTANCE.getRegistryName(), 4);
        defaults.put(AugmentAOE.INSTANCE.getRegistryName(), 4);
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 9.0);
        addAmpConfig(builder, 9.0);
    }
}
