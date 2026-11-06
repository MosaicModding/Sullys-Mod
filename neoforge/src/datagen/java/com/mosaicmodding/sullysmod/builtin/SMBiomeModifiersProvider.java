package com.mosaicmodding.sullysmod.builtin;

import com.mosaicmodding.sullysmod.core.biome_modifiers.OptionalAddSpawnModifier;
import com.mosaicmodding.sullysmod.core.event.SMBiomeModification;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.mosaicmodding.sullysmod.registry.SMRegUtils.path;

public class SMBiomeModifiersProvider {

    public static void create(BootstrapContext<BiomeModifier> context) {
        SMBiomeModification.forBiomeSpawnAdditions((biomeTag, type, weight, min, max, requiredModFeatures) -> {
            var name = Utils.getID(type).getPath();

            register(context, name, () -> addSingleSpawnModifier(context, biomeTag, type, weight, min, max, Arrays.stream(requiredModFeatures).toList()));
        });
    }

    private static OptionalAddSpawnModifier addSingleSpawnModifier(BootstrapContext<BiomeModifier> context, TagKey<Biome> biomeTag, EntityType<?> entity, int weight, int minCount, int maxCount, List<String> reguiredModFeatures) {
        return OptionalAddSpawnModifier.singleSpawn(context.lookup(Registries.BIOME).getOrThrow(biomeTag), new MobSpawnSettings.SpawnerData(entity, weight, minCount, maxCount), reguiredModFeatures);
    }

    private static void register(BootstrapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, path(name)), modifier.get());
    }
}
