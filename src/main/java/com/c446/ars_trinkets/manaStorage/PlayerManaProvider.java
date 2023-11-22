package com.c446.ars_trinkets.manaStorage;

import com.hollingsworth.arsnouveau.common.capability.CapabilityRegistry;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.CapabilityManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerMana> PLAYER_MANA = CapabilityManager.get(new CapabilityToken<PlayerMana>() {
    });
    private PlayerMana playerMana = null;
    private final LazyOptional<PlayerMana> optional = LazyOptional.of(this::createPlayerMana);

    private PlayerMana createPlayerMana() {
        if (this.playerMana == null) {
            this.playerMana = new PlayerMana();
        }
        return this.playerMana;
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