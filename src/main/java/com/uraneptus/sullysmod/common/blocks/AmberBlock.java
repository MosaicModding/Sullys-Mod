package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
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
        BlockPos blockPos = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
        if (pContext instanceof EntityCollisionContext entitycollisioncontext) {
            Entity entity = entitycollisioncontext.getEntity();
            if (entity != null) {
                Level level = entity.level();
                BlockEntity blockEntity = level.getBlockEntity(pPos);
                if (level.getBrightness(LightLayer.BLOCK, blockPos) > 11 && blockEntity != null && !((AmberBlockEntity) blockEntity).isFull()) {
                    return MELTING_COLLISION_SHAPE;
                }
            }
        }
        return Shapes.block();
    }

    /*
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        /*
        BlockEntity blockEntity = pLevel.getBlockEntity(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ()));
        assert blockEntity != null;
        CompoundTag compoundtag = ((AmberBlockEntity) blockEntity).getEntityStuck(1).entityData;
        FlingerTotemBlockEntity.removeIgnoredNBT(compoundtag);
        if (((AmberBlockEntity) blockEntity).isFull()) {
            LivingEntity livingEntity = (LivingEntity) EntityType.loadEntityRecursive(compoundtag, pLevel, entity -> entity);
            livingEntity.moveTo(pPos.getX() + 0.5, pPos.getY(), pPos.getZ() + 0.5);
            pLevel.addFreshEntity(livingEntity);
            System.out.println("BROKE WORKED");
        }
        System.out.println(blockEntity);
        System.out.println("AMBER BROKE");


    }
    */

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return SMBlockEntityTypes.AMBER.get().create(pPos, pState);
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
                    System.out.println("MOB STUCK IN AMBER");
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
