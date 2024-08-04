package com.c446.ars_trinkets.capabilities;

import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.event.PlayerCoresUpdated;
import com.c446.ars_trinkets.event.PlayerLevelUpdated;
import com.c446.ars_trinkets.perks.PerkAttributes;
import com.c446.ars_trinkets.util.Util;
import com.hollingsworth.arsnouveau.common.items.SpellBook;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher.ArcaneLevelsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;

import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class ArcaneLevels implements IArcaneLevels {
    private static boolean firstLogin = false;
    // counts as the player's level.
    public int player_arcane_level = 1;
    // counts as the player's experience.
    public int player_soul_refinement = 100;
    public int player_collected_souls = 0;
    public int player_cores = 1;
    private boolean profane = false;
    private boolean warning_2 = false;
    private boolean warning_3 = false;
    public boolean warned1 = false;
    public boolean warned2 = false;
    private int feeding_time = 600 * 20;
    private boolean feast;

    public void setFed() {
        feast = false;
        warned1 = false;
        warned2 = false;
    }

    public int getFeedingTime() {
        return feeding_time;
    }

    public boolean getLoginFirst() {
        return firstLogin;
    }

    public void setLoginFirst(boolean b) {
        firstLogin = b;
    }


    @Override
    public int getPlayerArcaneLevel() {
        return player_arcane_level;
    }

    public int getCores() {
        return player_cores;
    }

    @Override
    public void setPlayerArcaneLevel(int level) {
        this.player_arcane_level = level;
    }

    @Override
    public int getPlayerSoulRefinement() {
        return player_soul_refinement;
    }

    @Override
    public void setPlayerSoulRefinement(int refinement) {
        this.player_soul_refinement = refinement;
    }

    @Override
    public int getPlayerCollectedSouls() {
        return player_collected_souls;
    }

    @Override
    public void setCollectedSouls(int i) {
        this.player_collected_souls = i;
    }

    @Override
    public boolean getProfane() {
        return this.profane;
    }

    @Override
    public void setProfane(Boolean pr) {
        this.profane = pr;
    }

    @Override
    public int calcProfane(Player player) {
        int severity = 0;
        if (player_soul_refinement + 1000 - 2 * player_collected_souls < 0) {
            severity++;
        }
        if (player_soul_refinement + 1000 - player_collected_souls < 0) {
            severity++;
        }
        if (severity == 2) {

            if (!profane) {
                setProfane(true, false);
                player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.madness"), true);
            }
        }
        return severity;
    }

    @Override
    public MutableComponent getPlayerTitle() {
        if (!profane) {
            return Component.translatable("text.ars_trinkets.titles.asc" + getPlayerArcaneLevel());
        }
        return Component.translatable("text.ars_trinkets.titles.dsc" + getPlayerArcaneLevel());
    }

    @Override
    public int getPlayerManaBonus() {
        //        System.out.println("Max Mana bonus questioned");
        int bonus = 0;
        switch (player_arcane_level) {
            case 0 -> bonus = Config.MANA_BONUS_FLAT_LEVEL_0.get();
            case 1 -> bonus = Config.MANA_BONUS_FLAT_LEVEL_1.get();
            case 2 -> bonus = Config.MANA_BONUS_FLAT_LEVEL_2.get();
            case 3 -> bonus = Config.MANA_BONUS_FLAT_LEVEL_3.get();
            case 4 -> bonus = Config.MANA_BONUS_FLAT_LEVEL_4.get();
            case 5 -> bonus = Config.MANA_BONUS_FLAT_LEVEL_5.get();
            case 6 -> bonus = Config.MANA_BONUS_FLAT_LEVEL_6.get();
            case 7 -> bonus = Config.MANA_BONUS_FLAT_LEVEL_7.get();
            case 8 -> bonus = Config.MANA_BONUS_FLAT_LEVEL_8.get();
            case 9 -> bonus = Config.MANA_BONUS_FLAT_LEVEL_9.get();
        }
        return (int) (player_cores * Config.CORE_FLAT_BONUS.get() * bonus);
    }

    @Override
    public int getPlayerRegenBonus() {
        int bonus = 0;
        switch (player_arcane_level) {
            case 0 -> bonus = Config.MANA_REGEN_BONUS_LVL_0.get();
            case 1 -> bonus = Config.MANA_REGEN_BONUS_LVL_1.get();
            case 2 -> bonus = Config.MANA_REGEN_BONUS_LVL_2.get();
            case 3 -> bonus = Config.MANA_REGEN_BONUS_LVL_3.get();
            case 4 -> bonus = Config.MANA_REGEN_BONUS_LVL_4.get();
            case 5 -> bonus = Config.MANA_REGEN_BONUS_LVL_5.get();
            case 6 -> bonus = Config.MANA_REGEN_BONUS_LVL_6.get();
            case 7 -> bonus = Config.MANA_REGEN_BONUS_LVL_7.get();
            case 8 -> bonus = Config.MANA_REGEN_BONUS_LVL_8.get();
            case 9 -> bonus = Config.MANA_REGEN_BONUS_LVL_9.get();
        }
        return (int) (bonus * player_cores * Config.CORE_REGEN_BONUS.get());
    }

    @Override
    public float getPlayerDefense() {
        int bonus = 0;
        switch (player_arcane_level) {
            case 0 -> bonus = Config.DEFENSE_0.get();
            case 1 -> bonus = Config.DEFENSE_1.get();
            case 2 -> bonus = Config.DEFENSE_2.get();
            case 3 -> bonus = Config.DEFENSE_3.get();
            case 4 -> bonus = Config.DEFENSE_4.get();
            case 5 -> bonus = Config.DEFENSE_5.get();
            case 6 -> bonus = Config.DEFENSE_6.get();
            case 7 -> bonus = Config.DEFENSE_7.get();
            case 8 -> bonus = Config.DEFENSE_8.get();
            case 9 -> bonus = Config.DEFENSE_9.get();

        }
        return (float) ((double) 1 + (bonus / 100));
    }

    @Override
    public double getSpellDmg() {
        int bonus = 0;
        switch (player_arcane_level) {
            case 0 -> bonus = Config.SPELL_DMG_0.get();
            case 1 -> bonus = Config.SPELL_DMG_1.get();
            case 2 -> bonus = Config.SPELL_DMG_2.get();
            case 3 -> bonus = Config.SPELL_DMG_3.get();
            case 4 -> bonus = Config.SPELL_DMG_4.get();
            case 5 -> bonus = Config.SPELL_DMG_5.get();
            case 6 -> bonus = Config.SPELL_DMG_6.get();
            case 7 -> bonus = Config.SPELL_DMG_7.get();
            case 8 -> bonus = Config.SPELL_DMG_8.get();
            case 9 -> bonus = Config.SPELL_DMG_9.get();
        }
        return (double) 1 + (bonus / 100);
    }

    @Override
    public double getPhysDmg() {
        int bonus = 0;
        switch (player_arcane_level) {
            case 0 -> bonus = Config.PHYS_DAMAGE_0.get();
            case 1 -> bonus = Config.PHYS_DAMAGE_1.get();
            case 2 -> bonus = Config.PHYS_DAMAGE_2.get();
            case 3 -> bonus = Config.PHYS_DAMAGE_3.get();
            case 4 -> bonus = Config.PHYS_DAMAGE_4.get();
            case 5 -> bonus = Config.PHYS_DAMAGE_5.get();
            case 6 -> bonus = Config.PHYS_DAMAGE_6.get();
            case 7 -> bonus = Config.PHYS_DAMAGE_7.get();
            case 8 -> bonus = Config.PHYS_DAMAGE_8.get();
            case 9 -> bonus = Config.PHYS_DAMAGE_9.get();
        }
        return (double) 1 + (bonus / 100);
    }

    @Override
    public int checkRefinement(Player player) {
        float x = (float) player_soul_refinement / Util.getAllRefinementStages().get(this.getPlayerArcaneLevel());
        if (x < 0.5) {
            return 1;
        } else if (x > 0.5 && x < 0.75) {
            return 2;
        } else if (x > 0.75 && x < 0.99) {
            return 3;
        } else if (x > 0.99) {
            return 4;
        }
        return 0;
    }

    public void nextRank(Player player) {
        player_arcane_level++;
        feast = false;
        MinecraftForge.EVENT_BUS.post(new PlayerLevelUpdated.Post(player_arcane_level, player));

    }

    public boolean canPlayerFormCores() {
        return player_cores < Config.MAX_PLAYER_CORE.get();
    }

    public boolean nextCore(Player player) {

        if ((player_cores < Config.MAX_PLAYER_CORE.get())) {
            player_cores++;
            System.out.println("gate 2");
            player.displayClientMessage(Component.translatable("text.ars_trinkets.new_soul_core"), true);
            ;
        } else {
            System.out.println("gate 3");
            player.displayClientMessage(Component.translatable("text.ars_trinkets.max_soul_core"), true);
            ;
        }
        MinecraftForge.EVENT_BUS.post(new PlayerCoresUpdated.Post(player_cores, player));
        if (profane) return true;
        else return false;
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
//                player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.whispers"), false);
                if (a.profane) {
                    a.nextRank(player);
                    player.displayClientMessage(Component.translatable("text.ars_trinkets.ritual_" + a.getPlayerArcaneLevel()).append(a.getPlayerTitle()), true);
                } else {
                    a.nextRank(player);

                    player.displayClientMessage(Component.translatable("text.ars_trinkets.ritual_" + a.getPlayerArcaneLevel()).append(a.getPlayerTitle()), true);
                }
            } else {
                player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.shatter"), true);
                a.player_arcane_level--;
                a.setProfane(true, false);
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
//                    a.nextCore(player);
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
                if (calcProfane(player) == 1 && !this.feast) {
                    player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.warning_corruption"), true);
                    this.feast = true;
                }
            }
            this.calcProfane(player);
            playerMain(player);
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
                        player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.breakthrough.unholy"), true);
                        a.triggerPlayerBreakthrough(player);
                    } else {
                        player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.breakthrough.holy"), true);
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
        this.feast = source.feast;
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
        nbt.putBoolean("player_last_fed", feast);
        nbt.putBoolean("player_profane_soul", profane);
        nbt.putBoolean("warning_2", warning_2);
        nbt.putBoolean("warning_3", warning_3);
        nbt.putBoolean("player_first_login", firstLogin);

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
        feast = nbt.getBoolean("player_last_feed");
        profane = nbt.getBoolean("player_profane_soul");
        warning_2 = nbt.getBoolean("player_warning_2");
        warning_3 = nbt.getBoolean("player_warning_3");
        firstLogin = nbt.getBoolean("player_first_login");
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
        feast = false;
    }

    public void setCores(int number) {
        this.player_cores = number;
    }

    public static AttributeModifier getFlatManaBoostAttributeModifier(int i) {
        return new AttributeModifier(UUID.fromString("2a947a4e-01dc-42d8-8cf3-fd971bf723a6"), "ars_trinkets.leveling_system.mana_capacity", i, AttributeModifier.Operation.ADDITION);
    }

    public static AttributeModifier getRegenManaBoostAttributeModifier(int i) {
        return new AttributeModifier(UUID.fromString("ee141509-487f-4d09-adde-234ca9d58911"), "ars_trinkets.leveling_system.mana_regen", i, AttributeModifier.Operation.ADDITION);
    }

    public static AttributeModifier getSpellDamageImprovmentAttributeModifier(double i) {
        return new AttributeModifier(UUID.fromString("520626e0-ed4a-43cd-973a-934e29f7baac"), "ars_trinkets.leveling_system.spell_dmg", i, AttributeModifier.Operation.ADDITION);
    }

    public static AttributeModifier getDamageReducAttributeModifier(double i) {
        return new AttributeModifier(UUID.fromString("683b68a8-5f25-41dc-a90c-764e61e4082c"), "ars_trinkets.leveling_system.dmg_reduc", i / 100, AttributeModifier.Operation.ADDITION);
    }

    public static AttributeModifier getPhysDamageImprovmentAttributeModifier(double i) {
        return new AttributeModifier(UUID.fromString("7267ff97-e2d8-43f8-b7ce-efde6a1c361a"), "ars_trinkets.leveling_system.phys_dmg", 1 + i / 100, AttributeModifier.Operation.ADDITION);
    }

    public void setProfane(boolean turnsPlayerProfane, boolean isRelic) {
        setProfane(turnsPlayerProfane);

    }

    public void setProfane(boolean turnsPlayerProfane, boolean isRelic, Player player) {
        setProfane(turnsPlayerProfane);
        if (isRelic) {

            player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.going_to_madness_item"), true);
        } else {
            player.displayClientMessage(Component.translatable("text.ars_trinkets.souls.back_from_madness"), true);
        }
    }
}


