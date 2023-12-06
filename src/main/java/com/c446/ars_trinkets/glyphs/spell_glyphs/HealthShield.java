package com.c446.ars_trinkets.glyphs.spell_glyphs;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.capability.CapabilityRegistry;
import com.hollingsworth.arsnouveau.common.capability.ManaCap;
import com.hollingsworth.arsnouveau.common.capability.ManaCapAttacher;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HealthShield extends AbstractEffect {

    public HealthShield(ResourceLocation tag, String description) {
        super(tag, description);
    }

    public static final RotateEntity INSTANCE = new RotateEntity(new ResourceLocation(ArsTrinkets.MODID, "glyph_rotate_90"), "Advanced Rotate (90)");

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        shooter.getCapability(CapabilityRegistry.MANA_CAPABILITY).ifPresent(ManaCap -> {
            int maxMana = ManaCap.getMaxMana();
            int manaShield = maxMana / (80 * 6);
            shooter.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, (int) spellStats.getAmpMultiplier() * manaShield));

        });
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addAmpConfig(builder, 2);
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
        return 60;
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.MANIPULATION);
    }

}
