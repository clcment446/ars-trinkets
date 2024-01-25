package com.c446.ars_trinkets.capabilities;

import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.util.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher.ArcaneLevelsProvider;

import java.util.Random;

public class ArcaneLevels implements IArcaneLevels {
    // counts as the player's level.
    private int player_arcane_level = 1;
    // counts as the player's experience.
    private int player_soul_refinement = 100;
    private int player_collected_souls = 0;
    private int player_cores = 1;
    private boolean profane = false;
    protected static final int minimum_level = 0;
    protected static final int maximum_level = 8;
    private boolean warning_2 = false;
    private boolean warning_3 = false;
    private boolean cursed_chains = false;
    public boolean warned1 = false;
    public boolean warned2 = false;
    private int feeding_time = 600 * 20;
    private int last_feed;

    public void setCursedChains(boolean bool) {
        cursed_chains = bool;
    }

    public boolean getCursedChains() {
        return cursed_chains;
    }

    public void setFed() {
        last_feed = 0;
        warned1 = false;
        warned2 = false;
    }

    public void setLastFed(int time) {
        last_feed = time;
    }

    public int getLastFed() {
        return last_feed;
    }

    public int getFeedingTime() {
        return feeding_time;
    }

    @Override
    public int getPlayerArcaneLevel() {
//        System.out.println("player arcane level asked");
        return player_arcane_level;
    }

    public int getCores() {
        return player_cores;
    }

    @Override
    public void setPlayerArcaneLevel(int level) {
        this.player_arcane_level = level;
//        System.out.println("Player arcane level modified");
    }

    @Override
    public int getPlayerSoulRefinement() {
//        System.out.println("Player soul refinement asked");
        return player_soul_refinement;
    }

    @Override
    public void setPlayerSoulRefinement(int refinement) {
//        System.out.println("Player soul refinement modified");
        this.player_soul_refinement = refinement;
    }

    @Override
    public int getPlayerCollectedSouls() {
//        System.out.println("player collected souls asked");
        return player_collected_souls;
    }

    @Override
    public void setCollectedSouls(int i) {
//        System.out.println("Collected souls modified");
        this.player_collected_souls = i;
    }

    @Override
    public boolean getProfane() {
//        System.out.println("Profane Asked");
        return this.profane;
    }

    @Override
    public void setProfane(Boolean pr) {
//        System.out.println("Profane Modified");
        this.profane = pr;
    }

    public float getCorruption() {
        if (this.last_feed / 600 * 20 > 1) {
            return 0.1F;
        } else {return 1-this.last_feed / 600 * 20;}
    }

    @Override
    public void calcProfane(Player player) {
        int severity = 0;
        if (player_soul_refinement + 1000 - 2* player_collected_souls < 0) {
            severity++;
        }
        if (player_soul_refinement + 1000 -  player_collected_souls < 0) {
            severity++;
        }
//        System.out.println("Severity Calced");
        switch (severity) {
            case 0: {
//                System.out.println("Severity 0");
                return;
            }
            case 1: {
                if (!profane) {
//                    player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.approach_madness"), false);
//                    System.out.println("Severity 2");
                }
            }
            case 2: {
                if (!profane) {
                    setProfane(true);
                    player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.madness"), false);
//                    System.out.println("Severity 3");
                }
            }
        }
    }

    @Override
    public MutableComponent getPlayerTitle() {
        if (!profane) {
            return Component.translatable("text.ars_trinkets.titles.asc" + getPlayerArcaneLevel());
        }
//        System.out.println("Title asked");
        return Component.translatable("text.ars_trinkets.titles.dsc" + getPlayerArcaneLevel());
    }

    @Override
    public int getPlayerManaBonus() {
        //        System.out.println("Max Mana bonus questioned");
        int x;
        switch (player_arcane_level) {
            case 1 -> x = 1000;
            case 2 -> x = 2500;
            case 3 -> x = 5000;
            case 4 -> x = 12500;
            case 5 -> x = 18750;
            case 6 -> x = 25000;
            case 7 -> x = 50000;
            case 8 -> x = 125000;
            default -> x = 0;
        }
//        System.out.println("MANA INTEROGATED" + player_cores * x * this.getCorruption());
        return (int) (player_cores * x * getCorruption());
    }

