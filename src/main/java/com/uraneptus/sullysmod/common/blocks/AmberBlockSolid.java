package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blockentities.AmberBE;
import com.uraneptus.sullysmod.common.caps.SMEntityCap;
import com.uraneptus.sullysmod.common.networking.MsgEntityAmberStuck;
import com.uraneptus.sullysmod.common.networking.SMPacketHandler;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//This is the amber block holding an entity
public class AmberBlockSolid extends Block implements EntityBlock {

    public AmberBlockSolid(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void playerDestroy(Level level, Player pPlayer, BlockPos blockPos, BlockState pState, @javax.annotation.Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        if (level instanceof ServerLevel serverLevel) {
            if (!(pBlockEntity instanceof AmberBE amberBe)) return;
            var childrenOrSiblings = amberBe.getChildBlocks();
            int amount = childrenOrSiblings.size();

            if (amberBe.isValidChild()) {
                amount++;
            }

            int finalAmount = amount;
            Block.getDrops(pState, serverLevel, blockPos, pBlockEntity, pPlayer, pTool).forEach(itemStack -> {
                Block.popResource(level, blockPos, itemStack.copyWithCount(finalAmount));
            });

        }
        super.playerDestroy(level, pPlayer, blockPos, pState, pBlockEntity, pTool);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState pNewState, boolean pIsMoving) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (!(blockEntity instanceof AmberBE amberBe)) return;

        if (amberBe.isValidParent()) {
            removeAsParentBlock(amberBe, level, blockPos);
        }

        if (amberBe.isValidChild()) {
            removeAsChildBlock(amberBe, level);
        }
        super.onRemove(blockState, level, blockPos, pNewState, pIsMoving);
    }

    /**
     * When the parent block is broken, we take the first child block and remove it, which causes removeAsChildBlock to run. <br>
     * Thus the parent removes 1 child, this child removes all its siblings, then parent spawns entity
     */
    private void removeAsParentBlock(AmberBE amberBe, Level pLevel, BlockPos parentPos) {
        List<BlockPos> childBlocks = amberBe.getChildBlocks();
        if (!childBlocks.isEmpty()) {
            for (BlockPos childPos : childBlocks) {
                if (pLevel.getBlockState(childPos).isAir()) continue;
                System.out.println("Remove child as parent is broken");
                pLevel.removeBlock(childPos, false);
                pLevel.removeBlockEntity(childPos);
            }
        }
        CompoundTag compoundtag = amberBe.getEntityStuck();
        AmberBE.removeIgnoredNBT(compoundtag);
        spawnStuckEntity(pLevel, compoundtag, parentPos);
    }

    /**
     *
     *
     */
    private void removeAsChildBlock(AmberBE childBe, Level pLevel) {
        List<BlockPos> siblings = childBe.getChildBlocks();
        if (!siblings.isEmpty()) {
            for (BlockPos siblingPos : siblings) {
                System.out.println("Remove siblings as child is broken");
                pLevel.removeBlockEntity(siblingPos); //Yes, this should be called first in this case
                pLevel.removeBlock(siblingPos, false);
            }
        }

        BlockPos parentPos = childBe.getParentBlock();
        AmberBE parentBE = (AmberBE)pLevel.getBlockEntity(parentPos);
        if (!parentBE.isValidParent()) return;

        if (!pLevel.getBlockState(parentPos).isAir()) {
            System.out.println("Remove parent as child is broken");
            pLevel.removeBlockEntity(parentPos);
            pLevel.removeBlock(parentPos, false);

            CompoundTag compoundtag = parentBE.getEntityStuck();
            AmberBE.removeIgnoredNBT(compoundtag);
            spawnStuckEntity(pLevel, compoundtag, parentPos);
        }
    }

    private static void spawnStuckEntity(Level pLevel, CompoundTag compoundtag, BlockPos parentPos) {
        Entity entity = EntityType.loadEntityRecursive(compoundtag, pLevel, entityLoaded -> entityLoaded);
        if (entity != null) {
            SMEntityCap.getCapOptional(entity).ifPresent(cap -> {
                cap.stuckInAmber = false;
            });
            SMPacketHandler.sendMsg(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new MsgEntityAmberStuck(entity, false));
            if (entity instanceof ItemEntity) {
                entity.setDeltaMovement(0, 0, 0);
            }
            entity.moveTo(parentPos.getX() + 0.5, parentPos.getY(), parentPos.getZ() + 0.5);
            pLevel.addFreshEntity(entity);
        }
    }



    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (level1, pos, state1, tile) -> ((AmberBE) tile).tick();
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.is(this) || pAdjacentBlockState.is(SMBlocks.AMBER.get()) || super.skipRendering(pState, pAdjacentBlockState, pSide);
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
        return new AmberBE(pPos, pState);
    }
}
