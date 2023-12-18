package com.c446.ars_trinkets.glyphs.spell_glyphs;

import alexthw.ars_elemental.ArsNouveauRegistry;
import alexthw.ars_elemental.registry.ModItems;

import com.c446.ars_trinkets.ArsTrinkets;
import com.dkmk100.arsomega.glyphs.Schools;
import com.dkmk100.arsomega.glyphs.TierFourEffect;
import com.dkmk100.arsomega.util.RegistryHandler;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Set;


public class ExpSacrifice extends AbstractEffect implements IDamageEffect {

    public static final ExpSacrifice INSTANCE = new ExpSacrifice(new ResourceLocation(ArsTrinkets.MODID, "glyph_exp_sac"), "Sacrificing Experience");
    public ExpSacrifice(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    public void onResolveEntity(@NotNull EntityHitResult rayTraceResult, Level world, @NotNull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        Entity entity = rayTraceResult.getEntity();
        if (world instanceof ServerLevel level && entity instanceof LivingEntity living && shooter instanceof Player player) {
            int focus = 0;
            if (resolver.hasFocus(new ItemStack(RegistryHandler.FOCUS_OF_LIFE.get()))) {
                focus += 4;
            }
            if (resolver.hasFocus(new ItemStack(ModItems.NECRO_FOCUS.get()))) {
                focus += 4;
            }
            if (resolver.hasFocus(new ItemStack(RegistryHandler.FOCUS_OF_ADVANCED_ALCHEMY.get()))) {
                focus += 3;
            }
            if (resolver.hasFocus(new ItemStack(RegistryHandler.FOCUS_OF_ALCHEMY.get()))) {
                focus += 2;
            }
            int range = (int) (2 * spellStats.getAoeMultiplier());
            int exp = (int) (0.3 * (1 + focus / 2) * player.totalExperience * spellStats.getAmpMultiplier());
            DamageSource SOURCE = DamageSource.MAGIC.bypassMagic();
            player.giveExperiencePoints(-exp / 3);
            float damage = (float) (DAMAGE.get() + AMP_VALUE.get() * spellStats.getAmpMultiplier());

            Vec3 position = safelyGetHitPos(rayTraceResult);
            attemptDamage(world, shooter, spellStats, spellContext, resolver, living, SOURCE, damage);
            level.sendParticles(ParticleTypes.ENCHANT, position.x, position.y, position.z, 50,
                    ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), ParticleUtil.inRange(-0.1, 0.1), 0.3);
            for (Entity e : world.getEntities(shooter, new AABB(
                    living.position().add(range, range, range), living.position().subtract(range, range, range)))) {
                if (e.equals(living) || !(e instanceof LivingEntity))
                    continue;
                attemptDamage(world, shooter, spellStats, spellContext, resolver, e, SOURCE, damage);
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
        return this.setOf(ArsNouveauRegistry.NECROMANCY, Schools.LIFE, Schools.ALCHEMY);
    }

    @Override
    public SpellTier defaultTier() {
        return TierFourEffect.FOUR;
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addDamageConfig(builder, 13.0);
        addAmpConfig(builder, 9.0);
    }
}
