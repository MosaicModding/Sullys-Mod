package com.uraneptus.sullysmod.common.blockentities;

import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class AmberBE extends BlockEntity {
    @Nullable
    private AmberBE.StuckEntityData stuckEntityData;

    private boolean isBlockMelted;
    public boolean canWalkOnAmber = false;

    private static final List<String> IGNORED_NBT = Arrays.asList("UUID", "Leash");

    public AmberBE(BlockPos pPos, BlockState pBlockState) {
        super(SMBlockEntityTypes.AMBER.get(), pPos, pBlockState);
    }

    public static void removeIgnoredNBT(CompoundTag pTag) {
        for(String s : IGNORED_NBT) {
            pTag.remove(s);
        }

    }

    public boolean isBlockMelted() {
        return this.isBlockMelted;
    }

    public void setBlockMelted(boolean value) {
        this.isBlockMelted = value;
    }

    public boolean hasStuckEntity() {
        return this.stuckEntityData != null;
    }

    public CompoundTag getEntityStuck() {
        CompoundTag tag = new CompoundTag();
        return this.stuckEntityData != null ? this.stuckEntityData.entityData : tag;
    }

    public void makeEntityStuck(LivingEntity entity) {
        if (this.stuckEntityData == null) {
            CompoundTag compoundtag = new CompoundTag();
            entity.save(compoundtag);
            if (!compoundtag.isEmpty()) {
                this.storeEntity(compoundtag);
            }
            this.update();
            entity.discard();
        }
    }

    public void storeEntity(CompoundTag pEntityData) {
        this.stuckEntityData = new StuckEntityData(pEntityData);
    }

    public void tick() {

    }

    public void update() {
        setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType() {
        return SMBlockEntityTypes.AMBER.get();
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        ListTag listtag = pTag.getList("StuckEntity", 10);

        for(int i = 0; i < listtag.size(); ++i) {
            CompoundTag compoundtag = listtag.getCompound(i);
            this.stuckEntityData = new AmberBE.StuckEntityData(compoundtag.getCompound("EntityData"));
        }
        this.isBlockMelted = pTag.getBoolean("AmberMelted");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("StuckEntity", this.writeProjectiles());
        pTag.putBoolean("AmberMelted", this.isBlockMelted);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.put("StuckEntity", this.writeProjectiles());
        tag.putBoolean("AmberMelted", this.isBlockMelted);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public ListTag writeProjectiles() {
        ListTag listtag = new ListTag();

        if (this.stuckEntityData != null) {
            CompoundTag compoundtag = this.stuckEntityData.entityData.copy();
            compoundtag.remove("UUID");
            CompoundTag compoundtag1 = new CompoundTag();
            compoundtag1.put("EntityData", compoundtag);
            listtag.add(compoundtag1);
        }

        return listtag;
    }

    static class StuckEntityData {
        final CompoundTag entityData;

        StuckEntityData(CompoundTag pEntityData) {
            AmberBE.removeIgnoredNBT(pEntityData);
            this.entityData = pEntityData;
        }
    }
}
