package com.c446.ars_trinkets;

import com.c446.ars_trinkets.glyphs.forms.AuraForm;
import com.c446.ars_trinkets.glyphs.forms.FormMissile;
import com.c446.ars_trinkets.glyphs.forms.FormOverhead;
import com.c446.ars_trinkets.glyphs.propagators.*;
import com.c446.ars_trinkets.glyphs.effect_glyph.*;
import com.c446.ars_trinkets.item.SoulsInspector;
import com.hollingsworth.arsnouveau.api.registry.GlyphRegistry;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import net.minecraft.world.entity.monster.Giant;

import java.util.ArrayList;
import java.util.List;

public class ArsNouveauRegistry {

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>(); //this will come handy for datagen

    public static void registerGlyphs() {
        register(SacrificeHealth.INSTANCE);
        register(SacrificeExperience.INSTANCE);
        register(WaterSpear.INSTANCE);
        register(AirSword.INSTANCE);
        register(ShadowVeil.INSTANCE);
        register(SonicBoom.INSTANCE);
        register(SunFlare.INSTANCE);
        register(FilterIsSelf.INSTANCE);
        register(FilterIsNotSelf.INSTANCE);
        register(ManaBomb.INSTANCE);
        register(InspectSoul.INSTANCE);
        register(Devour.INSTANCE);
        register(DevourSoul.INSTANCE);
        register(WitherShield.INSTANCE);
        register(GiantStrength.INSTANCE);
//        register(Random25.INSTANCE);
//        register(Random50.INSTANCE);
//        register(Random75.INSTANCE);

        // register omega glyphs
        register(FormMissile.INSTANCE);
        register(FormOverhead.INSTANCE);
        register(PropagateOverhead.INSTANCE);
        register(PropagateMissile.INSTANCE);
        register(AuraForm.INSTANCE);



    }

    public static void registerSounds() {
    }

    public static void register(AbstractSpellPart spellPart) {
        GlyphRegistry.registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }

    public static void registerRituals() {
//        registerRitual(new LevelingRitual());
    }

    public static void registerRitual(AbstractRitual ritual) {
        RitualRegistry.registerRitual(ritual);
    }


}
