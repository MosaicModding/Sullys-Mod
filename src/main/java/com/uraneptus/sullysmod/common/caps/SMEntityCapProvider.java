package com.uraneptus.sullysmod.common.caps;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SMEntityCapProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    private final SMEntityCap backend = new SMEntityCap();

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return SMEntityCap.ENTITY_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> backend));
    }

    @Override
    public CompoundTag serializeNBT() {
        return backend.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.backend.deserializeNBT(nbt);
    }

    @Mod.EventBusSubscriber
    public static class Events {

        @SubscribeEvent
        public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof LivingEntity) {
                final SMEntityCapProvider provider = new SMEntityCapProvider();
                event.addCapability(SullysMod.modPrefix("sullysmod_entity_cap"), provider);
            }
        }
    }
}
