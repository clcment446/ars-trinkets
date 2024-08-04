package com.c446.ars_trinkets.perks;

import com.c446.ars_trinkets.ArsTrinkets;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

@EventBusSubscriber(modid = ArsTrinkets.MOD_ID, bus = EventBusSubscriber.Bus.MOD)

public class PerkAttributes {
    public static final HashMap<RegistryObject<Attribute>, UUID> UUIDS = new HashMap();
    public static final DeferredRegister<Attribute> ATTRIBUTES;
    public static final RegistryObject<Attribute> SPELL_DAMAGE_PCT;
    public static final RegistryObject<Attribute> TOTAL_MANA_BOOST;
    public static final RegistryObject<Attribute> TOTAL_MANA_REGEN_BOOST;
    public static final RegistryObject<Attribute> SOUL_STEALER;
    public static final RegistryObject<Attribute> FLAT_REGEN_BOOST;
    public static final RegistryObject<Attribute> FLAT_MANA_BOOST;
    public static final RegistryObject<Attribute> ALL_DAMAGE_REDUCTION;





    public PerkAttributes() {
    }

    public static RegistryObject<Attribute> registerAttribute(String name, Function<String, Attribute> attribute, String uuid) {
        return registerAttribute(name, attribute, UUID.fromString(uuid));
    }

    public static RegistryObject<Attribute> registerAttribute(String name, Function<String, Attribute> attribute, UUID uuid) {
        RegistryObject<Attribute> registryObject = ATTRIBUTES.register(name, () -> {
            return (Attribute) attribute.apply(name);
        });
        UUIDS.put(registryObject, uuid);
        return registryObject;
    }

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
        event.getTypes().stream().filter((e) -> {
            return e == EntityType.PLAYER;
        }).forEach((e) -> {
            ATTRIBUTES.getEntries().forEach((v) -> {
                event.add(e, (Attribute) v.get());
            });
        });
    }

    static {
        ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, ArsTrinkets.MOD_ID);
        SPELL_DAMAGE_PCT = registerAttribute("ars_trinkets.perk.spell_damage_pct", (id) ->
                {
                    return (new RangedAttribute(id, 1.0, 0.0, 1024.0)).setSyncable(true);
                },
                "7777076e-83bd-4e5c-957e-c065c7027cdb");

        TOTAL_MANA_REGEN_BOOST = registerAttribute("ars_trinkets.perk.total_mana_regen_boost", (id) ->
                {
                    return (new RangedAttribute(id, 1.0, 0.0, 1024.0)).setSyncable(true);
                },
                "ac58788e-4120-4815-8938-5165cde99011");
        TOTAL_MANA_BOOST = registerAttribute("ars_trinkets.perk.total_mana_boost", (id) ->
                {
                    return (new RangedAttribute(id, 1.0, 0.0, 1024.0)).setSyncable(true);
                },
                "9d6cca63-18ac-4db2-9235-d5cbc16411ec");
        SOUL_STEALER = registerAttribute("ars_trinkets.perk.soul_steal", (id) ->
                {
                    return (new RangedAttribute(id, 1.0, 0.0, 1024.0)).setSyncable(true);
                },
                "9937214c-3a59-4573-8dfd-c9c17eda283b"
        );
        FLAT_REGEN_BOOST = registerAttribute("ars_trinkets.perk.mana_boost_flat", (id) ->
                {
                    return (new RangedAttribute(id, 0, -Integer.MAX_VALUE, Integer.MAX_VALUE)).setSyncable(true);
                },
                "223f95c1-2c6d-4e04-92b9-8f3e6351d343"
        );


        FLAT_MANA_BOOST = registerAttribute("ars_trinkets.perk.mana_regen_flat", (id) ->
                {
                    return (new RangedAttribute(id, 0.0, -Integer.MAX_VALUE, Integer.MAX_VALUE)).setSyncable(true);
                },
                "e0f6483e-92b4-48d5-b67d-90e552147743"
        );
        ALL_DAMAGE_REDUCTION = registerAttribute("ars_trinkets.perk.damage_reduction_global", (id) ->
                {
                    return (new RangedAttribute(id, 0, 0, 1)).setSyncable(true);
                },
                "a73e41ae-2a90-4e44-84e5-1abba980ce11"
        );
    }
    public static double getAttributeValue(LivingEntity entity, Attribute attribute){
        AttributeInstance inst = entity.getAttribute(attribute);
        if (inst == null) return -Integer.MAX_VALUE;
        else return inst.getValue();
    }
}

