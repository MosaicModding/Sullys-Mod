package com.uraneptus.sullysmod.common.blockentities;

import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class AmberBE extends BlockEntity {
    @Nullable
    private AmberBE.StuckEntityData StuckEntityData;

    private boolean isBlockMelted;

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
        return this.StuckEntityData != null;
    }

    public CompoundTag getEntityStuck() {
        CompoundTag tag = new CompoundTag();
        return this.StuckEntityData != null ? this.StuckEntityData.entityData : tag;
    }

    public void makeEntityStuck(LivingEntity entity) {
        if (this.StuckEntityData == null) {
            CompoundTag compoundtag = new CompoundTag();
            entity.save(compoundtag);
            this.storeProjectile(compoundtag);
            entity.discard();
            super.setChanged();
        }
    }

    public void storeProjectile(CompoundTag pEntityData) {
        this.StuckEntityData = new StuckEntityData(pEntityData);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        ListTag listtag = pTag.getList("StuckEntity", 10);

        for(int i = 0; i < listtag.size(); ++i) {
            CompoundTag compoundtag = listtag.getCompound(i);
            this.StuckEntityData = new AmberBE.StuckEntityData(compoundtag.getCompound("EntityData"));
        }
        this.isBlockMelted = pTag.getBoolean("AmberMelted");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("StuckEntity", this.writeProjectiles());
        pTag.putBoolean("AmberMelted", this.isBlockMelted);
    }

    public ListTag writeProjectiles() {
        ListTag listtag = new ListTag();

        if (this.StuckEntityData != null) {
            CompoundTag compoundtag = this.StuckEntityData.entityData.copy();
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
            FlingerTotemBE.removeIgnoredNBT(pEntityData);
            this.entityData = pEntityData;
        }
    }
}
