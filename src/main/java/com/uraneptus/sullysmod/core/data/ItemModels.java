package com.uraneptus.sullysmod.core.data;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {
    public static final String GENERATED = "item/generated";

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SullysMod.MOD_ID, existingFileHelper);
    }

    private String name(Block blockForItem) {
        return blockForItem.getRegistryName().getPath();
    }

    private String name(Item item) {
        return item.getRegistryName().getPath();
    }

    public ResourceLocation blockLocation(String path) {
        return new ResourceLocation(SullysMod.MOD_ID, ModelProvider.BLOCK_FOLDER + "/" + path);
    }

    public ResourceLocation itemLocation(String path) {
        return new ResourceLocation(SullysMod.MOD_ID, ModelProvider.ITEM_FOLDER + "/" + path);
    }

    @Override
    protected void registerModels() {
        basicBlockItem(SMBlocks.JADE_ORE.get());
        basicBlockItem(SMBlocks.DEEPSLATE_JADE_ORE.get());
        basicBlockItem(SMBlocks.RAW_JADE_BLOCK.get());
        basicBlockItem(SMBlocks.RAW_JADE_BRICKS.get());
        basicBlockItem(SMBlocks.RAW_JADE_TILE.get());
        basicBlockItem(SMBlocks.RAW_JADE_TILES.get());
        basicBlockItem(SMBlocks.JADE_BRICKS.get());
        basicBlockItem(SMBlocks.SMALL_JADE_BRICKS.get());
        basicBlockItem(SMBlocks.JADE_SHINGLES.get());
        basicBlockItem(SMBlocks.JADE_TILES.get());
        basicItem(SMItems.RAW_JADE.get());

        System.out.println("ITEM GENERATION COMPLETE");
    }

    private void basicBlockItem(Block blockForItem) {
        withExistingParent(name(blockForItem), blockLocation(name(blockForItem)));
    }

    private void basicItem(Item item) {
        withExistingParent(name(item), GENERATED).texture("layer0", itemLocation(name(item)));
    }

}
