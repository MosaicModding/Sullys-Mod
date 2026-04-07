package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.items.*;
import com.uraneptus.sullysmod.core.other.SMProperties;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SullysMod.MOD_ID);

    public static List<RegistryObject<? extends Item>> AUTO_TRANSLATE = new ArrayList<>();

    //Basic Items
    public static final RegistryObject<Item> ROUGH_JADE = createItem("rough_jade");
    public static final RegistryObject<Item> JADE = createItem("jade");
    public static final RegistryObject<Item> MUSIC_DISC_SCOUR = createItem("music_disc_scour", () -> new SMRecordItem(12, SMSounds.MUSIC_DISC_SCOUR, SMProperties.Items.MUSIC_DISCS, 4980), true);
    public static final RegistryObject<Item> MUSIC_DISC_SUNKEN_PAST = createItem("music_disc_sunken_past", () -> new SMRecordItem(12, SMSounds.MUSIC_DISC_SUNKEN_PAST, SMProperties.Items.MUSIC_DISCS, 2700), true); //Doesn't have a feature category yet
    public static final RegistryObject<Item> TORTOISE_SCUTE = createItem("tortoise_scute");
    public static final RegistryObject<Item> TORTOISE_SHELL = createItem("tortoise_shell", () -> new TortoiseShellItem(SMProperties.Items.singleStack()));
    public static final RegistryObject<Item> JADE_UPGRADE_SMITHING_TEMPLATE = createItem("jade_upgrade_smithing_template", JadeSmithingTemplateItem::new, true);
    public static final RegistryObject<Item> GLASS_VIAL = createItem("glass_vial", () -> new VialItem(new Item.Properties()));
    public static final RegistryObject<Item> VENOM_VIAL = createItem("venom_vial", () -> new VenomVialItem(new Item.Properties().stacksTo(16)), true);
    public static final RegistryObject<Item> JADE_HORSE_ARMOR = createItem("jade_horse_armor", () -> new HorseArmorItem(9, "jade", SMProperties.Items.singleStack()));
    public static final RegistryObject<Item> PIRANHA_TOOTH = createItem("piranha_tooth");

    //Tools
    public static final RegistryObject<Item> JADE_SHIELD = createItem("jade_shield", () -> new JadeShieldItem(-2.0F, SMProperties.Items.JADE_SHIELD));
    public static final RegistryObject<Item> THROWING_KNIFE = createItem("throwing_knife", () -> new ThrowingKnifeItem(SMProperties.Items.sixteenStack()));

    //Food
    public static final RegistryObject<Item> LANTERNFISH = createItem("lanternfish", SMProperties.Items.food(SMProperties.Foods.LANTERNFISH_FOOD), true);
    public static final RegistryObject<Item> COOKED_LANTERNFISH = createItem("cooked_lanternfish", SMProperties.Items.food(SMProperties.Foods.COOKED_LANTERNFISH_FOOD));
    public static final RegistryObject<Item> PIRANHA = createItem("piranha", SMProperties.Items.food(SMProperties.Foods.PIRANHA_FOOD), true);
    public static final RegistryObject<Item> COOKED_PIRANHA = createItem("cooked_piranha", SMProperties.Items.food(SMProperties.Foods.COOKED_PIRANHA_FOOD));
    public static final RegistryObject<Item> BUG_MEAT = createItem("bug_meat", SMProperties.Items.food(SMProperties.Foods.BUG_MEAT_FOOD));
    public static final RegistryObject<Item> COOKED_BUG_MEAT = createItem("cooked_bug_meat", SMProperties.Items.food(SMProperties.Foods.COOKED_BUG_MEAT_FOOD));

    //Buckets & Spawn Eggs
    public static final RegistryObject<Item> MOLTEN_AMBER_BUCKET = createItem("molten_amber_bucket", () -> new SolidBucketItem(SMBlocks.AMBER.get(), SoundEvents.BUCKET_EMPTY, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> LANTERNFISH_BUCKET = createMobBucketItem("lanternfish_bucket", SMEntityTypes.LANTERNFISH::get);
    public static final RegistryObject<Item> LANTERNFISH_SPAWN_EGG = createSpawnEggItem("lanternfish", SMEntityTypes.LANTERNFISH::get, 0xFCE3D3, 9306085);
    public static final RegistryObject<Item> TORTOISE_SPAWN_EGG = createSpawnEggItem("tortoise", SMEntityTypes.TORTOISE::get, 15198183, 10844478);
    public static final RegistryObject<Item> BOULDERING_ZOMBIE_SPAWN_EGG = createSpawnEggItem("bouldering_zombie", SMEntityTypes.BOULDERING_ZOMBIE::get, 8142370, 4608338);
    public static final RegistryObject<Item> JUNGLE_SPIDER_SPAWN_EGG = createSpawnEggItem("jungle_spider", SMEntityTypes.JUNGLE_SPIDER::get, 5597514, 11013646);
    public static final RegistryObject<Item> PIRANHA_BUCKET = createMobBucketItem("piranha_bucket", SMEntityTypes.PIRANHA::get);
    public static final RegistryObject<Item> PIRANHA_SPAWN_EGG = createSpawnEggItem("piranha", SMEntityTypes.PIRANHA::get, 15561472, 4240022);
    public static final RegistryObject<Item> MAULED_SPAWN_EGG = createSpawnEggItem("mauled", SMEntityTypes.MAULED::get, 8553090, 9468304);

    private static RegistryObject<Item> createSpawnEggItem(String name, Supplier<EntityType<? extends Mob>> supplier, int primaryColor, int secondaryColor) {
        return createItem(name + "_spawn_egg", () -> new ForgeSpawnEggItem(supplier, primaryColor, secondaryColor, new Item.Properties()));
    }

    private static RegistryObject<Item> createMobBucketItem(String name, Supplier<EntityType<? extends WaterAnimal>> entityType) {
        return createItem(name, () -> new MobBucketItem(entityType, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, SMProperties.Items.singleStack()), true);
    }

    private static RegistryObject<Item> createItem(String name, boolean customTranslation) {
        return createItem(name, new Item.Properties(), customTranslation);
    }

    private static RegistryObject<Item> createItem(String name) {
        return createItem(name, new Item.Properties());
    }

    private static RegistryObject<Item> createItem(String name, Item.Properties properties, boolean customTranslation) {
        return createItem(name, () -> new Item(properties), customTranslation);
    }

    private static RegistryObject<Item> createItem(String name, Item.Properties properties) {
        return createItem(name, () -> new Item(properties));
    }

    private static <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier, boolean customTranslation) {
        RegistryObject<I> item = createItem(name, supplier);
        if (customTranslation) AUTO_TRANSLATE.remove(item);
        return item;
    }

    public static <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier) {
        RegistryObject<I> item = ITEMS.register(name, supplier);
        AUTO_TRANSLATE.add(item);
        return item;
    }
}
