package com.uraneptus.sullysmod.common.entities.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class LightAvoidingRandomSwimmingGoal extends RandomSwimmingGoal {
    protected final float probability;

    public LightAvoidingRandomSwimmingGoal(PathfinderMob mob, double speedModifier, int probability) {
        super(mob, speedModifier, probability);
        this.probability = probability;
    }

    /*
        I think this works, couldn't test it 100% tho!
     */
    @Nullable
    protected Vec3 getPosition() {
        Vec3 vec3 = this.mob.getViewVector(0.0F);
        //System.out.println("Goal Fired");
        if (this.mob.getLevel().getBrightness(LightLayer.BLOCK, mob.blockPosition()) > 0) {
            Vec3 vec31 = AirAndWaterRandomPos.getPos(this.mob, 16, 16, (int) vec3.reverse().y, vec3.reverse().x, vec3.reverse().z, (float)Math.PI / 2F);
            //System.out.println("First check");
            return vec31 == null ? super.getPosition() : vec31;
        } else {
            //System.out.println("Second check");
            return this.mob.getRandom().nextFloat() >= this.probability ? AirAndWaterRandomPos.getPos(this.mob, 16, 16, (int) vec3.reverse().y, vec3.x, vec3.z, (float)Math.PI / 2F) : super.getPosition();
        }
    }
}
