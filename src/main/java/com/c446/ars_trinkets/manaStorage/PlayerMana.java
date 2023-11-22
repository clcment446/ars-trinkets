package com.c446.ars_trinkets.manaStorage;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana {
    private int CrystalMana;
    private final int MINIMUM = 0;

    public int getCrystalMana() {
        return CrystalMana;
    }

    public void addCrystalMana(int Add) {
        this.CrystalMana = CrystalMana + Add;
    }

    public void subCrystalMana(int Sub) {
        this.CrystalMana = Math.min(CrystalMana - Sub, this.MINIMUM);
    }

    public void CopyFrom(PlayerMana source) {
        this.CrystalMana = source.CrystalMana;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("CrystalMana", CrystalMana);
    }

    public void loadNBTData(CompoundTag nbt) {
        CrystalMana = nbt.getInt("CrystalMana");
    }
}
