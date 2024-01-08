package com.c446.ars_trinkets.glyphs.forms;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.entities.EntityMissileSpell;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.*;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Set;

public class FormMissile extends AbstractCastMethod {
    /**
     * Shamelessly stolen from DKMK's code
     * */
    public static FormMissile INSTANCE = new FormMissile("missile","Missile");

    private FormMissile(String tag, String description) {
        super(ModRegistry.getGlyphName(tag),description);
    }

    public int getDefaultManaCost() {
        return 20;
    }

    public void summonProjectiles(Level world, LivingEntity shooter, SpellStats stats, SpellResolver resolver) {
        final boolean activate = true;
        int duration = 30 + (int)Math.round(7f * stats.getDurationMultiplier());
        duration = Math.max(5,duration);
        ArrayList<EntityMissileSpell> projectiles = new ArrayList<>();
        EntityMissileSpell projectileSpell = new EntityMissileSpell(world, resolver,duration,activate, (float)stats.getAoeMultiplier(),shooter);
        projectiles.add(projectileSpell);
        int numSplits = stats.getBuffCount(AugmentSplit.INSTANCE);

        for(int i = 1; i < numSplits + 1; ++i) {
            Direction offset = shooter.getDirection().getClockWise();
            if (i % 2 == 0) {
                offset = offset.getOpposite();
            }

            BlockPos projPos = shooter.blockPosition().relative(offset, i);
            projPos = projPos.offset(0, (int) 1.5D, 0);
            EntityMissileSpell spell = new EntityMissileSpell(world, resolver,duration,activate,(float)stats.getAoeMultiplier(),shooter);
            spell.setPos(projPos.getX(), projPos.getY(), projPos.getZ());
            projectiles.add(spell);
        }

        float velocity = 1.0F + (float)stats.getBuffCount(AugmentAccelerate.INSTANCE);

        for (EntityMissileSpell proj : projectiles) {
            proj.shoot(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, velocity, 0.8F);
            world.addFreshEntity(proj);
        }
    }

    public void summonProjectiles(Level world, BlockPos pos, LivingEntity shooter, SpellStats stats, SpellResolver resolver) {
        final boolean activate = true;
        int duration = 30 + (int)Math.round(7f * stats.getDurationMultiplier());
        duration = Math.max(5,duration);
        ArrayList<EntityMissileSpell> projectiles = new ArrayList<>();
        EntityMissileSpell projectileSpell = new EntityMissileSpell(world, resolver,duration,activate,(float)stats.getAoeMultiplier(),shooter);
        projectileSpell.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
        projectiles.add(projectileSpell);
        int numSplits = stats.getBuffCount(AugmentSplit.INSTANCE);

        for(int i = 1; i < numSplits + 1; ++i) {
            Direction offset = shooter.getDirection().getClockWise();
            if (i % 2 == 0) {
                offset = offset.getOpposite();
            }

            BlockPos projPos = pos.relative(offset, i);
            projPos = projPos.offset(0, (int) 1.5D, 0);
            EntityMissileSpell spell = new EntityMissileSpell(world, resolver,duration,activate,(float)stats.getAoeMultiplier(),shooter);
            spell.setPos(projPos.getX(), projPos.getY(), projPos.getZ());
            projectiles.add(spell);
        }

        for (EntityMissileSpell proj : projectiles) {
            proj.setDeltaMovement(new Vec3(0.0D, -0.1D, 0.0D));
            world.addFreshEntity(proj);
        }

    }

    @Override
    public CastResolveType onCast(ItemStack stack, LivingEntity shooter, Level world, SpellStats stats, SpellContext context, SpellResolver resolver) {
        this.summonProjectiles(world, shooter, stats, resolver);
        return CastResolveType.SUCCESS;
    }

    @Override
    public CastResolveType onCastOnBlock(UseOnContext context, SpellStats stats, SpellContext spellContext, SpellResolver resolver) {
        Level world = context.getLevel();
        Player shooter = context.getPlayer();
        this.summonProjectiles(world, shooter, stats, resolver);
        return CastResolveType.SUCCESS;
    }

    @Override
    public CastResolveType onCastOnBlock(BlockHitResult blockRayTraceResult, LivingEntity caster,  SpellStats stats, SpellContext spellContext, SpellResolver resolver) {
        caster.lookAt(EntityAnchorArgument.Anchor.EYES, blockRayTraceResult.getLocation().add(0.0D, 0.0D, 0.0D));
        this.summonProjectiles(caster.getCommandSenderWorld(), blockRayTraceResult.getBlockPos(), caster, stats, resolver);
        return CastResolveType.SUCCESS;
    }

    @Override
    public CastResolveType onCastOnEntity(ItemStack stack, LivingEntity caster, Entity target, InteractionHand hand,  SpellStats stats, SpellContext spellContext, SpellResolver resolver) {
        this.summonProjectiles(caster.getCommandSenderWorld(), caster, stats, resolver);
        return CastResolveType.SUCCESS;
    }


    @Nonnull
    public Set<AbstractAugment> getCompatibleAugments() {
        return this.augmentSetOf(AugmentPierce.INSTANCE, AugmentSplit.INSTANCE, AugmentAccelerate.INSTANCE, AugmentSensitive.INSTANCE, AugmentDurationDown.INSTANCE, AugmentExtendTime.INSTANCE, AugmentAOE.INSTANCE);
    }

    public String getBookDescription() {
        return "A spell you start with. Summons a projectile that applies spell effects when this projectile hits a target or block. Sensitive will allow Projectiles to break plants or other materials that do not block motion.";
    }

    @Override
    public SpellTier defaultTier() {
        return SpellTier.TWO;
    }

    @NotNull
    @Override
    public Set<SpellSchool> getSchools() {
        return this.setOf(SpellSchools.ELEMENTAL_AIR, SpellSchools.ELEMENTAL_FIRE);
    }
}