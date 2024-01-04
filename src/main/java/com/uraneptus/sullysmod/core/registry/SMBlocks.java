package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.*;
import com.uraneptus.sullysmod.core.other.SMProperties;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMBlocks {
    public static final BlockSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getBlockSubHelper();

    //Jade
    public static final RegistryObject<Block> JADE_ORE = HELPER.createBlock("jade_ore", () -> new DropExperienceBlock(SMProperties.Blocks.JADE_ORE));
    public static final RegistryObject<Block> DEEPSLATE_JADE_ORE = HELPER.createBlock("deepslate_jade_ore", () -> new DropExperienceBlock(SMProperties.Blocks.DEEPSLATE_JADE_ORE));
    public static final RegistryObject<Block> ROUGH_JADE_BLOCK = HELPER.createBlock("rough_jade_block", () -> new Block(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> ROUGH_JADE_BRICKS = HELPER.createBlock("rough_jade_bricks", () -> new Block(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> SMOOTHED_ROUGH_JADE = HELPER.createBlock("smoothed_rough_jade", () -> new Block(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> ROUGH_JADE_TILES = HELPER.createBlock("rough_jade_tiles", () -> new Block(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_JADE_BLOCK = HELPER.createBlock("polished_jade_block", () -> new Block(SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_JADE_BRICKS = HELPER.createBlock("polished_jade_bricks", () -> new Block(SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_SMALL_JADE_BRICKS = HELPER.createBlock("polished_small_jade_bricks", () -> new Block(SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_JADE_SHINGLES = HELPER.createBlock("polished_jade_shingles", () -> new Block(SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_JADE_TILES = HELPER.createBlock("polished_jade_tiles", () -> new Block(SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_CHISELED_JADE = HELPER.createBlock("polished_chiseled_jade", () -> new Block(SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> JADE_TOTEM = HELPER.createBlock("jade_totem", () -> new SMDirectionalBlock(SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> JADE_FLINGER_TOTEM = HELPER.createBlock("jade_flinger_totem", () -> new FlingerTotem(SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_JADE_PILLAR = HELPER.createBlock("polished_jade_pillar", () -> new RotatedPillarBlock(SMProperties.Blocks.POLISHED_JADE_BLOCKS));

    //Jade Stairs
    public static final RegistryObject<Block> ROUGH_JADE_BRICK_STAIRS = HELPER.createBlock("rough_jade_brick_stairs", () -> new StairBlock(() -> ROUGH_JADE_BRICKS.get().defaultBlockState(), SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> SMOOTHED_ROUGH_JADE_STAIRS = HELPER.createBlock("smoothed_rough_jade_stairs", () -> new StairBlock(() -> SMOOTHED_ROUGH_JADE.get().defaultBlockState(), SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> ROUGH_JADE_TILE_STAIRS = HELPER.createBlock("rough_jade_tile_stairs", () -> new StairBlock(() -> ROUGH_JADE_TILES.get().defaultBlockState(), SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_JADE_BRICK_STAIRS = HELPER.createBlock("polished_jade_brick_stairs", () -> new StairBlock(() -> POLISHED_JADE_BRICKS.get().defaultBlockState(), SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_SMALL_JADE_BRICK_STAIRS = HELPER.createBlock("polished_small_jade_brick_stairs", () -> new StairBlock(() -> POLISHED_SMALL_JADE_BRICKS.get().defaultBlockState(), SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_JADE_SHINGLE_STAIRS = HELPER.createBlock("polished_jade_shingle_stairs", () -> new StairBlock(() -> POLISHED_JADE_SHINGLES.get().defaultBlockState(), SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_JADE_TILE_STAIRS = HELPER.createBlock("polished_jade_tile_stairs", () -> new StairBlock(() -> POLISHED_JADE_TILES.get().defaultBlockState(), SMProperties.Blocks.POLISHED_JADE_BLOCKS));

    //Jade Slabs
    public static final RegistryObject<Block> ROUGH_JADE_BRICK_SLAB = HELPER.createBlock("rough_jade_brick_slab", () -> new SlabBlock(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> SMOOTHED_ROUGH_JADE_SLAB = HELPER.createBlock("smoothed_rough_jade_slab", () -> new SlabBlock(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> ROUGH_JADE_TILE_SLAB = HELPER.createBlock("rough_jade_tile_slab", () -> new SlabBlock(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_JADE_BRICK_SLAB = HELPER.createBlock("polished_jade_brick_slab", () -> new SlabBlock(SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_SMALL_JADE_BRICK_SLAB = HELPER.createBlock("polished_small_jade_brick_slab", () -> new SlabBlock(SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_JADE_SHINGLE_SLAB = HELPER.createBlock("polished_jade_shingle_slab", () -> new SlabBlock(SMProperties.Blocks.POLISHED_JADE_BLOCKS));
    public static final RegistryObject<Block> POLISHED_JADE_TILE_SLAB = HELPER.createBlock("polished_jade_tile_slab", () -> new SlabBlock(SMProperties.Blocks.POLISHED_JADE_BLOCKS));

    //Copper Buttons
    public static final RegistryObject<Block> COPPER_BUTTON = HELPER.createBlock("copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, BlockSetType.STONE, 10, true, WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> EXPOSED_COPPER_BUTTON = HELPER.createBlock("exposed_copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, BlockSetType.STONE, 20, true, WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WEATHERED_COPPER_BUTTON = HELPER.createBlock("weathered_copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, BlockSetType.STONE, 30, true, WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> OXIDIZED_COPPER_BUTTON = HELPER.createBlock("oxidized_copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, BlockSetType.STONE, 40, true, WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_COPPER_BUTTON = HELPER.createBlock("waxed_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, BlockSetType.STONE, 10, true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BUTTON = HELPER.createBlock("waxed_exposed_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, BlockSetType.STONE, 20, true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BUTTON = HELPER.createBlock("waxed_weathered_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, BlockSetType.STONE, 30, true));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BUTTON = HELPER.createBlock("waxed_oxidized_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, BlockSetType.STONE, 40, true));

    //Amber
    public static final RegistryObject<Block> AMBER = HELPER.createBlock("amber", () -> new AmberBlock(SMProperties.Blocks.AMBER.noOcclusion().dynamicShape()));
    public static final RegistryObject<Block> AMBER_BRICKS = HELPER.createBlock("amber_bricks", () -> new AmberBlock(SMProperties.Blocks.AMBER));
    public static final RegistryObject<Block> AMBER_BRICK_STAIRS = HELPER.createBlock("amber_brick_stairs", () -> new AmberStairBlock(AMBER_BRICKS.get().defaultBlockState(), SMProperties.Blocks.AMBER));
    public static final RegistryObject<Block> AMBER_BRICK_SLAB = HELPER.createBlock("amber_brick_slab", () -> new AmberSlabBlock(SMProperties.Blocks.AMBER));
    public static final RegistryObject<Block> AMBER_BRICK_WALL = HELPER.createBlock("amber_brick_wall", () -> new AmberWallBlock(SMProperties.Blocks.AMBER));
    //Misc
    public static final RegistryObject<Block> TORTOISE_EGG = HELPER.createBlock("tortoise_egg", () -> new TortoiseEggBlock(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG)));
}
