package com.mosaicmodding.sullysmod.content.artifacts;

import com.mosaicmodding.sullysmod.core.blocks.SMDirectionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class ArtifactDirectionalBlockWithShape extends SMDirectionalBlock {
    private final Map<Direction, VoxelShape> shapeMap;

    public ArtifactDirectionalBlockWithShape(Map<Direction, VoxelShape> shapeMap,  Properties properties) {
        super(properties);
        this.shapeMap = shapeMap;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (shapeMap.isEmpty()) return Shapes.block();
        var direction = pState.getValue(FACING);
        var south = shapeMap.get(Direction.SOUTH);
        if (direction.equals(Direction.SOUTH)) {
            return south;
        } else {
            return shapeMap.getOrDefault(direction, south);
        }
    }
}
