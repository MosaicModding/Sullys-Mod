package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.content.amber.*;
import com.mosaicmodding.sullysmod.content.gourd.CarvedGourdBlock;
import com.mosaicmodding.sullysmod.content.gourd.EquipableGourdBlock;
import com.mosaicmodding.sullysmod.content.gourd.GourdBlock;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.registry.registry_sets.SimpleBlockRegistrySet;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import static com.mosaicmodding.sullysmod.registry.helpers.SMRegUtils.path;
import static com.mosaicmodding.sullysmod.registry.properties.SMBlockProperties.*;

public class SMBlocks {

    //TODO add block type registration (in case it gets important at some point, then we already have it)

    public static final RegSupplier<Block> AMBER = RegHelper.registerBlock(path("amber"), () -> new AmberBlock(AMBER(false)));
    public static final RegSupplier<Block> AMBER_SOLID = RegHelper.registerBlockWithItem(path("amber_solid"), () -> new AmberBlockSolid(AMBER(true)));
    public static final RegSupplier<Block> ROUGH_AMBER = RegHelper.registerBlockWithItem(path("rough_amber"), () -> new AmberDecorationBlock(AMBER_DECORATION()));
    public static final RegSupplier<Block> AMBER_CAULDRON = RegHelper.registerBlock(path("amber_cauldron"), () -> new AmberLayeredCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)));

    //TODO recipes
    //public static final RegSupplier<Block> AMBER_BRICKS = SMRegHelper.registerBlockWithItem(path("amber_bricks"), () -> new AmberDecorationBlock(AMBER_DECORATION()));
    //public static final RegSupplier<Block> AMBER_BRICK_STAIRS = SMRegHelper.registerBlockWithItem(path("amber_bricks_stairs"), () -> new AmberStairBlock(AMBER_BRICKS, AMBER_DECORATION()));
    //public static final RegSupplier<Block> AMBER_BRICK_SLAB = SMRegHelper.registerBlockWithItem(path("amber_bricks_slab"), () -> new AmberSlabBlock(AMBER_DECORATION()));
    //public static final RegSupplier<Block> AMBER_BRICK_WALL = SMRegHelper.registerBlockWithItem(path("amber_bricks_wall"), () -> new AmberWallBlock(AMBER_DECORATION()));
    //public static final RegSupplier<Block> CHISELED_AMBER = SMRegHelper.registerBlockWithItem(path("chiseled_amber"), () -> new AmberDecorationBlock(AMBER_DECORATION()));
    //public static final RegSupplier<Block> AMBER_PILLAR = SMRegHelper.registerBlockWithItem(path("amber_pillar"), () -> new AmberRotatedPillarBlock(AMBER_DECORATION()));

    public static final SimpleBlockRegistrySet AMBER_BLOCK_SET = new SimpleBlockRegistrySet("amber", AmberDecorationBlock::new, AmberStairBlock::new, AmberSlabBlock::new, AMBER_DECORATION(), SMCommonConfig.AMBER_BLOCKS.getSecond())
            .withWall(AmberWallBlock::new).withPillar(AmberRotatedPillarBlock::new).withChiseled(AmberDecorationBlock::new);

    //public static final EnumMap<RegHelper.VariantType, Supplier<Block>> AMBER_BRICKS_BLOCK_SET = SMRegHelper.registerFullBlockSet(path("amber_bricks"), AMBER_DECORATION());

    public static final RegSupplier<Block> MINIMU_GOURD = RegHelper.registerBlockWithItem(path("minimu_gourd"), () -> new GourdBlock(GOURD(MapColor.COLOR_PINK, false)));
    public static final RegSupplier<Block> CARVED_MINIMU_GOURD = RegHelper.registerBlockWithItem(path("minimu_gourd_carved"), () -> new EquipableGourdBlock(GOURD(MapColor.COLOR_PINK, false)));
    public static final RegSupplier<Block> MINIMU_GOURD_LANTERN = RegHelper.registerBlockWithItem(path("minimu_gourd_lantern"), () -> new CarvedGourdBlock(GOURD(MapColor.COLOR_PINK, true)));

    public static final RegSupplier<Block> GIGANTO_GOURD = RegHelper.registerBlockWithItem(path("giganto_gourd"), () -> new GourdBlock(GOURD(MapColor.COLOR_LIGHT_GREEN, false)));
    public static final RegSupplier<Block> CARVED_GIGANTO_GOURD = RegHelper.registerBlockWithItem(path("giganto_gourd_carved"), () -> new EquipableGourdBlock(GOURD(MapColor.COLOR_LIGHT_GREEN, false)));
    public static final RegSupplier<Block> GIGANTO_GOURD_LANTERN = RegHelper.registerBlockWithItem(path("giganto_gourd_lantern"), () -> new CarvedGourdBlock(GOURD(MapColor.COLOR_LIGHT_GREEN, true)));

    public static void init() {}
}
