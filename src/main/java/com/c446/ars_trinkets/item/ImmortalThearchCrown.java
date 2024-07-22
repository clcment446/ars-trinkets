package com.c446.ars_trinkets.item;

import com.c446.ars_trinkets.perks.PerkAttributes;
import com.google.common.collect.Multimap;
import com.hollingsworth.arsnouveau.api.mana.IManaEquipment;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ImmortalThearchCrown extends RegularItems implements ICurioItem, IManaEquipment {
    public ImmortalThearchCrown(Properties properties
    ) {
        super(properties.stacksTo(1).rarity(Rarity.EPIC).fireResistant());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(Component.translatable("tooltips.ars_trinkets.crown0").withStyle(ChatFormatting.OBFUSCATED));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }


    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            return slotContext.entity() instanceof Player player && player.getStringUUID().equals("2980a99e-8582-4f63-9b82-f7117bc8be2c");
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
//        attributes.put((Attribute) PerkAttributes.FLAT_MANA_BONUS.get(), new AttributeModifier(uuid, "flat_max_mana_modifier_curio", (double) this.getMaxManaBoost(stack), AttributeModifier.Operation.ADDITION));
        attributes.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "thearch_crown_atk_spd", 20, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "thearch_crown_atk_dmg", 200, AttributeModifier.Operation.MULTIPLY_TOTAL));

        attributes.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "thearch_crown_hlth", 500, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(Attributes.LUCK, new AttributeModifier(uuid, "thearch_crown_lck", 100, AttributeModifier.Operation.MULTIPLY_TOTAL));

        attributes.put(Attributes.ARMOR, new AttributeModifier(uuid, "thearch_crown_armor", 250, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "thearch_crown_toughness", 250, AttributeModifier.Operation.MULTIPLY_TOTAL));

        attributes.put(PerkAttributes.SPELL_DAMAGE_PCT.get(), new AttributeModifier(uuid, "thearch_crown_spell_pct", 5, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(PerkAttributes.TOTAL_MANA_BOOST.get(), new AttributeModifier(uuid, "thearch_crown_flat_mana", 100, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(PerkAttributes.TOTAL_MANA_REGEN_BOOST.get(), new AttributeModifier(uuid, "thearch_crown_mana_regen", 100, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return attributes;


    }
}
