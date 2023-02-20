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
        addOreFeature("jade_ore", -16, 112, 16);

        return JsonCodecProvider.forDatapackRegistry(generator, fileHelper, SullysMod.MOD_ID,  RegistryOps.create(JsonOps.INSTANCE, SMDatagenUtil.REGISTRY_ACCESS), Registry.PLACED_FEATURE_REGISTRY, ENTRIES);
    }

    private static void addOreFeature(String name, int minHeight, int maxHeight, int count) {
        addFeaturePlacement(name, HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)), CountPlacement.of(count), InSquarePlacement.spread(), BiomeFilter.biome());
    }

    private static void addFeaturePlacement(String name, PlacementModifier... placementModifiers) {
        addEntry(name, new PlacedFeature(SMDatagenUtil.getConfigHolder(name), List.of(placementModifiers)));
    }

    private static void addEntry(String name, PlacedFeature feature) {
        ENTRIES.put(SullysMod.modPrefix(name), feature);
    }
}