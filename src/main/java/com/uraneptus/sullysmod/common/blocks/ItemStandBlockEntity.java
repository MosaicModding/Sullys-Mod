package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemStandBlockEntity extends BlockEntity {
    ItemStack displayItem = ItemStack.EMPTY;

    public ItemStandBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SMBlockEntityTypes.ITEM_STAND.get(), pPos, pBlockState);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.setDisplayItem(ItemStack.of(pTag.getCompound("DisplayItem")));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (!this.getDisplayItem().isEmpty()) {
            pTag.put("DisplayItem", this.getDisplayItem().save(new CompoundTag()));
        }

    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        if (!this.getDisplayItem().isEmpty()) {
            tag.put("DisplayItem", this.getDisplayItem().save(new CompoundTag()));
        }

        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public ItemStack getDisplayItem() {
        return this.displayItem;
    }

    public void setDisplayItem(ItemStack item) {
        this.displayItem = item;
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType() {
        return SMBlockEntityTypes.ITEM_STAND.get();
    }
}
