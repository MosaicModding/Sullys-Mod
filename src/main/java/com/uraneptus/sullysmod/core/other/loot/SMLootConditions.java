package com.uraneptus.sullysmod.core.other.loot;

import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class SMLootConditions {
    public static final LootItemCondition.Builder ON_LANTERNFISH_HEIGHT = LocationCheck.checkLocation(LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.between(-57.0D, 16.0D)));
    public static final LootItemCondition.Builder IS_DEEPSLATE_CAVE = LocationCheck.checkLocation(LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.between(-59.0D, 0.0D)));
    public static final LootItemCondition.Builder IS_NORMAL_CAVE = LocationCheck.checkLocation(LocationPredicate.Builder.location().setY(MinMaxBounds.Doubles.between(1.0D, 140.0D)));

    public static LootItemCondition.Builder getPiranhaBiomes() {
        return biomeConditionCheck(Biomes.MANGROVE_SWAMP)
                .or(isJungle());
    }

    public static LootItemCondition.Builder isJungle() {
        return biomeConditionCheck(Biomes.JUNGLE)
                .or(biomeConditionCheck(Biomes.SPARSE_JUNGLE))
                .or(biomeConditionCheck(Biomes.BAMBOO_JUNGLE));
    }

    public static LootItemCondition.Builder isSwamp() {
        return biomeConditionCheck(Biomes.SWAMP)
                .or(biomeConditionCheck(Biomes.MANGROVE_SWAMP));
    }

    public static LootItemCondition.Builder isSnowy() {
        return biomeConditionCheck(Biomes.SNOWY_BEACH)
                .or(biomeConditionCheck(Biomes.SNOWY_PLAINS))
                .or(biomeConditionCheck(Biomes.SNOWY_SLOPES))
                .or(biomeConditionCheck(Biomes.SNOWY_TAIGA))
                .or(biomeConditionCheck(Biomes.FROZEN_PEAKS))
                .or(biomeConditionCheck(Biomes.JAGGED_PEAKS))
                .or(biomeConditionCheck(Biomes.ICE_SPIKES))
                .or(biomeConditionCheck(Biomes.FROZEN_OCEAN))
                .or(biomeConditionCheck(Biomes.FROZEN_RIVER))
                .or(biomeConditionCheck(Biomes.GROVE));
    }

    public static LootItemCondition.Builder isForrest() {
        return biomeConditionCheck(Biomes.FOREST)
                .or(biomeConditionCheck(Biomes.FLOWER_FOREST))
                .or(biomeConditionCheck(Biomes.BIRCH_FOREST))
                .or(biomeConditionCheck(Biomes.DARK_FOREST))
                .or(biomeConditionCheck(Biomes.OLD_GROWTH_BIRCH_FOREST))
                .or(biomeConditionCheck(Biomes.WINDSWEPT_FOREST));
    }

    public static LootItemCondition.Builder isMountain() {
        return biomeConditionCheck(Biomes.MEADOW)
                .or(biomeConditionCheck(Biomes.FROZEN_PEAKS))
                .or(biomeConditionCheck(Biomes.JAGGED_PEAKS))
                .or(biomeConditionCheck(Biomes.STONY_PEAKS))
                .or(biomeConditionCheck(Biomes.SNOWY_SLOPES))
                .or(biomeConditionCheck(Biomes.CHERRY_GROVE));
    }


    public static LootItemCondition.Builder biomeConditionCheck(ResourceKey<Biome> biome) {
        return LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(biome));
    }
}
