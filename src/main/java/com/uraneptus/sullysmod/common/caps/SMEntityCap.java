package com.uraneptus.sullysmod.common.caps;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class SMEntityCap implements INBTSerializable<CompoundTag> {
    public static Capability<SMEntityCap> ENTITY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});
    public boolean stuckInAmber;

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("stuckInAmber", this.stuckInAmber);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.stuckInAmber = nbt.getBoolean("stuckInAmber");
    }

    public static LazyOptional<SMEntityCap> getCapOptional(LivingEntity entity) {
        return entity.getCapability(ENTITY_CAPABILITY);
    }
}
