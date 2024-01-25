//package com.c446.ars_trinkets.perks;
//
//import com.c446.ars_trinkets.ArsTrinkets;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.ai.attributes.Attribute;
//import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.util.HashMap;
//import java.util.UUID;
//import java.util.function.Function;
//
//@EventBusSubscriber(modid = ArsTrinkets.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
//
//public class PerkAttributes {
//    public static final HashMap<RegistryObject<Attribute>, UUID> UUIDS = new HashMap();
//    public static final DeferredRegister<Attribute> ATTRIBUTES;
//    public static final RegistryObject<Attribute> WARDING;
//
//    public PerkAttributes() {
//    }
//
//
//
//    public static RegistryObject<Attribute> registerAttribute(String name, Function<String, Attribute> attribute, UUID uuid) {
//        RegistryObject<Attribute> registryObject = ATTRIBUTES.register(name, () -> attribute.apply(name));
//        UUIDS.put(registryObject, uuid);
//        return registryObject;
//    }
//
//    @SubscribeEvent
//    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
//        event.getTypes().stream().filter((e) -> e == EntityType.PLAYER).forEach((e) -> {
//            ATTRIBUTES.getEntries().forEach((v) -> {
//                event.add(e, v.get());
//            });
//        });
//    }
//    static {
//
//
//
//
//
//    }
//}
