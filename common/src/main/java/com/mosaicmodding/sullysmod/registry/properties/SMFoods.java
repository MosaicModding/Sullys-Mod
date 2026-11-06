package com.mosaicmodding.sullysmod.registry.properties;

import com.mosaicmodding.sullysmod.registry.SMMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class SMFoods {
    public static final FoodProperties MINIMU_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).effect(new MobEffectInstance(SMMobEffects.MINIMU_EFFECT, 3600, 0, false, false, false), 1).build();
    public static final FoodProperties GIGANTO_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).effect(new MobEffectInstance(SMMobEffects.GIGANTO_EFFECT, 3600, 0, false, false, false), 1).build();
    public static final FoodProperties LANTERNFISH_FOOD = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).effect(new MobEffectInstance(MobEffects.GLOWING, 200), 1.0F).build();
    public static final FoodProperties COOKED_LANTERNFISH_FOOD = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.GLOWING, 200), 1.0F).build();
    public static final FoodProperties PIRANHA_FOOD = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1F).build();
    public static final FoodProperties COOKED_PIRANHA_FOOD = new FoodProperties.Builder().nutrition(7).saturationModifier(0.5F).build();
    public static final FoodProperties PETRIFIED_COOKIE = new FoodProperties.Builder().build();
    public static final FoodProperties CAVE_CARROT = new FoodProperties.Builder().nutrition(6).build();
    public static final FoodProperties BUG_MEAT_FOOD = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).effect(new MobEffectInstance(MobEffects.HUNGER, 600), 1.0F).effect(new MobEffectInstance(MobEffects.CONFUSION, 600), 1.0F).build(); //TODO dog food
    public static final FoodProperties COOKED_BUG_MEAT_FOOD = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 600), 1.0F).build(); //TODO dog food
}
