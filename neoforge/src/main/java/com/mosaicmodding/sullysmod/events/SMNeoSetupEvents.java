package com.mosaicmodding.sullysmod.events;

import com.mosaicmodding.sullysmod.core.NeoSpecificHandler;
import com.mosaicmodding.sullysmod.registry.SMDataMaps;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber
public class SMNeoSetupEvents {

    @SubscribeEvent
    private static void commonSetup(FMLCommonSetupEvent event) {
        NeoSpecificHandler.addPlantsToPot();
    }

    @SubscribeEvent
    private static void registerDataMaps(RegisterDataMapTypesEvent event) {
        SMDataMaps.registerDataMapTypes(event);
    }
}
