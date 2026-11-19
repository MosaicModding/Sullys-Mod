package com.mosaicmodding.sullysmod.events;

import com.mosaicmodding.sullysmod.content.petrified.PetrifiedTreeVariant;
import com.mosaicmodding.sullysmod.core.NeoSpecificHandler;
import com.mosaicmodding.sullysmod.registry.SMDataMaps;
import com.mosaicmodding.sullysmod.registry.datapack.SMPetrifiedTreeVariants;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber
public class SMNeoSetupEvents {

    @SubscribeEvent
    static void commonSetup(FMLCommonSetupEvent event) {
        NeoSpecificHandler.addPlantsToPot();
    }

    @SubscribeEvent
    static void registerDataMaps(RegisterDataMapTypesEvent event) {
        SMDataMaps.registerDataMapTypes(event);
    }

    @SubscribeEvent
    static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(SMPetrifiedTreeVariants.REGISTRY_KEY, PetrifiedTreeVariant.CODEC);
    }
}
