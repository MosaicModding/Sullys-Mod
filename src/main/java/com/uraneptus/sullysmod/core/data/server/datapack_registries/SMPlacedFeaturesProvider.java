package com.uraneptus.sullysmod.core.data.server.datapack_registries;

import com.mojang.serialization.JsonOps;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.data.SMDatagenUtil;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SMPlacedFeaturesProvider {
    private static final Map<ResourceLocation, PlacedFeature> ENTRIES = new HashMap<>();

    public static JsonCodecProvider<PlacedFeature> createPlacedFeatures(DataGenerator generator, ExistingFileHelper fileHelper) {
        addFeaturePlacement("jade_ore", HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112)), CountPlacement.of(16), InSquarePlacement.spread(), BiomeFilter.biome());

        return JsonCodecProvider.forDatapackRegistry(generator, fileHelper, SullysMod.MOD_ID,  RegistryOps.create(JsonOps.INSTANCE, SMDatagenUtil.REGISTRY_ACCESS), Registry.PLACED_FEATURE_REGISTRY, ENTRIES);
    }

    private static void addFeaturePlacement(String name, PlacementModifier... placementModifiers) {
        addEntry(name, new PlacedFeature(SMDatagenUtil.getConfigHolder(name), List.of(placementModifiers)));
    }

    private static void addEntry(String name, PlacedFeature feature) {
        ENTRIES.put(SullysMod.modPrefix(name), feature);
    }
}