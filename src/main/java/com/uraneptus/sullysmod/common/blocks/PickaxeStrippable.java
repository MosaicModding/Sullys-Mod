package com.uraneptus.sullysmod.common.blocks;

import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface PickaxeStrippable {
    Supplier<Block> getStrippedBlock();
}
