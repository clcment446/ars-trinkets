package com.c446.ars_trinkets.manaStorage;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.CapabilityManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ManaStoneProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<ManaStone> PLAYER_MANA = CapabilityManager.get(new CapabilityToken<ManaStone>() {
    });
    private ManaStone manaStone = null;
    private final LazyOptional<ManaStone> optional = LazyOptional.of(this::createPlayerMana);

    private ManaStone createPlayerMana() {
        if (this.manaStone == null) {
            this.manaStone = new ManaStone();
        }
        return this.manaStone;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_MANA) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerMana().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerMana().loadNBTData(nbt);
    }
}