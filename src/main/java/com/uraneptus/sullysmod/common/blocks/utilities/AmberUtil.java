package com.uraneptus.sullysmod.common.blocks.utilities;

import com.uraneptus.sullysmod.common.blocks.AmberLayeredCauldronBlock;
import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.*;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class AmberUtil {
    public static final BooleanProperty IS_MELTED = BooleanProperty.create("is_melted");
    public static final VoxelShape MELTING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.0F, 1.0D);
    public static final Predicate<BlockState> AMBER_MELTING_BLOCKS = (blockstate) -> blockstate.is(SMBlockTags.MELTS_AMBER) && blockstate.getLightEmission() >= 3;

    public static VoxelShape basicCollisionShapeUpdate(Block instance, BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pContext instanceof EntityCollisionContext entitycollisioncontext) {
            Entity entity = entitycollisioncontext.getEntity();
            if (entity instanceof Projectile) return instance.getShape(pState, pLevel, pPos, pContext);
            if (entity != null) {
                Level level = entity.level();
                boolean shouldMeltFlag = false;
                level.setBlock(pPos, pState.setValue(IS_MELTED, false), Block.UPDATE_ALL);

                for (BlockPos pos : BlockPos.betweenClosed(pPos.offset(-1, -1, -1), pPos.offset(1, 1, 1))) {
                    BlockState state = pLevel.getBlockState(pos);

                    if (AmberUtil.AMBER_MELTING_BLOCKS.test(state)) {
                        shouldMeltFlag = true;
                    }
                    if (pState.getValue(IS_MELTED) && level.getBrightness(LightLayer.BLOCK, pPos.above()) >= 9) {
                        shouldMeltFlag = true;
                    }
                }
                if (shouldMeltFlag) {
                    level.setBlock(pPos, pState.setValue(IS_MELTED, true), Block.UPDATE_ALL);
                    return MELTING_COLLISION_SHAPE;
                }
            }
        }
        return instance.getShape(pState, pLevel, pPos, pContext);
    }

    public static void basicEntityInsideBehavior(Block instance, BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pState.getValue(IS_MELTED)) {
            if (!(pEntity instanceof LivingEntity) || pEntity.getFeetBlockState().is(instance)) {
                if (pEntity instanceof Player) {
                    pEntity.makeStuckInBlock(pState, new Vec3(0.8F, 0.1D, 0.8F));
                } else if (pEntity instanceof Mob mob) {
                    if (mob.isVehicle()) {
                        mob.makeStuckInBlock(pState, new Vec3(0.5F, 0.1D, 0.5F));
                    } else {
                        mob.makeStuckInBlock(pState, new Vec3(0.8F, 0.1D, 0.8F));
                    }
                }
                if (pLevel.isClientSide) {
                    RandomSource randomsource = pLevel.getRandom();
                    boolean flag = pEntity.xOld != pEntity.getX() || pEntity.zOld != pEntity.getZ();
                    if (flag && randomsource.nextBoolean()) {
                        pLevel.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, SMBlocks.AMBER.get().defaultBlockState()), pEntity.getX(), pPos.getY() + 1, pEntity.getZ(), Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F, 0.05F, Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F);
                    }
                }
            }
        }
    }

    public static void fillCauldronBehavior(BlockState pState, ServerLevel pLevel, BlockPos pPos) {
        if (pState.getValue(IS_MELTED)) {
            BlockPos cauldronPos = findFillableCauldronBelow(pLevel, pPos);
            if (cauldronPos != null) {
                BlockState cauldronState = pLevel.getBlockState(cauldronPos);
                if (cauldronState.is(Blocks.CAULDRON)) {
                    BlockState blockstate = SMBlocks.AMBER_CAULDRON.get().defaultBlockState();
                    pLevel.setBlockAndUpdate(cauldronPos, blockstate);
                    pLevel.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(blockstate));
                    pLevel.levelEvent(1047, cauldronPos, 0);
                }
                if (cauldronState.getBlock() instanceof AmberLayeredCauldronBlock amberCauldron) {
                    if (!amberCauldron.isFull(cauldronState)) {
                        BlockState blockstate = cauldronState.setValue(AmberLayeredCauldronBlock.LEVEL, cauldronState.getValue(AmberLayeredCauldronBlock.LEVEL) + 1);
                        pLevel.setBlockAndUpdate(cauldronPos, blockstate);
                        pLevel.gameEvent(GameEvent.BLOCK_CHANGE, cauldronPos, GameEvent.Context.of(blockstate));
                        pLevel.levelEvent(1047, cauldronPos, 0);
                    }
                }
            }
        }
    }

    @Nullable
    private static BlockPos findFillableCauldronBelow(Level pLevel, BlockPos pPos) {
        Predicate<BlockState> predicate = state -> state.is(Blocks.CAULDRON) || state.is(SMBlocks.AMBER_CAULDRON.get());
        BiPredicate<BlockPos, BlockState> bipredicate = (p_202034_, p_202035_) -> canDripThrough(pLevel, p_202034_, p_202035_);
        return findBlockVertical(pLevel, pPos, Direction.DOWN.getAxisDirection(), bipredicate, predicate, 11).orElse(null);
    }

    private static boolean canDripThrough(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        if (pState.isAir()) {
            return true;
        } else if (pState.isSolidRender(pLevel, pPos)) {
            return false;
        } else if (!pState.getFluidState().isEmpty()) {
            return false;
        } else {
            VoxelShape voxelshape = pState.getCollisionShape(pLevel, pPos);
            return !Shapes.joinIsNotEmpty(Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), voxelshape, BooleanOp.AND);
        }
    }

    private static Optional<BlockPos> findBlockVertical(LevelAccessor pLevel, BlockPos pPos, Direction.AxisDirection pAxis, BiPredicate<BlockPos, BlockState> pPositionalStatePredicate, Predicate<BlockState> pStatePredicate, int pMaxIterations) {
        Direction direction = Direction.get(pAxis, Direction.Axis.Y);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();

        for(int i = 1; i < pMaxIterations; ++i) {
            blockpos$mutableblockpos.move(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos$mutableblockpos);
            if (pStatePredicate.test(blockstate)) {
                return Optional.of(blockpos$mutableblockpos.immutable());
            }

            if (pLevel.isOutsideBuildHeight(blockpos$mutableblockpos.getY()) || !pPositionalStatePredicate.test(blockpos$mutableblockpos, blockstate)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    //TODO prevent particles if blocks are on the sides
    public static void spawnAmberParticles(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(IS_MELTED)) {
            for(int i = 0; i < pRandom.nextInt(1) + 1; ++i) {
                if (pRandom.nextInt(3) == 0) {
                    spawnParticlesOnBlockFaces(pLevel, pPos, SMParticleTypes.AMBER_DRIPPING.get(), UniformInt.of(0, 1));
                }
            }
        }
    }

    private static void spawnParticlesOnBlockFaces(Level pLevel, BlockPos pPos, ParticleOptions pParticle, IntProvider pCount) {
        for(Direction direction : Direction.values()) {
            if (direction != Direction.UP) {
                if (pLevel.getBlockState(pPos.relative(direction)).isAir()) {
                    ParticleUtils.spawnParticlesOnBlockFace(pLevel, pPos, pParticle, pCount, direction, () -> new Vec3(Mth.nextDouble(pLevel.random, -0.5D, 0.5D), Mth.nextDouble(pLevel.random, -0.5D, 0.5D), Mth.nextDouble(pLevel.random, -0.5D, 0.5D)), 0.55D);
                }
            }
        }
    }
}
