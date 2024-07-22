package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.api.util.DamageUtil;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

public class Devour extends AbstractEffect implements IDamageEffect {
    public static final Devour INSTANCE = new Devour(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_devour"), "Air Sword");

    public Devour(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    protected int getDefaultManaCost() {
        return 3000;
    }


    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (world instanceof ServerLevel level && shooter instanceof Player player && rayTraceResult.getEntity() instanceof LivingEntity living) {
            float damage = (float) (DAMAGE.get()+ AMP_VALUE.get()* spellStats.getAmpMultiplier());
            player.setHealth(player.getHealth()+living.getHealth()/100*damage);
            attemptDamage(level,player,spellStats,spellContext,resolver,living, DamageUtil.source(level, DamageTypes.STARVE, player),damage);
        }
    }
    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return setOf(AugmentAmplify.INSTANCE);
    }
    @Override
    protected void addDefaultAugmentLimits(Map<ResourceLocation, Integer> defaults) {
        defaults.put(AugmentAmplify.INSTANCE.getRegistryName(), 3);
    }
    @Override
    public SpellTier defaultTier() {
        return SpellTier.THREE;}
    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 12);
        addAmpConfig(builder, 9);
    }
}
