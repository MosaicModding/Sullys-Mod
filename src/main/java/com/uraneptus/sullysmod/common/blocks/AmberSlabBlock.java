package com.uraneptus.sullysmod.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AmberSlabBlock extends SlabBlock {


    private static final VoxelShape MELTING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, (double)0.0F, 1.0D);

    private static final VoxelShape SLAB_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, (double)0.5F, 1.0D);

    public AmberSlabBlock(Properties pProperties) {
        super(pProperties);
    }


    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        BlockPos blockPos = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
        if (pContext instanceof EntityCollisionContext entitycollisioncontext) {
            Entity entity = entitycollisioncontext.getEntity();
            if (entity != null) {
                Level level = entity.level();
                if (level.getBrightness(LightLayer.BLOCK, blockPos) > 11) {
                    if (entity instanceof Player player) {
                        if (!player.jumping) {
                            return MELTING_COLLISION_SHAPE;
                        }
                    } else {
                        return MELTING_COLLISION_SHAPE;
                    }
                }
            }
        }
        return SLAB_SHAPE;
    }
}
