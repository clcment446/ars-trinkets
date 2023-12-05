package com.c446.ars_trinkets;

import com.c446.ars_trinkets.glyphs.SacrificeHealth;
import com.c446.ars_trinkets.glyphs.WaterBeam;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.sound.SpellSound;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class ArsNouveauRegistry {

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>(); //this will come handy for datagen

    public static void registerGlyphs(){
        register(SacrificeHealth.INSTANCE);
        register(WaterBeam.INSTANCE);
    }
    public static void registerSounds(){
        ModRegistry.EXAMPLE_SPELL_SOUND = ArsNouveauAPI.getInstance().registerSpellSound(new SpellSound(ModRegistry.EXAMPLE_FAMILY.get(), Component.literal("Example")));
    }
    public static void register(AbstractSpellPart spellPart){
        ArsNouveauAPI.getInstance().registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }

}
