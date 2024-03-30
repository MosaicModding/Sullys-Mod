package com.uraneptus.sullysmod.common.blockentities;

import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class AncientSkullBE extends SkullBlockEntity {

    public AncientSkullBE(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState);
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType() {
        return SMBlockEntityTypes.FLINGER_TOTEM.get();
    }

}
