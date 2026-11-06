package com.mosaicmodding.sullysmod;

import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.core.event.SMBiomeModification;
import com.mosaicmodding.sullysmod.registry.SMFabricConditions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;

import java.util.Arrays;

public class SullysModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        SullysMod.init();
        SMFabricConditions.init();
        registerBiomeSpawnAdditions();
    }

    private void registerBiomeSpawnAdditions() {
        SMBiomeModification.forBiomeSpawnAdditions((biomeTag, type, weight, min, max, requiredModFeatures) -> {
            if (Arrays.stream(requiredModFeatures).allMatch(SMCommonConfig.HOLDER::isFeatureEnabled)) {
                BiomeModifications.addSpawn(context -> context.hasTag(biomeTag), type.getCategory(), type, weight, min, max);
            }
        });
    }
}
