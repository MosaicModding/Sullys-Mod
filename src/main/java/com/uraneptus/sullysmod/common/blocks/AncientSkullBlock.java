package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.common.blockentities.AncientSkullBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AncientSkullBlock extends SkullBlock {

    public AncientSkullBlock(Type pType, Properties pProperties) {
        super(pType, pProperties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AncientSkullBE(pPos, pState);
    }

    public enum Types implements SkullBlock.Type {
        CRACKED,
        CRESTED,
        FLATBILLED,
        GIGANTIC,
        HORNED,
        LONG,
        TINY,
        WIDE,
    }
}