    @Override
    public int getPlayerRegenBonus() {
        int x;
        switch (player_arcane_level) {
            case 1 -> x = 20;
            case 2 -> x = 100;
            case 3 -> x = 200;
            case 4 -> x = 500;
            case 5 -> x = 750;
            case 6 -> x = 1000;
            case 7 -> x = 2000;
            case 8 -> x = 5000;
            default -> x = 0;
        }
//        System.out.println("Mana Regen bonus interrogated" + x * player_cores * getCorruption());
        return (int) (x * player_cores * getCorruption());
    }

    @Override
    public int checkRefinement(Player player) {
//        System.out.println("CheckRefinement triggered");
        float x = (float) player_soul_refinement / Util.getAllRefinementStages().get(this.getPlayerArcaneLevel());
        if (x < 0.5) {
//            System.out.println("case 1");
            return 1;
        } else if (x > 0.5 && x < 0.75) {
//            System.out.println("case 2");
            return 2;
        } else if (x > 0.75 && x < 0.99) {
//            System.out.println("case 3");
            return 3;
        } else if (x > 0.99) {
//            System.out.println("case 4");
            return 4;
        }
        return 0;
    }

    public void nextRank() {
        player_arcane_level++;
    }

    public void nextCore(Player player) {

        if (!(player_cores >=Config.MAX_PLAYER_CORE.get())) {
            player_cores++;
            player.displayClientMessage(Component.translatable("text.ars_trinkets.new_soul_core"), false);
        } else {
            player.displayClientMessage(Component.translatable("text.ars_trinkets.max_soul_core"), false);
        }
    }

