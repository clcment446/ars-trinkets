package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.api.util.DamageUtil;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SunFlare extends AbstractEffect implements IDamageEffect {
    public SunFlare(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final SunFlare INSTANCE = new SunFlare(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_sun_flare"), "Solar Flare");
    double damage;


    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (world instanceof ServerLevel level && rayTraceResult.getEntity() instanceof LivingEntity living) {
            double range = 1 + 1.7 * spellStats.getAoeMultiplier();
            int bonus = 1;
            DamageSource source = DamageUtil.source(level, DamageTypes.IN_FIRE, shooter);

            if (living.isOnFire()) {
                bonus += 3;
            }
            if (living.hasEffect(ModPotions.FREEZING_EFFECT.get())) {
                bonus += Objects.requireNonNull(living.getEffect(ModPotions.FREEZING_EFFECT.get())).getAmplifier();
            }
            if (living.getMobType() == MobType.UNDEAD || living.getMobType() == MobType.ARTHROPOD || living.getMobType() == MobType.ILLAGER) {
                bonus += 10;
            }

            damage = this.DAMAGE.get() + (6 * (1 + bonus)) + (this.AMP_VALUE.get() * spellStats.getAmpMultiplier());
            if (living instanceof Player player) {
                player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> attemptDamage(level, shooter, spellStats, spellContext, resolver, player, source, (float) ((float) damage * 1.5)));
            }
            attemptDamage(level, shooter, spellStats, spellContext, resolver, living, source, (float) damage);

            level.sendParticles(ParticleTypes.GLOW, living.getX(), living.getY(), living.getZ(), 10, 0, 0, 0, 1);
            level.sendParticles(ParticleTypes.FLAME, living.getX(), living.getY(), living.getZ(), 50, 0, 0, 0, 1);
//            level.sendParticles(new DustParticle.Provider((SpriteSet) ParticleTypes.DUST).createParticle().setColor(1,1,1), 0,0,0,1,0,0, 0,1);

            for (Entity e : world.getEntities(shooter, new AABB(
                    living.position().add(range, range, range), living.position().subtract(range, range, range)))) {
                if (e.equals(living) || !(e instanceof LivingEntity l))
                    continue;

                level.sendParticles(ParticleTypes.GLOW, l.getX(), l.getY(), l.getZ(), 20, 0, 0, 0, 1);
                level.sendParticles(ParticleTypes.FLAME, l.getX(), l.getY(), l.getZ(), 100, 0, 0, 0, 1);

                bonus = 0;

                if (l.isOnFire()) {
                    bonus += 3;
                }
                if (l.hasEffect(ModPotions.FREEZING_EFFECT.get())) {
                    bonus += Objects.requireNonNull(l.getEffect(ModPotions.FREEZING_EFFECT.get())).getAmplifier();
                }
                if (l.getMobType() == MobType.UNDEAD || l.getMobType() == MobType.ARTHROPOD || l.getMobType() == MobType.ILLAGER) {
                    bonus += 10;
                }
                damage = (this.DAMAGE.get() + ((this.AMP_VALUE.get()) / 1.5 * (spellStats.getAmpMultiplier()))) * (1 + bonus) / 1.5;
                if (l instanceof Player player) {
                    player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
                        if (a.getProfane()) {
                            attemptDamage(level, shooter, spellStats, spellContext, resolver, player, source, (float) ((float) damage * 1.5));
                        } else {
                            attemptDamage(level, shooter, spellStats, spellContext, resolver, player, source, (float) damage);
                        }
                    });
                }
                attemptDamage(level, shooter, spellStats, spellContext, resolver, l, source, (float) damage);
            }
        }
    }


    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 13);
        addAmpConfig(builder, 7);
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
        return 3000;
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.ELEMENTAL_FIRE);
    }

    @Override
    public void addDefaultAugmentLimits(Map<ResourceLocation, Integer> defaults) {
        defaults.put(AugmentAmplify.INSTANCE.getRegistryName(), 4);
        defaults.put(AugmentAOE.INSTANCE.getRegistryName(), 4);
    }
}
