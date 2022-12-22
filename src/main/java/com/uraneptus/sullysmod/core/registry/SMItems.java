package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.common.item.BlueprintMobBucketItem;
import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.common.item.InjectedItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.items.JadeShieldItem;
import com.uraneptus.sullysmod.common.items.SMFoodBucketItem;
import com.uraneptus.sullysmod.common.items.TortoiseShellItem;
import com.uraneptus.sullysmod.core.other.SMProperties;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMItems {
    public static final ItemSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getItemSubHelper();

    //Basic Items
    public static final RegistryObject<Item> ROUGH_JADE = HELPER.createItem("rough_jade", () -> new InjectedItem(Items.COPPER_INGOT, SMProperties.Items.MISC_TAB));
    public static final RegistryObject<Item> POLISHED_JADE = HELPER.createItem("polished_jade", () -> new InjectedItem(SMItems.ROUGH_JADE.get(), SMProperties.Items.MISC_TAB));
    public static final RegistryObject<Item> MUSIC_DISC_SCOUR = HELPER.createItem("music_disc_scour", () -> new BlueprintRecordItem(12, SMSounds.MUSIC_DISC_SCOUR, SMProperties.Items.MUSIC_DISCS, 4980));
    public static final RegistryObject<Item> TORTOISE_SCUTE = HELPER.createItem("tortoise_scute", () -> new InjectedItem(Items.SCUTE, SMProperties.Items.MISC_TAB));
    public static final RegistryObject<Item> TORTOISE_SHELL = HELPER.createItem("tortoise_shell", () -> new TortoiseShellItem(SMProperties.Items.TOROTISE_SHELL));

    //Tools
    public static final RegistryObject<Item> JADE_SHIELD = HELPER.createItem("jade_shield", () -> new JadeShieldItem(-2.0F, SMProperties.Items.JADE_SHIELD));

    //Food
    public static final RegistryObject<Item> LANTERNFISH = HELPER.createItem("lanternfish", () -> new InjectedItem(Items.COD, SMProperties.Items.FOOD_TAB.food(SMProperties.Foods.LANTERNFISH_FOOD)));
    public static final RegistryObject<Item> COOKED_LANTERNFISH = HELPER.createItem("cooked_lanternfish", () -> new InjectedItem(Items.COOKED_COD, SMProperties.Items.FOOD_TAB.food(SMProperties.Foods.COOKED_LANTERNFISH_FOOD)));

    //Compat Food
    public static final RegistryObject<Item> LANTERNFISH_SLICE = HELPER.createItem("lanternfish_slice", () -> new Item(SMProperties.Items.FD_COMPAT_TAB.food(SMProperties.Foods.LANTERNFISH_SLICE_FOOD)));
    public static final RegistryObject<Item> COOKED_LANTERNFISH_SLICE = HELPER.createItem("cooked_lanternfish_slice", () -> new Item(SMProperties.Items.FD_COMPAT_TAB.food(SMProperties.Foods.COOKED_LANTERNFISH_SLICE_FOOD)));
    public static final RegistryObject<Item> LANTERNFISH_ROLL = HELPER.createItem("lanternfish_roll", () -> new Item(SMProperties.Items.FD_COMPAT_TAB.food(SMProperties.Foods.LANTERNFISH_ROLL_FOOD)));
    public static final RegistryObject<Item> CAVE_CHUM_BUCKET = HELPER.createItem("cave_chum_bucket", () -> new SMFoodBucketItem(SMProperties.Items.FD_COMPAT_TAB.food(SMProperties.Foods.CAVE_CHUM_BUCKET_FOOD)));

    //Mob Buckets & Spawn Eggs
    public static final RegistryObject<Item> LANTERNFISH_BUCKET = HELPER.createItem("lanternfish_bucket", () -> SMItems.createMobBucketItem(SMEntityTypes.LANTERNFISH::get));
    public static final RegistryObject<ForgeSpawnEggItem> LANTERNFISH_SPAWN_EGG = HELPER.createSpawnEggItem("lanternfish", SMEntityTypes.LANTERNFISH::get, 16316617, 9306085);
    public static final RegistryObject<ForgeSpawnEggItem> TORTOISE_SPAWN_EGG = HELPER.createSpawnEggItem("tortoise", SMEntityTypes.TORTOISE::get, 15198183, 10844478);
    public static final RegistryObject<ForgeSpawnEggItem> RASCAL_SPAWN_EGG = HELPER.createSpawnEggItem("rascal", SMEntityTypes.RASCAL::get, 10999507, 3042907);
    public static final RegistryObject<ForgeSpawnEggItem> CHAMELEON_SPAWN_EGG = HELPER.createSpawnEggItem("chameleon", SMEntityTypes.CHAMELEON::get,9804590 ,6645808);

    public static Item createMobBucketItem(Supplier<EntityType<? extends WaterAnimal>> entityType) {
        return new BlueprintMobBucketItem(entityType, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, SMProperties.Items.cannotStack().tab(CreativeModeTab.TAB_MISC));
    }
}
