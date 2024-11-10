package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blocks.utilities.AmberUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AmberRotatedPillarBlock extends RotatedPillarBlock {
    public static final BooleanProperty IS_MELTED = AmberUtil.IS_MELTED;

    public AmberRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(IS_MELTED, false).setValue(AXIS, Direction.Axis.Y));
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
        return AmberUtil.basicCollisionShapeUpdate(this, pState, pLevel, pPos, pContext);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        AmberUtil.basicEntityInsideBehavior(this, pState, pLevel, pPos, pEntity);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(IS_MELTED, AXIS);
    }
}
