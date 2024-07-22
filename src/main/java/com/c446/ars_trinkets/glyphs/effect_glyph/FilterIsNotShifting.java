package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Set;

public class FilterIsNotShifting extends AbstractEffect {
    public FilterIsNotShifting(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final FilterIsNotShifting INSTANCE = new FilterIsNotShifting(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_filter_not_shift"), "Filter Not Shifting");

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if ((rayTraceResult.getEntity().isCrouching())) {
            spellContext.setCanceled(true);
        }
    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.TWO;
    }

    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return Collections.emptySet();
    }

    @Override
    public int getDefaultManaCost() {
        return 0;
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.MANIPULATION);
    }
}




