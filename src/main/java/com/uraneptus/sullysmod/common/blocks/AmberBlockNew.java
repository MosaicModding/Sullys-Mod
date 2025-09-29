package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blockentities.AmberBENew;
import com.uraneptus.sullysmod.common.blocks.utilities.AmberUtil;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class AmberBlockNew extends Block {
    public static final BooleanProperty IS_MELTED = AmberUtil.IS_MELTED;

    public AmberBlockNew(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(IS_MELTED, false));
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pState.getValue(IS_MELTED)) {
            if (!(pEntity instanceof LivingEntity) || pEntity.getFeetBlockState().is(this)) {
                if (pEntity instanceof Player) {
                    pEntity.makeStuckInBlock(pState, new Vec3(0.8F, 0.1D, 0.8F));
                }
                if (pEntity instanceof ItemEntity itemEntity) {
                    itemEntity.makeStuckInBlock(pState, new Vec3(0.8F, 0.1D, 0.8F));

                    if (closeToCenter(pPos, pEntity)) {
                        makeStuckInAmber(pLevel, pPos, pEntity);
                    }
                } else if (pEntity instanceof Mob mob) {
                    if (mob.isVehicle()) {
                        mob.makeStuckInBlock(pState, new Vec3(0.5F, 0.1D, 0.5F));
                    } else {
                        mob.makeStuckInBlock(pState, new Vec3(0.8F, 0.1D, 0.8F));
                        if (closeToCenter(pPos, pEntity)) {
                            // Get full bounding box dimensions
                            double height = pEntity.getBoundingBox().getYsize();
                            double width = pEntity.getBoundingBox().getXsize();
                            double depth = pEntity.getBoundingBox().getZsize();

                            // Calculate blocks needed in each dimension
                            int blocksNeededY = (int) Math.ceil(height);
                            int blocksNeededX = (int) Math.ceil(width);
                            int blocksNeededZ = (int) Math.ceil(depth);

                            // If entity fits in a single block
                            if (blocksNeededY == 1 && blocksNeededX == 1 && blocksNeededZ == 1) {
                                makeStuckInAmber(pLevel, pPos, pEntity);
                            } else {
                                // Calculate all positions needed for the full bounding box
                                List<BlockPos> allPositions = new ArrayList<>();
                                boolean canStoreEntity = true;

                                // Calculate offset for centering the entity
                                int xOffset = (blocksNeededX - 1) / 2;
                                int zOffset = (blocksNeededZ - 1) / 2;

                                // Check all required positions
                                for (int y = 0; y < blocksNeededY; y++) {
                                    for (int x = -xOffset; x <= (blocksNeededX - 1 - xOffset); x++) {
                                        for (int z = -zOffset; z <= (blocksNeededZ - 1 - zOffset); z++) {
                                            BlockPos checkPos = pPos.offset(x, y, z);

                                            // Skip the base position (current block)
                                            if (checkPos.equals(pPos)) {
                                                continue;
                                            }

                                            BlockState checkState = pLevel.getBlockState(checkPos);
                                            if (checkState.is(SMBlocks.NEW_AMBER.get()) && checkState.getValue(IS_MELTED)) {
                                                allPositions.add(checkPos);
                                            } else {
                                                canStoreEntity = false;
                                                break;
                                            }
                                        }
                                        if (!canStoreEntity) break;
                                    }
                                    if (!canStoreEntity) break;
                                }

                                if (canStoreEntity) {
                                    makeStuckInMultipleAmber(pLevel, pEntity, pPos, allPositions);
                                } else {
                                    // Apply normal stuck behavior if can't store
                                    mob.makeStuckInBlock(pState, new Vec3(0.8F, 0.1D, 0.8F));
                                }
                            }
                        }
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

    public void makeStuckInAmber(Level level, BlockPos pos, Entity entity) {
        level.setBlock(pos, SMBlocks.NEW_AMBER_SOLID.get().defaultBlockState(), Block.UPDATE_ALL);
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof AmberBENew amberBE) {
            amberBE.storeEntity(entity);
        }
    }

    public void makeStuckInMultipleAmber(Level level, Entity entity, BlockPos parentBlock, List<BlockPos> childBlocks) {
        level.setBlock(parentBlock, SMBlocks.NEW_AMBER_SOLID.get().defaultBlockState(), Block.UPDATE_ALL);
        BlockEntity be = level.getBlockEntity(parentBlock);
        if (be instanceof AmberBENew amberBE) {
            amberBE.storeAsParent(entity, childBlocks);
        }

        for (BlockPos pos : childBlocks) {
            level.setBlock(pos, SMBlocks.NEW_AMBER_SOLID.get().defaultBlockState(), Block.UPDATE_ALL);
            BlockEntity childBE = level.getBlockEntity(pos);
            if (childBE instanceof AmberBENew amberBE) {
                amberBE.storeAsChild(parentBlock);
            }
        }
    }

    public boolean closeToCenter(BlockPos pPos, Entity pEntity) {
        Vec3 extendedCenter = pPos.getCenter().subtract(0.07, 0.07, 0.07);
        double vec = pEntity.position().add(0, 0.5, 0).distanceTo(extendedCenter);
        return vec < 0.5;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pContext instanceof EntityCollisionContext entitycollisioncontext) {
            Entity entity = entitycollisioncontext.getEntity();
            if (entity == null || entity instanceof Projectile) return Shapes.block();
            Level level = entity.level();
            boolean shouldMeltFlag = false;
            level.setBlock(pPos, pState.setValue(IS_MELTED, false), Block.UPDATE_ALL);

            for (BlockPos pos : BlockPos.betweenClosed(pPos.offset(-1, -1, -1), pPos.offset(1, 1, 1))) {
                BlockState state = pLevel.getBlockState(pos);
                if (AmberUtil.AMBER_MELTING_BLOCKS.test(state)) {
                    shouldMeltFlag = true;
                }
                if (state.hasProperty(IS_MELTED) && state.getValue(IS_MELTED) && level.getBrightness(LightLayer.BLOCK, pPos.above()) >= 9) {
                    shouldMeltFlag = true;
                }
            }
            if (shouldMeltFlag) {
                level.setBlock(pPos, pState.setValue(IS_MELTED, true), Block.UPDATE_ALL);
                return AmberUtil.MELTING_COLLISION_SHAPE;
            }

        }
        return Shapes.block();
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.is(this) || pAdjacentBlockState.is(SMBlocks.NEW_AMBER_SOLID.get()) || super.skipRendering(pState, pAdjacentBlockState, pSide);
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
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        AmberUtil.spawnAmberParticles(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        AmberUtil.fillCauldronBehavior(pState, pLevel, pPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(IS_MELTED);
    }
}
