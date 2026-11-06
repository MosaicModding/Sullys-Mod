package com.mosaicmodding.sullysmod.mixin;

import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(WallBlock.class)
public interface WallBlockAccessor {
    @Accessor("shapeByIndex")
    Map<BlockState, VoxelShape> sullysmod$getShapeByIndex();

    @Mutable
    @Accessor("shapeByIndex")
    void sullysmod$setShapeByIndex(Map<BlockState, VoxelShape> shapeByIndex);

    @Accessor("collisionShapeByIndex")
    Map<BlockState, VoxelShape> sullysmod$getCollisionShapeByIndex();

    @Mutable
    @Accessor("collisionShapeByIndex")
    void sullysmod$setCollisionShapeByIndex(Map<BlockState, VoxelShape> collisionShapeByIndex);
}
