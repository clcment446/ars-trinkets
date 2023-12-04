package com.c446.ars_trinkets.item;

public class ManaStone extends MagicalItems implements ICurioItem, IManaEquipment {
    int boost;
    int regen;

    public ManaStone(Properties properties, int boost, int regen) {
        this.boost = boost;
        this.regen = regen;
    }
}