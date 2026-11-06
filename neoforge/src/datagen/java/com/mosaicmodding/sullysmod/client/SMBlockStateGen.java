package com.mosaicmodding.sullysmod.client;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.content.amber.AmberLayeredCauldronBlock;
import com.mosaicmodding.sullysmod.registry.SMBlocks;
import com.mosaicmodding.sullysmod.registry.SMRegUtils;
import com.mosaicmodding.tessera.datagen.BlockStateSmithTypes;
import com.mosaicmodding.tessera.datagen.ItemModelSmithTypes;
import com.mosaicmodding.tessera.datagen.providers.block.TesseraBlockStateSystem;
import com.mosaicmodding.tessera.datagen.providers.item.TesseraItemModelSystem;
import com.mosaicmodding.tessera.datagen.smith.blockstate.BlockStateSmith;
import com.mosaicmodding.tessera.datagen.smith.blockstate.BlockStateSystemData;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class SMBlockStateGen extends TesseraBlockStateSystem {

    public SMBlockStateGen(PackOutput output, ExistingFileHelper exFileHelper, TesseraItemModelSystem itemModelProvider) {
        super(output, SullysMod.ID, exFileHelper, itemModelProvider);
    }

    @Override
    protected void registerStatesAndModels() {
        var data = new BlockStateSystemData<>(this, SMRegUtils.getAllModBlocks()::remove);

        setTexturePath("amber");
        BlockStateSmithTypes.FULL_BLOCK_NO_ITEM.act(data, SMBlocks.AMBER);
        BlockStateSmithTypes.FULL_BLOCK.act(data, SMBlocks.AMBER_SOLID);
        BlockStateSmithTypes.FULL_BLOCK.act(data, SMBlocks.ROUGH_AMBER);
        BlockStateSmithTypes.FULL_BLOCK.act(data, SMBlocks.CHISELED_AMBER);
        BlockStateSmithTypes.FULL_BLOCK.act(data, SMBlocks.AMBER_BRICKS);
        BlockStateSmithTypes.ROTATED_PILLAR_BLOCK.act(data, SMBlocks.AMBER_PILLAR);
        BlockStateSmithTypes.STAIRS_BLOCK.act(data, SMBlocks.AMBER_BRICK_STAIRS);
        BlockStateSmithTypes.SLAB_BLOCK.act(data, SMBlocks.AMBER_BRICK_SLAB);
        BlockStateSmithTypes.WALL_BLOCK.act(data, SMBlocks.AMBER_BRICK_WALL);
        AMBER_CAULDRON.act(data, SMBlocks.AMBER_CAULDRON);

        setTexturePath("gourds");
        BlockStateSmithTypes.BOTTOM_TOP_BLOCK.act(data, SMBlocks.GIGANTO_GOURD);
        BlockStateSmithTypes.BOTTOM_TOP_BLOCK.act(data, SMBlocks.MINIMU_GOURD);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.CARVED_GIGANTO_GOURD);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.CARVED_MINIMU_GOURD);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.GIGANTO_GOURD_LANTERN);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.MINIMU_GOURD_LANTERN);

    }

    public static BlockStateSmith<AmberLayeredCauldronBlock> AMBER_CAULDRON = new BlockStateSmith<>(AmberLayeredCauldronBlock.class, ItemModelSmithTypes.NO_DATAGEN, (block, provider) -> {
        provider.getVariantBuilder(block).forAllStates(blockState -> {
            int level = blockState.getValue(AmberLayeredCauldronBlock.LEVEL);
            String levelSuffix = switch (level) {
                case 1 -> "_level1";
                case 2 -> "_level2";
                case 3 -> "_full";
                default -> "";
            };

            ResourceLocation templateLoc = provider.getVanillaBlockTexture("template_cauldron" + levelSuffix);
            String modelName = provider.getBlockName(block) + levelSuffix;
            ResourceLocation cauldronLoc = provider.getVanillaBlockTexture(Blocks.CAULDRON);
            ResourceLocation contentTexture = provider.getBlockTexture("amber");

            ModelFile file = provider.models().withExistingParent(modelName, templateLoc)
                    .texture("bottom", cauldronLoc + "_bottom")
                    .texture("content", contentTexture)
                    .texture("inside", cauldronLoc + "_inner")
                    .texture("particle", cauldronLoc + "_side")
                    .texture("side", cauldronLoc + "_side")
                    .texture("top", cauldronLoc + "_top");


            return ConfiguredModel.builder().modelFile(file).build();
        });
    });
}
