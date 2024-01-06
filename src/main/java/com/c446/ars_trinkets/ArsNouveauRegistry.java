package com.c446.ars_trinkets;

import com.c446.ars_trinkets.glyphs.spell_glyphs.*;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.c446.ars_trinkets.rituals.LevelingRitual;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.registry.GlyphRegistry;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.sound.SpellSound;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.common.items.Glyph;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class ArsNouveauRegistry {

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>(); //this will come handy for datagen

    public static void registerGlyphs(){
        register(SacrificeHealth.INSTANCE);
        register(SacrificeExperience.INSTANCE);
        register(WaterSpear.INSTANCE);
        register(AirSword.INSTANCE);
        register(ShadowVeil.INSTANCE);
        register(SonicBoom.INSTANCE);
        register(SunFlare.INSTANCE);
        register(FilterIsSelf.INSTANCE);
        register(FilterIsNotSelf.INSTANCE);
    }
    public static void registerSounds(){
        }
    public static void register(AbstractSpellPart spellPart){
        GlyphRegistry.registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }
    public static void registerRituals(){
//        registerRitual(new LevelingRitual());
    }
    public static void registerRitual(AbstractRitual ritual) {
        RitualRegistry.registerRitual(ritual);
    }


}
