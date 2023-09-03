package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.items.JadeShieldItem;
import com.uraneptus.sullysmod.common.items.SMFoodBucketItem;
import com.uraneptus.sullysmod.common.items.TortoiseShellItem;
import com.uraneptus.sullysmod.core.integration.fd.FDCompat;
import com.uraneptus.sullysmod.core.other.SMProperties;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.minecraft.world.item.crafting.Ingredient.of;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMItems {
    public static final ItemSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getItemSubHelper();

    //Basic Items
    public static final RegistryObject<Item> ROUGH_JADE = HELPER.createItem("rough_jade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_JADE = HELPER.createItem("polished_jade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MUSIC_DISC_SCOUR = HELPER.createItem("music_disc_scour", () -> new BlueprintRecordItem(12, SMSounds.MUSIC_DISC_SCOUR, SMProperties.Items.MUSIC_DISCS, 4980));
    public static final RegistryObject<Item> TORTOISE_SCUTE = HELPER.createItem("tortoise_scute", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TORTOISE_SHELL = HELPER.createItem("tortoise_shell", () -> new TortoiseShellItem(SMProperties.Items.TOROTISE_SHELL));

    //Tools
    public static final RegistryObject<Item> JADE_SHIELD = HELPER.createItem("jade_shield", () -> new JadeShieldItem(-2.0F, SMProperties.Items.JADE_SHIELD));

    //Food
    public static final RegistryObject<Item> LANTERNFISH = HELPER.createItem("lanternfish", () -> new Item(new Item.Properties().food(SMProperties.Foods.LANTERNFISH_FOOD))); // after cod
    public static final RegistryObject<Item> COOKED_LANTERNFISH = HELPER.createItem("cooked_lanternfish", () -> new Item(new Item.Properties().food(SMProperties.Foods.COOKED_LANTERNFISH_FOOD))); // after cooked cod

    //Compat Food

    public static final RegistryObject<Item> LANTERNFISH_SLICE = HELPER.createItem("lanternfish_slice", () -> new Item(SMProperties.Items.FD_COMPAT_TAB.food(SMProperties.Foods.LANTERNFISH_SLICE_FOOD)));
    public static final RegistryObject<Item> COOKED_LANTERNFISH_SLICE = HELPER.createItem("cooked_lanternfish_slice", () -> new Item(SMProperties.Items.FD_COMPAT_TAB.food(SMProperties.Foods.COOKED_LANTERNFISH_SLICE_FOOD)));
    public static final RegistryObject<Item> LANTERNFISH_ROLL = HELPER.createItem("lanternfish_roll", () -> new Item(SMProperties.Items.FD_COMPAT_TAB.food(SMProperties.Foods.LANTERNFISH_ROLL_FOOD)));
    public static final RegistryObject<Item> CAVE_CHUM_BUCKET = HELPER.createItem("cave_chum_bucket", () -> new SMFoodBucketItem(SMProperties.Items.CAVE_CHUM_BUCKET));


    //Mob Buckets & Spawn Eggs
    public static final RegistryObject<Item> LANTERNFISH_BUCKET = HELPER.createItem("lanternfish_bucket", () -> SMItems.createMobBucketItem(SMEntityTypes.LANTERNFISH::get));
    public static final RegistryObject<ForgeSpawnEggItem> LANTERNFISH_SPAWN_EGG = HELPER.createSpawnEggItem("lanternfish", SMEntityTypes.LANTERNFISH::get, 16316617, 9306085);
    public static final RegistryObject<ForgeSpawnEggItem> TORTOISE_SPAWN_EGG = HELPER.createSpawnEggItem("tortoise", SMEntityTypes.TORTOISE::get, 15198183, 10844478);

    public static Item createMobBucketItem(Supplier<EntityType<? extends WaterAnimal>> entityType) {
        return new MobBucketItem(entityType, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, SMProperties.Items.cannotStack());
    }

    public static void buildCreativeTabContents() {
        CreativeModeTabContentsPopulator.mod(SullysMod.MOD_ID)
                .tab(CreativeModeTabs.INGREDIENTS)
                .addItemsAfter(of(Items.COPPER_INGOT), ROUGH_JADE, POLISHED_JADE)
                .addItemsAfter(of(Items.SCUTE), TORTOISE_SCUTE, TORTOISE_SHELL)
                .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .addItemsAfter(of(Items.MUSIC_DISC_RELIC), MUSIC_DISC_SCOUR)
                .addItemsAfter(of(Items.COD_BUCKET), LANTERNFISH_BUCKET)
                .tab(CreativeModeTabs.FOOD_AND_DRINKS)
                .addItemsAfter(of(Items.COD), LANTERNFISH, FDCompat.IS_LOADED ? LANTERNFISH_SLICE : ItemStack.EMPTY::getItem)
                .addItemsAfter(of(LANTERNFISH_SLICE.get()), FDCompat.IS_LOADED ? LANTERNFISH_ROLL : ItemStack.EMPTY::getItem)
                .addItemsAfter(of(Items.COOKED_COD), COOKED_LANTERNFISH, FDCompat.IS_LOADED ? COOKED_LANTERNFISH_SLICE : ItemStack.EMPTY::getItem)
                .addItems(FDCompat.IS_LOADED ? CAVE_CHUM_BUCKET : ItemStack.EMPTY::getItem)
                .tab(CreativeModeTabs.COMBAT)
                .addItemsAfter(of(Items.SHIELD), JADE_SHIELD)
                .tab(CreativeModeTabs.SPAWN_EGGS)
                .addItemsAfter(of(Items.COD_SPAWN_EGG), LANTERNFISH_SPAWN_EGG)
                .addItemsAfter(of(Items.TURTLE_SPAWN_EGG), TORTOISE_SPAWN_EGG);


    }
}
