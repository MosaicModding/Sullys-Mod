package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.CopperButtonBlock;
import com.uraneptus.sullysmod.common.blocks.JadeFlingerTotem;
import com.uraneptus.sullysmod.common.blocks.JadeTotemBlock;
import com.uraneptus.sullysmod.common.blocks.WeatheringCopperButtonBlock;
import net.minecraft.server.commands.HelpCommand;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMBlocks {
    public static final BlockSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getBlockSubHelper();

    //Jade
    public static final RegistryObject<Block> JADE_ORE = HELPER.createBlock("jade_ore", () -> new OreBlock(Properties.JADE_ORE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> DEEPSLATE_JADE_ORE = HELPER.createBlock("deepslate_jade_ore", () -> new OreBlock(Properties.DEEPSLATE_JADE_ORE), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAW_JADE_BLOCK = HELPER.createBlock("raw_jade_block", () -> new Block(Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAW_JADE_BRICKS = HELPER.createBlock("raw_jade_bricks", () -> new Block(Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_RAW_JADE = HELPER.createBlock("smooth_raw_jade", () -> new Block(Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAW_JADE_TILES = HELPER.createBlock("raw_jade_tiles", () -> new Block(Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_BLOCK = HELPER.createBlock("jade_block", () -> new Block(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_BRICKS = HELPER.createBlock("jade_bricks", () -> new Block(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMALL_JADE_BRICKS = HELPER.createBlock("small_jade_bricks", () -> new Block(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_SHINGLES = HELPER.createBlock("jade_shingles", () -> new Block(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_TILES = HELPER.createBlock("jade_tiles", () -> new Block(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_JADE = HELPER.createBlock("chiseled_jade", () -> new Block(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_TOTEM = HELPER.createBlock("jade_totem", () -> new JadeTotemBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_FLINGER_TOTEM = HELPER.createBlock("jade_flinger_totem", () -> new JadeFlingerTotem(Properties.JADE_BLOCKS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> JADE_PILLAR = HELPER.createBlock("jade_pillar", () -> new RotatedPillarBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    //Jade Stairs
    public static final RegistryObject<Block> RAW_JADE_BRICK_STAIRS = HELPER.createBlock("raw_jade_brick_stairs", () -> new StairBlock(RAW_JADE_BRICKS.get().defaultBlockState(), Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_RAW_JADE_STAIRS = HELPER.createBlock("smooth_raw_jade_stairs", () -> new StairBlock(SMOOTH_RAW_JADE.get().defaultBlockState(), Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAW_JADE_TILE_STAIRS = HELPER.createBlock("raw_jade_tile_stairs", () -> new StairBlock(RAW_JADE_TILES.get().defaultBlockState(), Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_BRICK_STAIRS = HELPER.createBlock("jade_brick_stairs", () -> new StairBlock(JADE_BRICKS.get().defaultBlockState(), Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMALL_JADE_BRICK_STAIRS = HELPER.createBlock("small_jade_brick_stairs", () -> new StairBlock(SMALL_JADE_BRICKS.get().defaultBlockState(), Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_SHINGLE_STAIRS = HELPER.createBlock("jade_shingle_stairs", () -> new StairBlock(JADE_SHINGLES.get().defaultBlockState(), Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_TILE_STAIRS = HELPER.createBlock("jade_tile_stairs", () -> new StairBlock(JADE_TILES.get().defaultBlockState(), Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    //Jade Slabs
    public static final RegistryObject<Block> RAW_JADE_BRICK_SLAB = HELPER.createBlock("raw_jade_brick_slab", () -> new SlabBlock(Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_RAW_JADE_SLAB = HELPER.createBlock("smooth_raw_jade_slab", () -> new SlabBlock(Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAW_JADE_TILE_SLAB = HELPER.createBlock("raw_jade_tile_slab", () -> new SlabBlock(Properties.RAW_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_BRICK_SLAB = HELPER.createBlock("jade_brick_slab", () -> new SlabBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMALL_JADE_BRICK_SLAB = HELPER.createBlock("small_jade_brick_slab", () -> new SlabBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_SHINGLE_SLAB = HELPER.createBlock("jade_shingle_slab", () -> new SlabBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_TILE_SLAB = HELPER.createBlock("jade_tile_slab", () -> new SlabBlock(Properties.JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

    //Copper Buttons
    public static final RegistryObject<Block> COPPER_BUTTON = HELPER.createBlock("copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.UNAFFECTED, Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> EXPOSED_COPPER_BUTTON = HELPER.createBlock("exposed_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.EXPOSED, Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WEATHERED_COPPER_BUTTON = HELPER.createBlock("weathered_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.WEATHERED, Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> OXIDIZED_COPPER_BUTTON = HELPER.createBlock("oxidized_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.OXIDIZED, Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_COPPER_BUTTON = HELPER.createBlock("waxed_copper_button", () -> new CopperButtonBlock(Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BUTTON = HELPER.createBlock("waxed_exposed_copper_button", () -> new CopperButtonBlock(Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BUTTON = HELPER.createBlock("waxed_weathered_copper_button", () -> new CopperButtonBlock(Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BUTTON = HELPER.createBlock("waxed_oxidized_copper_button", () -> new CopperButtonBlock(Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);

    public static final class Properties {
        public static final BlockBehaviour.Properties JADE_ORE = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F);
        public static final BlockBehaviour.Properties DEEPSLATE_JADE_ORE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE);
        public static final BlockBehaviour.Properties RAW_JADE_BLOCKS = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(5.0F, 6.0F);
        public static final BlockBehaviour.Properties JADE_BLOCKS = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.EMERALD).requiresCorrectToolForDrops().strength(5.0F, 6.0F);
        public static final BlockBehaviour.Properties COPPER_BUTTONS = BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5f);
    }
}
