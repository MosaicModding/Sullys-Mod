package com.mosaicmodding.sullysmod.core.biome_modifiers;

import com.mojang.serialization.MapCodec;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.registry.SMBiomeModifiers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

import java.util.List;

public record OptionalAddSpawnModifier(HolderSet<Biome> biomes, List<MobSpawnSettings.SpawnerData> spawners, List<String> reguiredModFeatures) implements BiomeModifier {
    public static OptionalAddSpawnModifier singleSpawn(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawner, List<String> reguiredModFeatures) {
        return new OptionalAddSpawnModifier(biomes, List.of(spawner), reguiredModFeatures);
    }

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (this.reguiredModFeatures.stream().allMatch(SMCommonConfig.HOLDER::isFeatureEnabled)) {
            if (phase == Phase.ADD && this.biomes.contains(biome)) {
                MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();

                for(MobSpawnSettings.SpawnerData spawner : this.spawners) {
                    EntityType<?> type = spawner.type;
                    spawns.addSpawn(type.getCategory(), spawner);
                }
            }
        }

    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return SMBiomeModifiers.OPTIONAL_ADD_SPAWN.get();
    }
}
