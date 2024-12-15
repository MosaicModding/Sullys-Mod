package com.uraneptus.sullysmod.data.server.builtin;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.levelgen.biome_modifiers.OptionalAddFeaturesBiomeModifier;
import com.uraneptus.sullysmod.common.levelgen.biome_modifiers.OptionalAddSpawnsBiomeModifier;
import com.uraneptus.sullysmod.core.SMFeatures;
import com.uraneptus.sullysmod.core.other.SMFeatureDefinitions;
import com.uraneptus.sullysmod.core.other.tags.SMBiomeTags;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.data.SMDatagenUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Supplier;

public class SMBiomeModifiersProvider {
    public static void create(BootstapContext<BiomeModifier> context) {
        register(context, "lanternfish", () -> addSingleSpawnModifier(context, SMBiomeTags.LANTERNFISH_SPAWN_IN, SMEntityTypes.LANTERNFISH.get(), 15, 4, 6, SMFeatures.LANTERNFISH));
        register(context, "piranha", () -> addSingleSpawnModifier(context, SMBiomeTags.PIRANHA_SPAWN_IN, SMEntityTypes.PIRANHA.get(), 10, 2, 5, SMFeatures.PIRANHA));
        register(context, "tortoise", () -> addSingleSpawnModifier(context, SMBiomeTags.TORTOISES_SPAWN_IN, SMEntityTypes.TORTOISE.get(), 5, 1, 3, SMFeatures.TORTOISE));
        register(context, "bouldering_zombie", () -> addSingleSpawnModifier(context, SMBiomeTags.BOULDERING_ZOMBIE_SPAWN_IN, SMEntityTypes.BOULDERING_ZOMBIE.get(), 100, 4, 6, SMFeatures.BOULDERING_ZOMBIE));
        register(context, "jungle_spider", () -> addSingleSpawnModifier(context, SMBiomeTags.JUNGLE_SPIDER_SPAWN_IN, SMEntityTypes.JUNGLE_SPIDER.get(), 100, 3, 6, SMFeatures.JUNGLE_SPIDER));
        register(context, "jade_ore", () -> addFeatureModifier(context, SMFeatureDefinitions.PLACED_JADE_ORE, SMBiomeTags.JADE_GENERATES_IN, GenerationStep.Decoration.UNDERGROUND_ORES, SMFeatures.JADE));
        register(context, "petrified_tree", () -> addFeatureModifier(context, SMFeatureDefinitions.PLACED_PETRIFIED_TREE, SMBiomeTags.PETRIFIED_TREES_GENERATE_IN, GenerationStep.Decoration.UNDERGROUND_STRUCTURES, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER));
        register(context, "artifact_gravel", () -> addFeatureModifier(context, SMFeatureDefinitions.PLACED_ARTIFACT_GRAVEL, SMBiomeTags.ARTIFACT_GRAVEL_GENERATE_IN, GenerationStep.Decoration.UNDERGROUND_DECORATION, SMFeatures.ARTIFACTS));
    }

    private static OptionalAddFeaturesBiomeModifier addFeatureModifier(BootstapContext<BiomeModifier> context, ResourceKey<PlacedFeature> placedSet, TagKey<Biome> biomeTag, GenerationStep.Decoration decoration, SMFeatures... features) {
        return new OptionalAddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomeTag),  SMDatagenUtil.getPlacedHolderSet(context, placedSet), decoration, List.of(features));
    }

    private static OptionalAddSpawnsBiomeModifier addSingleSpawnModifier(BootstapContext<BiomeModifier> context, TagKey<Biome> biomeTag, EntityType<?> entity, int weight, int minCount, int maxCount, SMFeatures... features) {
        return OptionalAddSpawnsBiomeModifier.singleSpawn(context.lookup(Registries.BIOME).getOrThrow(biomeTag), new MobSpawnSettings.SpawnerData(entity, weight, minCount, maxCount), List.of(features));
    }

    private static void register(BootstapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, SullysMod.modPrefix(name)), modifier.get());
    }
}
