package com.uraneptus.sullysmod.core.data.server;

import com.google.common.collect.ImmutableList;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.levelgen.PetrifiedTreeGravelDecorator;
import com.uraneptus.sullysmod.core.data.SMDatagenUtil;
import com.uraneptus.sullysmod.core.other.tags.SMBiomeTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMDamageTypes;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class SMDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder SET_BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ConfiguredFeatures::create)
            .add(Registries.PLACED_FEATURE, PlacedFeatures::create)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModifiers::create)
            .add(Registries.DAMAGE_TYPE, DamageSources::create);

    public SMDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, SET_BUILDER, Set.of(SullysMod.MOD_ID));
    }

    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_JADE_ORE = ResourceKey.create(Registries.CONFIGURED_FEATURE, SullysMod.modPrefix("jade_ore"));
    public static final ResourceKey<PlacedFeature> PLACED_JADE_ORE = ResourceKey.create(Registries.PLACED_FEATURE, SullysMod.modPrefix("jade_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_PETRIFIED_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, SullysMod.modPrefix("petrified_tree"));
    public static final ResourceKey<PlacedFeature> PLACED_PETRIFIED_TREE = ResourceKey.create(Registries.PLACED_FEATURE, SullysMod.modPrefix("petrified_tree"));

    private static class ConfiguredFeatures {
        private static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        private static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        private static final List<OreConfiguration.TargetBlockState> JADE_ORE_TARGET_LIST = List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, SMBlocks.JADE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, SMBlocks.DEEPSLATE_JADE_ORE.get().defaultBlockState()));

        public static void create(BootstapContext<ConfiguredFeature<?, ?>> context) {
            register(context, SMDatapackBuiltinEntriesProvider.CONFIGURED_JADE_ORE, () -> addOreConfig(JADE_ORE_TARGET_LIST, 10));
            register(context, SMDatapackBuiltinEntriesProvider.CONFIGURED_PETRIFIED_TREE, () -> addTreeConfig(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(SMBlocks.PETRIFIED_LOG.get()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).decorators(ImmutableList.of(new PetrifiedTreeGravelDecorator()))));
        }

        private static ConfiguredFeature<?, ?> addOreConfig(List<OreConfiguration.TargetBlockState> targetList, int veinSize) {
            return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targetList, veinSize));
        }

        private static ConfiguredFeature<?, ?> addTreeConfig(TreeConfiguration.TreeConfigurationBuilder treeBuilder) {
            return new ConfiguredFeature<>(Feature.TREE, treeBuilder.build());
        }

        private static void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> featureKey, Supplier<? extends ConfiguredFeature<?, ?>> feature) {
            context.register(featureKey, feature.get());
        }
    }

    private static class PlacedFeatures {
        public static void create(BootstapContext<PlacedFeature> context) {
            register(context, SMDatapackBuiltinEntriesProvider.PLACED_JADE_ORE, addOreFeature(context.lookup(Registries.CONFIGURED_FEATURE).get(SMDatapackBuiltinEntriesProvider.CONFIGURED_JADE_ORE).orElseThrow(), -16, 112, 16));
            register(context, SMDatapackBuiltinEntriesProvider.PLACED_PETRIFIED_TREE, addFeaturePlacement(context.lookup(Registries.CONFIGURED_FEATURE).get(SMDatapackBuiltinEntriesProvider.CONFIGURED_PETRIFIED_TREE).orElseThrow(), PlacementUtils.filteredByBlockSurvival(SMBlocks.PETRIFIED_SAPLING.get())));
        }

        private static PlacedFeature addOreFeature(Holder<ConfiguredFeature<?, ?>> configureFeature, int minHeight, int maxHeight, int count) {
            return addFeaturePlacement(configureFeature, HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)), CountPlacement.of(count), InSquarePlacement.spread(), BiomeFilter.biome());
        }

        private static PlacedFeature addFeaturePlacement(Holder<ConfiguredFeature<?, ?>> configureFeature, PlacementModifier... placementModifiers) {
            return new PlacedFeature(configureFeature, List.of(placementModifiers));
        }

        private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> featureKey, PlacedFeature feature) {
            context.register(featureKey, feature);
        }
    }

    private static class BiomeModifiers {
        public static void create(BootstapContext<BiomeModifier> context) {
            register(context, "lanternfish", () -> addSingleSpawnModifier(context, SMBiomeTags.LANTERNFISH_SPAWN_IN, SMEntityTypes.LANTERNFISH.get(), 15, 4, 6));
            register(context, "piranha", () -> addSingleSpawnModifier(context, SMBiomeTags.PIRANHA_SPAWN_IN, SMEntityTypes.PIRANHA.get(), 10, 2, 5));
            register(context, "tortoise", () -> addSingleSpawnModifier(context, SMBiomeTags.TORTOISES_SPAWN_IN, SMEntityTypes.TORTOISE.get(), 5, 1, 3));
            register(context, "bouldering_zombie", () -> addSingleSpawnModifier(context, SMBiomeTags.BOULDERING_ZOMBIE_SPAWN_IN, SMEntityTypes.BOULDERING_ZOMBIE.get(), 100, 4, 6));
            register(context, "jungle_spider", () -> addSingleSpawnModifier(context, SMBiomeTags.JUNGLE_SPIDER_SPAWN_IN, SMEntityTypes.JUNGLE_SPIDER.get(), 100, 3, 6));
            register(context, "jade_ore", () -> addFeatureModifier(context, SMDatagenUtil.getPlacedHolderSet(context, SMDatapackBuiltinEntriesProvider.PLACED_JADE_ORE), SMBiomeTags.JADE_GENERATES_IN, GenerationStep.Decoration.UNDERGROUND_ORES));

        }

        private static ForgeBiomeModifiers.AddFeaturesBiomeModifier addFeatureModifier(BootstapContext<BiomeModifier> context, HolderSet<PlacedFeature> placedSet, TagKey<Biome> biomeTag, GenerationStep.Decoration decoration) {
            return new ForgeBiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomeTag), placedSet, decoration);
        }

        private static ForgeBiomeModifiers.AddSpawnsBiomeModifier addSingleSpawnModifier(BootstapContext<BiomeModifier> context, TagKey<Biome> biomeTag, EntityType<?> entity, int weight, int minCount, int maxCount) {
            return ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(context.lookup(Registries.BIOME).getOrThrow(biomeTag), new MobSpawnSettings.SpawnerData(entity, weight, minCount, maxCount));
        }

        private static void register(BootstapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
            context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, SullysMod.modPrefix(name)), modifier.get());
        }
    }

    private static class DamageSources {
        protected static void create(BootstapContext<DamageType> context) {
            SMDamageTypes.damageTypeMap.forEach(((damageTypeKey, damageType) -> register(context, damageTypeKey, damageType)));
        }

        protected static void register(BootstapContext<DamageType> context, ResourceKey<DamageType> key, DamageType damageType) {
            context.register(key, damageType);
        }
    }
}
