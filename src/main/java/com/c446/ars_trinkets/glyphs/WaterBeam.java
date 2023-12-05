package com.c446.ars_trinkets.glyphs;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.c446.ars_trinkets.util.Util;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Set;

import static com.c446.ars_trinkets.util.Util.Vec1Vec2TraceGet;

public class WaterBeam extends AbstractEffect {

    public static WaterBeam INSTANCE = new WaterBeam(ArsTrinkets.prefix("glyph_test"), "Test");

    public WaterBeam(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    public int getDefaultManaCost() {
        return 100;
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        super.onResolve(rayTraceResult, world, shooter, spellStats, spellContext, resolver);
        Entity entity = rayTraceResult.getEntity();
        if (entity instanceof LivingEntity) {
            Vec3 vecTar = entity.position();
            Vec3 vecOri = shooter.position();
            double aoe= spellStats.getAoeMultiplier();
            ArrayList<double[]> particles = Vec1Vec2TraceGet(vecOri, vecTar, (double)5*aoe,(double) 1, (int) (2.5*aoe));
            for (int i = 0; i<particles.size(); i++) {
                double[] position = particles.get(i);
                //world.addParticle(particleoptions);
            }

        }

    }


    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE);
    }
}
