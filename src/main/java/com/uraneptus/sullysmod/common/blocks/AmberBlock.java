package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blockentities.AmberBE;
import com.uraneptus.sullysmod.common.blocks.utilities.AmberUtil;
import com.uraneptus.sullysmod.common.caps.SMEntityCap;
import com.uraneptus.sullysmod.common.networking.MsgEntityAmberStuck;
import com.uraneptus.sullysmod.common.networking.SMPacketHandler;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

public class AmberBlock extends Block implements EntityBlock {
    public static final BooleanProperty IS_MELTED = AmberUtil.IS_MELTED;

    public AmberBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(IS_MELTED, false));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.is(this) || super.skipRendering(pState, pAdjacentBlockState, pSide);
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
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);

        if (blockEntity instanceof AmberBE amber) {
            if (pContext instanceof EntityCollisionContext entitycollisioncontext) {
                Entity entity = entitycollisioncontext.getEntity();
                if (entity instanceof Projectile) return Shapes.block();
                Level level = blockEntity.getLevel();
                if (level == null) return Shapes.block();
                if (!amber.hasStuckEntity()) {
                    if (entity != null) {
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
                        for (BlockPos pos : BlockPos.betweenClosed(pPos.offset(0, -1, 0), pPos.offset(0, -2, 0))) {
                            BlockState state = pLevel.getBlockState(pos);
                            BlockEntity be = pLevel.getBlockEntity(pos);
                            if (state.is(SMBlocks.AMBER.get())) {
                                if (be instanceof AmberBE amberBE && amberBE.hasStuckEntity()) {
                                    CompoundTag compoundtag = amberBE.getEntityStuck();
                                    Entity entityLoaded = EntityType.loadEntityRecursive(compoundtag, level, entityStuck -> entityStuck);
                                    if (entityLoaded != null) {
                                        if (entityLoaded.getBoundingBox().getYsize() > 1.5F && entityLoaded.getBoundingBox().getYsize() < 2F && pos.equals(pPos.offset(0, -1, 0))) {
                                            shouldMeltFlag = false;
                                        }
                                        else if (entityLoaded.getBoundingBox().getYsize() >= 2F && entityLoaded.getBoundingBox().getYsize() < 3.5F && pos.equals(pPos.offset(0, -2, 0))) {
                                            shouldMeltFlag = false;
                                        }
                                    }
                                }
                            }
                        }
                        if (shouldMeltFlag) {
                            level.setBlock(pPos, pState.setValue(IS_MELTED, true), Block.UPDATE_ALL);
                            return AmberUtil.MELTING_COLLISION_SHAPE;
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
                }
                for (BlockPos pos : BlockPos.betweenClosed(blockPos.offset(0, -1, 0), blockPos.offset(0, -2, 0))) {
                    BlockState state = pLevel.getBlockState(pos);
                    BlockEntity be = pLevel.getBlockEntity(pos);
                    if (state.is(SMBlocks.AMBER.get())) {
                        if (be instanceof AmberBE amberBE && amberBE.hasStuckEntity()) {
                            CompoundTag compoundtag = amberBE.getEntityStuck();
                            AmberBE.removeIgnoredNBT(compoundtag);
                            Entity entity = EntityType.loadEntityRecursive(compoundtag, pLevel, entityStuck -> entityStuck);
                            if (entity != null) {
                                SMEntityCap.getCapOptional(entity).ifPresent(cap -> {
                                    cap.stuckInAmber = false;
                                });
                                SMPacketHandler.sendMsg(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new MsgEntityAmberStuck(entity, false));
                                if (entity.getBoundingBox().getYsize() > 1.5F && entity.getBoundingBox().getYsize() < 2F && pos.equals(blockPos.offset(0, -1, 0))) {
                                    pLevel.setBlock(pos, blockState.setValue(IS_MELTED, false), Block.UPDATE_ALL);
                                    entity.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                                    pLevel.addFreshEntity(entity);
                                    amberBE.setStuckEntityData(null);

                                }
                                else if (entity.getBoundingBox().getYsize() >= 2F && entity.getBoundingBox().getYsize() < 3.5F) {
                                    pLevel.setBlock(pos, blockState.setValue(IS_MELTED, false), Block.UPDATE_ALL);
                                    entity.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                                    pLevel.addFreshEntity(entity);
                                    amberBE.setStuckEntityData(null);

                                }

                            }
                        }
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
            if (!amber.hasStuckEntity() && pState.getValue(IS_MELTED)) {
                if (!(pEntity instanceof LivingEntity) || pEntity.getFeetBlockState().is(this)) {
                    if (pEntity instanceof Player) {
                        pEntity.makeStuckInBlock(pState, new Vec3(0.8F, 0.1D, 0.8F));
                    }
                    if (pEntity instanceof ItemEntity itemEntity) {
                        itemEntity.makeStuckInBlock(pState, new Vec3(0F, 0.1D, 0F));
                        if (itemEntity.onGround()) {
                            amber.makeEntityStuck(itemEntity);
                        }
                    } else if (pEntity instanceof Mob mob) {
                        if (mob.isVehicle()) {
                            mob.makeStuckInBlock(pState, new Vec3(0.5F, 0.1D, 0.5F));
                        } else if (mob.getBoundingBox().getYsize() < 1.5F) {
                            mob.makeStuckInBlock(pState, new Vec3(0F, 0.1D, 0F));
                            if (mob.onGround()) {
                                amber.makeEntityStuck(mob);
                            }
                        }
                        else if (mob.getBoundingBox().getYsize() < 2F) {
                            BlockPos extendedPos = new BlockPos(mob.getBlockX(), mob.getBlockY() + 1, mob.getBlockZ());
                            if (pLevel.getBlockState(extendedPos).is(SMBlocks.AMBER.get())) {
                                mob.makeStuckInBlock(pState, new Vec3(0F, 0.1D, 0F));
                                if (mob.onGround()) {
                                    pLevel.setBlock(extendedPos, pState.setValue(IS_MELTED, false), Block.UPDATE_ALL);
                                    amber.makeEntityStuck(mob);
                                }
                            } else {
                                mob.makeStuckInBlock(pState, new Vec3(0.8F, 0.1D, 0.8F));
                            }
                        }
                        else if (mob.getBoundingBox().getYsize() < 3.5F) {
                            BlockPos extendedPos = new BlockPos(mob.getBlockX(), mob.getBlockY() + 1, mob.getBlockZ());
                            BlockPos extendedPos1 = new BlockPos(mob.getBlockX(), mob.getBlockY() + 2, mob.getBlockZ());
                            if (pLevel.getBlockState(extendedPos).is(SMBlocks.AMBER.get()) && pLevel.getBlockState(extendedPos1).is(SMBlocks.AMBER.get())) {
                                mob.makeStuckInBlock(pState, new Vec3(0F, 0.1D, 0F));
                                if (mob.onGround()) {
                                    pLevel.setBlock(extendedPos, pState.setValue(IS_MELTED, false), Block.UPDATE_ALL);
                                    pLevel.setBlock(extendedPos1, pState.setValue(IS_MELTED, false), Block.UPDATE_ALL);
                                    amber.makeEntityStuck(mob);
                                }
                            } else {
                                mob.makeStuckInBlock(pState, new Vec3(0.8F, 0.1D, 0.8F));
                            }
                        }
                        else {
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
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(IS_MELTED);
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
