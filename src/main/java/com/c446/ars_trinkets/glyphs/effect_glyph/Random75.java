package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.Set;

public class Random75 extends AbstractEffect {
    public Random75(ResourceLocation tag, String description) {
        super(tag, description);
    }
    public static final Random75 INSTANCE = new Random75(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_random_75"), "Random 75%");
    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (!(world.random.nextDouble()< 0.75)) {
            spellContext.setCanceled(true);
        }
    }

    @Override
    protected int getDefaultManaCost() {
        return 50;
    }

    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return setOf();
    }
}
