package com.uraneptus.sullysmod.common.entities.chameleon;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.AnimalPanic;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.animal.frog.FrogAi;
import net.minecraft.world.entity.schedule.Activity;

public class ChameleonAi {
    private static final ImmutableList<SensorType<? extends Sensor<? super Chameleon>>> SENSOR_TYPES = ImmutableList.of(SensorType.HURT_BY);
    private static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.PATH, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.LOOK_TARGET, MemoryModuleType.IS_PANICKING);

    public static Brain<?> makeBrain(Brain<Chameleon> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    public static Brain.Provider<Chameleon> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

    private static void initCoreActivity(Brain<Chameleon> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(new Swim(0.8F), new AnimalPanic(0.5F), new LookAtTargetSink(45, 80), new MoveToTargetSink()));
    }

    private static void initIdleActivity(Brain<Chameleon> brain) {
        brain.addActivity(Activity.IDLE, 0, ImmutableList.of());
    }
}
