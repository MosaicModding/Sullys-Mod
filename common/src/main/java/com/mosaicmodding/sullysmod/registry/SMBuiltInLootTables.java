package com.mosaicmodding.sullysmod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class SMBuiltInLootTables {
    public static final ResourceKey<LootTable> GRAVEL_PETRIFIED_SAPLING_TREE = register("archaeology/gravel_petrified_sapling_tree");
    public static final ResourceKey<LootTable> GRAVEL_BIG_PETRIFIED_TREE = register("archaeology/gravel_big_petrified_tree");
    public static final ResourceKey<LootTable> OVERWORLD_ARTIFACTS = register("archaeology/overworld_artifacts");

    private static ResourceKey<LootTable> register(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, path(name));
    }

}
