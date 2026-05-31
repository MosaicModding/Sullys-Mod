package com.uraneptus.sullysmod.core.registry;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.levelgen.biome_modifiers.OptionalAddFeaturesBiomeModifier;
import com.uraneptus.sullysmod.common.levelgen.biome_modifiers.OptionalAddSpawnsBiomeModifier;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class SMBiomeModifiers {
    private static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, SullysMod.MOD_ID);

    public static final Supplier<MapCodec<OptionalAddFeaturesBiomeModifier>> ADD_OPTIONAL_FEATURES_BIOME_MODIFIER_TYPE =
            BIOME_MODIFIERS.register("add_optional_features", () ->
            RecordCodecBuilder.mapCodec(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(OptionalAddFeaturesBiomeModifier::biomes),
                    PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(OptionalAddFeaturesBiomeModifier::features),
                    GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(OptionalAddFeaturesBiomeModifier::step),
                    SMFeatures.CODEC.listOf().fieldOf("mod_feature").forGetter(OptionalAddFeaturesBiomeModifier::modFeatures)
            ).apply(builder, OptionalAddFeaturesBiomeModifier::new))
    );

    public static final Supplier<MapCodec<OptionalAddSpawnsBiomeModifier>> ADD_OPTIONAL_SPAWNS_BIOME_MODIFIER_TYPE = BIOME_MODIFIERS.register("add_optional_spawns", () ->
            RecordCodecBuilder.mapCodec(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(OptionalAddSpawnsBiomeModifier::biomes),
                    Codec.either(MobSpawnSettings.SpawnerData.CODEC.listOf(), MobSpawnSettings.SpawnerData.CODEC).xmap(
                            either -> either.map(Function.identity(), List::of),
                            list -> list.size() == 1 ? Either.right(list.get(0)) : Either.left(list)
                    ).fieldOf("spawners").forGetter(OptionalAddSpawnsBiomeModifier::spawners),
                    SMFeatures.CODEC.listOf().fieldOf("mod_feature").forGetter(OptionalAddSpawnsBiomeModifier::modFeatures)
            ).apply(builder, OptionalAddSpawnsBiomeModifier::new))
    );
}
