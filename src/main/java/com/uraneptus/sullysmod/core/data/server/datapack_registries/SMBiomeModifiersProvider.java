package com.uraneptus.sullysmod.core.data.server.datapack_registries;

import com.mojang.serialization.JsonOps;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.data.SMDatagenUtil;
import com.uraneptus.sullysmod.core.other.tags.SMBiomeTags;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import net.minecraft.core.HolderSet;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class SMBiomeModifiersProvider {
    private static final Map<ResourceLocation, BiomeModifier> ENTRIES = new HashMap<>();

    public static JsonCodecProvider<BiomeModifier> createBiomeModifiers(DataGenerator generator, ExistingFileHelper fileHelper) {
        addFeatureModifier("jade_ore", SMBiomeTags.JADE_GENERATES_IN, GenerationStep.Decoration.UNDERGROUND_ORES);
        addSingleSpawnModifier("lanternfish", SMBiomeTags.LANTERNFISH_SPAWN_IN, SMEntityTypes.LANTERNFISH.get(), 10, 1, 2);
        addSingleSpawnModifier("tortoise", SMBiomeTags.TORTOISES_SPAWN_IN, SMEntityTypes.TORTOISE.get(), 5, 1, 3);

        return JsonCodecProvider.forDatapackRegistry(generator, fileHelper, SullysMod.MOD_ID,  RegistryOps.create(JsonOps.INSTANCE, SMDatagenUtil.REGISTRY_ACCESS), ForgeRegistries.Keys.BIOME_MODIFIERS, ENTRIES);
    }

    private static void addFeatureModifier(String name, TagKey<Biome> biomeTag, GenerationStep.Decoration decorationStep) {
        addEntry(name, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(SMDatagenUtil.BIOME_REGISTRY, biomeTag), SMDatagenUtil.getPlacementHolder(name), decorationStep));
    }

    private static void addSingleSpawnModifier(String name, TagKey<Biome> biomeTag, EntityType<?> entity, int weight, int minCount, int maxCount) {
        addEntry(name, ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(new HolderSet.Named<>(SMDatagenUtil.BIOME_REGISTRY, biomeTag), new MobSpawnSettings.SpawnerData(entity, weight, minCount, maxCount)));
    }

    private static void addEntry(String name, BiomeModifier modifier) {
        ENTRIES.put(SullysMod.modPrefix(name), modifier);
    }
}