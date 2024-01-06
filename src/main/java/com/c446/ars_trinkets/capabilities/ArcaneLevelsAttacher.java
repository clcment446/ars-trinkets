package com.c446.ars_trinkets.capabilities;

import com.c446.ars_trinkets.ArsTrinkets;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArcaneLevelsAttacher {

    public static class ArcaneLevelsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        public static final ResourceLocation IDENTIFIER = new  ResourceLocation(ArsTrinkets.MODID,"levels");
        public static Capability<ArcaneLevels> PLAYER_LEVEL = CapabilityManager.get(new CapabilityToken<ArcaneLevels>() {});
        private ArcaneLevels arcane_levels = null;
        private final LazyOptional<ArcaneLevels> optional = LazyOptional.of(this::createArcaneLevel);

        private ArcaneLevels createArcaneLevel() {
            if (this.arcane_levels == null) {
                this.arcane_levels = new ArcaneLevels();
            }
            return this.arcane_levels;
        }

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            if (cap == PLAYER_LEVEL) {
                return optional.cast();
            }
            return LazyOptional.empty();
        }



        @Override
        public CompoundTag serializeNBT() {
            CompoundTag nbt = new CompoundTag();
            createArcaneLevel().saveNBTData(nbt);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            createArcaneLevel().loadNBTData(nbt);
        }


    }
    public static void attach(final AttachCapabilitiesEvent<Entity> event) {
        final ArcaneLevelsProvider provider = new ArcaneLevelsProvider();
        event.addCapability(ArcaneLevelsProvider.IDENTIFIER, provider);
    }
}
