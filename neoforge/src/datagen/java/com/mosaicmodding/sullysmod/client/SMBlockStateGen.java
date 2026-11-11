package com.mosaicmodding.sullysmod.client;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.content.amber.AmberLayeredCauldronBlock;
import com.mosaicmodding.sullysmod.registry.SMBlocks;
import com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils;
import com.mosaicmodding.sullysmod.registry.registry_sets.SimpleBlockRegistrySet;
import com.mosaicmodding.tessera.datagen.BlockStateSmithTypes;
import com.mosaicmodding.tessera.datagen.ItemModelSmithTypes;
import com.mosaicmodding.tessera.datagen.providers.block.TesseraBlockStateSystem;
import com.mosaicmodding.tessera.datagen.providers.item.TesseraItemModelSystem;
import com.mosaicmodding.tessera.datagen.smith.blockstate.BlockStateSmith;
import com.mosaicmodding.tessera.datagen.smith.blockstate.BlockStateSystemData;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.EnumMap;
import java.util.function.Supplier;

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

        createForBlockSet(data, SMBlocks.AMBER_BLOCK_SET);
        AMBER_CAULDRON.act(data, SMBlocks.AMBER_CAULDRON);

        setTexturePath("gourds");
        BlockStateSmithTypes.BOTTOM_TOP_BLOCK.act(data, SMBlocks.GIGANTO_GOURD);
        BlockStateSmithTypes.BOTTOM_TOP_BLOCK.act(data, SMBlocks.MINIMU_GOURD);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.CARVED_GIGANTO_GOURD);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.CARVED_MINIMU_GOURD);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.GIGANTO_GOURD_LANTERN);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.MINIMU_GOURD_LANTERN);

    }

    public static void createForBlockSet(BlockStateSystemData<SMBlockStateGen> data, SimpleBlockRegistrySet blockSet) {
        BlockStateSmithTypes.FULL_BLOCK.act(data, blockSet.getBricksBlock());
        BlockStateSmithTypes.STAIRS_BLOCK.act(data, blockSet.getStairsBlock());
        BlockStateSmithTypes.SLAB_BLOCK.act(data, blockSet.getSlabBlock());
        if (blockSet.getWallBlock().isPresent()) {
            BlockStateSmithTypes.WALL_BLOCK.act(data, blockSet.getWallBlock().get());
        }
        if (blockSet.getPillarBlock().isPresent()) {
            BlockStateSmithTypes.ROTATED_PILLAR_BLOCK.act(data, blockSet.getPillarBlock().get());
        }
        if (blockSet.getChiseledBlock().isPresent()) {
            BlockStateSmithTypes.FULL_BLOCK.act(data, blockSet.getChiseledBlock().get());
        }

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
