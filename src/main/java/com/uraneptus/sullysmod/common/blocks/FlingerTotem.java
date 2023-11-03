package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class FlingerTotem extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty HONEY_AMOUNT = IntegerProperty.create("honey_amount", 0, 4);

    public FlingerTotem(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HONEY_AMOUNT, 0).setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);
        if (itemInHand.is(Items.HONEYCOMB) && pState.getValue(HONEY_AMOUNT) < 4) {
            increaseHoneyLevel(pPlayer, pLevel, pPos, pState);
            if (!pPlayer.getAbilities().instabuild) {
                itemInHand.shrink(1);
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (itemInHand.is(Items.SHEARS) && pState.getValue(HONEY_AMOUNT) != 0) {
            decreaseHoneyLevel(pPlayer, pLevel, pPos, pState);
            if (!pPlayer.isCreative()) {
                itemInHand.hurtAndBreak(1, pPlayer, (player1) -> player1.broadcastBreakEvent(pHand));
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
        return InteractionResult.PASS;
    }

    //TODO use different soundevents
    public static void increaseHoneyLevel(@Nullable Entity pEntity, Level pLevel, BlockPos pPos, BlockState pState) {
        BlockState blockstate = pState.setValue(HONEY_AMOUNT, pState.getValue(HONEY_AMOUNT) + 1);
        pLevel.setBlock(pPos, blockstate, 3);
        pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pEntity, blockstate));
        pLevel.playSound(null, (double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public static void decreaseHoneyLevel(@Nullable Entity pEntity, Level pLevel, BlockPos pPos, BlockState pState) {
        BlockState blockstate = pState.setValue(HONEY_AMOUNT, pState.getValue(HONEY_AMOUNT) - 1);
        pLevel.setBlock(pPos, blockstate, 3);
        pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pEntity, blockstate));
        pLevel.playSound(null, (double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, SoundEvents.BEEHIVE_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HONEY_AMOUNT).add(FACING);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return SMBlockEntityTypes.FLINGER_TOTEM.get().create(pPos, pState);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide() ? null : createTickerHelper(pBlockEntityType, SMBlockEntityTypes.FLINGER_TOTEM.get(), FlingerTotemBlockEntity::serverTick);
    }
}