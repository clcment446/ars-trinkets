package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentExtendTime;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Set;


public class ShadowVeil extends AbstractEffect {
    public ShadowVeil(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final ShadowVeil INSTANCE = new ShadowVeil(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_shadow_veil"), "Shadow Veil");

    double damage;

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 7);
        addAmpConfig(builder, 3.33);
    }


    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE, AugmentExtendTime.INSTANCE);
    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.THREE;
    }

    @Override
    public int getDefaultManaCost() {
        return 2000;
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.ELEMENTAL_WATER);
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        Entity entity = rayTraceResult.getEntity();
        if (entity instanceof LivingEntity living && world instanceof ServerLevel level) {
            living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, (int) (20+20 * spellStats.getDurationMultiplier()), (int) (1+2 * spellStats.getAmpMultiplier())));
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (20+20 * spellStats.getDurationMultiplier()), (int) (1+0.5 * spellStats.getAmpMultiplier())));
            living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, (int) (20+20 * spellStats.getDurationMultiplier()), (int)(1+1.5* spellStats.getAmpMultiplier())));
            Vec3 livingPos = living.getEyePosition();
            level.sendParticles(ParticleTypes.SQUID_INK, livingPos.x, livingPos.y, livingPos.z, 100, 0, 0, 0, 0);
        }
    }
}
