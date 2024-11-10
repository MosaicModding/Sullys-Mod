package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blocks.utilities.PickaxeStrippable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;

import java.util.function.Supplier;

public class PetrifiedLog extends RotatedPillarBlock implements PickaxeStrippable {
    private final Supplier<Block> strippedBlock;

    public PetrifiedLog(Supplier<Block> strippedBlock, Properties pProperties) {
        super(pProperties);
        this.strippedBlock = strippedBlock;
    }

    public Supplier<Block> getStrippedBlock() {
        return strippedBlock;
    }
}
