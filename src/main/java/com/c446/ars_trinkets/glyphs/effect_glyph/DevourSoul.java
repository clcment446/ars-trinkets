package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.capabilities.ArcaneLevels;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.api.util.DamageUtil;
import com.hollingsworth.arsnouveau.api.util.SourceUtil;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAOE;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class DevourSoul extends AbstractEffect implements IDamageEffect {
    public static final DevourSoul INSTANCE = new DevourSoul(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_devour_soul"), "Devour Soul");

    private int soulRef = 0;

    public DevourSoul(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    protected int getDefaultManaCost() {
        return 3000;
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (rayTraceResult.getEntity() instanceof LivingEntity living && world instanceof ServerLevel level && shooter instanceof Player player) {

            if (resolver.hasFocus(ModRegistry.UNHOLY_FOCUS.get())) {
                spellStats.setAmpMultiplier(spellStats.getAmpMultiplier() + 2);
                spellStats.setDurationMultiplier(spellStats.getDurationMultiplier() + 2);
            }
            AtomicBoolean isProfane = new AtomicBoolean();
            player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
                isProfane.set(a.getProfane());
            });
            if (!isProfane.get()) {
                player.displayClientMessage(Component.translatable("player_not_cursed.glyph_stopped"), true);
                return;
            }
            if (living != shooter) {
                player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> soulRef = a.getPlayerSoulRefinement());
                if (soulRef > Math.pow(living.getHealth(), 3)) {
                    Vec3 pos = living.getEyePosition();
                    attemptDamage(level, shooter, spellStats, spellContext, resolver, living, DamageUtil.source(level, DamageTypes.MAGIC, shooter), living.getHealth());
                    level.sendParticles(ParticleTypes.SCULK_SOUL, pos.x, pos.y, pos.z, 100, 0, 0, 0, 2);
                    player.setHealth(player.getHealth() / 5 + 1);
                    if (living.getHealth() < 0) {
                        player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.devour"), true);
                        player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {
                            a.setPlayerSoulRefinement((int) (a.getPlayerSoulRefinement() + 20 * living.getHealth()));
                            a.setCollectedSouls((int) (a.getPlayerCollectedSouls() + 20 * living.getHealth()));
                        });
                    }
                }
            }
        }
    }

    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return setOf();
    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.THREE;
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(ModRegistry.UNHOLY);
    }
}