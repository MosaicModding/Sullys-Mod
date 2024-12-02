package com.uraneptus.sullysmod.common.blocks;

import com.google.common.collect.ImmutableMap;
import com.uraneptus.sullysmod.common.blocks.utilities.AmberUtil;
import com.uraneptus.sullysmod.common.blocks.utilities.SMWallBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class AmberWallBlock extends SMWallBlock {
    public static final BooleanProperty IS_MELTED = AmberUtil.IS_MELTED;

    public AmberWallBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(IS_MELTED, false));

        Map<BlockState, VoxelShape> shapeMap = this.shapeByIndex;
        this.shapeByIndex = fixShapeMap(shapeMap);
        Map<BlockState, VoxelShape> collisionMap = this.collisionShapeByIndex;
        this.collisionShapeByIndex = fixShapeMap(collisionMap);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        AmberUtil.spawnAmberParticles(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        AmberUtil.fillCauldronBehavior(pState, pLevel, pPos);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return AmberUtil.basicCollisionShapeUpdate(super.getCollisionShape(pState, pLevel, pPos, pContext), pState, pLevel, pPos, pContext);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        AmberUtil.basicEntityInsideBehavior(this, pState, pLevel, pPos, pEntity);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(IS_MELTED);
    }

    private static Map<BlockState, VoxelShape> fixShapeMap(Map<BlockState, VoxelShape> map) {
        ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();
        builder.putAll(map);
        for (BlockState state : map.keySet()) {
            builder.put(state.cycle(IS_MELTED), map.get(state));
        }
        return builder.build();
    }
}
