package com.c446.ars_trinkets.registry;

import net.minecraft.world.level.GameRules;

/** Per-world switches. Use {@code /gamerule arsTrinketsEssenceDrops false} to stop mobs dropping essence. */
public final class ModGameRules {
    public static final GameRules.Key<GameRules.BooleanValue> ESSENCE_DROPS =
            GameRules.register("arsTrinketsEssenceDrops", GameRules.Category.DROPS, GameRules.BooleanValue.create(true));

    private ModGameRules() {
    }

    /** Forces class loading so the rule is registered during mod construction. */
    public static void init() {
    }
}
