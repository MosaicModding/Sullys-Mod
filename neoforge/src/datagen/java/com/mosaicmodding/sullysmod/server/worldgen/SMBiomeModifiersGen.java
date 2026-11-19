package com.mosaicmodding.sullysmod.server.worldgen;

import com.mosaicmodding.sullysmod.core.biome_modifiers.OptionalAddSingleFeatureModifier;
import com.mosaicmodding.sullysmod.core.biome_modifiers.OptionalAddSpawnModifier;
import com.mosaicmodding.sullysmod.registry.SMEntityTypes;
import com.mosaicmodding.sullysmod.registry.datapack.SMWorldgenFeatureDefinitions;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class SMBiomeModifiersGen {

    public static void add(BootstrapContext<BiomeModifier> context) {
        SMEntityTypes.registerBiomeSpawnAdditions((biomeTag, type, weight, min, max, requiredModFeatures) -> {
            var name = Utils.getID(type).getPath();

            register(context, name, addSingleSpawnModifier(context, biomeTag, type, weight, min, max, Arrays.stream(requiredModFeatures).toList()));
        });
        SMWorldgenFeatureDefinitions.registerBiomeFeatureAdditions((biomeTag, placed, generationStep, requiredModFeatures) -> {
            register(context, placed.location().getPath(), addFeatureModifier(context, placed, biomeTag, generationStep, Arrays.stream(requiredModFeatures).toList()));
        });
    }

    private static OptionalAddSpawnModifier addSingleSpawnModifier(BootstrapContext<BiomeModifier> context, TagKey<Biome> biomeTag, EntityType<?> entity, int weight, int minCount, int maxCount, List<String> reguiredModFeatures) {
        return OptionalAddSpawnModifier.singleSpawn(context.lookup(Registries.BIOME).getOrThrow(biomeTag), new MobSpawnSettings.SpawnerData(entity, weight, minCount, maxCount), reguiredModFeatures);
    }

    private static OptionalAddSingleFeatureModifier addFeatureModifier(BootstrapContext<BiomeModifier> context, ResourceKey<PlacedFeature> placed, TagKey<Biome> biomeTag, GenerationStep.Decoration generationStep, List<String> reguiredModFeatures) {
        return new OptionalAddSingleFeatureModifier(context.lookup(Registries.BIOME).getOrThrow(biomeTag), context.lookup(Registries.PLACED_FEATURE).getOrThrow(placed), generationStep, reguiredModFeatures);
    }

    private static void register(BootstrapContext<BiomeModifier> context, String name, BiomeModifier modifier) {
        context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, path(name)), modifier);
    }
}
