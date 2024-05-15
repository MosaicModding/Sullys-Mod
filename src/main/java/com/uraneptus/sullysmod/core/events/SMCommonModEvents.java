package com.uraneptus.sullysmod.core.events;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.BoulderingZombie;
import com.uraneptus.sullysmod.common.entities.Lanternfish;
import com.uraneptus.sullysmod.common.entities.Piranha;
import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import com.uraneptus.sullysmod.core.other.tags.SMBiomeTags;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.resource.PathPackResources;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("unused")
public class SMCommonModEvents {

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
       event.register(SMEntityTypes.LANTERNFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Lanternfish::checkLanternfishSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
       event.register(SMEntityTypes.PIRANHA.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Piranha::checkPiranhaSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
       event.register(SMEntityTypes.TORTOISE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
       event.register(SMEntityTypes.BOULDERING_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoulderingZombie::checkBoulderingZombieSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
       event.register(SMEntityTypes.JUNGLE_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);

       event.register(EntityType.ZOMBIE, SMCommonModEvents::zombieExtraRules, SpawnPlacementRegisterEvent.Operation.AND);
       event.register(EntityType.SPIDER, SMCommonModEvents::spiderExtraRules, SpawnPlacementRegisterEvent.Operation.AND);
    }

    public static boolean zombieExtraRules(EntityType<? extends Monster> pType, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return !SMConfig.DISABLE_DEEPSLATE_ZOMBIE_SPAWNS.get() || pSpawnType.equals(MobSpawnType.SPAWNER) || pPos.getY() > 0;
    }

    public static boolean spiderExtraRules(EntityType<? extends Monster> pType, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return !SMConfig.DISABLE_SPIDER_IN_JUNGLE_SPAWNS.get() || pSpawnType.equals(MobSpawnType.SPAWNER) || !pLevel.getBiome(pPos).is(SMBiomeTags.JUNGLE_SPIDER_SPAWN_IN);
    }

    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent event) {
        event.addRepositorySource(consumer -> {
            String path = SullysMod.modPrefix(SMTextDefinitions.ZOMBIE_PACK_NAME).toString();
            IModFile file = ModList.get().getModFileById(SullysMod.MOD_ID).getFile();
            try (PathPackResources packResources = new PathPackResources(path, true, file.findResource("builtin/" + SMTextDefinitions.ZOMBIE_PACK_NAME))) {
                consumer.accept(Pack.readMetaAndCreate(path, SMTextDefinitions.ZOMBIE_PACK_DISPLAY_NAME, false, (id) -> packResources, PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN));
            }
        });
    }
}
