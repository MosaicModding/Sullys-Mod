package com.mosaicmodding.sullysmod.registry;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.core.biome_modifiers.OptionalAddFeatureModifier;
import com.mosaicmodding.sullysmod.core.biome_modifiers.OptionalAddSpawnModifier;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.function.Function;

public class SMBiomeModifiers {
    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, SullysMod.ID);

    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<OptionalAddFeatureModifier>> OPTIONAL_ADD_FEATURE = BIOME_MODIFIERS.register("optional_add_features",
            () -> RecordCodecBuilder.mapCodec(builder -> builder
                    .group(
                            Biome.LIST_CODEC.fieldOf("biomes").forGetter(OptionalAddFeatureModifier::biomes),
                            PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(OptionalAddFeatureModifier::features),
                            GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(OptionalAddFeatureModifier::step),
                            Codec.STRING.listOf().fieldOf("required_mod_features").forGetter(OptionalAddFeatureModifier::required_mod_features))
                    .apply(builder, OptionalAddFeatureModifier::new)));

    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<OptionalAddSpawnModifier>> OPTIONAL_ADD_SPAWN = BIOME_MODIFIERS.register("optional_add_spawns",
            () -> RecordCodecBuilder.mapCodec(builder -> builder
                    .group(
                            Biome.LIST_CODEC.fieldOf("biomes").forGetter(OptionalAddSpawnModifier::biomes),
                            Codec.either(MobSpawnSettings.SpawnerData.CODEC.listOf(), MobSpawnSettings.SpawnerData.CODEC).xmap(
                                    either -> either.map(Function.identity(), List::of),
                                    list -> list.size() == 1 ? Either.right(list.get(0)) : Either.left(list)
                            ).fieldOf("spawners").forGetter(OptionalAddSpawnModifier::spawners),
                            Codec.STRING.listOf().fieldOf("required_mod_features").forGetter(OptionalAddSpawnModifier::reguiredModFeatures))
                    .apply(builder, OptionalAddSpawnModifier::new)));
}
