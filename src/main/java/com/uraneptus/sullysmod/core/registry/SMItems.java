package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.items.*;
import com.uraneptus.sullysmod.core.other.SMArmorMaterials;
import com.uraneptus.sullysmod.core.other.SMProperties;
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import com.uraneptus.sullysmod.core.other.SMTextUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SullysMod.MOD_ID);

    public static List<RegistryObject<? extends Item>> AUTO_TRANSLATE = new ArrayList<>();

    //We have this here so KubeJS can access it!
    public static final Rarity ANCIENT = Rarity.create("sullysmod:ancient", style -> style.withColor(15107584));

    //Basic Items
    public static final RegistryObject<Item> ROUGH_JADE = createItem("rough_jade");
    public static final RegistryObject<Item> JADE = createItem("jade");
    public static final RegistryObject<Item> MUSIC_DISC_SCOUR = createItem("music_disc_scour", () -> new SMRecordItem(12, SMSounds.MUSIC_DISC_SCOUR, SMProperties.Items.MUSIC_DISCS, 4980), true);
    public static final RegistryObject<Item> MUSIC_DISC_SUNKEN_PAST = createItem("music_disc_sunken_past", () -> new SMRecordItem(12, SMSounds.MUSIC_DISC_SUNKEN_PAST, SMProperties.Items.MUSIC_DISCS, 2700), true); //Doesn't have a feature category yet
    public static final RegistryObject<Item> TORTOISE_SCUTE = createItem("tortoise_scute");
    public static final RegistryObject<Item> TORTOISE_SHELL = createItem("tortoise_shell", () -> new TortoiseShellItem(PropertyUtil.stacksOnce()));
    public static final RegistryObject<Item> JADE_UPGRADE_SMITHING_TEMPLATE = createItem("jade_upgrade_smithing_template", JadeSmithingTemplateItem::new, true);
    public static final RegistryObject<Item> GLASS_VIAL = createItem("glass_vial", () -> new VialItem(new Item.Properties()));
    public static final RegistryObject<Item> VENOM_VIAL = createItem("venom_vial", () -> new VenomVialItem(new Item.Properties().stacksTo(16)), true);
    public static final RegistryObject<Item> JADE_HORSE_ARMOR = createItem("jade_horse_armor", () -> new HorseArmorItem(9, "jade", PropertyUtil.stacksOnce()));
    public static final RegistryObject<Item> PIRANHA_TOOTH = createItem("piranha_tooth");

    //Tools
    public static final RegistryObject<Item> JADE_SHIELD = createItem("jade_shield", () -> new JadeShieldItem(-2.0F, SMProperties.Items.JADE_SHIELD));
    public static final RegistryObject<Item> THROWING_KNIFE = createItem("throwing_knife", () -> new ThrowingKnifeItem(SMProperties.Items.sixteenStack()));

    //Food
    public static final RegistryObject<Item> LANTERNFISH = createItem("lanternfish", PropertyUtil.food(SMProperties.Foods.LANTERNFISH_FOOD), true);
    public static final RegistryObject<Item> COOKED_LANTERNFISH = createItem("cooked_lanternfish", PropertyUtil.food(SMProperties.Foods.COOKED_LANTERNFISH_FOOD));
    public static final RegistryObject<Item> PIRANHA = createItem("piranha", PropertyUtil.food(SMProperties.Foods.PIRANHA_FOOD), true);
    public static final RegistryObject<Item> COOKED_PIRANHA = createItem("cooked_piranha", PropertyUtil.food(SMProperties.Foods.COOKED_PIRANHA_FOOD));

    //Buckets & Spawn Eggs
    public static final RegistryObject<Item> MOLTEN_AMBER_BUCKET = createItem("molten_amber_bucket", () -> new BucketItem(SMFluids.SOURCE_MOLTEN_AMBER, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> LANTERNFISH_BUCKET = createMobBucketItem("lanternfish_bucket", SMEntityTypes.LANTERNFISH::get);
    public static final RegistryObject<Item> LANTERNFISH_SPAWN_EGG = createSpawnEggItem("lanternfish", SMEntityTypes.LANTERNFISH::get, 0xFCE3D3, 9306085);
    public static final RegistryObject<Item> TORTOISE_SPAWN_EGG = createSpawnEggItem("tortoise", SMEntityTypes.TORTOISE::get, 15198183, 10844478);
    public static final RegistryObject<Item> BOULDERING_ZOMBIE_SPAWN_EGG = createSpawnEggItem("bouldering_zombie", SMEntityTypes.BOULDERING_ZOMBIE::get, 8142370, 4608338);
    public static final RegistryObject<Item> JUNGLE_SPIDER_SPAWN_EGG = createSpawnEggItem("jungle_spider", SMEntityTypes.JUNGLE_SPIDER::get, 5597514, 11013646);
    public static final RegistryObject<Item> PIRANHA_BUCKET = createMobBucketItem("piranha_bucket", SMEntityTypes.PIRANHA::get);
    public static final RegistryObject<Item> PIRANHA_SPAWN_EGG = createSpawnEggItem("piranha", SMEntityTypes.PIRANHA::get, 15561472, 4240022);

    //Artifacts
    public static Map<RegistryObject<Item>, Component> ARTIFACT_DESC_MAP = new HashMap<>();
    public static Map<Supplier<Item>, Integer> TRADES = new HashMap<>();

    public static final RegistryObject<Item> BROKEN_VASE = registerArtifact("broken_vase", "A large piece of the side is missing", 10);
    public static final RegistryObject<Item> PRIMITIVE_KNIFE = registerArtifact("primitive_knife", "A small knife made from obsidian", () -> new ArtifactWeaponItem(5, -2.5F, null, SMProperties.Items.artifacts().stacksTo(1).durability(20)), 15);
    public static final RegistryObject<Item> MINERS_HELMET = registerArtifact("miners_helmet", "Looks like it’s previous owner couldn’t get the candle lit anymore",
            () -> new MinersHelmetItem(SMProperties.Items.artifacts().stacksTo(1)), 15);
    public static final RegistryObject<Item> SMALL_DENTED_HELMET = registerArtifact("small_dented_helmet", "A small rusty helmet. Barely fits",
            () -> new ArtifactHelmetItem(SMArmorMaterials.SMALL_DENTED_HELMET, SMProperties.Items.artifacts().stacksTo(1)), 22);
    public static final RegistryObject<Item> LOST_CROWN = registerArtifact("lost_crown", "Once belonged to the king of a now fallen kingdom",
            () -> new ArtifactHelmetItem(SMArmorMaterials.LOST_CROWN, SMProperties.Items.artifacts().stacksTo(1)), 30);
    public static final RegistryObject<Item> JADE_AMULET = registerArtifact("jade_amulet", "A creature is carefully sculpted from the stone", 20);
    public static final RegistryObject<Item> PRIMITIVE_RING = registerArtifact("primitive_ring", "A roughly made metal ring", 10);
    public static final RegistryObject<Item> RUSTY_TOOLS = registerArtifact("rusty_tools", "Maybe their owners are still out there", 9);
    public static final RegistryObject<Item> BROKEN_BOWL = registerArtifact("broken_bowl", "A large crack runs down the edge", 9);
    public static final RegistryObject<Item> COPPER_COG = registerArtifact("copper_cog", "Said to have been part of living creatures", 23);
    public static final RegistryObject<Item> PETRIFIED_COOKIE = registerArtifact("petrified_cookie", "Petrified food is still food, just extra crisp", () -> new Item(SMProperties.Items.artifacts().food(SMProperties.Foods.PETRIFIED_COOKIE)), 12);
    public static final RegistryObject<Item> ARROWHEAD = registerArtifact("arrowhead", "The tip of an ancient arrow", 5);
    public static final RegistryObject<Item> DEATH_WHISTLE = registerArtifact("death_whistle", "Screeches horrible noises when blown into", DeathWhistleItem::new, 20);
    public static final RegistryObject<Item> OMINOUS_TABLET = registerArtifact("ominous_tablet", "A dark figure is carved into the stone", 25);
    public static final RegistryObject<Item> MOON_TABLET = registerArtifact("moon_tablet", "Has a carved image of the moon", 27);
    public static final RegistryObject<Item> STONE_IDOL = registerArtifact("stone_idol", "Almost looks alive", 20);
    public static final RegistryObject<Item> RED_CAP = registerArtifact("red_cap", "A tiny red cap. It’s too small to wear and the fabric feels strange", 20);
    public static final RegistryObject<Item> DRIED_CYAN_FLOWER = registerArtifact("dried_cyan_flower", "A delicate cyan flower that feels strangely familiar", 21);
    public static final RegistryObject<Item> DRIED_RED_FLOWER = registerArtifact("dried_red_flower", "A delicate red flower that feels like home", 22);
    public static final RegistryObject<Item> METALLIC_SKULL = registerArtifact("metallic_skull", "The a metallic skull attached to broken off bars", 25);
    public static final RegistryObject<Item> LOST_BAG = registerArtifact("lost_bag", "A small lightweight bag sloppily sewn together", 14);
    public static final RegistryObject<Item> MYSTERIOUS_PLATE = registerArtifact("mysterious_plate", "Made from an unknown material", 35);
    public static final RegistryObject<Item> FAMILIAR_CUBE = registerArtifact("familiar_cube", "Hot to the touch and has strange growths on it", 30);
    public static final RegistryObject<Item> AMBER_ENCASED_BUG = registerArtifact("amber_encased_bug", "A small bug that was covered by tree sap ages ago", 20);
    public static final RegistryObject<Item> FOSSILISED_SHELLS = registerArtifact("fossilised_shells", "Shells from a sea creature that lived long ago", 27);
    public static final RegistryObject<Item> FOSSILISED_BONE = registerArtifact("fossilised_bone", "A large bone of an extinct creature", 27);
    public static final RegistryObject<Item> FOSSILISED_FOOTSTEP = registerArtifact("fossilised_footstep", "An ancient footprint that never faded", 25);
    public static final RegistryObject<Item> FOSSILISED_FISH = registerArtifact("fossilised_fish", "The bones of a small fish from times past", 27);
    public static final RegistryObject<Item> TORN_MANUSCRIPT = registerArtifact("torn_manuscript", "Part of a manuscript with an unknown language", 18);
    public static final RegistryObject<Item> LOST_JOURNAL = registerArtifact("lost_journal", "Waterlogged and left unreadable, it has a few pages ripped out", 17);
    public static final RegistryObject<Item> LOST_SKETCHBOOK = registerArtifact("lost_sketchbook", "A small book with scratchy drawings of an unknown large mouthed biped", 24);
    public static final RegistryObject<Item> LOST_RECIPE_BOOK = registerArtifact("lost_recipe_book", "Mostly ruined and unreadable, but still has some recipes inside.", 12);
    public static final RegistryObject<Item> GOLDEN_BELT_BUCKLE = registerArtifact("golden_belt_buckle", "Has a peculiar shape and it’s leather feels strange", 26);
    public static final RegistryObject<Item> DEEPSLATE_VASE = registerArtifact("deepslate_vase", "Who would’ve needed a vase made from deepslate?", 25);
    public static final RegistryObject<Item> SMALL_GEODE = registerArtifact("small_geode", "Kind of cute", 23);
    public static final RegistryObject<Item> TORN_CLOTH = registerArtifact("torn_cloth", "A dirty torn off piece of clothing", 6);
    public static final RegistryObject<Item> GOLDEN_GOBLET = registerArtifact("golden_goblet", "An old but beautiful chalice made by a skilled goldsmith", 29);
    public static final RegistryObject<Item> EMERALD_EARRING = registerArtifact("emerald_earring", "Besides the beautiful emerald, it looks sloppily put together", 17);
    public static final RegistryObject<Item> BROKEN_BOTTLE = registerArtifact("broken_bottle", "The top half of a bottle", () -> new ArtifactWeaponItem(4, -1.2F, SMSounds.BROKEN_BOTTLE_SHATTERS, SMProperties.Items.artifacts().stacksTo(1).durability(1)), 5);
    public static final RegistryObject<Item> FROG_IDOL = registerArtifact("frog_idol", "Everybody likes frogs", 29);

    private static RegistryObject<Item> registerArtifact(String name, String description, int price) {
        return registerArtifact(name, description, () -> new Item(SMProperties.Items.artifacts()), price);
    }

    private static RegistryObject<Item> registerArtifact(String name, String description, Supplier<? extends Item> item, int price) {
        RegistryObject<Item> object = createItem(name, item, true);
        ARTIFACT_DESC_MAP.put(object, SMTextUtil.addSMTranslatable("artifact." + name + ".desc", description).withStyle(SMTextDefinitions.ARTIFACT_DESC_STYLE));
        TRADES.put(object, price);
        return object;
    }

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
