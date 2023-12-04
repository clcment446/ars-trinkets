package com.c446.ars_trinkets.glyphs;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import java.util.Set;

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
            int amp = (int) spellStats.getAmpMultiplier();
            double aoe = spellStats.getAoeMultiplier();
            if (!(vecTar.distanceTo(vecOri)>5*amp)){
                /*
                * desc:
                * get difference between vecTar and vecOri
                * using the difference, spawn particules using the difference. (posStart + incr * count
                * summon particle at k
                * increment k
                * if k == length of vectar: stop
                * else continue
                * */

            } else {
                return;
            }


        }

    }


    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE);
    }
}
