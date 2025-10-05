package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blockentities.AmberBE;
import com.uraneptus.sullysmod.common.blockentities.AmberBENew;
import com.uraneptus.sullysmod.common.caps.SMEntityCap;
import com.uraneptus.sullysmod.common.networking.MsgEntityAmberStuck;
import com.uraneptus.sullysmod.common.networking.SMPacketHandler;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AmberBlockNewSolid extends Block implements EntityBlock {
    private int extraDropAmount = 0;

    public AmberBlockNewSolid(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @javax.annotation.Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        if (extraDropAmount > 0) {
            if (pLevel instanceof ServerLevel serverLevel) {
                Block.getDrops(pState, serverLevel, pPos, pBlockEntity, pPlayer, pTool).forEach(itemStack -> {
                    Block.popResource(pLevel, pPos, itemStack.copyWithCount(extraDropAmount));
                });
                extraDropAmount = 0;
            }
        }
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pBlockEntity, pTool);
    }

    @Override
    public void onRemove(BlockState blockState, Level pLevel, BlockPos blockPos, BlockState pNewState, boolean pIsMoving) {
        BlockEntity blockEntity = pLevel.getBlockEntity(blockPos);
        if (!(blockEntity instanceof AmberBENew amberBe)) return;
        this.extraDropAmount = 0;
        if (amberBe.isValidChild()) {
            BlockPos parentPos = amberBe.getParentBlock();
            BlockState parentState = pLevel.getBlockState(parentPos);
            parentState.getBlock().onRemove(parentState, pLevel, parentPos, pNewState, pIsMoving);
        } else if (amberBe.isValidParent()) {
            if (!amberBe.getChildBlocks().isEmpty()) {
                for (BlockPos pos : amberBe.getChildBlocks()) {
                    pLevel.removeBlock(pos, false);
                    pLevel.removeBlockEntity(pos);
                }
            }

            CompoundTag compoundtag = amberBe.getEntityStuck();
            AmberBENew.removeIgnoredNBT(compoundtag);
            Entity entity = EntityType.loadEntityRecursive(compoundtag, pLevel, entityLoaded -> entityLoaded);
            if (entity != null) {
                SMEntityCap.getCapOptional(entity).ifPresent(cap -> {
                    cap.stuckInAmber = false;
                });
                SMPacketHandler.sendMsg(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new MsgEntityAmberStuck(entity, false));
                if (entity instanceof ItemEntity) {
                    entity.setDeltaMovement(0, 0, 0);
                }
                entity.moveTo(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
                pLevel.addFreshEntity(entity);
            }
            this.extraDropAmount = amberBe.getChildBlocks().size();
            pLevel.removeBlock(blockPos, pIsMoving);
        }
        super.onRemove(blockState, pLevel, blockPos, pNewState, pIsMoving);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (level1, pos, state1, tile) -> ((AmberBENew) tile).tick();
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.is(this) || pAdjacentBlockState.is(SMBlocks.NEW_AMBER.get()) || super.skipRendering(pState, pAdjacentBlockState, pSide);
    }

    @Override
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return true;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AmberBENew(pPos, pState);
    }
}
