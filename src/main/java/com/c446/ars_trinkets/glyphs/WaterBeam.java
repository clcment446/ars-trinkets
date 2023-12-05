package com.c446.ars_trinkets.glyphs;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Set;

import static com.c446.ars_trinkets.util.Util.Vec1Vec2TraceGet;

public class WaterBeam extends AbstractEffect {

    double AMP_DAMAGE = 0.5D;
    public static WaterBeam INSTANCE = new WaterBeam(ArsTrinkets.prefix("glyph_test"), "Test");

    public WaterBeam(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    public int getDefaultManaCost() {
        return 2500;
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        super.onResolve(rayTraceResult, world, shooter, spellStats, spellContext, resolver);
        Entity entity = rayTraceResult.getEntity();

        if (entity instanceof LivingEntity && world instanceof ServerLevel level) {
            Vec3 vecTar = entity.position();
            Vec3 eyesPos = shooter.getEyePosition(1.0f);
            float damage = (float)(this.DAMAGE.get() + (this.AMP_VALUE.get() * this.AMP_DAMAGE));
            double aoe = spellStats.getAoeMultiplier();
            ArrayList<double[]> particles = Vec1Vec2TraceGet(eyesPos, vecTar, (double) 5 * aoe, 1, (int) (2.5 * aoe));
            for (double[] position : particles) {
                level.sendParticles(ParticleTypes.SPLASH, position[0], position[1], position[2], 3, 0, 0, 0, 1);
            }

        }

        super.onResolve(rayTraceResult, world, shooter, spellStats, spellContext, resolver);
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE);
    }
}
