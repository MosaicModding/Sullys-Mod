package com.uraneptus.sullysmod.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

import static com.uraneptus.sullysmod.data.SMDatagenUtil.*;

@SuppressWarnings({"unused", "SameParameterValue"})
public class SMItemModelProvider extends ItemModelProvider {

    public SMItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItemHandheld(SMItems.MOLTEN_AMBER_BUCKET);
        basicBlockItem(SMBlocks.JADE_ORE);
        basicBlockItem(SMBlocks.DEEPSLATE_JADE_ORE);
        basicBlockItem(SMBlocks.ROUGH_JADE_BLOCK);
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICKS);
        basicBlockItem(SMBlocks.JADE_BLOCK);
        basicBlockItem(SMBlocks.JADE_BRICKS);
        basicBlockItem(SMBlocks.CHISELED_JADE);
        basicBlockItem(SMBlocks.JADE_PILLAR);
        basicBlockItem(SMBlocks.JADE_TOTEM);
        basicBlockItem(SMBlocks.JADE_FLINGER_TOTEM);
        basicItem(SMItems.ROUGH_JADE);
        basicItem(SMItems.JADE);
        basicItem(SMItems.LANTERNFISH_BUCKET);
        basicItem(SMItems.PIRANHA_BUCKET);
        basicSpawnEggItem(SMItems.LANTERNFISH_SPAWN_EGG);
        basicSpawnEggItem(SMItems.PIRANHA_SPAWN_EGG);
        basicSpawnEggItem(SMItems.TORTOISE_SPAWN_EGG);
        basicSpawnEggItem(SMItems.BOULDERING_ZOMBIE_SPAWN_EGG);
        basicSpawnEggItem(SMItems.JUNGLE_SPIDER_SPAWN_EGG);
        basicItem(SMItems.LANTERNFISH);
        basicItem(SMItems.COOKED_LANTERNFISH);
        basicItem(SMItems.PIRANHA);
        basicItem(SMItems.COOKED_PIRANHA);
        blockItemWithItemTexture(SMBlocks.TORTOISE_EGG);
        copperButtonBlockItem(SMBlocks.COPPER_BUTTON, () -> Blocks.COPPER_BLOCK);
        copperButtonBlockItem(SMBlocks.EXPOSED_COPPER_BUTTON, () -> Blocks.EXPOSED_COPPER);
        copperButtonBlockItem(SMBlocks.WEATHERED_COPPER_BUTTON, () -> Blocks.WEATHERED_COPPER);
        copperButtonBlockItem(SMBlocks.OXIDIZED_COPPER_BUTTON, () -> Blocks.OXIDIZED_COPPER);
        copperButtonBlockItem(SMBlocks.WAXED_COPPER_BUTTON, () -> Blocks.COPPER_BLOCK);
        copperButtonBlockItem(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON, () -> Blocks.EXPOSED_COPPER);
        copperButtonBlockItem(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON, () -> Blocks.WEATHERED_COPPER);
        copperButtonBlockItem(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON, () -> Blocks.OXIDIZED_COPPER);
        basicBlockItem(SMBlocks.JADE_BRICK_STAIRS);
        basicBlockItem(SMBlocks.JADE_BRICK_SLAB);
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICK_STAIRS);
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICK_SLAB);
        basicItem(SMItems.MUSIC_DISC_SCOUR);
        basicItem(SMItems.MUSIC_DISC_SUNKEN_PAST);
        basicItem(SMItems.TORTOISE_SCUTE);
        basicItem(SMItems.TORTOISE_SHELL);
        basicItem(SMItems.JADE_UPGRADE_SMITHING_TEMPLATE);
        basicItem(SMItems.GLASS_VIAL);
        basicItem(SMItems.JADE_HORSE_ARMOR);
        basicItemHandheld(SMItems.THROWING_KNIFE);
        basicBlockItem(SMBlocks.AMBER);
        basicBlockItem(SMBlocks.ROUGH_AMBER);
        basicBlockItem(SMBlocks.CHISELED_AMBER);
        basicBlockItem(SMBlocks.AMBER_PILLAR);
        basicBlockItem(SMBlocks.AMBER_LANTERN);
        basicBlockItem(SMBlocks.AMBER_BRICKS);
        basicBlockItem(SMBlocks.AMBER_BRICK_SLAB);
        wallBlockItem(SMBlocks.AMBER_BRICK_WALL, SMBlocks.AMBER_BRICKS);
        wallBlockItem(SMBlocks.ROUGH_JADE_BRICK_WALL, SMBlocks.ROUGH_JADE_BRICKS);
        wallBlockItem(SMBlocks.JADE_BRICK_WALL, SMBlocks.JADE_BRICKS);
        basicBlockItem(SMBlocks.AMBER_BRICK_STAIRS);
        basicItem(SMItems.PIRANHA_TOOTH);
        basicBlockItem(SMBlocks.PETRIFIED_PLANKS);
        basicBlockItem(SMBlocks.PETRIFIED_LOG);
        basicBlockItem(SMBlocks.STRIPPED_PETRIFIED_LOG);
        basicBlockItem(SMBlocks.PETRIFIED_WOOD);
        basicBlockItem(SMBlocks.STRIPPED_PETRIFIED_WOOD);
        basicBlockItem(SMBlocks.PETRIFIED_PRESSURE_PLATE);
        trapdoorBlockItem(SMBlocks.PETRIFIED_TRAPDOOR);
        basicBlockItem(SMBlocks.PETRIFIED_STAIRS);
        basicBlockItem(SMBlocks.PETRIFIED_SLAB);
        modButtonBlockItem(SMBlocks.PETRIFIED_BUTTON, SMBlocks.PETRIFIED_PLANKS);
        basicBlockItem(SMBlocks.PETRIFIED_FENCE_GATE);
        fenceBlockItem(SMBlocks.PETRIFIED_FENCE, SMBlocks.PETRIFIED_PLANKS);
        blockItemWithItemTexture(SMBlocks.PETRIFIED_SIGN.getFirst());
        blockItemWithItemTexture(SMBlocks.PETRIFIED_HANGING_SIGN.getFirst());
        blockItemWithItemTexture(SMBlocks.PETRIFIED_DOOR);
        itemFromBlockTexture(SMBlocks.PETRIFIED_SAPLING);
        basicItem(SMItems.BROKEN_VASE);
        basicItem(SMItems.MINERS_HELMET);
        basicItem(SMItems.SMALL_DENTED_HELMET);
        basicItem(SMItems.LOST_CROWN);
        basicItemHandheld(SMItems.PRIMITIVE_KNIFE);
        blockItemWithItemTexture(SMBlocks.ITEM_STAND);
        basicItem(SMItems.JADE_AMULET);
        basicItem(SMItems.PRIMITIVE_RING);
        basicItem(SMItems.RUSTY_TOOLS);
        basicItem(SMItems.BROKEN_BOWL);
        basicItem(SMItems.COPPER_COG);
        basicItem(SMItems.PETRIFIED_COOKIE);
        basicItem(SMItems.ARROWHEAD);
        basicItem(SMItems.DEATH_WHISTLE);
        basicItem(SMItems.OMINOUS_TABLET);
        basicItem(SMItems.MOON_TABLET);
        basicItem(SMItems.STONE_IDOL);
        basicItem(SMItems.RED_CAP);
        basicItem(SMItems.DRIED_CYAN_FLOWER);
        basicItem(SMItems.DRIED_RED_FLOWER);
        basicItem(SMItems.METALLIC_SKULL);
        basicItem(SMItems.LOST_BAG);
        basicItem(SMItems.MYSTERIOUS_PLATE);
        basicItem(SMItems.FAMILIAR_CUBE);
        basicItem(SMItems.AMBER_ENCASED_BUG);
        basicItem(SMItems.FOSSILISED_SHELLS);
        basicItem(SMItems.FOSSILISED_BONE);
        basicItem(SMItems.FOSSILISED_FOOTSTEP);
        basicItem(SMItems.FOSSILISED_FISH);
        basicItem(SMItems.TORN_MANUSCRIPT);
        basicItem(SMItems.LOST_JOURNAL);
        basicItem(SMItems.LOST_SKETCHBOOK);
        basicItem(SMItems.LOST_RECIPE_BOOK);
        basicItem(SMItems.GOLDEN_BELT_BUCKLE);
        basicItem(SMItems.DEEPSLATE_VASE);
        basicItem(SMItems.SMALL_GEODE);
        basicItem(SMItems.TORN_CLOTH);
        basicItem(SMItems.GOLDEN_GOBLET);
        basicItem(SMItems.EMERALD_EARRING);
        basicItemHandheld(SMItems.BROKEN_BOTTLE);
        basicItem(SMItems.FROG_IDOL);
        //Single use methods
        brokenBottle();
        venomVialItem();
        jadeShieldItem();
        SMBlocks.ANCIENT_SKULLS.forEach(this::ancientSkull);
    }

    private void basicBlockItem(Supplier<? extends Block> blockForItem) {
        withExistingParent(name(blockForItem.get()), modBlockLocation(name(blockForItem.get())));
    }

    private void basicItem(Supplier<? extends Item> item) {
        basicItem(item.get());
    }

    private void basicItemHandheld(Supplier<? extends Item> item) {
        withExistingParent(name(item.get()), HANDHELD)
                .texture(LAYER0, modItemLocation(name(item.get())));
    }

    private void blockItemWithItemTexture(Supplier<? extends Block> blockForItem) {
        basicItem(blockForItem.get().asItem());
    }

    private void itemFromBlockTexture(Supplier<? extends Block> block) {
        withExistingParent(name(block.get()), GENERATED).texture(LAYER0, modBlockLocation(name(block.get())));
    }

    private void modButtonBlockItem(Supplier<? extends Block> blockForItem, Supplier<? extends Block> blockForTexture) {
        buttonInventory(name(blockForItem.get()), modBlockLocation(name(blockForTexture.get())));
    }

    private void copperButtonBlockItem(Supplier<? extends Block> blockForItem, Supplier<? extends Block> blockForTexture) {
        buttonInventory(name(blockForItem.get()), vanillaBlockLocation(name(blockForTexture.get())));
    }

    private void fenceBlockItem(Supplier<? extends Block> block, Supplier<? extends Block> blockForTexture) {
        fenceInventory(name(block.get()), modBlockLocation(name(blockForTexture.get())));
    }
    private void wallBlockItem(Supplier<? extends Block> block, Supplier<? extends Block> blockForTexture) {
        wallInventory(name(block.get()), modBlockLocation(name(blockForTexture.get())));
    }

    private void basicBlockItemWithSuffix(Supplier<? extends Block> block, String suffix) {
        withExistingParent(name(block.get()), modBlockLocation(name(block.get()) + suffix));
    }

    private void trapdoorBlockItem(Supplier<? extends Block> block) {
        basicBlockItemWithSuffix(block, "_bottom");
    }

    private void basicSpawnEggItem(Supplier<? extends Item> item) {
        withExistingParent(name(item.get()), SPAWN_EGG);
    }

    private void jadeShieldItem() {
        Item item = SMItems.JADE_SHIELD.get();
        getBuilder(name(item) + "_blocking")
                .parent(new ModelFile.UncheckedModelFile(ENTITY))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("particle", vanillaBlockLocation(name(Blocks.DARK_OAK_PLANKS)))
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(45, 135, 0).translation(3.51F, 11, -2).end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(45, 135, 0).translation(13.51F, 3, 5).end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(0, 180, -5).translation(-15, 5, -11).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(0, 180, -5).translation(5, 5, -11).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemDisplayContext.GUI).rotation(15, -25, -5).translation(2, 2.5F, 0).scale(0.65F, 0.65F, 0.65F).end()
                .end();

        getBuilder(name(item))
                .parent(new ModelFile.UncheckedModelFile(ENTITY))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("particle", vanillaBlockLocation(name(Blocks.DARK_OAK_PLANKS)))
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(0, 90, 0).translation(10, 6, -4).end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(0, 90, 0).translation(10, 6, 12).end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(0, 180, 5).translation(-10, 2, -10).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(0, 180, 5).translation(10, 0, -10).scale(1.25F, 1.25F, 1.25F).end()
                .transform(ItemDisplayContext.GUI).rotation(15, -25, -5).translation(2, 2.5F, 0).scale(0.65F, 0.65F, 0.65F).end()
                .transform(ItemDisplayContext.FIXED).rotation(0, 180, 0).translation(-4.5F, 4.5F, -5).scale(0.55F, 0.55F, 0.55F).end()
                .transform(ItemDisplayContext.GROUND).rotation(0, 0, 0).translation(2, 4, 2).scale(0.25F, 0.25F, 0.25F).end()
                .end()
                .override().predicate(new ResourceLocation("blocking"), 1).model(new ModelFile.UncheckedModelFile(modItemLocation(name(item) + "_blocking")));
    }

    private void venomVialItem() {
        Item vial = SMItems.VENOM_VIAL.get();
        withExistingParent(name(vial), GENERATED)
                .texture("layer0", modItemLocation("glass_vial"))
                .texture("layer1", modItemLocation("venom_vial_1"))
                .texture("layer2", modItemLocation("venom_vial_2"));
    }
    
    private void ancientSkull(Supplier<? extends Block> skull) {
        getBuilder(name(skull.get()))
                .parent(new ModelFile.UncheckedModelFile(ENTITY))
                .texture("particle", vanillaBlockLocation(name(Blocks.SOUL_SAND)))
                .transforms()
                .transform(ItemDisplayContext.GUI).rotation(30, 45, 0).translation(0, 3, 0).scale(0.6F, 0.6F, 0.6F).end()
                .transform(ItemDisplayContext.FIXED).rotation(0, 180, 0).translation(0, 4, 0).end()
                .transform(ItemDisplayContext.GROUND).translation(0, 3, 0).scale(0.5F, 0.5F, 0.5F).end()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(45, 45, 0).translation(0, 3, 0).scale(0.5F, 0.5F, 0.5F).end()
                .end();

    }

    private void brokenBottle() {
        Item bottle = SMItems.BROKEN_BOTTLE.get();
        getBuilder(name(bottle))
                .parent(getExistingFile(HANDHELD))
                .texture("layer0", modItemLocation(name(bottle)))
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(180, -90, 0).translation(0, 4, 0.5F).scale(0.85F, 0.85F, 0.85F).end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(-180, 90, 0).translation(0, 4, 0.5F).scale(0.85F, 0.85F, 0.85F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(180, -90, 0).translation(1.13F, 3.2F, 1.13F).scale(0.68F, 0.68F, 0.68F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(-180, 90, 0).translation(1.13F, 3.2F, 1.13F).scale(0.68F, 0.68F, 0.68F).end()
                .end();
    }
}
