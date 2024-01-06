package com.c446.ars_trinkets.glyphs.spell_glyphs;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;

import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Set;

public class WaterSpear extends AbstractEffect implements IDamageEffect {


    public static final WaterSpear INSTANCE = new WaterSpear(new ResourceLocation(ArsTrinkets.MODID, "glyph_water_spear"), "Water Spear");

    public WaterSpear(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    public int getDefaultManaCost() {
        return 4500;
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        Entity entity = rayTraceResult.getEntity();
        if (entity instanceof LivingEntity living && world instanceof ServerLevel level) {
            Vec3 vecTar = entity.position();
            double damageBonusTimes = 1;
            float AMP_DAMAGE = 15f;
            double DAMAGE = 30;
            if (living.hasEffect(ModPotions.FREEZING_EFFECT.get())) {
                int s = Objects.requireNonNull(living.getEffect(ModPotions.FREEZING_EFFECT.get())).getAmplifier();
                s /= 2;
                damageBonusTimes += s;
            }
            if (living.hasEffect(ModPotions.SHOCKED_EFFECT.get())) {
                int s = Objects.requireNonNull(living.getEffect(ModPotions.SHOCKED_EFFECT.get())).getAmplifier();
                s /= 2.5;
                damageBonusTimes += s;
            }
            if (living.hasEffect(ModPotions.HEX_EFFECT.get())) {
                int s = Objects.requireNonNull(living.getEffect(ModPotions.HEX_EFFECT.get())).getAmplifier();
                s /= 2;
                damageBonusTimes += s;
            }
            if (living.isOnFire()) {
                damageBonusTimes *= 1.5;
            }
            damageBonusTimes/=4;
            DAMAGE += (spellStats.getAmpMultiplier() * AMP_DAMAGE);
            DAMAGE *= damageBonusTimes;

            level.sendParticles(ParticleTypes.SPLASH,vecTar.x, vecTar.y, vecTar.z,20,0,0,0,1);
            this.attemptDamage(world, shooter, spellStats, spellContext, resolver, entity, buildDamageSource(world,shooter), (float) (DAMAGE));
            living.invulnerableTime = 3;
        }
        com.hollingsworth.arsnouveau.api.util.DamageUtil.source((LevelAccessor) world, DamageTypes.DROWN);
    }
    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE, AugmentAOE.INSTANCE);
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.ELEMENTAL_WATER);
    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.THREE;
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 10.0);
        addAmpConfig(builder, 2.0);
    }
}
