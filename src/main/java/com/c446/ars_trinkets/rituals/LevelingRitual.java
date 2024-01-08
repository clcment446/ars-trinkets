package com.c446.ars_trinkets.rituals;

import com.c446.ars_trinkets.ArsTrinkets;
import com.c446.ars_trinkets.capabilities.ArcaneLevelsAttacher.*;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.PlayMessages;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LevelingRitual extends AbstractRitual {

    private String name = "Ascension Ritual";
    public static final String ID = "ritual_leveling";
    private final int source = 2000;
    boolean profane;

    protected final HashMap<Item, Integer> allowed = new HashMap<>();
    Player target;
    int targer_player_ref;
    int past_target_ref = 0;

    public LevelingRitual() {

        allowed.clear();
        allowed.put(ModRegistry.IRON_ESSENCE.get(), 25);
        allowed.put(ModRegistry.COPPER_ESSENCE.get(), 50);
        allowed.put(ModRegistry.SILVER_ESSENCE.get(), 100);
        allowed.put(ModRegistry.GOLD_ESSENCE.get(), 200);
        allowed.put(ModRegistry.CRYSTAL_ESSENCE.get(), 300);
        allowed.put(ModRegistry.GREEN_ESSENCE.get(), 1000);
        allowed.put(ModRegistry.RED_ESSENCE.get(), 2500);
        allowed.put(ModRegistry.WHITE_ESSENCE.get(), 7000);
        allowed.put(ModRegistry.YELLOW_ESSENCE.get(), 14000);
        allowed.put(ModRegistry.PURPLE_ESSENCE.get(), 27000);

    }

    @Override
    public String getLangName() {
        return this.name;
    }

    private Player getTargetPlayer() {
        return target;
    }

    private void setTargetPlayer(Player target) {
        this.target = target;
    }

    @Override
    protected void tick() {

        if (getWorld() instanceof ServerLevel level && this.tile != null) {
            int ritual_level = 0;
            List<Player> entities = this.getWorld().getEntitiesOfClass(Player.class, (new AABB(Objects.requireNonNull(this.getPos()))).inflate(10));
            for (Player p : entities) {
                p.getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(arcaneLevels -> {
                    targer_player_ref = arcaneLevels.getPlayerSoulRefinement();
                });
                if (targer_player_ref > past_target_ref) {
                    past_target_ref = targer_player_ref;
                    setTargetPlayer(p);
                }
            }

            getTargetPlayer().getCapability(ArcaneLevelsProvider.PLAYER_LEVEL).ifPresent(arcaneLevels -> {
                arcaneLevels.triggerPlayerBreakthrough(getTargetPlayer());
            });
        }

        setFinished();
    }


    @Override

    public boolean canConsumeItem(ItemStack item) {
        return allowed.containsKey(item.getItem());
    }

    @Override
    public int getSourceCost() {
        return this.source;
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsTrinkets.prefix(this.ID);
    }
}