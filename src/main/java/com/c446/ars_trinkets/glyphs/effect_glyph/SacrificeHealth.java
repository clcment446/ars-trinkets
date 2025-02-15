package com.c446.ars_trinkets.glyphs.effect_glyph;


import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.c446.ars_trinkets.util.ParticleUtil;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.api.util.DamageUtil;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

import java.util.Map;
import java.util.Set;

public class SacrificeHealth extends AbstractEffect implements IDamageEffect {
    public static final SacrificeHealth INSTANCE = new SacrificeHealth(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_sacrifice"), "Sacrifice Health");

    public SacrificeHealth(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    public int getDefaultManaCost() {
        return 100;
    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.THREE;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE);
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.CONJURATION);
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {

        if (resolver.hasFocus(ModRegistry.UNHOLY_FOCUS.get())) {
            spellStats.setAmpMultiplier(spellStats.getAmpMultiplier() + 2);
            spellStats.setDurationMultiplier(spellStats.getDurationMultiplier() + 2);
        }
        Entity entity = rayTraceResult.getEntity();
        if (entity instanceof LivingEntity living && world instanceof ServerLevel level && shooter instanceof Player player) {
            DamageSource source = DamageUtil.source(level, DamageTypes.IN_FIRE, shooter);
            Vec3 pos = living.getEyePosition();
            float health = (float) (0.11* spellStats.getAmpMultiplier() * player.getMaxHealth());
            player.setHealth((player.getMaxHealth() - health)+1);
            float damage = (float) (DAMAGE.get() + AMP_VALUE.get() * health);
/*
damage = (this.DAMAGE.get() * ((this.AMP_VALUE.get())/1.5 * (spellStats.getAmpMultiplier())))*(1 + bonus)/ 1.5;
damage = (this.DAMAGE.get() * (this.AMP_VALUE.get() * (spellStats.getAmpMultiplier())))*(1 + bonus);
* */
            attemptDamage(world, shooter, spellStats, spellContext, resolver, living, source, damage);
            level.sendParticles(ParticleUtil.CreateDustParticle(75,16,16),pos.x,pos.y,pos.z,20,0,0,0,0.3);
            level.sendParticles(ParticleTypes.CRIMSON_SPORE,pos.x,pos.y,pos.z,20,0,0,0,1);
        }
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 10.0);
        addAmpConfig(builder, 2.5);
    }
    @Override
    public void addDefaultAugmentLimits(Map<ResourceLocation, Integer> defaults) {
        defaults.put(AugmentAmplify.INSTANCE.getRegistryName(),4);
        defaults.put(AugmentAOE.INSTANCE.getRegistryName(),4);
    }
}
