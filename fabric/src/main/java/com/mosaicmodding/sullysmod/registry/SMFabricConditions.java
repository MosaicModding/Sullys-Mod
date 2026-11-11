package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.core.conditions.ConfigFlagCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;

public class SMFabricConditions {
    public static final ResourceConditionType<ConfigFlagCondition> CONFIG_FLAG_CONDITION_TYPE = ResourceConditionType.create(path("config"), ConfigFlagCondition.CODEC);

    public static void init() {
        ResourceConditions.register(CONFIG_FLAG_CONDITION_TYPE);
    }
}
