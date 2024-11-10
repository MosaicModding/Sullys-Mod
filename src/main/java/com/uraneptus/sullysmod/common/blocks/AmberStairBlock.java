package com.uraneptus.sullysmod.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class AmberStairBlock extends StairBlock {
    public static final BooleanProperty IS_MELTED = AmberUtil.IS_MELTED;

    public AmberStairBlock(Supplier<BlockState> pBaseState, Properties pProperties) {
        super(pBaseState, pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(IS_MELTED, false));
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        AmberUtil.spawnAmberParticles(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        AmberUtil.fillCauldronBehavior(pState, pLevel, pPos);
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return AmberUtil.basicCollisionShapeUpdate(this, pState, pLevel, pPos, pContext);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        AmberUtil.basicEntityInsideBehavior(this, pState, pLevel, pPos, pEntity);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(IS_MELTED, FACING, HALF, SHAPE, WATERLOGGED);
    }
}
