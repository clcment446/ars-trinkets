package com.c446.ars_trinkets.manaStorage;

import net.minecraft.nbt.CompoundTag;

public class ManaStone {
    private int CrystalMana;

    public int getCrystalMana() {
        return CrystalMana;
    }

    public void addCrystalMana(int Add) {
        this.CrystalMana = CrystalMana + Add;
    }

    public static boolean isSubLegal(int Sub1, int Sub2) {
        return Sub1 - Sub2 > 0;}

    public void subCrystalMana(int Sub) {
        int MINIMUM = 0;
        this.CrystalMana = Math.min(CrystalMana - Sub, MINIMUM);
    }

    public void CopyFrom(ManaStone source) {
        this.CrystalMana = source.CrystalMana;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("CrystalMana", CrystalMana);
    }

    public void loadNBTData(CompoundTag nbt) {
        CrystalMana = nbt.getInt("CrystalMana");
    }
}
