package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.api.util.DamageUtil;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentSensitive;
import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;


import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class AirSword extends AbstractEffect implements IDamageEffect {
    public AirSword(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final AirSword INSTANCE = new AirSword(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_sword"), "Air Sword");


    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        Entity entity = rayTraceResult.getEntity();
        if (entity instanceof LivingEntity living && world instanceof ServerLevel level) {

            Vec3 livingEyes = living.getEyePosition();
            double x = livingEyes.x;
            double y = livingEyes.y;
            double z = livingEyes.z;
            level.sendParticles(ParticleTypes.END_ROD, x, y, z, 1, 0, 0, 0, 1);

            level.sendParticles(ParticleTypes.END_ROD, x, y, z, 1, 0, 0, 0, 1);
            level.sendParticles(ParticleTypes.END_ROD, x, y, z, 1, -0.5, -0.5, 0, 1);
            level.sendParticles(ParticleTypes.END_ROD, x, y, z, 1, 0.5, -0.5, 0, 1);
            level.sendParticles(ParticleTypes.END_ROD, x, y, z, 1, 0.5, 0.5, 0, 1);
            level.sendParticles(ParticleTypes.END_ROD, x, y, z, 1, -0.5, 0.5, 0, 1);

            level.sendParticles(ParticleTypes.SWEEP_ATTACK, x, y, z, 1, 0, 0, 0.5, 1);

            double damageBonusTimes = 1;
            float AMP_DAMAGE = 2f;
            double DAMAGE = 6;
            if (living.hasEffect(ModPotions.HEX_EFFECT.get())) {
                int multiplier = Objects.requireNonNull(living.getEffect(ModPotions.HEX_EFFECT.get())).getAmplifier();
                damageBonusTimes += multiplier / 8.0;
            }
            if (living.hasEffect(ModPotions.SNARE_EFFECT.get())) {

                int s = Objects.requireNonNull(living.getEffect(ModPotions.SNARE_EFFECT.get())).getAmplifier();
                damageBonusTimes += s / 5.0;
            }
            DAMAGE += (spellStats.getAmpMultiplier() * AMP_DAMAGE);
            DAMAGE *= damageBonusTimes/2;
            DamageSource source = DamageUtil.source(level, DamageTypes.PLAYER_ATTACK, shooter);
            attemptDamage(world, shooter, spellStats, spellContext, resolver, entity, source, (float) DAMAGE);
            living.invulnerableTime = 0;
        }
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 17);
        addAmpConfig(builder, 10);
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
        return this.setOf(SpellSchools.ELEMENTAL_AIR);
    }
    @Override
    public void addDefaultAugmentLimits(Map<ResourceLocation, Integer> defaults) {
        defaults.put(AugmentAmplify.INSTANCE.getRegistryName(),4);
        defaults.put(AugmentAOE.INSTANCE.getRegistryName(),4);

    }
}
