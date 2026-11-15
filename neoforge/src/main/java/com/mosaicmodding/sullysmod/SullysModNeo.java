package com.mosaicmodding.sullysmod;

import com.mosaicmodding.sullysmod.registry.SMBiomeModifiers;
import com.mosaicmodding.sullysmod.registry.SMDataMaps;
import com.mosaicmodding.sullysmod.registry.SMNeoConditions;
import com.mosaicmodding.sullysmod.core.NeoSpecificHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@Mod(SullysMod.ID)
public class SullysModNeo {

    public SullysModNeo(IEventBus bus) {
        SullysMod.init();
        SMBiomeModifiers.BIOME_MODIFIERS.register(bus);

        //Never register a condition during datagen if that condition has a fabricated-conditions counterpart
        if (!DatagenModLoader.isRunningDataGen()) {
            SMNeoConditions.CONDITION_CODECS.register(bus);
        }
    }
}