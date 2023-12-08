package com.c446.ars_trinkets.glyphs.spell_glyphs;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.potions.ModPotions;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Set;

public class AirSword extends AbstractEffect implements IDamageEffect {
    public AirSword(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final AirSword INSTANCE = new AirSword(new ResourceLocation(ArsTrinkets.MODID, "glyph_sword"), "Air Sword");

    double damage;
    double damageBonusTimes;
    float AMP_DAMAGE = 4f;

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        Entity entity = rayTraceResult.getEntity();
        if (entity instanceof LivingEntity && world instanceof ServerLevel level) {

            LivingEntity living = (LivingEntity) entity;
            Vec3 livingEyes = living.getEyePosition();
            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, 0, 0, 0, 1);

            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, 0, 0, 0, 1);
            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, -0.5, -0.5, 0, 1);
            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, 0.5, -0.5, 0, 1);
            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, 0.5, 0.5, 0, 1);
            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, -0.5, 0.5, 0, 1);

            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, -0.5, -0.5, 0.5, 1);
            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, 0.5, -0.5, 0.5, 1);
            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, 0.5, 0.5, 0.5, 1);
            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, -0.5, 0.5, 0.5, 1);

            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, -0.5, -0.5, -0.5, 1);
            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, 0.5, -0.5, -0.5, 1);
            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, 0.5, 0.5, -0.5, 1);
            level.sendParticles(ParticleTypes.END_ROD, livingEyes.x, livingEyes.y, livingEyes.z, 1, -0.5, 0.5, -0.5, 1);

            level.sendParticles(ParticleTypes.SWEEP_ATTACK, livingEyes.x, livingEyes.y, livingEyes.z, 1, 0, 0, 0, 1);
            if (living.hasEffect(ModPotions.HEX_EFFECT.get())) {
                damageBonusTimes += Objects.requireNonNull(living.getEffect(ModPotions.HEX_EFFECT.get())).getAmplifier();
            }
            if (living.hasEffect(ModPotions.SNARE_EFFECT.get())) {
                damageBonusTimes += Objects.requireNonNull(living.getEffect(ModPotions.SNARE_EFFECT.get())).getAmplifier();
            }
            damage = (this.DAMAGE.get() + (this.AMP_VALUE.get() * this.AMP_DAMAGE));
            this.attemptDamage(world, shooter, spellStats, spellContext, resolver, entity, DamageSource.MAGIC.bypassMagic(), (float) ((float) damage * 1.5));
            living.invulnerableTime = 0;
        }
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 7);
        addAmpConfig(builder, 3.33);
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
        return 6000;
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.ELEMENTAL_AIR);
    }
}
