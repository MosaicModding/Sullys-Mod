package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AmberBlock extends BaseEntityBlock {

    private static final VoxelShape MELTING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, (double)0.0F, 1.0D);

    public AmberBlock(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }


    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        //GET BLOCK STATES
        BlockState blockStateXP = pLevel.getBlockState(pPos.relative(Direction.Axis.X, 1));
        BlockState blockStateXN = pLevel.getBlockState(pPos.relative(Direction.Axis.X, -1));
        BlockState blockStateZP = pLevel.getBlockState(pPos.relative(Direction.Axis.Z, 1));
        BlockState blockStateZN = pLevel.getBlockState(pPos.relative(Direction.Axis.Z, -1));
        BlockState blockStateYP = pLevel.getBlockState(pPos.relative(Direction.Axis.Y, 1));
        BlockState blockStateYN = pLevel.getBlockState(pPos.relative(Direction.Axis.Y, -1));

        //GET BLOCK ENTITIES
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        BlockEntity blockEntityXP = pLevel.getBlockEntity(pPos.relative(Direction.Axis.X, 1));
        BlockEntity blockEntityXN = pLevel.getBlockEntity(pPos.relative(Direction.Axis.X, -1));
        BlockEntity blockEntityZP = pLevel.getBlockEntity(pPos.relative(Direction.Axis.X, 1));
        BlockEntity blockEntityZN = pLevel.getBlockEntity(pPos.relative(Direction.Axis.X, -1));

        if (pContext instanceof EntityCollisionContext entitycollisioncontext) {
            Entity entity = entitycollisioncontext.getEntity();
            if (blockEntity instanceof AmberBlockEntity amber) {
                Level level = blockEntity.getLevel();
                if (!amber.hasStuckEntity()) {
                    if (entity != null) {
                        if (level != null && level.getBrightness(LightLayer.BLOCK, pPos.above()) >= 9) {
                            if (entity instanceof Player player) {
                                if (player.jumping) {
                                    amber.setBlockMelted(false);
                                    return Shapes.block();
                                }
                            } else {
                                amber.setBlockMelted(true);
                                return MELTING_COLLISION_SHAPE;
                            }
                        }
                    }
                    if (level != null && level.getBrightness(LightLayer.BLOCK, pPos.above()) >= 9) {
                        if (blockStateXP.is(SMBlockTags.MELTS_AMBER) || blockStateXN.is(SMBlockTags.MELTS_AMBER) || blockStateZP.is(SMBlockTags.MELTS_AMBER) || blockStateZN.is(SMBlockTags.MELTS_AMBER) || blockStateYP.is(SMBlockTags.MELTS_AMBER) || blockStateYN.is(SMBlockTags.MELTS_AMBER)) {
                            amber.setBlockMelted(true);
                            return MELTING_COLLISION_SHAPE;
                        }
                        if (blockEntityXP instanceof AmberBlockEntity amberXP) {
                            if (amberXP.isBlockMelted()) {
                                amber.setBlockMelted(true);
                                return MELTING_COLLISION_SHAPE;
                            }
                        }
                        if (blockEntityXN instanceof AmberBlockEntity amberXN) {
                            if (amberXN.isBlockMelted()) {
                                amber.setBlockMelted(true);
                                return MELTING_COLLISION_SHAPE;
                            }
                        }
                        if (blockEntityZP instanceof AmberBlockEntity amberZP) {
                            if (amberZP.isBlockMelted()) {
                                amber.setBlockMelted(true);
                                return MELTING_COLLISION_SHAPE;
                            }
                        }
                        if (blockEntityZN instanceof AmberBlockEntity amberZN) {
                            if (amberZN.isBlockMelted()) {
                                amber.setBlockMelted(true);
                                return MELTING_COLLISION_SHAPE;
                            }
                        }

                    }
                } else {
                    amber.setBlockMelted(false);
                    return Shapes.block();
                }
            }

        }
        if (blockEntity != null) {
            ((AmberBlockEntity) blockEntity).setBlockMelted(false);
        }
        return Shapes.block();
    }


    public void onRemove(BlockState blockState, Level pLevel, BlockPos blockPos, BlockState pNewState, boolean pIsMoving) {
        if (blockState.getBlock() == SMBlocks.AMBER.get()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(blockPos);
            if (blockEntity instanceof AmberBlockEntity amberBlockEntity) {
                if (amberBlockEntity.hasStuckEntity()) {
                    CompoundTag compoundtag = amberBlockEntity.getEntityStuck();
                    AmberBlockEntity.removeIgnoredNBT(compoundtag);
                    LivingEntity livingEntity = (LivingEntity) EntityType.loadEntityRecursive(compoundtag, (Level) pLevel, entity -> entity);
                    livingEntity.moveTo(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
                    pLevel.addFreshEntity(livingEntity);
                }
            }
        }
        super.onRemove(blockState, pLevel, blockPos, pNewState, pIsMoving);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return SMBlockEntityTypes.AMBER.get().create(pPos, pState);
    }




    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof AmberBlockEntity amber) {
            if (!amber.hasStuckEntity()) {
                if (!(pEntity instanceof LivingEntity) || pEntity.getFeetBlockState().is(this)) {
                    if (pEntity instanceof Player) {
                        pEntity.makeStuckInBlock(pState, new Vec3((double) 0.8F, 0.1D, (double) 0.8F));
                    } else if (pEntity instanceof Mob mob) {
                        mob.makeStuckInBlock(pState, new Vec3((double) 0.0F, 0.1D, (double) 0.0F));
                        if (mob.getBlockStateOn() != SMBlocks.AMBER.get().defaultBlockState()) {
                            amber.makeEntityStuck(mob);
                        }
                    }
                    if (pLevel.isClientSide) {
                        RandomSource randomsource = pLevel.getRandom();
                        boolean flag = pEntity.xOld != pEntity.getX() || pEntity.zOld != pEntity.getZ();
                        if (flag && randomsource.nextBoolean()) {
                            pLevel.addParticle(ParticleTypes.SOUL_FIRE_FLAME, pEntity.getX(), (double) (pPos.getY() + 1), pEntity.getZ(), (double) (Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F), (double) 0.05F, (double) (Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F));
                        }
                    }
                }
            }
        }
    }
}
