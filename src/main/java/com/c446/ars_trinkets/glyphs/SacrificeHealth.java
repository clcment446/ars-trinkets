package com.c446.ars_trinkets.glyphs;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.capability.CapabilityRegistry;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

import java.util.Set;

public class SacrificeHealth extends AbstractEffect implements IDamageEffect{

    public static SacrificeHealth INSTANCE = new SacrificeHealth(ArsTrinkets.prefix("glyph_sacrifice"), "Sacrifice Health");

    public SacrificeHealth(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    public int getDefaultManaCost() {
        return 100;
    }

    @Override
    public void onResolveEntity(EntityHitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        Entity entity = rayTraceResult.getEntity();
        if (entity instanceof LivingEntity){
            LivingEntity living = (LivingEntity) entity;
            int amp = (int) spellStats.getAmpMultiplier();
            living.getCapability(CapabilityRegistry.MANA_CAPABILITY).ifPresent(iManaCap -> {iManaCap.addMana(living.getHealth()*100* amp);});
            this.attemptDamage(world, shooter, spellStats, spellContext, resolver, living, DamageSource.MAGIC,  living.getHealth()*1/3);
        }



        super.onResolve(rayTraceResult, world, shooter, spellStats, spellContext, resolver);


    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE);
    }
    @Override
    protected @NotNull Set<SpellSchool> getSchools(){
        return this.setOf(SpellSchools.ELEMENTAL_WATER);
    }
}
