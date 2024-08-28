package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.client.model.MinersHelmetModel;
import com.uraneptus.sullysmod.common.items.*;
import com.uraneptus.sullysmod.core.other.SMArmorMaterials;
import com.uraneptus.sullysmod.core.other.SMProperties;
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import com.uraneptus.sullysmod.core.other.SMTextUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.uraneptus.sullysmod.core.registry.SMBlocks.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class SMItems {
    public static final ItemSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getItemSubHelper();

    //Basic Items
    public static final RegistryObject<Item> ROUGH_JADE = HELPER.createItem("rough_jade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JADE = HELPER.createItem("jade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MUSIC_DISC_SCOUR = HELPER.createItem("music_disc_scour", () -> new BlueprintRecordItem(12, SMSounds.MUSIC_DISC_SCOUR, SMProperties.Items.MUSIC_DISCS, 4980));
    public static final RegistryObject<Item> MUSIC_DISC_SUNKEN_PAST = HELPER.createItem("music_disc_sunken_past", () -> new BlueprintRecordItem(12, SMSounds.MUSIC_DISC_SUNKEN_PAST, SMProperties.Items.MUSIC_DISCS, 2700));
    public static final RegistryObject<Item> TORTOISE_SCUTE = HELPER.createItem("tortoise_scute", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TORTOISE_SHELL = HELPER.createItem("tortoise_shell", () -> new TortoiseShellItem(PropertyUtil.stacksOnce()));
    public static final RegistryObject<Item> JADE_UPGRADE_SMITHING_TEMPLATE = HELPER.createItem("jade_upgrade_smithing_template", JadeSmithingTemplateItem::new);
    public static final RegistryObject<Item> GLASS_VIAL = HELPER.createItem("glass_vial", () -> new VialItem(new Item.Properties()));
    public static final RegistryObject<Item> VENOM_VIAL = HELPER.createItem("venom_vial", () -> new VenomVialItem(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> JADE_HORSE_ARMOR = HELPER.createItem("jade_horse_armor", () -> new HorseArmorItem(9, "jade", PropertyUtil.stacksOnce()));
    public static final RegistryObject<Item> PIRANHA_TOOTH = HELPER.createItem("piranha_tooth", () -> new Item(new Item.Properties()));

    //Tools
    public static final RegistryObject<Item> JADE_SHIELD = HELPER.createItem("jade_shield", () -> new JadeShieldItem(-2.0F, SMProperties.Items.JADE_SHIELD));
    public static final RegistryObject<Item> THROWING_KNIFE = HELPER.createItem("throwing_knife", () -> new ThrowingKnifeItem(SMProperties.Items.sixteenStack()));

    //Food
    public static final RegistryObject<Item> LANTERNFISH = HELPER.createItem("lanternfish", () -> new Item(PropertyUtil.food(SMProperties.Foods.LANTERNFISH_FOOD)));
    public static final RegistryObject<Item> COOKED_LANTERNFISH = HELPER.createItem("cooked_lanternfish", () -> new Item(PropertyUtil.food(SMProperties.Foods.COOKED_LANTERNFISH_FOOD)));
    public static final RegistryObject<Item> PIRANHA = HELPER.createItem("piranha", () -> new Item(PropertyUtil.food(SMProperties.Foods.PIRANHA_FOOD)));
    public static final RegistryObject<Item> COOKED_PIRANHA = HELPER.createItem("cooked_piranha", () -> new Item(PropertyUtil.food(SMProperties.Foods.COOKED_PIRANHA_FOOD)));

    //Buckets & Spawn Eggs
    public static final RegistryObject<Item> MOLTEN_AMBER_BUCKET = HELPER.createItem("molten_amber_bucket", () -> new BucketItem(SMFluids.SOURCE_MOLTEN_AMBER, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> LANTERNFISH_BUCKET = HELPER.createItem("lanternfish_bucket", () -> SMItems.createMobBucketItem(SMEntityTypes.LANTERNFISH::get));
    public static final RegistryObject<ForgeSpawnEggItem> LANTERNFISH_SPAWN_EGG = HELPER.createSpawnEggItem("lanternfish", SMEntityTypes.LANTERNFISH::get, 16316617, 9306085);
    public static final RegistryObject<ForgeSpawnEggItem> TORTOISE_SPAWN_EGG = HELPER.createSpawnEggItem("tortoise", SMEntityTypes.TORTOISE::get, 15198183, 10844478);
    public static final RegistryObject<ForgeSpawnEggItem> BOULDERING_ZOMBIE_SPAWN_EGG = HELPER.createSpawnEggItem("bouldering_zombie", SMEntityTypes.BOULDERING_ZOMBIE::get, 8142370, 4608338);
    public static final RegistryObject<ForgeSpawnEggItem> JUNGLE_SPIDER_SPAWN_EGG = HELPER.createSpawnEggItem("jungle_spider", SMEntityTypes.JUNGLE_SPIDER::get, 5597514, 11013646);
    public static final RegistryObject<Item> PIRANHA_BUCKET = HELPER.createItem("piranha_bucket", () -> SMItems.createMobBucketItem(SMEntityTypes.PIRANHA::get));
    public static final RegistryObject<ForgeSpawnEggItem> PIRANHA_SPAWN_EGG = HELPER.createSpawnEggItem("piranha", SMEntityTypes.PIRANHA::get, 15561472, 4240022);

    //Artifacts
    public static Map<RegistryObject<Item>, Component> ARTIFACT_DESC_MAP = new HashMap<>();
    public static Map<Supplier<Item>, Integer> TRADES = new HashMap<>();

    public static final RegistryObject<Item> BROKEN_VASE = registerArtifact("broken_vase", "A large piece of the side is missing", 10);
    public static final RegistryObject<Item> PRIMITIVE_KNIFE = registerArtifact("primitive_knife", "A small knife made from obsidian", () -> new ArtifactWeaponItem(5, -2.5F, null, SMProperties.Items.artifacts().durability(20)), 15);
    public static final RegistryObject<Item> MINERS_HELMET = registerArtifact("miners_helmet", "Looks like it’s previous owner couldn’t get the candle lit anymore",
            () -> new ArtifactHelmetItem(SMArmorMaterials.MINERS_HELMET, SMProperties.Items.artifacts(), MinersHelmetModel.INSTANCE), 15);
    public static final RegistryObject<Item> SMALL_DENTED_HELMET = registerArtifact("small_dented_helmet", "A small rusty helmet. Barely fits",
            () -> new ArtifactHelmetItem(SMArmorMaterials.SMALL_DENTED_HELMET, SMProperties.Items.artifacts()), 22);
    public static final RegistryObject<Item> LOST_CROWN = registerArtifact("lost_crown", "Once belonged to the king of a now fallen kingdom",
            () -> new ArtifactHelmetItem(SMArmorMaterials.LOST_CROWN, SMProperties.Items.artifacts()), 30);
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
    public static final RegistryObject<Item> BROKEN_BOTTLE = registerArtifact("broken_bottle", "The top half of a bottle", () -> new ArtifactWeaponItem(4, -1.2F, SMSounds.BROKEN_BOTTLE_SHATTERS, SMProperties.Items.artifacts().durability(1)), 5);
    public static final RegistryObject<Item> FROG_IDOL = registerArtifact("frog_idol", "Everybody likes frogs", 29);

    public static RegistryObject<Item> registerArtifact(String name, String description, int price) {
        return registerArtifact(name, description, () -> new Item(SMProperties.Items.artifacts()), price);
    }

    public static RegistryObject<Item> registerArtifact(String name, String description, Supplier<? extends Item> item, int price) {
        RegistryObject<Item> object = HELPER.createItem(name, item);
        ARTIFACT_DESC_MAP.put(object, SMTextUtil.addSMTranslatable("artifact." + name + ".desc", description).withStyle(SMTextDefinitions.ARTIFACT_DESC_STYLE));
        TRADES.put(object, price);
        return object;
    }

    public static Item createMobBucketItem(Supplier<EntityType<? extends WaterAnimal>> entityType) {
        return new MobBucketItem(entityType, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, PropertyUtil.stacksOnce());
    }

    public static void buildCreativeTabContents() {
        CreativeModeTabContentsPopulator.mod(SullysMod.MOD_ID)
                .tab(CreativeModeTabs.INGREDIENTS)
                .addItemsAfter(of(Items.COPPER_INGOT), JADE)
                .addItemsAfter(of(Items.RAW_COPPER), ROUGH_JADE)
                .addItemsAfter(of(Items.SCUTE), TORTOISE_SCUTE)
                .addItemsAfter(of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), JADE_UPGRADE_SMITHING_TEMPLATE)
                .addItemsAfter(of(Items.GLASS_BOTTLE), GLASS_VIAL)
                .addItemsBefore(of(Items.BONE), PIRANHA_TOOTH)

                .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .addItemsAfter(of(Items.MUSIC_DISC_RELIC), MUSIC_DISC_SCOUR, MUSIC_DISC_SUNKEN_PAST)
                .addItemsAfter(of(Items.COD_BUCKET), LANTERNFISH_BUCKET, PIRANHA_BUCKET)
                .addItemsAfter(of(Items.SADDLE), TORTOISE_SHELL)

                .tab(CreativeModeTabs.FOOD_AND_DRINKS)
                .addItemsAfter(of(Items.COOKED_COD), LANTERNFISH, COOKED_LANTERNFISH, PIRANHA, COOKED_PIRANHA)

                .tab(CreativeModeTabs.COMBAT)
                .addItemsAfter(of(Items.SHIELD), JADE_SHIELD)
                .addItemsAfter(of(Items.DIAMOND_HORSE_ARMOR), JADE_HORSE_ARMOR)
                .addItemsAfter(of(Items.TRIDENT), THROWING_KNIFE)

                .tab(CreativeModeTabs.SPAWN_EGGS) //Sorted alphabetically
                .addItemsAfter(of(Items.TADPOLE_SPAWN_EGG), TORTOISE_SPAWN_EGG)
                .addItemsAfter(of(Items.BLAZE_SPAWN_EGG), BOULDERING_ZOMBIE_SPAWN_EGG)
                .addItemsAfter(of(Items.IRON_GOLEM_SPAWN_EGG), JUNGLE_SPIDER_SPAWN_EGG, LANTERNFISH_SPAWN_EGG)
                .addItemsAfter(of(Items.PILLAGER_SPAWN_EGG), PIRANHA_SPAWN_EGG)

                .tab(CreativeModeTabs.NATURAL_BLOCKS)
                .addItemsAfter(of(Items.COPPER_ORE), JADE_ORE)
                .addItemsAfter(of(Items.DEEPSLATE_COPPER_ORE), DEEPSLATE_JADE_ORE)
                .addItemsAfter(of(Items.RAW_COPPER_BLOCK), ROUGH_JADE_BLOCK)
                .addItemsAfter(of(Items.TURTLE_EGG), TORTOISE_EGG)
                .addItemsAfter(of(Items.CHERRY_LOG), PETRIFIED_LOG)
                .addItemsAfter(of(Items.CHERRY_SAPLING), PETRIFIED_SAPLING)
                .addItemsAfter(of(Items.COBWEB), AMBER, ROUGH_AMBER)

                .tab(CreativeModeTabs.BUILDING_BLOCKS)
                .addItemsAfter(of(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB),
                        ROUGH_JADE_BLOCK, ROUGH_JADE_BRICKS, ROUGH_JADE_BRICK_STAIRS, ROUGH_JADE_BRICK_SLAB, ROUGH_JADE_BRICK_WALL,
                        JADE_BLOCK, JADE_BRICKS, JADE_BRICK_STAIRS, JADE_BRICK_SLAB, JADE_BRICK_WALL,
                        JADE_PILLAR, CHISELED_JADE, JADE_TOTEM, ROUGH_AMBER, AMBER_LANTERN, CHISELED_AMBER, AMBER_PILLAR, AMBER_BRICKS,
                        AMBER_BRICK_STAIRS, AMBER_BRICK_SLAB, AMBER_BRICK_WALL)
                .addItemsAfter(of(Items.CHERRY_BUTTON), PETRIFIED_LOG, PETRIFIED_WOOD, STRIPPED_PETRIFIED_LOG, STRIPPED_PETRIFIED_WOOD,
                        PETRIFIED_PLANKS, PETRIFIED_STAIRS, PETRIFIED_SLAB, PETRIFIED_FENCE, PETRIFIED_FENCE_GATE, PETRIFIED_DOOR, PETRIFIED_TRAPDOOR,
                        PETRIFIED_PRESSURE_PLATE, PETRIFIED_BUTTON)

                .tab(CreativeModeTabs.REDSTONE_BLOCKS)
                .addItemsAfter(of(Items.DROPPER), JADE_FLINGER_TOTEM)
                .addItemsAfter(of(Items.STONE_BUTTON), COPPER_BUTTON, EXPOSED_COPPER_BUTTON, WEATHERED_COPPER_BUTTON, OXIDIZED_COPPER_BUTTON,
                        WAXED_COPPER_BUTTON, WAXED_EXPOSED_COPPER_BUTTON, WAXED_WEATHERED_COPPER_BUTTON, WAXED_OXIDIZED_COPPER_BUTTON)

                .tab(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .addItemsAfter(of(Items.CHERRY_HANGING_SIGN), PETRIFIED_SIGN.getFirst(), PETRIFIED_HANGING_SIGN.getFirst())
                .addItemsAfter(of(Items.INFESTED_DEEPSLATE), AMBER)
                .addItemsAfter(of(Items.ARMOR_STAND), ITEM_STAND)

        ;
    }
}
