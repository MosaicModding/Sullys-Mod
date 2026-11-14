package com.mosaicmodding.sullysmod.content.petrified;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class PetrifiedSapling extends SaplingBlock {

    public PetrifiedSapling(Properties properties) {
        super(new TreeGrower("petrified", Optional.empty(), Optional.empty(), Optional.empty()), properties); //TODO
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.GRAVEL);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.below()).is(Blocks.GRAVEL);
    }
}
