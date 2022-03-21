package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockModels extends BlockModelProvider {

    public BlockModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SullysMod.MOD_ID, existingFileHelper);
    }

    private String name(Block blockForName) {
        return blockForName.getRegistryName().getPath();
    }

    @Override
    protected void registerModels() {
        buttonBlockInventory((ButtonBlock)SMBlocks.COPPER_BUTTON.get(), "copper_block");
        buttonBlockInventory((ButtonBlock)SMBlocks.EXPOSED_COPPER_BUTTON.get(), "exposed_copper");
        buttonBlockInventory((ButtonBlock)SMBlocks.WEATHERED_COPPER_BUTTON.get(), "weathered_copper");
        buttonBlockInventory((ButtonBlock)SMBlocks.OXIDIZED_COPPER_BUTTON.get(), "oxidized_copper");
        buttonBlockInventory((ButtonBlock)SMBlocks.WAXED_COPPER_BUTTON.get(), "copper_block");
        buttonBlockInventory((ButtonBlock)SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), "exposed_copper");
        buttonBlockInventory((ButtonBlock)SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), "weathered_copper");
        buttonBlockInventory((ButtonBlock)SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), "oxidized_copper");

        SullysMod.LOGGER.info("BLOCK MODEL GENERATION COMPLETE");
    }

    private void buttonBlockInventory(ButtonBlock block, String texture) {
        buttonInventory(name(block) + "_inventory", new ResourceLocation("block/" + texture));
    }
}
