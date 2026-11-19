package com.mosaicmodding.sullysmod.core.events;

import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.minecraft.world.level.block.Block;

@FunctionalInterface
public interface StrippableBlockRegistryEvent {
    void register(RegSupplier<Block> input, RegSupplier<Block> stripped, boolean usingPickaxe);
}
