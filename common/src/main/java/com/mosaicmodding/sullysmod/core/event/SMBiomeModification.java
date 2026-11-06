package com.mosaicmodding.sullysmod.core.event;

import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.registry.SMEntityTypes;
import com.mosaicmodding.sullysmod.registry.SMTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;

public class SMBiomeModification {

    @FunctionalInterface
    public interface BiomeSpawnAdditionConsumer {
       void accept(TagKey<Biome> biomeTag, EntityType<?> type, int weight, int min, int max, String... requiredModFeatures);
    }

    public static void forBiomeSpawnAdditions(BiomeSpawnAdditionConsumer consumer) {
        consumer.accept(SMTags.Biomes.BOULDERING_ZOMBIE_SPAWN_IN, SMEntityTypes.BOULDERING_ZOMBIE.get(), 100, 4, 6, SMCommonConfig.BOULDERING_ZOMBIE.getSecond());
        consumer.accept(SMTags.Biomes.LANTERNFISH_SPAWN_IN, SMEntityTypes.LANTERNFISH.get(), 15, 4, 6, SMCommonConfig.LANTERNFISH.getSecond());
    }


}
