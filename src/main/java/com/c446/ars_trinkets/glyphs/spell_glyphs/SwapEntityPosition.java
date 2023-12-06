package com.c446.ars_trinkets.glyphs.spell_glyphs;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Set;

public class SwapEntityPosition extends AbstractEffect {
    public SwapEntityPosition(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final RotateEntity INSTANCE = new RotateEntity(new ResourceLocation(ArsTrinkets.MODID, "glyph_swap_position"), "Switch Position");

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        Entity entity = rayTraceResult.getEntity();
        if (entity instanceof LivingEntity living) {
            Vec3 ShooterPos = shooter.position();
            Vec3 TargetPos = living.position();
            living.setPos(ShooterPos);
            shooter.setPos(TargetPos);
        }
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE);
    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.TWO;
    }

    @Override
    public int getDefaultManaCost() {
        return 120;
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.MANIPULATION);
    }
}
