package com.uraneptus.sullysmod.data.server.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;

public class SMLootTableProvider extends LootTableProvider {

    public SMLootTableProvider(PackOutput packOutput) {
        super(packOutput, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(SMBlockLoot::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(SMEntityLoot::new, LootContextParamSets.ENTITY),
                new LootTableProvider.SubProviderEntry(SMArchaeologyLoot::new, LootContextParamSets.ARCHAEOLOGY)
        ));
    }

}
