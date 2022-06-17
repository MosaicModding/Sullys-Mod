package com.uraneptus.sullysmod.core.events;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SMGenerationEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        BiomeGenerationSettings.Builder generation = event.getGeneration();
        MobSpawnSettingsBuilder spawns = event.getSpawns();
        Biome.BiomeCategory biomeCategory = event.getCategory();
        ResourceLocation biome = event.getName();

        if (SMConfig.ENABLE_TORTOISES.get()) {
            if (biomeCategory == Biome.BiomeCategory.JUNGLE) {
                spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(SMEntityTypes.TORTOISE.get(), SMConfig.TORTOISE_SPAWN_WEIGHT.get(), 1, 3));
            }
            if (biomeCategory == Biome.BiomeCategory.SAVANNA) {
                spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(SMEntityTypes.TORTOISE.get(), SMConfig.TORTOISE_SPAWN_WEIGHT.get(), 1, 3));
            }
            if (DataUtil.matchesKeys(biome, Biomes.BIRCH_FOREST) || DataUtil.matchesKeys(biome, Biomes.OLD_GROWTH_BIRCH_FOREST)) {
                spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(SMEntityTypes.TORTOISE.get(), SMConfig.TORTOISE_SPAWN_WEIGHT.get() * 2, 1, 3));
            }
        }

        if (SMConfig.ENABLE_LANTERNFISH.get()) {
            if (!DataUtil.matchesKeys(biome, Biomes.LUSH_CAVES)) {
                spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(SMEntityTypes.LANTERNFISH.get(), SMConfig.LANTERNFISH_SPAWN_WEIGHT.get(), 1, 5));
            }
        }

        if (SMConfig.ENABLE_JADE.get()) {
            if (biomeCategory == Biome.BiomeCategory.JUNGLE) {
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, SMFeatures.Placement.JADE_ORE_PLACEMENT);
            }
        }
    }
}
