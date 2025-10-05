package com.uraneptus.sullysmod.common.blockentities;

import com.uraneptus.sullysmod.common.caps.SMEntityCap;
import com.uraneptus.sullysmod.common.networking.MsgEntityAmberStuck;
import com.uraneptus.sullysmod.common.networking.SMPacketHandler;
import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class AmberBENew extends BlockEntity {
    @Nullable
    private AmberBENew.StuckEntityData stuckEntityData;
    public boolean renderEntity;
    private boolean entityUpdated = false;
    @Nonnull
    private List<BlockPos> childBlocks = new ArrayList<>(); //In case we are parent
    @Nullable
    private BlockPos parentBlock = null; //In case we are child
    private static final List<String> IGNORED_NBT = Arrays.asList("Leash", "Fire");

    public AmberBENew(BlockPos pPos, BlockState pBlockState) {
        super(SMBlockEntityTypes.AMBER_NEW.get(), pPos, pBlockState);
    }

    public boolean isValidParent() {
        if (parentBlock != null) return false; // A parent isn't allowed to have a parent (sorry grandpa)
        return hasStuckEntity(); // Parent needs entity saved
    }

    public boolean isValidChild() {
        if (!childBlocks.isEmpty()) return false; //Children can't have other children (pretty small family here)
        if (hasStuckEntity()) return false; //Children shouldn't save entities
        return parentBlock != null; //Child needs a parent
    }

    public boolean hasStuckEntity() {
        return this.stuckEntityData != null;
    }

    public List<BlockPos> getChildBlocks() {
        return childBlocks;
    }

    @Nullable
    public BlockPos getParentBlock() {
        if (parentBlock != null) {
            return parentBlock.immutable();
        } else {
            return null;
        }
    }

    private void setParentBlock(@Nullable BlockPos pParentBlock) {
        this.parentBlock = pParentBlock;
    }

    public void storeAsParent(Entity entity, @Nonnull List<BlockPos> childBlocks) {
        storeEntity(entity);
        this.childBlocks = childBlocks;
    }

    public void storeAsChild(BlockPos parentBlock) {
        setParentBlock(parentBlock.immutable());
    }

    public void storeEntity(Entity entity) {
        if (this.stuckEntityData != null) return;
        Level level = this.getLevel();
        if (level == null) return;

        CompoundTag compoundtag = new CompoundTag();
        SMPacketHandler.sendMsg(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new MsgEntityAmberStuck(entity, true));
        SMEntityCap.getCapOptional(entity).ifPresent(cap -> cap.stuckInAmber = true);
        entity.save(compoundtag);
        if (!compoundtag.isEmpty()) {
            this.storeData(compoundtag);
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

        this.storeData(compoundtag);
        return true;
    }

    public void storeData(CompoundTag pEntityData) {
        this.stuckEntityData = new AmberBENew.StuckEntityData(pEntityData);
    }

    public CompoundTag getEntityStuck() {
        CompoundTag tag = new CompoundTag();
        return this.stuckEntityData != null ? this.stuckEntityData.entityData : tag;
    }

    public void tick() {
        CompoundTag stuckEntity = getEntityStuck();
        if (stuckEntity.isEmpty() || this.entityUpdated || this.level == null) return;
        if (!this.level.isClientSide()) {
            Entity entity = EntityType.loadEntityRecursive(stuckEntity, this.level, Function.identity());
            if (entity != null) {
                entity.setYBodyRot(Mth.randomBetween(level.random, 1, 270));
                this.stuckEntityData = null;
                this.storeEntity(entity);
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
        return SMBlockEntityTypes.AMBER_NEW.get();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("StuckEntity", this.writeStuckEntity());
        pTag.putBoolean("RenderEntity", this.renderEntity);
        if (this.getParentBlock() != null) {
            CompoundTag bp = NbtUtils.writeBlockPos(this.getParentBlock());
            pTag.put("ParentBlock", bp);
        } else if (!childBlocks.isEmpty()) {
            ListTag childBlocksTag = new ListTag();
            for (BlockPos pos : childBlocks) {
                childBlocksTag.add(NbtUtils.writeBlockPos(pos));
            }
            pTag.put("ChildBlocks", childBlocksTag);
        }

    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        ListTag listtag = pTag.getList("StuckEntity", 10);
        if (!listtag.isEmpty()) {
            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                this.stuckEntityData = new AmberBENew.StuckEntityData(compoundtag.getCompound("EntityData"));
            }
        } else {
            this.stuckEntityData = null;
        }
        this.renderEntity = pTag.getBoolean("RenderEntity");
        if (pTag.contains("ParentBlock")) {
            setParentBlock(NbtUtils.readBlockPos(pTag.getCompound("ParentBlock")).immutable());
        } else if (pTag.contains("ChildBlocks")) {
            ListTag childBlocksTag = pTag.getList("ChildBlocks", 10);
            if (!childBlocksTag.isEmpty()) {
                List<BlockPos> positions = new ArrayList<>();
                for(int i = 0; i < childBlocksTag.size(); ++i) {
                    positions.add(NbtUtils.readBlockPos(childBlocksTag.getCompound(i)));
                }
                this.childBlocks = positions;
            }
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.put("StuckEntity", this.writeStuckEntity());
        tag.putBoolean("RenderEntity", this.renderEntity);
        if (this.getParentBlock() != null) {
            CompoundTag bp = NbtUtils.writeBlockPos(this.getParentBlock());
            tag.put("ParentBlock", bp);
        } else if (!childBlocks.isEmpty()) {
            ListTag childBlocksTag = new ListTag();
            for (BlockPos pos : childBlocks) {
                childBlocksTag.add(NbtUtils.writeBlockPos(pos));
            }
            tag.put("ChildBlocks", childBlocksTag);
        }
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
            CompoundTag entityDataTag = this.stuckEntityData.entityData.copy();
            CompoundTag compoundtag1 = new CompoundTag();
            compoundtag1.put("EntityData", entityDataTag);
            listtag.add(compoundtag1);
        }

        return listtag;
    }

    public static void removeIgnoredNBT(CompoundTag pTag) {
        for(String s : IGNORED_NBT) {
            pTag.remove(s);
        }
    }

    public static class StuckEntityData {
        final CompoundTag entityData;

        StuckEntityData(CompoundTag pEntityData) {
            AmberBENew.removeIgnoredNBT(pEntityData);
            this.entityData = pEntityData;
        }
    }
}
