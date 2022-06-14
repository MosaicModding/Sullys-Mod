package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.common.item.BlueprintMobBucketItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMItems {
    public static final ItemSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getItemSubHelper();

    //Basic Items
    public static final RegistryObject<Item> RAW_JADE = HELPER.createItem("raw_jade", () -> new Item(Properties.MISC_TAB));
    public static final RegistryObject<Item> POLISHED_JADE = HELPER.createItem("polished_jade", () -> new Item(Properties.MISC_TAB));

    //Food
    public static final RegistryObject<Item> RAW_LANTERNFISH = HELPER.createItem("raw_lanternfish", () -> new Item(Properties.FOOD_TAB.food(Properties.FoodProperties.LANTERNFISH_FOOD)));
    public static final RegistryObject<Item> COOKED_LANTERNFISH = HELPER.createItem("cooked_lanternfish", () -> new Item(Properties.FOOD_TAB.food(Properties.FoodProperties.COOKED_LANTERNFISH_FOOD)));

    //Mob Buckets & Spawn Eggs
    public static final RegistryObject<Item> LANTERNFISH_BUCKET = HELPER.createItem("lanternfish_bucket", () -> Properties.createMobBucketItem(SMEntityTypes.LANTERNFISH::get));
    public static final RegistryObject<ForgeSpawnEggItem> LANTERNFISH_SPAWN_EGG = HELPER.createSpawnEggItem("lanternfish", SMEntityTypes.LANTERNFISH::get, 12829605, 8778172);
    public static final RegistryObject<ForgeSpawnEggItem> TORTOISE_SPAWN_EGG = HELPER.createSpawnEggItem("tortoise", SMEntityTypes.TORTOISE::get, 15198183, 10844478);


    private static final class Properties {
        public static final Item.Properties MISC_TAB = new Item.Properties().tab(CreativeModeTab.TAB_MISC);
        public static final Item.Properties FOOD_TAB = new Item.Properties().tab(CreativeModeTab.TAB_FOOD);

        public static Item createMobBucketItem(Supplier<EntityType<? extends WaterAnimal>> entityType) {
            return new BlueprintMobBucketItem(entityType, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC));
        }

        protected static final class FoodProperties {
            public static final net.minecraft.world.food.FoodProperties LANTERNFISH_FOOD = new net.minecraft.world.food.FoodProperties.Builder().nutrition(2).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 0.1F).build();
            public static final net.minecraft.world.food.FoodProperties COOKED_LANTERNFISH_FOOD = new net.minecraft.world.food.FoodProperties.Builder().nutrition(5).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 0.1F).build();
        }
    }
}
