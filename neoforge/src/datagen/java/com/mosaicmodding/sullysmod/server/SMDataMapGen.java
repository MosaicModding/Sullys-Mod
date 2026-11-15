package com.mosaicmodding.sullysmod.server;

import com.mosaicmodding.sullysmod.core.data_maps.PickaxeStrippable;
import com.mosaicmodding.sullysmod.registry.SMBlocks;
import com.mosaicmodding.sullysmod.registry.SMDataMaps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;

import java.util.concurrent.CompletableFuture;

public class SMDataMapGen extends DataMapProvider {

    public SMDataMapGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    protected void gather(HolderLookup.Provider provider) {
        SMBlocks.addStrippableBlocks(((input, stripped, usingPickaxe) -> {
            if (usingPickaxe) {
                final var pickaxe_strippables = builder(SMDataMaps.PICKAXE_STRIPPABLES);
                pickaxe_strippables.add(input.getId(), new PickaxeStrippable(stripped.get()), false);
            } else {
                final var strippables = builder(NeoForgeDataMaps.STRIPPABLES);
                strippables.add(input.getId(), new Strippable(stripped.get()), false);
            }
        }));
    }
}
