package com.c446.ars_trinkets.glyphs;

import com.c446.ars_trinkets.ArsTrinkets;
import com.hollingsworth.arsnouveau.api.spell.AbstractEffect;
import com.hollingsworth.arsnouveau.api.spell.SpellTier;
import net.minecraft.resources.ResourceLocation;

public abstract class TierArcaneEffect extends AbstractEffect {
    public TierArcaneEffect(ResourceLocation tag, String description){
        super(tag, description);
    }
public static SpellTier ArcaneTier = SpellTier.createTier(new ResourceLocation(ArsTrinkets.MODID, "arcane"),5);

}
