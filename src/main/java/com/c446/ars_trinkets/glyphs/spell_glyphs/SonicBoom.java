package com.c446.ars_trinkets.glyphs.spell_glyphs;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Set;

import static com.c446.ars_trinkets.util.Util.Vec1Vec2TraceGet;

public class SonicBoom extends AbstractEffect implements IDamageEffect {
    public SonicBoom(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final SonicBoom INSTANCE = new SonicBoom(new ResourceLocation(ArsTrinkets.MODID, "glyph_sound_boom"), "Sonic Bool");

    double damage;
    double damageBonusTimes;
    float AMP_DAMAGE = 4f;

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 7);
        addAmpConfig(builder, 3.33);
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        Entity entity = rayTraceResult.getEntity();
        if (world instanceof ServerLevel && entity instanceof LivingEntity living){
            Vec3 eyesPosTar = living.getEyePosition();
            Vec3 eyesPosPla = shooter.getEyePosition();
//            ArrayList<double[]> particles = Vec1Vec2TraceGet()



        }
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE, AugmentAOE.INSTANCE);
    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.THREE;
    }

    @Override
    public int getDefaultManaCost() {
        return 1200;
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.ELEMENTAL_AIR);
    }
}