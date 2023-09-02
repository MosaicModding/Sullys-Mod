package com.uraneptus.sullysmod.core.data.server.datapack_registries;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.data.SMDatagenUtil;
import com.uraneptus.sullysmod.core.other.tags.SMBiomeTags;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class SMBiomeModifiers {

    public static void create(BootstapContext<BiomeModifier> context) {
        register(context, "lanternfish", () -> addSingleSpawnModifier(context, SMBiomeTags.LANTERNFISH_SPAWN_IN, SMEntityTypes.LANTERNFISH.get(), 10, 1, 2));
        register(context, "tortoise", () -> addSingleSpawnModifier(context, SMBiomeTags.TORTOISES_SPAWN_IN, SMEntityTypes.TORTOISE.get(), 5, 1, 3));
        register(context, "jade_ore", () -> addFeatureModifier(context, "jade_ore", SMBiomeTags.JADE_GENERATES_IN, GenerationStep.Decoration.UNDERGROUND_ORES));

    }

    private static ForgeBiomeModifiers.AddFeaturesBiomeModifier addFeatureModifier(BootstapContext<BiomeModifier> context, String name, TagKey<Biome> biomeTag, GenerationStep.Decoration decoration) {
        return new ForgeBiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomeTag), SMDatagenUtil.getPlacementHolder(name, context.lookup(Registries.PLACED_FEATURE)), decoration);
    }

    private static ForgeBiomeModifiers.AddSpawnsBiomeModifier addSingleSpawnModifier(BootstapContext<BiomeModifier> context, TagKey<Biome> biomeTag, EntityType<?> entity, int weight, int minCount, int maxCount) {
        return ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(context.lookup(Registries.BIOME).getOrThrow(biomeTag), new MobSpawnSettings.SpawnerData(entity, weight, minCount, maxCount));
    }

    private static void register(BootstapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, SullysMod.modPrefix(name)), modifier.get());
    }
}