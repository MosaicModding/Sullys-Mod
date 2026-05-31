package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

public class SMPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, SullysMod.MOD_ID);
    public static Map<Holder<? extends Potion>, String> POTION_TRANSLATIONS = new HashMap<>();

    public static final Holder<Potion> UNLUCK = register("unluck", "Bad Luck", new MobEffectInstance(MobEffects.UNLUCK, 6000));
    public static final Holder<Potion> RESISTANCE = register("resistance", "Resistance", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1800));
    public static final Holder<Potion> LONG_RESISTANCE = register("long_resistance", "Resistance", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600));
    public static final Holder<Potion> STRONG_RESISTANCE = register("strong_resistance", "Resistance", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1000, 1));

    public static Holder<Potion> register(String name, String translation, MobEffectInstance... instances) {
        Holder<Potion> potion = POTIONS.register(name, () -> new Potion(name, instances));
        POTION_TRANSLATIONS.put(potion, translation);
        return potion;
    }
}
