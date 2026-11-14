package com.mosaicmodding.sullysmod.content.petrified;

import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface PickaxeStrippable {
    Supplier<Block> getStrippedBlock();
}
