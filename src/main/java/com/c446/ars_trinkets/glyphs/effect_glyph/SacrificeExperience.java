package com.c446.ars_trinkets.glyphs.effect_glyph;



import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.api.util.DamageUtil;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;


public class SacrificeExperience extends AbstractEffect implements IDamageEffect {

    public static final SacrificeExperience INSTANCE = new SacrificeExperience(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_exp_sac"), "Corrupted Blessing");

    public SacrificeExperience(ResourceLocation tag, String description) {
        super(tag, description);
    }

    float damage;

    @Override
    public void onResolveEntity(@NotNull EntityHitResult rayTraceResult, Level world, @NotNull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {

        AtomicBoolean is_profane = new AtomicBoolean(false);
        shooter.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a->{
            is_profane.set(a.getProfane());
        });

        if (!(is_profane.get()) && shooter instanceof Player player){
            player.displayClientMessage(Component.translatable("player_not_cursed.glyph_stopped"), true);
            return;
        }

        if (resolver.hasFocus(ModRegistry.UNHOLY_FOCUS.get())) {
            spellStats.setAmpMultiplier(spellStats.getAmpMultiplier() + 2);
            spellStats.setDurationMultiplier(spellStats.getDurationMultiplier() + 2);
        }

        Entity entity = rayTraceResult.getEntity();
        if (world instanceof ServerLevel level && entity instanceof LivingEntity living && shooter instanceof Player player) {

            int range = (int) (1.5 * (1 + spellStats.getAoeMultiplier()));
            long exp = (long) (5 * Math.log((long) ((0.3*(1+spellStats.getAmpMultiplier())*player.experienceLevel))));
            player.giveExperiencePoints(-(int) exp);
            damage = (float) (DAMAGE.get() + AMP_VALUE.get() * spellStats.getAmpMultiplier()) * exp;

            Vec3 position = safelyGetHitPos(rayTraceResult);
            level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, position.x, position.y, position.z, 20, ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), 0.3);
            level.sendParticles(ParticleTypes.SCULK_SOUL, position.x, position.y, position.z, 100, ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), 0.6);
            living.setSecondsOnFire((int) (3*(1+spellStats.getDurationMultiplier())));
            attemptDamage(world, shooter, spellStats, spellContext, resolver, living, DamageUtil.source(level, DamageTypes.MAGIC, shooter), damage);

//            level.sendParticles(ParticleTypes.WITCH, position.x, position.y, position.z, 100, ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), 3);

            for (Entity e : world.getEntities(shooter, new AABB(
                    living.position().add(range, range, range), living.position().subtract(range, range, range)))) {
                if (e.equals(living) || !(e instanceof LivingEntity))
                    continue;
                attemptDamage(world, shooter, spellStats, spellContext, resolver, e, buildDamageSource(world, shooter), damage *= 0.7);
                level.sendParticles(ParticleTypes.ENCHANT, position.x, position.y, position.z, 100,
                        ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), 3);
            }
        }
    }

    @Override
    public int getDefaultManaCost() {
        return 2000;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE, AugmentAOE.INSTANCE);
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.CONJURATION);
    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.THREE;
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 13.0);
        addAmpConfig(builder, 9.0);
        addExtendTimeConfig(builder,10);
    }

    @Override
    public void addDefaultAugmentLimits(Map<ResourceLocation, Integer> defaults) {
        defaults.put(AugmentAmplify.INSTANCE.getRegistryName(), 4);
        defaults.put(AugmentAOE.INSTANCE.getRegistryName(), 4);
    }
}
