package com.mosaicmodding.sullysmod.content.amber;

import com.mosaicmodding.sullysmod.registry.SMBlockEntityTypes;
import com.mosaicmodding.sullysmod.registry.SMEntityAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class AmberBE extends BlockEntity {
    @Nullable
    private StuckEntityData stuckEntityData;
    public boolean renderEntity;
    private boolean entityUpdated = false; //Used to indicate that an actual entity is stored here and not just the id.

    //A Parent can only have children. A child has a parent and its siblings
    private boolean isParent;

    @NotNull
    private List<BlockPos> childBlocks = new ArrayList<>(); //In case we are parent or a child
    @Nullable
    private BlockPos parentBlock = null; //In case we are child
    private static final List<String> IGNORED_NBT = Arrays.asList("Leash", "Fire", "UUID");

    public AmberBE(BlockPos pPos, BlockState pBlockState) {
        super(SMBlockEntityTypes.AMBER.get(), pPos, pBlockState);
    }

    public boolean isValidParent() {
        if (!isParent) return false;
        if (parentBlock != null) return false; // A parent isn't allowed to have a parent (sorry grandpa)
        return hasStuckEntity(); // Parent needs entity saved
    }

    public boolean isValidChild() {
        if (isParent) return false;
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

    public void storeAsParent(Entity entity, List<BlockPos> childBlocks) {
        storeEntityToBlock(entity);
        this.childBlocks = childBlocks;
        this.isParent = true;
    }

    public void storeAsChild(BlockPos parentBlock, List<BlockPos> siblingBlocks) {
        setParentBlock(parentBlock.immutable());
        if (this.childBlocks.isEmpty()) {
            this.childBlocks = siblingBlocks;
        }
        this.isParent = false;
    }

    /**
     * NEVER call this directly! Always use storeAsParent
     */
    private void storeEntityToBlock(Entity entity) {
        if (this.stuckEntityData != null) return;
        Level level = this.getLevel();
        if (level == null) return;

        CompoundTag compoundtag = new CompoundTag();
        if (entity instanceof LivingEntity livingEntity) {
            var stuckData = SMEntityAttachments.STUCK_DATA.getOrCreate(livingEntity);
            stuckData.setStuck(livingEntity);
        }
        entity.save(compoundtag);
        if (!compoundtag.isEmpty()) {
            this.storeData(compoundtag);
        }
        this.renderEntity = true;
        this.entityUpdated = true;
        this.update();
        entity.discard();
    }

    /**
     * This is exclusively used for worldgen where we don't have a level yet! This only saves the entity id. <br>
     * The actual saving process for the generated entities is later done in the tick method.
     */
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
        this.stuckEntityData = new StuckEntityData(pEntityData);
    }

    public CompoundTag getEntityStuck() {
        CompoundTag tag = new CompoundTag();
        return this.stuckEntityData != null ? this.stuckEntityData.entityData : tag;
    }

    /**
     * This only runs once when a BE is loaded that was created during worldgen. <br>
     * It then creates the actual entity from the id, sets a random rotation and then saves it as a single parent block.
     */
    public void tick() {
        CompoundTag stuckEntity = getEntityStuck();
        if (stuckEntity.isEmpty() || this.entityUpdated || this.level == null) return;
        if (!this.level.isClientSide()) {
            Entity entity = EntityType.loadEntityRecursive(stuckEntity, this.level, Function.identity());
            if (entity != null) {
                float randomRot = Mth.randomBetween(level.random, 1, 270);
                entity.setYRot(randomRot);
                this.stuckEntityData = null;
                this.storeAsParent(entity, List.of());
            }
        }
    }

    public void update() {
        setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @NotNull
    @Override
    public BlockEntityType<?> getType() {
        return SMBlockEntityTypes.AMBER.get();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("StuckEntity", this.writeStuckEntity());
        tag.putBoolean("RenderEntity", this.renderEntity);
        tag.putBoolean("isParent", this.isParent);
        if (this.getParentBlock() != null) {
            tag.put("ParentBlock", NbtUtils.writeBlockPos(this.getParentBlock()));
        } else if (!childBlocks.isEmpty()) {
            ListTag childBlocksTag = new ListTag();
            for (BlockPos pos : childBlocks) {
                childBlocksTag.add(NbtUtils.writeBlockPos(pos));
            }
            tag.put("ChildBlocks", childBlocksTag);
        }

    }

    @Override
    public void loadAdditional(CompoundTag pTag, HolderLookup.Provider registries) {
        super.loadAdditional(pTag, registries);
        ListTag listtag = pTag.getList("StuckEntity", 10);
        if (!listtag.isEmpty()) {
            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                this.stuckEntityData = new StuckEntityData(compoundtag.getCompound("EntityData"));
            }
        } else {
            this.stuckEntityData = null;
        }
        this.renderEntity = pTag.getBoolean("RenderEntity");
        this.isParent = pTag.getBoolean("isParent");
        if (pTag.contains("ParentBlock")) {
            NbtUtils.readBlockPos(pTag, "ParentBlock").ifPresent(p -> {
                setParentBlock(p.immutable());
            });
        }
        if (pTag.contains("ChildBlocks")) {
            ListTag childBlocksTag = pTag.getList("ChildBlocks", 10);
            if (!childBlocksTag.isEmpty()) {
                List<BlockPos> positions = new ArrayList<>();
                for(int i = 0; i < childBlocksTag.size(); ++i) {
                    NbtUtils.readBlockPos(childBlocksTag.getCompound(i), "").ifPresent(positions::add);

                }
                this.childBlocks = positions;
            }
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        tag.put("StuckEntity", this.writeStuckEntity());
        tag.putBoolean("RenderEntity", this.renderEntity);
        tag.putBoolean("isParent", this.isParent);
        if (this.getParentBlock() != null) {
            tag.put("ParentBlock", NbtUtils.writeBlockPos(this.getParentBlock()));
        }
        if (!childBlocks.isEmpty()) {
            ListTag childBlocksTag = new ListTag();
            for (BlockPos pos : childBlocks) {
                childBlocksTag.add(NbtUtils.writeBlockPos(pos));
            }
            tag.put("ChildBlocks", childBlocksTag);
        }
        return tag;
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

    public record StuckEntityData(CompoundTag entityData) {
        public StuckEntityData {
            AmberBE.removeIgnoredNBT(entityData);
        }
    }
}
