package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.content.lanternfish.LanternfishEntity;
import com.mosaicmodding.sullysmod.content.undead_variants.BoulderingZombieEntity;
import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Function;

import static com.mosaicmodding.sullysmod.registry.SMRegUtils.path;

public class SMEntityTypes {

    public static final RegSupplier<EntityType<BoulderingZombieEntity>> BOULDERING_ZOMBIE = register("bouldering_zombie", BoulderingZombieEntity::new, MobCategory.MONSTER, 0.6F, 1.95F);
    public static final RegSupplier<EntityType<LanternfishEntity>> LANTERNFISH = register("lanternfish", LanternfishEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE, 0.5F, 0.3F);

    @EventCalled
    public static void registerAttributes(RegHelper.AttributeEvent event) {
        event.register(BOULDERING_ZOMBIE.get(), BoulderingZombieEntity.createAttributes());
        event.register(LANTERNFISH.get(), LanternfishEntity.createAttributes());
    }

    @EventCalled
    public static void registerSpawnPlacements(RegHelper.SpawnPlacementEvent event) {
        event.register(BOULDERING_ZOMBIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoulderingZombieEntity::checkBoulderingZombieSpawnRules);
        event.register(LANTERNFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LanternfishEntity::checkLanternfishSpawnRules);
    }

    public static <T extends Entity> RegSupplier<EntityType<T>> register(String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height) {
        return register(name, factory, category, width, height, builder -> builder);
    }

    public static <T extends Entity> RegSupplier<EntityType<T>> register(String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, Function<EntityType.Builder<T>, EntityType.Builder<T>> additional) {
        var builder = EntityType.Builder.of(factory, category);
        return RegHelper.registerEntityType(path(name), additional.apply(builder));
    }

    public static void init() {
        RegHelper.addAttributeRegistration(SMEntityTypes::registerAttributes);
        RegHelper.addSpawnPlacementsRegistration(SMEntityTypes::registerSpawnPlacements);
    }
}
