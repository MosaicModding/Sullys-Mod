package com.uraneptus.sullysmod.common.blockentities;

import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nonnull;
import java.util.HashSet;

public class AncientSkullBE extends SkullBlockEntity {
    public static HashSet<Block> SKULLS = new HashSet<>();

    public AncientSkullBE(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState);
    }

    public AABB getRenderBoundingBox() {
        BlockPos pos = this.getBlockPos();
        return new AABB(pos.offset(-1, -1, -1), pos.offset(1, 1, 1));
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType() {
        return SMBlockEntityTypes.ANCIENT_SKULL.get();
    }

}
