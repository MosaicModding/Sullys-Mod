package com.uraneptus.sullysmod.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AmberBlock extends HalfTransparentBlock {

    private static final VoxelShape MELTING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, (double)0.0F, 1.0D);

    LivingEntity ENTITY_STUCK;
    public AmberBlock(Properties pProperties) {
        super(pProperties);
    }


    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        BlockPos blockPos = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
        if (pContext instanceof EntityCollisionContext entitycollisioncontext) {
            Entity entity = entitycollisioncontext.getEntity();
            if (entity != null) {
                Level level = entity.level();
                if (level.getBrightness(LightLayer.BLOCK, blockPos) > 11) {
                    return MELTING_COLLISION_SHAPE;
                }
            }
        }
        return Shapes.block();
    }

    /*

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!(pEntity instanceof LivingEntity) || pEntity.getFeetBlockState().is(this)) {
            if (pEntity instanceof Player) {
                pEntity.makeStuckInBlock(pState, new Vec3((double) 0.8F, 0.1D, (double) 0.8F));
            } else if (pEntity instanceof Mob mob) {
                pEntity.makeStuckInBlock(pState, new Vec3((double) 0.0F, 0.1D, (double) 0.0F));
                if (mob.getBlockStateOn() != SMBlocks.AMBER.get().defaultBlockState()) {
                    mob.setSilent(true);
                    mob.setRemainingFireTicks(0);
                    mob.setInvulnerable(true);
                    mob.setNoAi(true);
                    ENTITY_STUCK = mob;
                }
            }
            if (pLevel.isClientSide) {
                RandomSource randomsource = pLevel.getRandom();
                boolean flag = pEntity.xOld != pEntity.getX() || pEntity.zOld != pEntity.getZ();
                if (flag && randomsource.nextBoolean()) {
                    pLevel.addParticle(ParticleTypes.SOUL_FIRE_FLAME, pEntity.getX(), (double)(pPos.getY() + 1), pEntity.getZ(), (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F), (double)0.05F, (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F));
                }
            }
        }
    }

     */
}
