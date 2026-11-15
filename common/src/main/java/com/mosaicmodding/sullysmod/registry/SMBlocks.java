package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.content.amber.*;
import com.mosaicmodding.sullysmod.content.gourd.CarvedGourdBlock;
import com.mosaicmodding.sullysmod.content.gourd.EquipableGourdBlock;
import com.mosaicmodding.sullysmod.content.gourd.GourdBlock;
import com.mosaicmodding.sullysmod.content.petrified.PetrifiedSapling;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.core.event.StrippableBlockRegistryEvent;
import com.mosaicmodding.sullysmod.registry.properties.SMBlockProperties;
import com.mosaicmodding.sullysmod.registry.registry_sets.AbstractBlockRegistrySet;
import com.mosaicmodding.sullysmod.registry.registry_sets.WoodBlockRegistrySet;
import com.mosaicmodding.sullysmod.registry.registry_sets.SimpleBlockRegistrySet;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.List;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;
import static com.mosaicmodding.sullysmod.registry.properties.SMBlockProperties.*;

public class SMBlocks {
    public static final List<AbstractBlockRegistrySet> REG_SETS = new ArrayList<>();

    //TODO maybe add block type registration (in case it gets important at some point, then we already have it)

    public static final RegSupplier<Block> AMBER = RegHelper.registerBlock(path("amber"), () -> new AmberBlock(AMBER(false)));
    public static final RegSupplier<Block> AMBER_SOLID = RegHelper.registerBlockWithItem(path("amber_solid"), () -> new AmberBlockSolid(AMBER(true)));
    public static final RegSupplier<Block> ROUGH_AMBER = RegHelper.registerBlockWithItem(path("rough_amber"), () -> new AmberDecorationBlock(AMBER_DECORATION()));
    public static final RegSupplier<Block> AMBER_CAULDRON = RegHelper.registerBlock(path("amber_cauldron"), () -> new AmberLayeredCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)));

    public static final SimpleBlockRegistrySet AMBER_BLOCK_SET = new SimpleBlockRegistrySet("amber", AmberDecorationBlock::new, AmberStairBlock::new, AmberSlabBlock::new, AMBER_DECORATION(), SMCommonConfig.AMBER_BLOCKS.getSecond(), ROUGH_AMBER)
            .withWall(AmberWallBlock::new).withPillar(AmberRotatedPillarBlock::new).withChiseled(AmberDecorationBlock::new);

    //TODO add recipes
    public static final WoodBlockRegistrySet PETRIFIED_WOOD_BLOCK_SET = new WoodBlockRegistrySet.Builder(SMBlockProperties::PETRIFIED)
            .modifyLogProps(() -> PETRIFIED_LOGS(MapColor.TERRACOTTA_ORANGE, MapColor.COLOR_BROWN))
            .modifyStrippedWoodProps(() -> PETRIFIED().mapColor(MapColor.TERRACOTTA_ORANGE))
            .modifyWoodProps(() -> PETRIFIED().mapColor(MapColor.COLOR_BROWN))
            .modifyButtonPressedTicks(20)
            .build("petrified", SMCommonConfig.PETRIFIED_TREES.getSecond(), SMWoodTypes.PETRIFIED, () -> new PetrifiedSapling(SAPLING()));

    public static final RegSupplier<Block> MINIMU_GOURD = RegHelper.registerBlockWithItem(path("minimu_gourd"), () -> new GourdBlock(GOURD(MapColor.COLOR_PINK, false)));
    public static final RegSupplier<Block> CARVED_MINIMU_GOURD = RegHelper.registerBlockWithItem(path("minimu_gourd_carved"), () -> new EquipableGourdBlock(GOURD(MapColor.COLOR_PINK, false)));
    public static final RegSupplier<Block> MINIMU_GOURD_LANTERN = RegHelper.registerBlockWithItem(path("minimu_gourd_lantern"), () -> new CarvedGourdBlock(GOURD(MapColor.COLOR_PINK, true)));

    public static final RegSupplier<Block> GIGANTO_GOURD = RegHelper.registerBlockWithItem(path("giganto_gourd"), () -> new GourdBlock(GOURD(MapColor.COLOR_LIGHT_GREEN, false)));
    public static final RegSupplier<Block> CARVED_GIGANTO_GOURD = RegHelper.registerBlockWithItem(path("giganto_gourd_carved"), () -> new EquipableGourdBlock(GOURD(MapColor.COLOR_LIGHT_GREEN, false)));
    public static final RegSupplier<Block> GIGANTO_GOURD_LANTERN = RegHelper.registerBlockWithItem(path("giganto_gourd_lantern"), () -> new CarvedGourdBlock(GOURD(MapColor.COLOR_LIGHT_GREEN, true)));

    public static void addStrippableBlocks(StrippableBlockRegistryEvent event) {
        event.register(PETRIFIED_WOOD_BLOCK_SET.getLog(), PETRIFIED_WOOD_BLOCK_SET.getStrippedLog(), true);
        event.register(PETRIFIED_WOOD_BLOCK_SET.getWood(), PETRIFIED_WOOD_BLOCK_SET.getStrippedWood(), true);
    }

    public static void init() {
        REG_SETS.add(AMBER_BLOCK_SET);
        REG_SETS.add(PETRIFIED_WOOD_BLOCK_SET);
    }
}
