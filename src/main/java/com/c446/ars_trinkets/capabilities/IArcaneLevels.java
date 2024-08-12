package com.c446.ars_trinkets.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;

public interface IArcaneLevels {
    // counts as the player's level.
    int player_arcane_level = 0;
    // counts as the player's experience.
    int player_soul_refinement = 0;
    int player_collected_souls = 0;
    HashMap<Integer, String[]> player_titles = null;
    boolean profane = false;


    default int getPlayerArcaneLevel() {
        return 0;
    }

    default void setPlayerArcaneLevel(int level) {
    }


    default int getPlayerSoulRefinement() {
        return 0;
    }


    default void setPlayerSoulRefinement(int refinement) {
    }

    default int getPlayerCollectedSouls() {
        return 0;
    }

    default void setCollectedSouls(int i) {
    }

    default boolean getProfane() {
        return false;
    }

    default void setProfane(Boolean pr) {
    }

    default int calcProfane(Player player) {
        return 0;
    }


    default MutableComponent getPlayerTitle() {
        return (MutableComponent) MutableComponent.EMPTY;
    }

    default int getPlayerManaBonus() {
        return 0;
    }

    default int getPlayerRegenBonus() {

        return 0;
    }


    float getPlayerDefense();

    double getSpellDmg();

    double getPhysDmg();

    default int checkRefinement(Player player) {

        return 0;
    }

    @Deprecated
    default void sendPlayerRefinement(Player player) {
    }

    default void triggerPlayerBreakthrough(Player player) {

    }

    default void updateSoulEssence(int quantity, boolean slain, Player player) {
    }

    default void playerMain(Player player) {
    }

    default void CopyFrom(com.c446.ars_trinkets.capabilities.ArcaneLevels source) {
    }

    default void saveNBTData(CompoundTag nbt) {
    }

    default void loadNBTData(CompoundTag nbt) {
    }
}



