package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

import java.util.HashMap;
import java.util.Map;

public class SMDamageTypes {
    public static Map<ResourceKey<DamageType>, DamageType> damageTypeMap = new HashMap<>();

    public static final ResourceKey<DamageType> TORTOISE_SHELL = register(new DamageType("tortoise_shell", 0));
    public static final ResourceKey<DamageType> THROWING_KNIFE = register(new DamageType("throwing_knife", 0));

    private static ResourceKey<DamageType> register(DamageType damageType) {
        ResourceKey<DamageType> key = ResourceKey.create(Registries.DAMAGE_TYPE, SullysMod.modPrefix(damageType.msgId()));
        damageTypeMap.put(key, damageType);
        return key;
    }
}