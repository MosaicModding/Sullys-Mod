package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

import static com.uraneptus.sullysmod.core.data.SMDatagenUtil.*;
public class SMItemModelProvider extends ItemModelProvider {

    public SMItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SullysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicBlockItem(SMBlocks.JADE_ORE);
        basicBlockItem(SMBlocks.DEEPSLATE_JADE_ORE);
        basicBlockItem(SMBlocks.ROUGH_JADE_BLOCK);
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICKS);
        basicBlockItem(SMBlocks.SMOOTHED_ROUGH_JADE);
        basicBlockItem(SMBlocks.ROUGH_JADE_TILES);
        basicBlockItem(SMBlocks.POLISHED_JADE_BLOCK);
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICKS);
        basicBlockItem(SMBlocks.POLISHED_SMALL_JADE_BRICKS);
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLES);
        basicBlockItem(SMBlocks.POLISHED_JADE_TILES);
        basicBlockItem(SMBlocks.POLISHED_CHISELED_JADE);
        basicBlockItem(SMBlocks.POLISHED_JADE_PILLAR);
        basicBlockItem(SMBlocks.JADE_TOTEM);
        basicBlockItem(SMBlocks.JADE_FLINGER_TOTEM);
        basicItem(SMItems.ROUGH_JADE);
        basicItem(SMItems.POLISHED_JADE);
        basicItem(SMItems.LANTERNFISH_BUCKET);
        basicSpawnEggItem(SMItems.LANTERNFISH_SPAWN_EGG);
        basicSpawnEggItem(SMItems.TORTOISE_SPAWN_EGG);
        basicItem(SMItems.RAW_LANTERNFISH);
        basicItem(SMItems.COOKED_LANTERNFISH);
        blockItemWithItemTexture(SMBlocks.TORTOISE_EGG);
        copperButtonBlockItem(SMBlocks.COPPER_BUTTON, COPPER_BLOCK);
        copperButtonBlockItem(SMBlocks.EXPOSED_COPPER_BUTTON, EXPOSED_COPPER);
        copperButtonBlockItem(SMBlocks.WEATHERED_COPPER_BUTTON, WEATHERED_COPPER);
        copperButtonBlockItem(SMBlocks.OXIDIZED_COPPER_BUTTON, OXIDIZED_COPPER);
        copperButtonBlockItem(SMBlocks.WAXED_COPPER_BUTTON, COPPER_BLOCK);
        copperButtonBlockItem(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON, EXPOSED_COPPER);
        copperButtonBlockItem(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON, WEATHERED_COPPER);
        copperButtonBlockItem(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON, OXIDIZED_COPPER);
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICK_STAIRS);
        basicBlockItem(SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS);
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLE_STAIRS);
        basicBlockItem(SMBlocks.POLISHED_JADE_TILE_STAIRS);
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICK_SLAB);
        basicBlockItem(SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB);
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLE_SLAB);
        basicBlockItem(SMBlocks.POLISHED_JADE_TILE_SLAB);
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICK_STAIRS);
        basicBlockItem(SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS);
        basicBlockItem(SMBlocks.ROUGH_JADE_TILE_STAIRS);
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICK_SLAB);
        basicBlockItem(SMBlocks.SMOOTHED_ROUGH_JADE_SLAB);
        basicBlockItem(SMBlocks.ROUGH_JADE_TILE_SLAB);
        basicBlockItem(SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.SMOOTHED_ROUGH_JADE_VERTICAL_SLAB);
        basicBlockItem(SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB);
        basicItem(SMItems.MUSIC_DISC_SCOUR);

        SullysMod.LOGGER.info("ITEM MODEL GENERATION COMPLETE");
    }

    /**
     * Creates a BlockItem Model based on the provided {@link Block}'s BlockModel.
     * @param blockForItem The supplied {@link Block} from which a BlockItem Model is being created.
     */
    private void basicBlockItem(Supplier<? extends Block> blockForItem) {
        withExistingParent(name(blockForItem.get()), modBlockLocation(name(blockForItem.get())));
    }

    /**
     * Creates the standard Generated Item Model for the provided {@link Item}.
     * @param item The supplied {@link Item} from which the Item Model will be created.
     */
    private void basicItem(Supplier<? extends Item> item) {
        basicItem(item.get());
    }

    /**
     * Creates a Generated Item Model for the provided {@link Block}, with a texture file <br/>
     * that is using the provided {@link Block}'s RegistryName and is located in <code>assets/modid/textures/block</code>. <br/>
     * E.g.: The {@link net.minecraft.world.level.block.SaplingBlock}'s Block Item.
     * @param blockForItem The supplied {@link Block} from which a BlockItem Model is being created.
     */
    private void blockItemWithBlockTexture(Supplier<? extends Block> blockForItem) {
        withExistingParent(name(blockForItem.get()), GENERATED).texture(LAYER0, modBlockLocation(name(blockForItem.get())));
    }

    /**
     * Creates a Generated Item Model for the provided {@link Block}, with a texture file <br/>
     * that is using the provided {@link Block}'s RegistryName and is located in <code>assets/modid/textures/item</code>. <br/>
     * E.g.: The {@link net.minecraft.world.level.block.DoorBlock}'s Block Item.
     * @param blockForItem The supplied {@link Block} from which a BlockItem Model is being created.
     */
    private void blockItemWithItemTexture(Supplier<? extends Block> blockForItem) {
        basicItem(blockForItem.get().asItem());
    }

    private void copperButtonBlockItem(Supplier<? extends Block> blockForItem, String texture) {
        buttonInventory(name(blockForItem.get()), vanillaBlockLocation(texture));
    }

    private void basicSpawnEggItem(Supplier<? extends Item> item) {
        withExistingParent(name(item.get()), SPAWN_EGG);
    }
}
