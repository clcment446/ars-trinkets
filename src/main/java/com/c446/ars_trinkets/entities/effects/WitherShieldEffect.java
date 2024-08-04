package com.c446.ars_trinkets.entities.effects;

import com.c446.ars_trinkets.util.ParticleUtil;
import com.c446.ars_trinkets.util.Util;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class WitherShieldEffect extends MobEffect {

    protected WitherShieldEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    protected WitherShieldEffect(MobEffectCategory pCategory, int red, int green, int blue) {
        super(pCategory, ParticleUtil.createIntColor(red, green, blue));
    }

    static void dum(){
//        com.hollingsworth.arsnouveau.common.potions.FreezingEffect;
//        com.hollingsworth.arsnouveau.setup.registry.ModPotions.

    }
}
