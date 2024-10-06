package com.uraneptus.sullysmod.common.blockentities;

import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class SolidAmberBE extends BlockEntity {

    private boolean isBlockMelted;

    public SolidAmberBE(BlockPos pPos, BlockState pBlockState) {
        super(SMBlockEntityTypes.SOLID_AMBER.get(), pPos, pBlockState);
    }



    public boolean isBlockMelted() {
        return this.isBlockMelted;
    }

    public void setBlockMelted(boolean value) {
        this.isBlockMelted = value;
    }



    public void tick() {
    }


    @Nonnull
    @Override
    public BlockEntityType<?> getType() {
        return SMBlockEntityTypes.SOLID_AMBER.get();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putBoolean("AmberMelted", this.isBlockMelted);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.isBlockMelted = pTag.getBoolean("AmberMelted");
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("AmberMelted", this.isBlockMelted);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        this.load(tag);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
