package com.mosaicmodding.sullysmod.content.artifacts;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ArtifactBlockWithShape extends Block {
    private final VoxelShape shape;

    public ArtifactBlockWithShape(VoxelShape shape, Properties properties) {
        super(properties);
        this.shape = shape;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return shape;
    }
}
