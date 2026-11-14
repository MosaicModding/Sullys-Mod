package com.mosaicmodding.sullysmod.core.blocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;

/**
 * A StairBlock class that doesn't require a base block object. This simplifies registration.
 */
public class SMStairBlock extends StairBlock {

    public SMStairBlock(Properties properties) {
        super(Blocks.AIR.defaultBlockState(), properties);
    }

    /**
     * This normally derives the explosion resistance from the base block.
     * In this case we use the resistance value from the block properties
     */
    @Override
    public float getExplosionResistance() {
        return this.explosionResistance;
    }
}
