package com.c446.ars_trinkets.capabilities;

import com.hollingsworth.arsnouveau.api.mana.IManaCap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityRegistry {
    public static final Capability<ArcaneLevels> arcane_cap = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static LazyOptional<ArcaneLevels> getArcaneLevels(final LivingEntity living) {
        if (living == null) {
            return LazyOptional.empty();
        }
        return living.getCapability(arcane_cap);
    }
}
