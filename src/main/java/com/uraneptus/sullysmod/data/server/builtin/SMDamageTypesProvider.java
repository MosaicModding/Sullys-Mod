package com.uraneptus.sullysmod.data.server.builtin;

import com.uraneptus.sullysmod.core.registry.SMDamageTypes;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class SMDamageTypesProvider {
    protected static void create(BootstapContext<DamageType> context) {
        SMDamageTypes.damageTypeMap.forEach(((damageTypeKey, damageType) -> register(context, damageTypeKey, damageType)));
    }

    protected static void register(BootstapContext<DamageType> context, ResourceKey<DamageType> key, DamageType damageType) {
        context.register(key, damageType);
    }
}
