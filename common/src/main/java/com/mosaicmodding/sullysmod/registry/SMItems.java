package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.registry.properties.SMFoods;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.level.material.Fluids;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;
import static com.mosaicmodding.sullysmod.registry.properties.SMItemProperties.*;

public class SMItems {

    public static final RegSupplier<Item> BOULDERING_ZOMBIE_SPAWN_EGG = RegHelper.registerItem(path("bouldering_zombie_spawn_egg"), () -> PlatHelper.newSpawnEgg(SMEntityTypes.BOULDERING_ZOMBIE, 0x7C3E22, 0x465152, new Item.Properties()));


    public static final RegSupplier<Item> MOLTEN_AMBER_BUCKET = RegHelper.registerItem(path("molten_amber_bucket"), () -> new SolidBucketItem(SMBlocks.AMBER.get(), SoundEvents.BUCKET_EMPTY, new Item.Properties().stacksTo(1)));

    public static final RegSupplier<Item> MINIMU_SLICE = RegHelper.registerItem(path("minimu_slice"), () -> foodItem(SMFoods.MINIMU_SLICE));
    public static final RegSupplier<Item> MINIMU_SEEDS = RegHelper.registerItem(path("minimu_seeds"), SMItems::basicItem);
    public static final RegSupplier<Item> GIGANTO_SLICE = RegHelper.registerItem(path("giganto_slice"), () -> foodItem(SMFoods.GIGANTO_SLICE));
    public static final RegSupplier<Item> GIGANTO_SEEDS = RegHelper.registerItem(path("giganto_seeds"), SMItems::basicItem);

    public static final RegSupplier<Item> LANTERNFISH_SPAWN_EGG = RegHelper.registerItem(path("lanternfish_spawn_egg"), () -> PlatHelper.newSpawnEgg(SMEntityTypes.LANTERNFISH, 0xFCE3D3, 0x8DFFE5, new Item.Properties()));
    public static final RegSupplier<Item> LANTERNFISH_BUCKET = RegHelper.registerItem(path("lanternfish_bucket"), () -> new MobBucketItem(SMEntityTypes.LANTERNFISH.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, SINGLE_STACK()));
    public static final RegSupplier<Item> LANTERNFISH = RegHelper.registerItem(path("lanternfish"), () -> foodItem(SMFoods.LANTERNFISH_FOOD));
    public static final RegSupplier<Item> COOKED_LANTERNFISH = RegHelper.registerItem(path("cooked_lanternfish"), () -> foodItem(SMFoods.COOKED_LANTERNFISH_FOOD));

    private static Item basicItem() {
        return new Item(DEFAULT());
    }

    private static Item foodItem(FoodProperties props) {
        return new Item(FOOD(props));
    }

    public static void init() {}
}
