package com.mosaicmodding.sullysmod.registry;

import com.mojang.serialization.MapCodec;
import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.core.conditions.ConfigFlagCondition;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class SMNeoConditions {
    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS = DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, SullysMod.ID);

    public static final DeferredHolder<MapCodec<? extends ICondition>, MapCodec<ConfigFlagCondition>> CONFIG_FLAG_CONDITION = CONDITION_CODECS.register("config", () -> ConfigFlagCondition.CODEC);


}