    @Override
    public void triggerPlayerBreakthrough(Player player) {
        /**
         * This will forcibly trigger the player's breakthrough.
         * If the player's SRF is not superior to a random float from 0.0-0.9, the refinement will fail.
         * @InternalValue SRF: the percentage of refinement from the minimum value to the maximum value of the arcane level
         * */
        player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {

            float k = (float) (a.checkRefinement(player));
            float x = new Random().nextFloat();
            k /= 3.0;
//            System.out.println("SRF = " + k);
            if (k > x) {
                player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.whispers"), false);
                if (a.profane) {
                    a.nextRank();
                    player.displayClientMessage(Component.translatable("text.ars_trinkets.ritual_dsc_" + a.getPlayerArcaneLevel()), false);
                } else {
                    a.nextRank();
                    player.displayClientMessage(Component.translatable("text.ars_trinkets.ritual_asc_" + a.getPlayerArcaneLevel()), false);
                }
            } else {
                player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.shatter"), false);
                a.player_arcane_level--;
                a.setProfane(true);
                a.setPlayerSoulRefinement((int) (a.getPlayerSoulRefinement() * 0.3));
            }
            a.warning_2 = false;
            a.warning_3 = false;
        });
    }

    public void updatePlayerCores(Player player) {

        player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {

            if (!a.profane) {
                return;
            } else {
                int souls = (int) Math.pow(a.getPlayerArcaneLevel() + 1, player_cores) + 1500 * player_cores;
                if (a.getPlayerCollectedSouls() > souls) {
                    a.nextCore(player);
                    a.setCollectedSouls(a.getPlayerCollectedSouls() / 5);
                }
            }
        });
    }

    @Override
    public void updateSoulEssence(int quantity, boolean slain, Player player) {
        /**
         * @Param slain: were the soul fragments obtained from killing.
         * @Param quantity: the number of soul fragments.
         * */
        if (player_arcane_level <= Config.MAX_PLAYER_LEVEL.get()) {
//            System.out.println("UpdateSoulEssence called" + quantity);
//            player.displayClientMessage(Component.literal("USING EXPERIMENTAL FEATURES : LEVELING ! USE AT YOUR OWN RISKS"),true);
            if (!slain) {
                this.player_soul_refinement += quantity;
            } else {
                this.player_soul_refinement += quantity;
                this.player_collected_souls += 1.2 * quantity;
                this.setFed();
                player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.warning_corruption"),true);
            }
            this.calcProfane(player);
            playerMain(player);
//            System.out.println("Soul Essence Updated");
        } else {
            if (!slain) {
                player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.finished"), true);
            }
        }
    }

    @Override
    public void playerMain(Player player) {
        /**
         * @Param player: the player.
         * what does this function do :
         * it will check the refinement of the player's soul.
         * then it will either do nothing, launch a warning message, or triggger the player's ascension/descension.
         * */
        player.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(a -> {

            int refi = a.checkRefinement(player);
//            System.out.println("check refinement created successfully");


            switch (refi) {
                case 0, 1 -> {
                }
                case 2 -> {
                    if (!a.warning_2) {
                        player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.bloated"), false);
                        a.warning_2 = true;
                    }
                }
                case 3 -> {
                    if (!a.warning_3) {
                        player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.overflow"), false);
                        a.warning_3 = true;
                    }
                }
                case 4 -> {
                    if (a.getProfane()) {
//                    player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.breakthrough.unholy"), false);
                        a.triggerPlayerBreakthrough(player);
                    } else {
//                    player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.breakthrough.holy"), false);
                        a.triggerPlayerBreakthrough(player);
                    }
//                    System.out.println("Player main accessed with success");
                }
            }


        });
//        System.out.println("Player Main triggered");
    }

    @Override
    public void CopyFrom(ArcaneLevels source) {
        /**
         * standard code for the capability
         * */
        this.player_arcane_level = source.player_arcane_level;
        this.player_soul_refinement = source.player_soul_refinement;
        this.player_collected_souls = source.player_collected_souls;
        this.profane = source.profane;
        this.warning_2 = source.warning_2;
        this.warning_3 = source.warning_3;
        this.player_cores = source.player_cores;
        this.feeding_time = source.feeding_time;
        this.last_feed = source.last_feed;
//        System.out.println("Arcane Cap Copied");
    }

    @Override
    public void saveNBTData(CompoundTag nbt) {
        /**
         * standard code for the capability
         * */
        nbt.putInt("player_arcane_level", player_arcane_level);
        nbt.putInt("player_cores", player_cores);
        nbt.putInt("player_soul_refinement", player_soul_refinement);
        nbt.putInt("player_slain_souls", player_collected_souls);
        nbt.putInt("player_feeding_time", feeding_time);
        nbt.putInt("player_last_fed", last_feed);
        nbt.putBoolean("player_profane_soul", profane);
        nbt.putBoolean("warning_2", warning_2);
        nbt.putBoolean("warning_3", warning_3);

//        System.out.println("Arcane Cap Saved");
    }

    @Override
    public void loadNBTData(CompoundTag nbt) {
        /**
         * standard code for the capability
         * */
        player_arcane_level = nbt.getInt("player_arcane_level");
        player_cores = nbt.getInt("player_cores");
        player_soul_refinement = nbt.getInt("player_soul_refinement");
        player_collected_souls = nbt.getInt("player_slain_souls");
        feeding_time = nbt.getInt("player_feeding_time");
        last_feed = nbt.getInt("player_last_feed");
        profane = nbt.getBoolean("player_profane_soul");
        warning_2 = nbt.getBoolean("player_warning_2");
        warning_3 = nbt.getBoolean("player_warning_3");
//        System.out.println("Arcane Cap loaded");
    }


    public void RESET_PLAYER() {
        player_arcane_level = 1;
        player_soul_refinement = 100;
        player_collected_souls = 0;
        warning_2 = false;
        warning_3 = false;
        profane = false;
        player_cores = 1;
        feeding_time = 600 * 20;
        last_feed = 0;
    }
}
