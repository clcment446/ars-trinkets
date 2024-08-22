package com.c446.ars_trinkets.item.trinkets;

import com.c446.ars_trinkets.Config;
import com.c446.ars_trinkets.item.RegularItems;
import com.c446.ars_trinkets.registry.CurioConfigManager;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.c446.ars_trinkets.util.Util;
import com.google.common.collect.Multimap;
import com.hollingsworth.arsnouveau.api.mana.IManaEquipment;
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.HashMap;
import java.util.UUID;

import static com.c446.ars_trinkets.registry.CurioConfigManager.defaultLotusMana;
import static com.c446.ars_trinkets.registry.CurioConfigManager.defaultLotusRegen;

public class GenericLotusTrinkets extends RegularItems implements ICurioItem, IManaEquipment {

    protected int level;
    int regen;
    int boost;

    public GenericLotusTrinkets(Properties p, int level) {
        super(p);
        this.level = level;
    }
    public GenericLotusTrinkets(Properties p, boolean showEnch, int level) {
        super(p, showEnch);
        this.level = level;
    }
    public HashMap<Attribute, Long> createAttributeMap() {
        if (Config.SERVER_CONFIG.isLoaded()) {
            HashMap<Attribute, ForgeConfigSpec.IntValue> map = CurioConfigManager.lotuses_maps.get(level);
            return Util.fromConfigToLong(map);
        }
        else {
            HashMap<Attribute, Long> map = new HashMap<Attribute, Long>();
            map.put(PerkAttributes.MAX_MANA.get(), (long) defaultLotusMana[level]);
            map.put(PerkAttributes.MANA_REGEN_BONUS.get(), (long) defaultLotusRegen[level]);
            return map;
        }
    }

    @Override
    public int getManaRegenBonus(ItemStack stack) {
        return regen;
    }

    @Override
    public int getMaxManaBoost(ItemStack stack) {
        return boost;
    }
    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        HashMap<Attribute, Long> values = createAttributeMap();
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        attributes.put(PerkAttributes.MAX_MANA.get(), new AttributeModifier(uuid, "ars_trinkets.lotus_bonus", values.get(PerkAttributes.MAX_MANA.get()), AttributeModifier.Operation.ADDITION));
        attributes.put(PerkAttributes.MANA_REGEN_BONUS.get(), new AttributeModifier(uuid, "ars_trinkets.lotus_bonus", values.get(PerkAttributes.MANA_REGEN_BONUS.get()), AttributeModifier.Operation.ADDITION));
        return attributes;
    }}
