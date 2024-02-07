package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.items.*;
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

import static com.uraneptus.sullysmod.core.registry.SMBlocks.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMItems {
    public static final ItemSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getItemSubHelper();

    //Basic Items
    public static final RegistryObject<Item> ROUGH_JADE = HELPER.createItem("rough_jade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_JADE = HELPER.createItem("polished_jade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MUSIC_DISC_SCOUR = HELPER.createItem("music_disc_scour", () -> new BlueprintRecordItem(12, SMSounds.MUSIC_DISC_SCOUR, SMProperties.Items.MUSIC_DISCS, 4980));
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

    //Mob Buckets & Spawn Eggs
    public static final RegistryObject<Item> LANTERNFISH_BUCKET = HELPER.createItem("lanternfish_bucket", () -> SMItems.createMobBucketItem(SMEntityTypes.LANTERNFISH::get));
    public static final RegistryObject<ForgeSpawnEggItem> LANTERNFISH_SPAWN_EGG = HELPER.createSpawnEggItem("lanternfish", SMEntityTypes.LANTERNFISH::get, 16316617, 9306085);
    public static final RegistryObject<ForgeSpawnEggItem> TORTOISE_SPAWN_EGG = HELPER.createSpawnEggItem("tortoise", SMEntityTypes.TORTOISE::get, 15198183, 10844478);
    public static final RegistryObject<ForgeSpawnEggItem> BOULDERING_ZOMBIE_SPAWN_EGG = HELPER.createSpawnEggItem("bouldering_zombie", SMEntityTypes.BOULDERING_ZOMBIE::get, 8142370, 4608338);
    public static final RegistryObject<ForgeSpawnEggItem> JUNGLE_SPIDER_SPAWN_EGG = HELPER.createSpawnEggItem("jungle_spider", SMEntityTypes.JUNGLE_SPIDER::get, 5597514, 11013646);
    public static final RegistryObject<Item> PIRANHA_BUCKET = HELPER.createItem("piranha_bucket", () -> SMItems.createMobBucketItem(SMEntityTypes.PIRANHA::get));
    public static final RegistryObject<ForgeSpawnEggItem> PIRANHA_SPAWN_EGG = HELPER.createSpawnEggItem("piranha", SMEntityTypes.PIRANHA::get, 15561472, 4240022);

    public static Item createMobBucketItem(Supplier<EntityType<? extends WaterAnimal>> entityType) {
        return new MobBucketItem(entityType, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, PropertyUtil.stacksOnce());
    }

    public static void buildCreativeTabContents() {
        CreativeModeTabContentsPopulator.mod(SullysMod.MOD_ID)
                .tab(CreativeModeTabs.INGREDIENTS)
                .addItemsAfter(of(Items.COPPER_INGOT), POLISHED_JADE)
                .addItemsAfter(of(Items.RAW_COPPER), ROUGH_JADE)
                .addItemsAfter(of(Items.SCUTE), TORTOISE_SCUTE)
                .addItemsAfter(of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), JADE_UPGRADE_SMITHING_TEMPLATE)
                .addItemsAfter(of(Items.GLASS_BOTTLE), GLASS_VIAL)
                .addItemsBefore(of(Items.BONE), PIRANHA_TOOTH)

                .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .addItemsAfter(of(Items.MUSIC_DISC_RELIC), MUSIC_DISC_SCOUR)
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

                .tab(CreativeModeTabs.BUILDING_BLOCKS)
                .addItemsAfter(of(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB),
                        ROUGH_JADE_BLOCK, ROUGH_JADE_BRICKS, ROUGH_JADE_BRICK_STAIRS, ROUGH_JADE_BRICK_SLAB,
                        ROUGH_JADE_TILES, ROUGH_JADE_TILE_STAIRS, ROUGH_JADE_TILE_SLAB,
                        SMOOTHED_ROUGH_JADE, POLISHED_JADE_BLOCK,
                        POLISHED_JADE_BRICKS, POLISHED_JADE_BRICK_STAIRS, POLISHED_JADE_BRICK_SLAB,
                        POLISHED_JADE_TILES, POLISHED_JADE_TILE_STAIRS, POLISHED_JADE_TILE_SLAB,
                        POLISHED_SMALL_JADE_BRICKS, POLISHED_SMALL_JADE_BRICK_STAIRS, POLISHED_SMALL_JADE_BRICK_SLAB,
                        POLISHED_JADE_SHINGLES, POLISHED_JADE_SHINGLE_STAIRS, POLISHED_JADE_SHINGLE_SLAB,
                        POLISHED_JADE_PILLAR, POLISHED_CHISELED_JADE, JADE_TOTEM)
                .addItemsAfter(of(Items.WARPED_BUTTON), PETRIFIED_LOG, PETRIFIED_WOOD, STRIPPED_PETRIFIED_LOG, STRIPPED_PETRIFIED_WOOD,
                        PETRIFIED_PLANKS, PETRIFIED_STAIRS, PETRIFIED_SLAB, PETRIFIED_FENCE, PETRIFIED_FENCE_GATE, PETRIFIED_TRAPDOOR,
                        PETRIFIED_PRESSURE_PLATE, PETRIFIED_BUTTON)

                .tab(CreativeModeTabs.REDSTONE_BLOCKS)
                .addItemsAfter(of(Items.DROPPER), JADE_FLINGER_TOTEM)
                .addItemsAfter(of(Items.STONE_BUTTON), COPPER_BUTTON, EXPOSED_COPPER_BUTTON, WEATHERED_COPPER_BUTTON, OXIDIZED_COPPER_BUTTON,
                        WAXED_COPPER_BUTTON, WAXED_EXPOSED_COPPER_BUTTON, WAXED_WEATHERED_COPPER_BUTTON, WAXED_OXIDIZED_COPPER_BUTTON)
        ;
    }
}
