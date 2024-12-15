package com.uraneptus.sullysmod.common.levelgen.biome_modifiers;

import com.mojang.serialization.Codec;
import com.uraneptus.sullysmod.core.SMFeatures;
import com.uraneptus.sullysmod.core.registry.SMBiomeModifiers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.List;

public record OptionalAddSpawnsBiomeModifier(HolderSet<Biome> biomes, List<MobSpawnSettings.SpawnerData> spawners, List<SMFeatures> modFeatures) implements BiomeModifier {

    public static OptionalAddSpawnsBiomeModifier singleSpawn(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawner, List<SMFeatures> modFeatures) {
        return new OptionalAddSpawnsBiomeModifier(biomes, List.of(spawner), modFeatures);
    }

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (modFeatures.stream().allMatch(SMFeatures::isEnabled)) {
            if (phase == Phase.ADD && this.biomes.contains(biome)) {
                MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
                for (MobSpawnSettings.SpawnerData spawner : this.spawners) {
                    EntityType<?> type = spawner.type;
                    spawns.addSpawn(type.getCategory(), spawner);
                }
            }
        }

    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SMBiomeModifiers.ADD_OPTIONAL_SPAWNS_BIOME_MODIFIER_TYPE.get();
    }
}
