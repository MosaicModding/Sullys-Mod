package com.uraneptus.sullysmod.mixins;

import com.uraneptus.sullysmod.common.entities.Tortoise;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AbstractVillager {

    public VillagerMixin(EntityType<? extends AbstractVillager> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //this doesn't work?
    @Override
    public void rideTick() {
        super.rideTick();
        if (this.getVehicle() instanceof Tortoise tortoise) {
            this.yBodyRot = tortoise.yBodyRot;
            this.setDeltaMovement(Vec3.ZERO);
        }
    }
}
