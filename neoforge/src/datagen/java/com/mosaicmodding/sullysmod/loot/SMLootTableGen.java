package com.mosaicmodding.sullysmod.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SMLootTableGen extends LootTableProvider {

    public SMLootTableGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(SMBlockLoot::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(SMEntityLoot::new, LootContextParamSets.ENTITY)
        ), registries);
    }
}
