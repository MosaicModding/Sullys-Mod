package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.core.data_maps.PickaxeStrippable;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;

public class SMDataMaps {
    public static final DataMapType<Block, PickaxeStrippable> PICKAXE_STRIPPABLES = DataMapType.builder(path("pickaxe_strippables"), Registries.BLOCK, PickaxeStrippable.CODEC).synced(PickaxeStrippable.STRIPPED_BLOCK_CODEC, false).build();

    public static void registerDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(PICKAXE_STRIPPABLES);
    }
}
