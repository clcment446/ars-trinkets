package com.c446.ars_trinkets.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class TrinketsDamageTypes {
    public static final ResourceKey<DamageType> DUMMY_SRC;

    public TrinketsDamageTypes() {
    }

    static {
        DUMMY_SRC = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("ars_trinkets", "test_src"));

    }
}
