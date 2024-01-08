package com.c446.ars_trinkets.glyphs.effect_glyph;


import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.util.Util;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentSplit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nonnull;
import java.util.Set;

public class GlyphMeteorite extends AbstractEffect implements IDamageEffect {
    public GlyphMeteorite(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final GlyphMeteorite INSTANCE = new GlyphMeteorite(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_meteorite"), "Celestial Shower");


    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (world instanceof ServerLevel level && rayTraceResult.getEntity() instanceof LivingEntity living){
            Vec3 targetEyes = living.getEyePosition();
            Vec3 upper = new Vec3(Util.randomLongFromRange(-15,15),Util.randomLongFromRange(-15,15),Util.randomLongFromRange(75,125));
            Vec3 ground = new Vec3(targetEyes.x+Util.randomLongFromRange(-3,3),targetEyes.y+Util.randomLongFromRange(-3,3),targetEyes.z);
            Util.CreateParticleBeam(upper, targetEyes, level, ParticleTypes.ASH);
        //create explosion and deal damage
        }
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 18);
        addAmpConfig(builder, 5);
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE, AugmentAOE.INSTANCE, AugmentSplit.INSTANCE);

    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.THREE;
    }

    @Override
    public int getDefaultManaCost() {
        return 6000;
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.ELEMENTAL_AIR);
    }
}
