package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentExtendTime;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentExtract;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class WitherShield extends AbstractEffect {
    public WitherShield(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final WitherShield INSTANCE = new WitherShield(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_wither_shield"), "Wither Shield");

    @Override
    protected int getDefaultManaCost() {
        return 3500;
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (world instanceof ServerLevel level && rayTraceResult.getEntity() instanceof LivingEntity living) {
            AtomicBoolean is_profane = new AtomicBoolean(false);
            shooter.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a->{
                is_profane.set(a.getProfane());
            });

            if (!(is_profane.get()) && shooter instanceof Player player){
                player.displayClientMessage(Component.translatable("player_not_cursed.glyph_stopped"), true);
                return;
            }

            if (resolver.hasFocus(ModRegistry.UNHOLY_FOCUS.get())){
                spellStats.setAmpMultiplier(spellStats.getAmpMultiplier()+2);
                spellStats.setDurationMultiplier(spellStats.getDurationMultiplier()+2);
            }

            shooter.addEffect(new MobEffectInstance(ModRegistry.WITHER_SHIELD_EFFECT.get(), (int) (this.EXTEND_TIME.get() * spellStats.getDurationMultiplier()), (int) (spellStats.getAmpMultiplier())));
            shooter.addEffect(new MobEffectInstance(MobEffects.WITHER, (int) (this.EXTEND_TIME.get() * spellStats.getDurationMultiplier()/4), 1));

            ((ServerLevel) world).sendParticles(ParticleTypes.SOUL, shooter.getX(), shooter.getY(), shooter.getZ(), (int) (5 * spellStats.getAmpMultiplier()), 0, 1, 0, 1);



        }
    }
    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(ModRegistry.UNHOLY);
    }

    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return setOf(AugmentAmplify.INSTANCE, AugmentExtendTime.INSTANCE);
    }

    @Override
    public void addDefaultAugmentLimits(Map<ResourceLocation, Integer> defaults) {
        defaults.put(AugmentAmplify.INSTANCE.getRegistryName(), 4);
        defaults.put(AugmentAOE.INSTANCE.getRegistryName(), 4);
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addAmpConfig(builder, 1);
        addExtendTimeTicksConfig(builder, 100);
    }
}
