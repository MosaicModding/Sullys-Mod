package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blockentities.ItemStandBE;
import com.uraneptus.sullysmod.core.other.SMItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ItemStandBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public ItemStandBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockEntity entity = level.getBlockEntity(pos);
        ItemStack itemInHand = player.getItemInHand(hand);
        if (entity instanceof ItemStandBE itemStand) {
            if (itemStand.getDisplayItem().isEmpty()) {
                if (!(itemInHand.getItem() instanceof BlockItem)) {
                    if (itemInHand.getItem() instanceof ArmorItem armorItem && armorItem.getType() != ArmorItem.Type.HELMET) {
                        return super.use(blockState, level, pos, player, hand, hitResult);
                    }
                    itemStand.setDisplayItem(itemInHand.copy());
                    SMItemUtil.nonCreativeShrinkStack(player, itemInHand);
                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            } else if (itemInHand.isEmpty()) {
                SMItemUtil.nonCreativeAddItems(player, new ItemStack(itemStand.getDisplayItem().getItem()));
                itemStand.setDisplayItem(ItemStack.EMPTY);
                return InteractionResult.sidedSuccess(level.isClientSide());
            }
        }
        return super.use(blockState, level, pos, player, hand, hitResult);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos pos, BlockState pNewState, boolean pIsMoving) {
        if (!blockState.is(pNewState.getBlock())) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof ItemStandBE itemStand && !itemStand.getDisplayItem().isEmpty()) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), itemStand.getDisplayItem());
            }
            super.onRemove(blockState, level, pos, pNewState, pIsMoving);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public VoxelShape getVoxelShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.0625, 0.4375, 0.5625, 0.625, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.875, 0.0625, 0.875), BooleanOp.OR);
        return shape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.getVoxelShape();
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.getVoxelShape();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ItemStandBE(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return super.getTicker(pLevel, pState, pBlockEntityType);
    }
}
