package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AmberBlockEntity extends BlockEntity {

    int stuckMobID;

    public AmberBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SMBlockEntityTypes.AMBER.get(), pPos, pBlockState);
    }


    public void setStuckMob(int value) {
        this.stuckMobID = value;
    }

    public int getStuckMob() {
        return this.stuckMobID;
    }


    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.stuckMobID = compoundTag.getInt("StuckMobID");
    }
    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putInt("StuckMobID", stuckMobID);
    }
}
