package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blocks.utilities.SMDirectionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StoneIdolBlock extends SMDirectionalBlock {

    public StoneIdolBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        var direction = pState.getValue(FACING);
        return switch (direction) {
            case SOUTH -> Shapes.box(0.25, 0, 0.25, 0.75, 0.5625, 0.69);
            case WEST -> Shapes.box(0.3125, 0, 0.25, 0.75, 0.5625, 0.75);
            case EAST -> Shapes.box(0.25, 0, 0.25, 0.69, 0.5625, 0.75);
            default -> Shapes.box(0.25, 0, 0.3125, 0.75, 0.5625, 0.75);
        };
    }
}
