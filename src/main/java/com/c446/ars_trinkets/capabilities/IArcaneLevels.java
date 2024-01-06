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


    public default int getPlayerArcaneLevel() {
        return 0;
    }

    public default void setPlayerArcaneLevel(int level) {
    }


    public default int getPlayerSoulRefinement() {
        return 0;
    }


    public default void setPlayerSoulRefinement(int refinement) {
    }

    public default int getPlayerCollectedSouls() {
        return 0;
    }

    public default void setCollectedSouls(int i) {
    }

    public default boolean getProfane() {
        return false;
    }

    public default void setProfane(Boolean pr) {
    }

    default void calcProfane(Player player) {
    }


    public default MutableComponent getPlayerTitle() {
        return (MutableComponent) MutableComponent.EMPTY;
    }

    public default int getPlayerManaBonus() {
        return 0;
    }

    public default int getPlayerRegenBonus() {

        return 0;
    }


    public default int checkRefinement(int refinement, int level, Player player) {

        return 0;
    }

    @Deprecated
    public default void sendPlayerRefinement(Player player) {
    }

    public default void triggerPlayerBreakthrough(Player player) {

    }

    public default void updateSoulEssence(int quantity, boolean slain, Player player) {
    }

    public default void playerMain(Player player) {
    }

    public default void CopyFrom(com.c446.ars_trinkets.capabilities.ArcaneLevels source) {
    }

    public default void saveNBTData(CompoundTag nbt) {
    }

    public default void loadNBTData(CompoundTag nbt) {
    }
}



