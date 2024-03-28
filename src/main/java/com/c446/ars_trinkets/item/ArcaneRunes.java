package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.perks.PerkAttributes;
import com.google.common.collect.Multimap;
import com.hollingsworth.arsnouveau.api.mana.IManaEquipment;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class ArcaneRunes extends RegularItems implements ICurioItem, IManaEquipment {
    double armor;
    double soulsteal;
    double health;
    double damage;
    double luck;
    double all_stats;
    double damage_boost;
    double totalmanaboostpct;
    double totalmanaregenboostpct;
    double attack_speed;
    public ArcaneRunes(Properties properties,
                       double armor,
                       double soul_steal,
                       double health,
                       double damage,
                       double luck,
                       double all_stats,
                       double damage_boost,
                       double total_mana_boost_pct,
                       double total_mana_regen_boost_pct,
                       double attack_speed
                       ) {
        super(properties);
        this.armor = armor;
        this.soulsteal = soul_steal;
        this.health = health;
        this.damage = damage;
        this.luck = luck;
        this.all_stats = all_stats;
        this.damage_boost = damage_boost;
        this.totalmanaboostpct = total_mana_boost_pct;
        this.totalmanaregenboostpct = total_mana_regen_boost_pct;
        this.attack_speed = attack_speed;
    }
    public double getSoulsteal(ItemStack stack) {
        return soulsteal/100;
    }
    public double getArmor(ItemStack stack) {
        return armor/100;
    }
    public double getHealth(ItemStack stack){return health/100;}
    public double getBonusDamage(ItemStack stack){return damage/100;}
    public double getLuck(ItemStack stack){return luck/100;}
    public double getAllStats(ItemStack stack){return all_stats/100;}
    public double getDamage_boost(ItemStack stack){return damage_boost/100;}
    public double getTotalManaBoostPCT(ItemStack stack){return totalmanaboostpct/100;}
    public double getTotalManaRegenBoostPCT(ItemStack stack){return totalmanaregenboostpct/100;}

    public double getAttackSpeed(ItemStack stack){return attack_speed;}

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
//        attributes.put((Attribute) PerkAttributes.FLAT_MANA_BONUS.get(), new AttributeModifier(uuid, "flat_max_mana_modifier_curio", (double) this.getMaxManaBoost(stack), AttributeModifier.Operation.ADDITION));
        attributes.put((Attribute) Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "runestone_speed_curio", (double) this.getAttackSpeed(stack),AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put((Attribute) Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "runestone_damage_curio", (double) this.getBonusDamage(stack), AttributeModifier.Operation.MULTIPLY_TOTAL));

        attributes.put((Attribute) Attributes.MAX_HEALTH, new AttributeModifier(uuid, "runestone_health_curio", (double) this.getHealth(stack), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put((Attribute) Attributes.LUCK, new AttributeModifier(uuid, "runestone_luck_curio", (double) this.getLuck(stack), AttributeModifier.Operation.MULTIPLY_TOTAL));

        attributes.put((Attribute)  Attributes.ARMOR, new AttributeModifier(uuid, "runestone_armor_curio", (double) this.getArmor(stack), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put((Attribute) PerkAttributes.SOUL_STEALER.get(), new AttributeModifier(uuid, "runestone_soul_stealer_curio", (double) this.getSoulsteal(stack), AttributeModifier.Operation.MULTIPLY_TOTAL));

        attributes.put((Attribute) PerkAttributes.SPELL_DAMAGE_PCT.get(), new AttributeModifier(uuid, "runestone_spell_damage_pct_curio", (double) this.getDamage_boost(stack), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put((Attribute) PerkAttributes.TOTAL_MANA_BOOST.get(), new AttributeModifier(uuid, "runestone_mana_boost_curio", (double) this.getTotalManaBoostPCT(stack), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put((Attribute) PerkAttributes.TOTAL_MANA_REGEN_BOOST.get(), new AttributeModifier(uuid, "runestone_mana_regen_boost_curio", (double) this.getTotalManaRegenBoostPCT(stack), AttributeModifier.Operation.MULTIPLY_TOTAL));
        return attributes;


    }
}

