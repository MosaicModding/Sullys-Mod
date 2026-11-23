package com.mosaicmodding.sullysmod.client;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.content.amber.AmberLayeredCauldronBlock;
import com.mosaicmodding.sullysmod.registry.SMArtifacts;
import com.mosaicmodding.sullysmod.registry.SMBlocks;
import com.mosaicmodding.sullysmod.core.SMUtils;
import com.mosaicmodding.sullysmod.registry.registry_sets.WoodBlockRegistrySet;
import com.mosaicmodding.sullysmod.registry.registry_sets.SimpleBlockRegistrySet;
import com.mosaicmodding.tessera.datagen.BlockStateSmithTypes;
import com.mosaicmodding.tessera.datagen.ItemModelSmithTypes;
import com.mosaicmodding.tessera.datagen.providers.block.TesseraBlockStateSystem;
import com.mosaicmodding.tessera.datagen.providers.item.TesseraItemModelSystem;
import com.mosaicmodding.tessera.datagen.smith.blockstate.BlockStateSmith;
import com.mosaicmodding.tessera.datagen.smith.blockstate.BlockStateSystemData;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class SMBlockStateGen extends TesseraBlockStateSystem {

    public SMBlockStateGen(PackOutput output, ExistingFileHelper exFileHelper, TesseraItemModelSystem itemModelProvider) {
        super(output, SullysMod.ID, exFileHelper, itemModelProvider);
    }

    @Override
    protected void registerStatesAndModels() {
        var data = new BlockStateSystemData<>(this, SMUtils.getAllModBlocks()::remove);

        setTexturePath("amber");
        BlockStateSmithTypes.FULL_BLOCK_NO_ITEM.act(data, SMBlocks.AMBER);
        BlockStateSmithTypes.FULL_BLOCK.act(data, SMBlocks.AMBER_SOLID);
        BlockStateSmithTypes.FULL_BLOCK.act(data, SMBlocks.ROUGH_AMBER);

        createForBlockSet(data, SMBlocks.AMBER_BLOCK_SET);
        AMBER_CAULDRON.act(data, SMBlocks.AMBER_CAULDRON);

        setTexturePath("petrified");
        createForWoodSet(data, SMBlocks.PETRIFIED_WOOD_BLOCK_SET);

        setTexturePath("gourds");
        BlockStateSmithTypes.BOTTOM_TOP_BLOCK.act(data, SMBlocks.GIGANTO_GOURD);
        BlockStateSmithTypes.BOTTOM_TOP_BLOCK.act(data, SMBlocks.MINIMU_GOURD);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.CARVED_GIGANTO_GOURD);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.CARVED_MINIMU_GOURD);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.GIGANTO_GOURD_LANTERN);
        BlockStateSmithTypes.ORIENTABLE_WITH_BOTTOM_BLOCK.act(data, SMBlocks.MINIMU_GOURD_LANTERN);

        setTexturePath("artifacts");
        basedOnTemplate(data, SMArtifacts.BROKEN_BOWL, "bowl", false, data.provider().getVanillaBlockTexture(Blocks.TERRACOTTA));
        basedOnTemplate(data, SMArtifacts.BROKEN_VASE, "vase", false, data.provider().getVanillaBlockTexture(Blocks.TERRACOTTA));
        basedOnTemplate(data, SMArtifacts.BROKEN_CUP, "cup", false, data.provider().getVanillaBlockTexture(Blocks.TERRACOTTA));
        basedOnTemplate(data, SMArtifacts.GOLDEN_IDOL, false, data.provider().getVanillaBlockTexture(Blocks.GOLD_BLOCK));
        basedOnTemplate(data, SMArtifacts.GOLDEN_GOBLET, false, data.provider().getVanillaBlockTexture(Blocks.GOLD_BLOCK));
        basedOnTemplate(data, SMArtifacts.FAMILIAR_CUBE, false);
        basedOnTemplate(data, SMArtifacts.STONE_IDOL, true, data.provider().getVanillaBlockTexture(Blocks.STONE));
        basedOnTemplate(data, SMArtifacts.FROG_IDOL, true, data.provider().getVanillaBlockTexture(Blocks.PACKED_MUD));
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, SMArtifacts.DRIED_RED_FLOWER);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, SMArtifacts.DRIED_CYAN_FLOWER);
        BlockStateSmithTypes.POTTED_PLANT.act(data, SMArtifacts.POTTED_DRIED_RED_FLOWER);
        BlockStateSmithTypes.POTTED_PLANT.act(data, SMArtifacts.POTTED_DRIED_CYAN_FLOWER);
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

    public static void createForWoodSet(BlockStateSystemData<SMBlockStateGen> data, WoodBlockRegistrySet blockSet) {
        BlockStateSmithTypes.FULL_BLOCK.act(data, blockSet.getPlanks());
        BlockStateSmithTypes.STAIRS_BLOCK.act(data, blockSet.getStairs());
        BlockStateSmithTypes.SLAB_BLOCK.act(data, blockSet.getSlab());

        BlockStateSmithTypes.ROTATED_PILLAR_BLOCK.act(data, blockSet.getLog());
        BlockStateSmithTypes.ROTATED_PILLAR_BLOCK.act(data, blockSet.getStrippedLog());

        BlockStateSmithTypes.WOOD_BLOCK.act(data, blockSet.getWood());
        BlockStateSmithTypes.WOOD_BLOCK.act(data, blockSet.getStrippedWood());

        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, blockSet.getPressurePlate());
        BlockStateSmithTypes.BUTTON_BLOCK.act(data, blockSet.getButton());

        BlockStateSmithTypes.TRAPDOOR_BLOCK.act(data, blockSet.getTrapdoor());
        BlockStateSmithTypes.DOOR_BLOCK.act(data, blockSet.getDoor());

        BlockStateSmithTypes.FENCE_BLOCK.act(data, blockSet.getFence());
        BlockStateSmithTypes.FENCE_GATE_BLOCK.act(data, blockSet.getFenceGate());

        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, blockSet.getSapling());
        BlockStateSmithTypes.POTTED_PLANT.act(data, blockSet.getPottedSaplingBlock());

        WOODEN_SIGN_BLOCK.act(data, blockSet.getSign().getLeft());
        WOODEN_SIGN_BLOCK.act(data, blockSet.getSign().getRight());
        WOODEN_SIGN_BLOCK.act(data, blockSet.getHangingSign().getLeft());
        WOODEN_SIGN_BLOCK.act(data, blockSet.getHangingSign().getRight());
    }

    @Deprecated //use updated tessera variant on next build
    public static BlockStateSmith<SignBlock> WOODEN_SIGN_BLOCK = new BlockStateSmith<>(SignBlock.class, ItemModelSmithTypes.GENERATED_ITEM, (block, provider) -> {
        var name = provider.getBlockName(block);
        var particleTextureName = name.replace("_wall", "").replace("_sign", "").replace("_hanging", "") + "_planks";
        provider.getVariantBuilder(block).partialState().addModels(ConfiguredModel.builder().modelFile(provider.models().sign(name, provider.getBlockTexture(particleTextureName))).build());
    });

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

    private void basedOnTemplate(BlockStateSystemData<SMBlockStateGen> data, RegSupplier<Block> blockSupplier, String templateName, boolean directional, @Nullable ResourceLocation customParticle) {
        BlockStateSmith<Block> smith = new BlockStateSmith<>(Block.class, ItemModelSmithTypes.NO_DATAGEN, (block, provider) -> {
            provider.getVariantBuilder(block).forAllStates(state -> {
                ResourceLocation templateLoc = provider.getBlockTexture("template_" + templateName);
                ResourceLocation baseTexture = provider.getBlockTexture(block);
                ModelFile file = models().withExistingParent(provider.getBlockName(block), templateLoc)
                        .texture("0", baseTexture)
                        .texture("1", customParticle != null ? customParticle : baseTexture)
                        .renderType("cutout");

                var builder = ConfiguredModel.builder();
                builder.modelFile(file);
                if (directional) {
                    builder.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360);
                }
                return builder.build();
            });
        });

        smith.act(data, blockSupplier);
    }
    private void basedOnTemplate(BlockStateSystemData<SMBlockStateGen> data, RegSupplier<Block> blockSupplier, boolean directional, ResourceLocation customParticle) {
        basedOnTemplate(data, blockSupplier, data.provider().getBlockName(blockSupplier.get()), directional, customParticle);
    }

    private void basedOnTemplate(BlockStateSystemData<SMBlockStateGen> data, RegSupplier<Block> blockSupplier, boolean directional) {
        basedOnTemplate(data, blockSupplier, directional, null);
    }
}
