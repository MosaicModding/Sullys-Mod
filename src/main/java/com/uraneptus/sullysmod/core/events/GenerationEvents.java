package com.uraneptus.sullysmod.core.events;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GenerationEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        MobSpawnSettingsBuilder spawns = event.getSpawns();
        Biome.BiomeCategory biomeCategory = event.getCategory();
        ResourceLocation biome = event.getName();

        if (!DataUtil.matchesKeys(biome, Biomes.LUSH_CAVES)) {
            spawns.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(SMEntityTypes.LANTERNFISH.get(), 5, 1, 5));
        }
    }
}
