package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.content.amber.*;
import com.mosaicmodding.sullysmod.content.gourd.CarvedGourdBlock;
import com.mosaicmodding.sullysmod.content.gourd.EquipableGourdBlock;
import com.mosaicmodding.sullysmod.content.gourd.GourdBlock;
import com.mosaicmodding.sullysmod.content.petrified.PetrifiedLog;
import com.mosaicmodding.sullysmod.content.petrified.PetrifiedSapling;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.registry.registry_sets.SimpleBlockRegistrySet;
import com.mosaicmodding.sullysmod.registry.registry_sets.WoodBlockRegistrySet;
import com.mosaicmodding.tessera.TesseraBlockProperties;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RootedDirtBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;
import static com.mosaicmodding.sullysmod.registry.properties.SMBlockProperties.*;
import static com.mosaicmodding.sullysmod.registry.properties.SMBlockProperties.AMBER_DECORATION;

public class SMBlocks {

    //TODO maybe add block type registration (in case it gets important at some point, then we already have it)

    public static final RegSupplier<Block> AMBER = RegHelper.registerBlock(path("amber"), () -> new AmberBlock(AMBER(false)));
    public static final RegSupplier<Block> AMBER_SOLID = RegHelper.registerBlockWithItem(path("amber_solid"), () -> new AmberBlockSolid(AMBER(true)));
    public static final RegSupplier<Block> ROUGH_AMBER = RegHelper.registerBlockWithItem(path("rough_amber"), () -> new AmberDecorationBlock(AMBER_DECORATION()));
    public static final RegSupplier<Block> AMBER_CAULDRON = RegHelper.registerBlock(path("amber_cauldron"), () -> new AmberLayeredCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)));

    public static final SimpleBlockRegistrySet AMBER_BLOCK_SET = new SimpleBlockRegistrySet("amber", AmberDecorationBlock::new, AmberStairBlock::new, AmberSlabBlock::new, AMBER_DECORATION(), SMCommonConfig.AMBER_BLOCKS.getSecond())
            .withWall(AmberWallBlock::new).withPillar(AmberRotatedPillarBlock::new).withChiseled(AmberDecorationBlock::new);

    //TODO add recipes
    public static final WoodBlockRegistrySet PETRIFIED_WOOD_BLOCK_SET = new WoodBlockRegistrySet.Builder(
            "petrified",
            PETRIFIED(),
            SMCommonConfig.PETRIFIED_TREES.getSecond(),
            SMWoodTypes.PETRIFIED,
            PetrifiedSapling::new)
            .customLogWithStripped((stripped, properties) -> new PetrifiedLog(stripped, PETRIFIED_LOGS(MapColor.TERRACOTTA_ORANGE, MapColor.COLOR_BROWN)))
            .setButtonTicksPressed(20)
            .build();

    public static final RegSupplier<Block> MINIMU_GOURD = RegHelper.registerBlockWithItem(path("minimu_gourd"), () -> new GourdBlock(GOURD(MapColor.COLOR_PINK, false)));
    public static final RegSupplier<Block> CARVED_MINIMU_GOURD = RegHelper.registerBlockWithItem(path("minimu_gourd_carved"), () -> new EquipableGourdBlock(GOURD(MapColor.COLOR_PINK, false)));
    public static final RegSupplier<Block> MINIMU_GOURD_LANTERN = RegHelper.registerBlockWithItem(path("minimu_gourd_lantern"), () -> new CarvedGourdBlock(GOURD(MapColor.COLOR_PINK, true)));

    public static final RegSupplier<Block> GIGANTO_GOURD = RegHelper.registerBlockWithItem(path("giganto_gourd"), () -> new GourdBlock(GOURD(MapColor.COLOR_LIGHT_GREEN, false)));
    public static final RegSupplier<Block> CARVED_GIGANTO_GOURD = RegHelper.registerBlockWithItem(path("giganto_gourd_carved"), () -> new EquipableGourdBlock(GOURD(MapColor.COLOR_LIGHT_GREEN, false)));
    public static final RegSupplier<Block> GIGANTO_GOURD_LANTERN = RegHelper.registerBlockWithItem(path("giganto_gourd_lantern"), () -> new CarvedGourdBlock(GOURD(MapColor.COLOR_LIGHT_GREEN, true)));

    public static void init() {}
}
