package com.mosaicmodding.sullysmod.content.amber;

import com.mojang.serialization.Codec;
import com.mosaicmodding.sullysmod.registry.SMEntityAttachments;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;

public class StuckInAmberData {
    private boolean isStuck;

    public StuckInAmberData(boolean isStuck) {
        this.isStuck = isStuck;
    }

    public StuckInAmberData() {
        this(false);
    }

    public static final Codec<StuckInAmberData> CODEC = Codec.BOOL.xmap(StuckInAmberData::new, d -> d.isStuck);
    public static final StreamCodec<RegistryFriendlyByteBuf, StuckInAmberData> STREAM_CODEC = ByteBufCodecs.fromCodecWithRegistries(CODEC);

    public void setStuck(LivingEntity entity) {
        this.isStuck = true;
        SMEntityAttachments.STUCK_DATA.sync(entity);
    }

    public void setFree(LivingEntity entity) {
        this.isStuck = false;
        SMEntityAttachments.STUCK_DATA.sync(entity);
    }

    public boolean isStuck() {
        return this.isStuck;
    }
}
