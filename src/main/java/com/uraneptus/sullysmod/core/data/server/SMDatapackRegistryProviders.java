package com.uraneptus.sullysmod.core.data.server;

import com.google.gson.JsonElement;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.tags.SMBiomeTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;

public class SMDatapackRegistryProviders {
    //Static Things
    public static final ResourceLocation JADE_ORE_RL = SullysMod.modPrefix("jade_ore");
    public static final ResourceLocation TORTOISE_RL = SullysMod.modPrefix("tortoise");
    public static final ResourceLocation LANTERNFISH_RL = SullysMod.modPrefix("lanternfish");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JADE_ORE_CONFIGURED_KEY = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, JADE_ORE_RL);
    public static void registerDatapackProviders(ExistingFileHelper fileHelper, DataGenerator generator, RegistryOps<JsonElement> registryOps){

        //TODO: This should probably be cleaned up at some point, but it does the job for now.

        //Configured Feature Stuff
        List<OreConfiguration.TargetBlockState> JADE_ORE_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SMBlocks.JADE_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SMBlocks.DEEPSLATE_JADE_ORE.get().defaultBlockState()));
        final ConfiguredFeature<OreConfiguration, ?> JADE_ORE_CONFIGURED = new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(JADE_ORE_TARGET_LIST, 10));
        final Holder<ConfiguredFeature<?, ?>> JADE_ORE_CONFIG_HOLDER = registryOps.registry(Registry.CONFIGURED_FEATURE_REGISTRY).orElseThrow().getOrCreateHolderOrThrow(JADE_ORE_CONFIGURED_KEY);

        //Placed Feature Stuff
        final PlacedFeature JADE_ORE_PLACED = new PlacedFeature(JADE_ORE_CONFIG_HOLDER, List.of(HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112)), CountPlacement.of(16), InSquarePlacement.spread(), BiomeFilter.biome()));

        //Biome Tags
        final HolderSet.Named<Biome> JADE_BIOMES = new HolderSet.Named<>(registryOps.registry(Registry.BIOME_REGISTRY).orElseThrow(), SMBiomeTags.JADE_GENERATES_IN);
        final HolderSet.Named<Biome> TORTOISE_BIOMES = new HolderSet.Named<>(registryOps.registry(Registry.BIOME_REGISTRY).orElseThrow(), SMBiomeTags.TORTOISES_SPAWN_IN);
        final HolderSet.Named<Biome> LANTERNFISH_BIOMES = new HolderSet.Named<>(registryOps.registry(Registry.BIOME_REGISTRY).orElseThrow(), SMBiomeTags.LANTERNFISH_SPAWN_IN);

        //Biome Modifiers
        final BiomeModifier JADE_MODIFIER = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(JADE_BIOMES,
                HolderSet.direct(registryOps.registry(Registry.PLACED_FEATURE_REGISTRY).orElseThrow().getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, JADE_ORE_RL))),
                GenerationStep.Decoration.UNDERGROUND_ORES);
        final BiomeModifier TORTOISE_MODIFIER = ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(TORTOISE_BIOMES, new MobSpawnSettings.SpawnerData(SMEntityTypes.TORTOISE.get(), 5, 1, 3));
        final BiomeModifier LANTERNFISH_MODIFIER = ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(LANTERNFISH_BIOMES, new MobSpawnSettings.SpawnerData(SMEntityTypes.LANTERNFISH.get(), 10, 1, 2));

        //Configured Feature Provider
        generator.addProvider(true, JsonCodecProvider.forDatapackRegistry(
                generator, fileHelper, SullysMod.MOD_ID, registryOps, Registry.CONFIGURED_FEATURE_REGISTRY, Map.of(
                        JADE_ORE_RL, JADE_ORE_CONFIGURED
                )));
        //Placed Feature Provider
        generator.addProvider(true, JsonCodecProvider.forDatapackRegistry(
                generator, fileHelper, SullysMod.MOD_ID, registryOps, Registry.PLACED_FEATURE_REGISTRY, Map.of(
                        JADE_ORE_RL, JADE_ORE_PLACED
                )));
        //Biome Modifier Provider
        generator.addProvider(true, JsonCodecProvider.forDatapackRegistry(
                generator, fileHelper, SullysMod.MOD_ID, registryOps, ForgeRegistries.Keys.BIOME_MODIFIERS, Map.of(
                        JADE_ORE_RL, JADE_MODIFIER,
                        TORTOISE_RL, TORTOISE_MODIFIER,
                        LANTERNFISH_RL, LANTERNFISH_MODIFIER
                )));
    }
}
