package com.mosaicmodding.sullysmod;

import com.mosaicmodding.sullysmod.content.petrified.PetrifiedTreeVariant;
import com.mosaicmodding.sullysmod.core.SMFabricEventCallbacks;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.registry.SMBlocks;
import com.mosaicmodding.sullysmod.registry.SMEntityTypes;
import com.mosaicmodding.sullysmod.registry.SMFabricConditions;
import com.mosaicmodding.sullysmod.registry.datapack.SMPetrifiedTreeVariants;
import com.mosaicmodding.sullysmod.registry.datapack.SMWorldgenFeatureDefinitions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

import java.util.Arrays;

public class SullysModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        SullysMod.init();
        SMFabricConditions.init();
        SMFabricEventCallbacks.init();
        DynamicRegistries.register(SMPetrifiedTreeVariants.REGISTRY_KEY, PetrifiedTreeVariant.CODEC);
        registerBiomeModifications();
        registerStrippableBlocks();
    }

    private void registerBiomeModifications() {
        SMEntityTypes.registerBiomeSpawnAdditions((biomeTag, type, weight, min, max, requiredModFeatures) -> {
            if (Arrays.stream(requiredModFeatures).allMatch(SMCommonConfig.HOLDER::isFeatureEnabled)) {
                BiomeModifications.addSpawn(context -> context.hasTag(biomeTag), type.getCategory(), type, weight, min, max);
            }
        });
        SMWorldgenFeatureDefinitions.registerBiomeFeatureAdditions(((biomeTag, placed, generationStep, requiredModFeatures) -> {
            if (Arrays.stream(requiredModFeatures).allMatch(SMCommonConfig.HOLDER::isFeatureEnabled)) {
                BiomeModifications.addFeature(context -> context.hasTag(biomeTag), generationStep, placed);
            }
        }));
    }

    private void registerStrippableBlocks() {
        SMBlocks.addStrippableBlocks(((input, stripped, usingPickaxe) -> {
            if (!usingPickaxe) {
                StrippableBlockRegistry.register(input.get(), stripped.get());
            }
        }));
    }
}
