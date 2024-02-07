package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;
import java.util.List;

public class AmberBlockEntity extends BlockEntity {

    public final List<AmberBlockEntity.StuckEntityData> stored = new ObjectArrayList<>();

    private static final List<String> IGNORED_NBT = Arrays.asList("UUID");



    public AmberBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SMBlockEntityTypes.AMBER.get(), pPos, pBlockState);
    }
    public static void removeIgnoredNBT(CompoundTag pTag) {
        for(String s : IGNORED_NBT) {
            pTag.remove(s);
        }

    }
    public boolean isFull() {
        return this.stored.size() == 1;
    }
    public CompoundTag getEntityStuck(int value) {
        return this.stored.get(value).entityData;
    }
    public void makeEntityStuck(LivingEntity entity) {
        if (this.stored.size() < 1) {
            CompoundTag compoundtag = new CompoundTag();
            entity.save(compoundtag);
            this.storeProjectile(compoundtag);
            entity.discard();
            super.setChanged();
        }
    }

    public void storeProjectile(CompoundTag pEntityData) {
        this.stored.add(new AmberBlockEntity.StuckEntityData(pEntityData));
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        ListTag listtag = pTag.getList("StuckEntity", 10);

        for(int i = 0; i < listtag.size(); ++i) {
            CompoundTag compoundtag = listtag.getCompound(i);
            AmberBlockEntity.StuckEntityData stuckEntityData = new AmberBlockEntity.StuckEntityData(compoundtag.getCompound("EntityData"));
            this.stored.add(stuckEntityData);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("StuckEntity", this.writeProjectiles());
    }

    public ListTag writeProjectiles() {
        ListTag listtag = new ListTag();

        for(AmberBlockEntity.StuckEntityData stuckEntityData : this.stored) {
            CompoundTag compoundtag = stuckEntityData.entityData.copy();
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
            FlingerTotemBlockEntity.removeIgnoredNBT(pEntityData);
            this.entityData = pEntityData;
        }
    }
}
