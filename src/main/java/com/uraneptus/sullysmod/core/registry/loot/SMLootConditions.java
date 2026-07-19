package com.uraneptus.sullysmod.core.registry.loot;

import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class SMLootConditions {
    public static final LootItemCondition.Builder ON_LANTERNFISH_HEIGHT = LocationCheck.checkLocation(LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.between(-57.0D, 16.0D)));
    public static final LootItemCondition.Builder IS_DEEPSLATE_CAVE = LocationCheck.checkLocation(LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.between(-59.0D, 0.0D)));
    public static final LootItemCondition.Builder IS_NORMAL_CAVE = LocationCheck.checkLocation(LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.between(1.0D, 140.0D)));

    public static LootItemCondition.Builder belowY(double y) {
        return LocationCheck.checkLocation(LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.between(-59.0D, y)));
    }

    public static LootItemCondition.Builder getPiranhaBiomes(HolderLookup.Provider registries) {
        return biomeConditionCheck(registries, Biomes.MANGROVE_SWAMP)
                .or(isJungle(registries));
    }

    public static LootItemCondition.Builder isJungle(HolderLookup.Provider registries) {
        return biomeConditionCheck(registries, Biomes.JUNGLE)
                .or(biomeConditionCheck(registries, Biomes.SPARSE_JUNGLE))
                .or(biomeConditionCheck(registries, Biomes.BAMBOO_JUNGLE));
    }

    public static LootItemCondition.Builder isSwamp(HolderLookup.Provider registries) {
        return biomeConditionCheck(registries, Biomes.SWAMP)
                .or(biomeConditionCheck(registries, Biomes.MANGROVE_SWAMP));
    }

    public static LootItemCondition.Builder isSnowy(HolderLookup.Provider registries) {
        return biomeConditionCheck(registries, Biomes.SNOWY_BEACH)
                .or(biomeConditionCheck(registries, Biomes.SNOWY_PLAINS))
                .or(biomeConditionCheck(registries, Biomes.SNOWY_SLOPES))
                .or(biomeConditionCheck(registries, Biomes.SNOWY_TAIGA))
                .or(biomeConditionCheck(registries, Biomes.FROZEN_PEAKS))
                .or(biomeConditionCheck(registries, Biomes.JAGGED_PEAKS))
                .or(biomeConditionCheck(registries, Biomes.ICE_SPIKES))
                .or(biomeConditionCheck(registries, Biomes.FROZEN_OCEAN))
                .or(biomeConditionCheck(registries, Biomes.FROZEN_RIVER))
                .or(biomeConditionCheck(registries, Biomes.GROVE));
    }

    public static LootItemCondition.Builder isForrest(HolderLookup.Provider registries) {
        return biomeConditionCheck(registries, Biomes.FOREST)
                .or(biomeConditionCheck(registries, Biomes.FLOWER_FOREST))
                .or(biomeConditionCheck(registries, Biomes.BIRCH_FOREST))
                .or(biomeConditionCheck(registries, Biomes.DARK_FOREST))
                .or(biomeConditionCheck(registries, Biomes.OLD_GROWTH_BIRCH_FOREST))
                .or(biomeConditionCheck(registries, Biomes.WINDSWEPT_FOREST));
    }

    public static LootItemCondition.Builder isMountain(HolderLookup.Provider registries) {
        return biomeConditionCheck(registries, Biomes.MEADOW)
                .or(biomeConditionCheck(registries, Biomes.FROZEN_PEAKS))
                .or(biomeConditionCheck(registries, Biomes.JAGGED_PEAKS))
                .or(biomeConditionCheck(registries, Biomes.STONY_PEAKS))
                .or(biomeConditionCheck(registries, Biomes.SNOWY_SLOPES))
                .or(biomeConditionCheck(registries, Biomes.CHERRY_GROVE));
    }


    public static LootItemCondition.Builder biomeConditionCheck(HolderLookup.Provider registries, ResourceKey<Biome> biome) {
        return LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiomes(HolderSet.direct(registries.lookupOrThrow(Registries.BIOME).getOrThrow(biome))));
    }
}
