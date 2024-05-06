package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blockentities.AmberBE;
import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AmberBlock extends BaseEntityBlock {

    private static final VoxelShape MELTING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.0F, 1.0D);

    public AmberBlock(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);

        if (blockEntity instanceof AmberBE amber) {
            if (pContext instanceof EntityCollisionContext entitycollisioncontext) {
                Entity entity = entitycollisioncontext.getEntity();
                Level level = blockEntity.getLevel();
                if (!amber.hasStuckEntity()) {
                    if (entity != null) {
                        boolean shouldMeltFlag = false;
                        amber.setBlockMelted(false);

                        for (BlockPos pos : BlockPos.betweenClosed(pPos.offset(-1, -1, -1), pPos.offset(1, 1, 1))) {
                            BlockState state = pLevel.getBlockState(pos);
                            BlockEntity be = pLevel.getBlockEntity(pos);
                            if (state.is(SMBlockTags.MELTS_AMBER)) {
                                shouldMeltFlag = true;
                            }
                            if (state.getBlock() instanceof AmberBlock && be instanceof AmberBE amberBE) {
                                if (amberBE.isBlockMelted() && level != null && level.getBrightness(LightLayer.BLOCK, pPos.above()) >= 9) {
                                    shouldMeltFlag = true;
                                }
                            }
                        }
                        if (shouldMeltFlag) {
                            if (entity instanceof Player player) {
                                if (player.jumping) {
                                    amber.setBlockMelted(false);
                                    return Shapes.block();
                                }
                            }

                            amber.setBlockMelted(true);
                            amber.update();
                            return MELTING_COLLISION_SHAPE;
                        }
                    }
                }
            }
        }
        return Shapes.block();
    }

    public void onRemove(BlockState blockState, Level pLevel, BlockPos blockPos, BlockState pNewState, boolean pIsMoving) {
        if (blockState.getBlock() == SMBlocks.AMBER.get()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(blockPos);
            if (blockEntity instanceof AmberBE amberBlockEntity) {
                if (amberBlockEntity.hasStuckEntity()) {
                    CompoundTag compoundtag = amberBlockEntity.getEntityStuck();
                    AmberBE.removeIgnoredNBT(compoundtag);
                    LivingEntity livingEntity = (LivingEntity) EntityType.loadEntityRecursive(compoundtag, pLevel, entity -> entity);
                    if (livingEntity != null) {
                        livingEntity.moveTo(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
                        pLevel.addFreshEntity(livingEntity);
                    }
                }
            }
        }
        super.onRemove(blockState, pLevel, blockPos, pNewState, pIsMoving);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof AmberBE amber) {
            if (!amber.hasStuckEntity() && amber.isBlockMelted()) {
                if (!(pEntity instanceof LivingEntity) || pEntity.getFeetBlockState().is(this)) {
                    if (pEntity instanceof Player) {
                        pEntity.makeStuckInBlock(pState, new Vec3(0.8F, 0.1D, 0.8F));
                    } else if (pEntity instanceof Mob mob) {
                        mob.makeStuckInBlock(pState, new Vec3(0.0F, 0.1D, 0.0F));
                        if (mob.onGround()) {
                            amber.makeEntityStuck(mob);
                        }
                    }
                    if (pLevel.isClientSide) {
                        RandomSource randomsource = pLevel.getRandom();
                        boolean flag = pEntity.xOld != pEntity.getX() || pEntity.zOld != pEntity.getZ();
                        if (flag && randomsource.nextBoolean()) {
                            pLevel.addParticle(ParticleTypes.SOUL_FIRE_FLAME, pEntity.getX(), pPos.getY() + 1, pEntity.getZ(), Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F, 0.05F, Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F);
                        }
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (level1, pos, state1, tile) -> ((AmberBE) tile).tick();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AmberBE(pPos, pState);
    }
}
