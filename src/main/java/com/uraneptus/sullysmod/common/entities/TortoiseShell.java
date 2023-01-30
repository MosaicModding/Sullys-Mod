package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TortoiseShell extends Entity {

    public TortoiseShell(EntityType<? extends Entity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TortoiseShell(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(SMEntityTypes.TORTOISE_SHELL.get(), level);
    }

    public TortoiseShell(Level pLevel, double pX, double pY, double pZ) {
        this(SMEntityTypes.TORTOISE_SHELL.get(), pLevel);
        this.setPos(pX, pY, pZ);
    }

    @Override
    protected void defineSynchedData() {

    }

    public boolean isPushable() {
        return true;
    }

    public boolean isPickable() {
        return this.isAlive();
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
