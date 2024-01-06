package com.c446.ars_trinkets.glyphs.spell_glyphs;

import alexthw.ars_elemental.util.ParticleUtil;
import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

import java.util.Set;

public class SacrificeHealth extends AbstractEffect implements IDamageEffect {
    public static final SacrificeHealth INSTANCE = new SacrificeHealth(new ResourceLocation(ArsTrinkets.MODID, "glyph_sacrifice"), "Sacrifice Health");

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
        Entity entity = rayTraceResult.getEntity();
        if (entity instanceof LivingEntity living && world instanceof ServerLevel level && shooter instanceof Player player) {
            DamageSource SOURCE = buildDamageSource(world, shooter);
            Vec3 pos = living.getEyePosition();
            float health = (float) (0.33 * player.getHealth());
            player.setHealth(player.getMaxHealth() - health);
            float damage = (float) (DAMAGE.get() + AMP_VALUE.get() * spellStats.getAmpMultiplier()*health);

            attemptDamage(world, shooter, spellStats, spellContext, resolver, living, SOURCE, damage);
            new ParticleUtil.ParticleBuilder(ParticleUtil.soulColor).spawn(level, pos.x,pos.y,pos.z );
        }
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 1.0);
        addAmpConfig(builder, 2.0);
    }
}
