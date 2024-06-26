package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blockentities.AmberBE;
import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AmberSlabBlock extends SlabBlock {


    private static final VoxelShape MELTING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, (double)0.0F, 1.0D);


    public AmberSlabBlock(Properties pProperties) {
        super(pProperties);
    }


    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pContext instanceof EntityCollisionContext entitycollisioncontext) {
            Entity entity = entitycollisioncontext.getEntity();

            if (entity != null) {
                Level level = entity.level();
                boolean shouldMeltFlag = false;

                for (BlockPos pos : BlockPos.betweenClosed(pPos.offset(-1, -1, -1), pPos.offset(1, 1, 1))) {
                    BlockState state = pLevel.getBlockState(pos);
                    BlockEntity be = pLevel.getBlockEntity(pos);
                    if (state.is(SMBlockTags.MELTS_AMBER)) {
                        shouldMeltFlag = true;
                    }
                    if (state.getBlock() instanceof AmberBlock && be instanceof AmberBE amberBE) {
                        if (amberBE.isBlockMelted() && level.getBrightness(LightLayer.BLOCK, pPos.above()) >= 9) {
                            shouldMeltFlag = true;
                        }
                    }
                }
                if (shouldMeltFlag) {
                    if (entity instanceof Player player) {
                        if (player.jumping) {
                            return this.getShape(pState, pLevel, pPos, pContext);
                        }
                    }
                    return MELTING_COLLISION_SHAPE;
                }
            }
        }
        return this.getShape(pState, pLevel, pPos, pContext);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (this.getCollisionShape(pState, pLevel, pPos, CollisionContext.of(pEntity)) == MELTING_COLLISION_SHAPE) {
            if (!(pEntity instanceof LivingEntity) || pEntity.getFeetBlockState().is(this)) {
                pEntity.makeStuckInBlock(pState, new Vec3(0.8F, 0.1D, 0.8F));
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
