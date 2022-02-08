package com.uraneptus.sullysmod.common.capabilities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;

public class EntityCapabilities implements INBTSerializable<CompoundTag> {
    public static final HashMap<BlockPos, Integer>  BOUNCE = new HashMap<>();

    public BlockPos entityPos;
    public static int bounceCount;

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("bounceCount", bounceCount);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        bounceCount = nbt.getInt("bounceCount");
    }
}
