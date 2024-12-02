package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blockentities.AncientSkullBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AncientWallSkullBlock extends WallSkullBlock {

    public AncientWallSkullBlock(SkullBlock.Type pType, Properties pProperties) {
        super(pType, pProperties);
        AncientSkullBE.SKULLS.add(this);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AncientSkullBE(pos, state);
    }
}
