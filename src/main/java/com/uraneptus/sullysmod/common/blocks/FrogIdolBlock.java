package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blocks.utilities.SMDirectionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FrogIdolBlock extends SMDirectionalBlock {

    static VoxelShape north() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.375, 0.3125, 0.75, 0.4375, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0.375, 0.3125, 0.4375, 0.4375, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.375, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.375, 0.1875, 0.4375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0, 0.1875, 0.8125, 0.1875, 0.4375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.5625, 0.4375, 0.25, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0, 0.5625, 0.8125, 0.25, 0.875), BooleanOp.OR);

        return shape;
    }

    static VoxelShape south() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0.375, 0.5, 0.4375, 0.4375, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.375, 0.5, 0.75, 0.4375, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.1875, 0.75, 0.375, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0, 0.5625, 0.8125, 0.1875, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.5625, 0.375, 0.1875, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0, 0.125, 0.8125, 0.25, 0.4375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.125, 0.4375, 0.25, 0.4375), BooleanOp.OR);

        return shape;
    }

    static VoxelShape east() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.5, 0.375, 0.5625, 0.6875, 0.4375, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.375, 0.25, 0.6875, 0.4375, 0.4375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.25, 0.75, 0.375, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0, 0.1875, 0.8125, 0.1875, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0, 0.625, 0.8125, 0.1875, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.1875, 0.4375, 0.25, 0.4375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.5625, 0.4375, 0.25, 0.8125), BooleanOp.OR);

        return shape;
    }

    static VoxelShape west() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.375, 0.25, 0.5, 0.4375, 0.4375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.375, 0.5625, 0.5, 0.4375, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.8125, 0.375, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.625, 0.4375, 0.1875, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.4375, 0.1875, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0, 0.5625, 0.875, 0.25, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0, 0.1875, 0.875, 0.25, 0.4375), BooleanOp.OR);

        return shape;
    }

    public FrogIdolBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        var direction = pState.getValue(FACING);
        return switch (direction) {
            case SOUTH -> south();
            case WEST -> west();
            case EAST -> east();
            default -> north();
        };
    }
}
