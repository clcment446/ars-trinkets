package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.item.UnholyFocus;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.c446.ars_trinkets.util.ParticleUtil;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentExtendTime;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class GiantStrength extends AbstractEffect {
    public GiantStrength(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final GiantStrength INSTANCE = new GiantStrength(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_giant_strength"), "Giant Strength");

    @Override
    protected int getDefaultManaCost() {
        return 3500;
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (world instanceof ServerLevel level && rayTraceResult.getEntity() instanceof LivingEntity living) {


            shooter.addEffect(new MobEffectInstance(MobEffects.JUMP, (int) (400 + 20 * (spellStats.getDurationMultiplier() * 5)), (int) (spellStats.getAmpMultiplier()*0.5)));
            shooter.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, (int) (400 + 20 * (spellStats.getDurationMultiplier() * 5)), (int) (spellStats.getAmpMultiplier() * 2)));
            shooter.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, (int) (400 + 20 * (spellStats.getDurationMultiplier() * 5)), (int) (spellStats.getAmpMultiplier() * 2)));

            shooter.addEffect(new MobEffectInstance(MobEffects.CONFUSION, (int) (100 + 5 * (spellStats.getDurationMultiplier() * 5)), (int) (spellStats.getAmpMultiplier())));

            ((ServerLevel)world).sendParticles(ParticleUtil.CreateDustParticle(100,30,30), shooter.getX(), shooter.getY()+2, shooter.getZ(), 50,0,0.2,0,1);
            ((ServerLevel)world).sendParticles(ParticleUtil.CreateDustParticle(100,30,30), shooter.getX(), shooter.getY()+1.75, shooter.getZ(), 3,0,0.2,0.1,0);
            ((ServerLevel)world).sendParticles(ParticleUtil.CreateDustParticle(100,30,30), shooter.getX(), shooter.getY()+1.75, shooter.getZ(), 3,0,0.2,-0.1,0);
            ((ServerLevel)world).sendParticles(ParticleUtil.CreateDustParticle(100,30,30), shooter.getX(), shooter.getY()+1.75, shooter.getZ(), 3,0.1,0.2,0,0);
            ((ServerLevel)world).sendParticles(ParticleUtil.CreateDustParticle(100,30,30), shooter.getX(), shooter.getY()+1.75, shooter.getZ(), 3,-0.1,0.2,0,0);
            if(resolver.hasFocus(ModRegistry.UNHOLY_FOCUS.get())){

            }

        }
    }
    @Override
    public SpellTier defaultTier() {
        return SpellTier.THREE;
    }
    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return setOf(AugmentAmplify.INSTANCE, AugmentExtendTime.INSTANCE);
    }
    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.ABJURATION, ModRegistry.UNHOLY);
    }
//




    @Override
    public void addDefaultAugmentLimits(Map<ResourceLocation, Integer> defaults) {
        defaults.put(AugmentAmplify.INSTANCE.getRegistryName(), 10);
        defaults.put(AugmentAOE.INSTANCE.getRegistryName(), 10);
    }
}
