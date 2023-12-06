package com.c446.ars_trinkets.glyphs.spell_glyphs;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.potions.ModPotions;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Set;

import static com.c446.ars_trinkets.util.Util.Vec1Vec2TraceGet;

public class WaterSpear extends AbstractEffect implements IDamageEffect {

    double AMP_DAMAGE = 1.3D;
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
        if (entity instanceof LivingEntity && world instanceof ServerLevel level) {
            Vec3 vecTar = entity.position();
            Vec3 eyesPos = shooter.getEyePosition(1.0f);
            LivingEntity living = (LivingEntity) entity;
            float bonusDamage = 0;
            if (living.hasEffect(ModPotions.FREEZING_EFFECT.get())) {
                bonusDamage += living.getEffect(ModPotions.FREEZING_EFFECT.get()).getAmplifier() * 1.2;
            }
            if (living.hasEffect(ModPotions.SHOCKED_EFFECT.get())) {
                bonusDamage += living.getEffect(ModPotions.SHOCKED_EFFECT.get()).getAmplifier() * 1.3;
            }
            if (living.hasEffect(ModPotions.HEX_EFFECT.get())) {
                bonusDamage += living.getEffect(ModPotions.HEX_EFFECT.get()).getAmplifier() * 1.1;
            }
            if (living.isOnFire()) {
                bonusDamage *= 1.5;
            }

            float damage = (float) (this.DAMAGE.get() + (this.AMP_VALUE.get() * this.AMP_DAMAGE) * bonusDamage);
            double amp = spellStats.getAmpMultiplier();
            double aoe = spellStats.getAoeMultiplier();
            ArrayList<double[]> particles = Vec1Vec2TraceGet(eyesPos, vecTar, (double) 5 * aoe, 1, (int) (this.AMP_DAMAGE * amp * aoe / 2));
            for (double[] position : particles) {
                level.sendParticles(ParticleTypes.SPLASH, position[0], position[1], position[2], 3, 0, 0, 0, 1);
            }
            this.attemptDamage(world, shooter, spellStats, spellContext, resolver, entity, DamageSource.MAGIC.bypassMagic().bypassArmor(), damage*5);
        }
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
