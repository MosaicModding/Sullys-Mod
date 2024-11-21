package com.uraneptus.sullysmod.common.blockentities;

import com.uraneptus.sullysmod.common.blocks.utilities.AmberUtil;
import com.uraneptus.sullysmod.common.caps.SMEntityCap;
import com.uraneptus.sullysmod.common.networking.MsgEntityAmberStuck;
import com.uraneptus.sullysmod.common.networking.SMPacketHandler;
import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class AmberBE extends BlockEntity {
    @Nullable
    private AmberBE.StuckEntityData stuckEntityData;
    public boolean renderEntity;
    private boolean entityUpdated = false;
    private static final List<String> IGNORED_NBT = Arrays.asList("UUID", "Leash");

    public AmberBE(BlockPos pPos, BlockState pBlockState) {
        super(SMBlockEntityTypes.AMBER.get(), pPos, pBlockState);
    }

    public static void removeIgnoredNBT(CompoundTag pTag) {
        for(String s : IGNORED_NBT) {
            pTag.remove(s);
        }
    }

    public void setStuckEntityData(@Nullable StuckEntityData value) {
        this.stuckEntityData = value;
        this.renderEntity = value != null;
        this.update();
    }

    public boolean hasStuckEntity() {
        return this.stuckEntityData != null;
    }

    public CompoundTag getEntityStuck() {
        CompoundTag tag = new CompoundTag();
        return this.stuckEntityData != null ? this.stuckEntityData.entityData : tag;
    }

    public void makeEntityStuck(Entity entity) {
        if (this.stuckEntityData != null) return;
        Level level = this.getLevel();
        if (level == null) return;
        level.setBlock(this.getBlockPos(), this.getBlockState().setValue(AmberUtil.IS_MELTED, false), Block.UPDATE_ALL);

        CompoundTag compoundtag = new CompoundTag();
        SMPacketHandler.sendMsg(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new MsgEntityAmberStuck(entity, true));
        SMEntityCap.getCapOptional(entity).ifPresent(cap -> cap.stuckInAmber = true);
        entity.save(compoundtag);
        if (!compoundtag.isEmpty()) {
            this.storeEntity(compoundtag);
        }
        this.renderEntity = true;
        this.entityUpdated = true;
        this.update();
        entity.discard();
    }

    //This method only stores the entity id and is only used by Amber worldgen
    //The actual saving process for the generated entities is later done in the tick method
    public boolean storeTypeForGeneration(EntityType<?> entityType) {
        if (this.stuckEntityData != null) return false;
        CompoundTag compoundtag = new CompoundTag();
        ResourceLocation resourcelocation = EntityType.getKey(entityType);
        String id = entityType.canSerialize() ? resourcelocation.toString() : null;
        if (id == null) return false;
        compoundtag.putString("id", id);

        this.storeEntity(compoundtag);
        return true;
    }

    protected ListTag newFloatList(float... pNumbers) {
        ListTag listtag = new ListTag();

        for(float f : pNumbers) {
            listtag.add(FloatTag.valueOf(f));
        }

        return listtag;
    }

    public void storeEntity(CompoundTag pEntityData) {
        this.stuckEntityData = new StuckEntityData(pEntityData);
    }

    public void tick() {
        CompoundTag stuckEntity = getEntityStuck();
        if (stuckEntity.isEmpty() || this.entityUpdated || this.level == null) return;
        if (!this.level.isClientSide()) {
            Entity entity = EntityType.loadEntityRecursive(stuckEntity, this.level, Function.identity());
            if (entity != null) {
                entity.setYBodyRot(Mth.randomBetween(level.random, 1, 270));
                this.stuckEntityData = null;
                this.makeEntityStuck(entity);
            }
        }
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
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("StuckEntity", this.writeStuckEntity());
        pTag.putBoolean("RenderEntity", this.renderEntity);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        ListTag listtag = pTag.getList("StuckEntity", 10);
        if (!listtag.isEmpty()) {
            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                this.stuckEntityData = new AmberBE.StuckEntityData(compoundtag.getCompound("EntityData"));
            }
        } else {
            this.stuckEntityData = null;
        }
        this.renderEntity = pTag.getBoolean("RenderEntity");
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.put("StuckEntity", this.writeStuckEntity());
        tag.putBoolean("RenderEntity", this.renderEntity);
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

    public ListTag writeStuckEntity() {
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

    public static class StuckEntityData {
        final CompoundTag entityData;

        StuckEntityData(CompoundTag pEntityData) {
            AmberBE.removeIgnoredNBT(pEntityData);
            this.entityData = pEntityData;
        }
    }
}
