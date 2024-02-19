package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SMPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, SullysMod.MOD_ID);
    public static Map<Supplier<? extends Potion>, String> POTION_TRANSLATIONS = new HashMap<>();

    public static final RegistryObject<Potion> UNLUCK = register("unluck", "Bad Luck", new MobEffectInstance(MobEffects.UNLUCK, 6000));
    public static final RegistryObject<Potion> RESISTANCE = register("resistance", "Resistance", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600));
    public static final RegistryObject<Potion> LONG_RESISTANCE = register("long_resistance", "Resistance", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9600));
    public static final RegistryObject<Potion> STRONG_RESISTANCE = register("strong_resistance", "Resistance", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1800, 1));

    public static RegistryObject<Potion> register(String name, String translation, MobEffectInstance... instances) {
        RegistryObject<Potion> potion = POTIONS.register(name, () -> new Potion(name, instances));
        POTION_TRANSLATIONS.put(potion, translation);
        return potion;
    }
}
