package com.c446.ars_trinkets.glyphs.effect_glyph;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher;
import com.hollingsworth.arsnouveau.api.spell.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Set;

import static alexthw.ars_elemental.ArsNouveauRegistry.NECROMANCY;

public class InspectSoul extends AbstractEffect {
    public InspectSoul(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    protected int getDefaultManaCost() {
        return 1000;
    }

    @Override
    protected @NotNull Set<AbstractAugment> getCompatibleAugments() {
        return setOf();
    }

    @Override
    protected @NotNull Set<SpellSchool> getSchools() {
        return this.setOf(NECROMANCY);
    }


    public static final InspectSoul INSTANCE = new InspectSoul(new ResourceLocation(ArsTrinkets.MOD_ID, "glyph_soul_inspector"), "Soul Inspector");
    Component title;


    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        if (rayTraceResult.getEntity() instanceof Player living && shooter instanceof Player player) {
            player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a ->
            {
                player.getCapability(ArcaneLevelsAttacher.ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(b -> {
                            if (!(a.getPlayerArcaneLevel() <= b.getPlayerArcaneLevel())) {
                                player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.whispers.forbidden"), false);
                            } else {
                                title = a.getPlayerTitle();
                                player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.whispers.lookat"), false);
                                player.displayClientMessage(title, false);
                            }
                        }
                );
            });
        }
    }
}

//