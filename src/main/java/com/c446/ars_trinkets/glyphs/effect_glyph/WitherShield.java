package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentExtendTime;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentExtract;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

public class WitherShield extends AbstractEffect {
    public WitherShield(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final WitherShield INSTANCE = new WitherShield(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_wither_shield"), "Wither Shield");

    @Override
    protected int getDefaultManaCost() {
        return 3500;
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (world instanceof ServerLevel level && rayTraceResult.getEntity() instanceof LivingEntity living) {
            shooter.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, (int) (400 + 20 * (spellStats.getDurationMultiplier() * 5)), (int) (spellStats.getAmpMultiplier() * 2.5)));
            shooter.addEffect(new MobEffectInstance(MobEffects.REGENERATION, (int) (400 + 20 * (spellStats.getDurationMultiplier() * 5)), (int) (spellStats.getAmpMultiplier() * 2)));
            shooter.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, (int) (400 + 20 * (spellStats.getDurationMultiplier() * 5)), (int) (spellStats.getAmpMultiplier() * 1.5)));

            shooter.addEffect(new MobEffectInstance(MobEffects.WITHER, (int) (100 + 5 * (spellStats.getDurationMultiplier() * 5)), (int) (spellStats.getAmpMultiplier())));

            ((ServerLevel) world).sendParticles(ParticleTypes.SOUL, shooter.getX(), shooter.getY(), shooter.getZ(), (int) (5 * spellStats.getAmpMultiplier()), 0, 1, 0, 1);



        }
    }

    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return setOf(AugmentAmplify.INSTANCE, AugmentExtendTime.INSTANCE);
    }

    @Override
    public void addDefaultAugmentLimits(Map<ResourceLocation, Integer> defaults) {
        defaults.put(AugmentAmplify.INSTANCE.getRegistryName(), 10);
        defaults.put(AugmentAOE.INSTANCE.getRegistryName(), 10);
    }
}
