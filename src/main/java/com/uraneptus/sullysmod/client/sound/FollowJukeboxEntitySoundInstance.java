package com.uraneptus.sullysmod.client.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FollowJukeboxEntitySoundInstance extends AbstractTickableSoundInstance {
    private final Entity entity;

    public FollowJukeboxEntitySoundInstance(Entity entity, SoundEvent soundEvent) {
        super(soundEvent, SoundSource.RECORDS, entity.level().random);
        this.entity = entity;
        this.looping = true;
    }

    @Override
    public void tick() {
        this.x = entity.getX();
        this.y = entity.getY();
        this.z = entity.getZ();
    }
}
