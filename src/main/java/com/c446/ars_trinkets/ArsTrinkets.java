package com.c446.ars_trinkets;

import com.c446.ars_trinkets.event.ModEvents;
import com.c446.ars_trinkets.item.EssenceItem;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.hollingsworth.arsnouveau.setup.registry.CreativeTabRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ArsTrinkets.MODID)
public class ArsTrinkets {
    public static final String MODID = "ars_trinkets";

    private static final Logger LOGGER = LogManager.getLogger();
    public static final ArrayList<Item> ESSENCE_LIST = new ArrayList<Item>();
    public static final HashMap<Item, Integer> ESSENCE_VALUE = new HashMap<Item, Integer>();

    public ArsTrinkets() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRegistry.registerRegistries(modbus);
        ArsNouveauRegistry.registerGlyphs();
        modbus.addListener(this::setup);
        modbus.addListener(this::doClientStuff);
        modbus.addListener(this::doTabThings);
        modbus.addListener(ModEvents::registerCapabilities);
//        modbus.addListener(ModEvents::onAttachCapabilitiesEntity);
//        modbus.addListener(ModEvents::PlayerKillRefineSoul);
//        modbus.addListener(this::)
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ArsNouveauRegistry.registerSounds();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
        EssenceItem.setEssenceLists();

    }

    @SubscribeEvent
    public void doTabThings(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == CreativeTabRegistry.BLOCKS.get()) {
            for (var item : ModRegistry.ITEMS.getEntries()) {
                event.accept(item::get);
            }
        }
    }
    @SubscribeEvent
    public void doCapabilities(RegisterCapabilitiesEvent event){

    }
}

