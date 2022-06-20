package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.teamabnormals.blueprint.common.item.InjectedBlockItem;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
    public static final RegistryObject<Block> JADE_ORE = HELPER.createBlockWithItem("jade_ore", () -> new OreBlock(Properties.JADE_ORE), () -> new InjectedBlockItem(Items.DEEPSLATE_COPPER_ORE, SMBlocks.JADE_ORE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Block> DEEPSLATE_JADE_ORE = HELPER.createBlockWithItem("deepslate_jade_ore", () -> new OreBlock(Properties.DEEPSLATE_JADE_ORE), () -> new InjectedBlockItem(SMBlocks.JADE_ORE.get().asItem(), SMBlocks.DEEPSLATE_JADE_ORE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Block> ROUGH_JADE_BLOCK = HELPER.createBlock("rough_jade_block", () -> new Block(Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ROUGH_JADE_BRICKS = HELPER.createBlock("rough_jade_bricks", () -> new Block(Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_ROUGH_JADE = HELPER.createBlock("smooth_rough_jade", () -> new Block(Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ROUGH_JADE_TILES = HELPER.createBlock("rough_jade_tiles", () -> new Block(Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_BLOCK = HELPER.createBlock("polished_jade_block", () -> new Block(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_BRICKS = HELPER.createBlock("polished_jade_bricks", () -> new Block(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMALL_POLISHED_JADE_BRICKS = HELPER.createBlock("small_polished_jade_bricks", () -> new Block(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_SHINGLES = HELPER.createBlock("polished_jade_shingles", () -> new Block(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_TILES = HELPER.createBlock("polished_jade_tiles", () -> new Block(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_CHISELED_JADE = HELPER.createBlock("polished_chiseled_jade", () -> new Block(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_TOTEM = HELPER.createBlock("jade_totem", () -> new SMDirectionalBlock(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JADE_FLINGER_TOTEM = HELPER.createBlock("jade_flinger_totem", () -> new JadeFlingerTotem(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> POLISHED_JADE_PILLAR = HELPER.createBlock("polished_jade_pillar", () -> new RotatedPillarBlock(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

    //Jade Stairs
    public static final RegistryObject<Block> ROUGH_JADE_BRICK_STAIRS = HELPER.createBlock("rough_jade_brick_stairs", () -> new StairBlock(ROUGH_JADE_BRICKS.get().defaultBlockState(), Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_ROUGH_JADE_STAIRS = HELPER.createBlock("smooth_rough_jade_stairs", () -> new StairBlock(SMOOTH_ROUGH_JADE.get().defaultBlockState(), Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ROUGH_JADE_TILE_STAIRS = HELPER.createBlock("rough_jade_tile_stairs", () -> new StairBlock(ROUGH_JADE_TILES.get().defaultBlockState(), Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_BRICK_STAIRS = HELPER.createBlock("polished_jade_brick_stairs", () -> new StairBlock(POLISHED_JADE_BRICKS.get().defaultBlockState(), Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMALL_POLISHED_JADE_BRICK_STAIRS = HELPER.createBlock("small_polished_jade_brick_stairs", () -> new StairBlock(SMALL_POLISHED_JADE_BRICKS.get().defaultBlockState(), Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_SHINGLE_STAIRS = HELPER.createBlock("polished_jade_shingle_stairs", () -> new StairBlock(POLISHED_JADE_SHINGLES.get().defaultBlockState(), Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_TILE_STAIRS = HELPER.createBlock("polished_jade_tile_stairs", () -> new StairBlock(POLISHED_JADE_TILES.get().defaultBlockState(), Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

    //Jade Slabs
    public static final RegistryObject<Block> ROUGH_JADE_BRICK_SLAB = HELPER.createBlock("rough_jade_brick_slab", () -> new SlabBlock(Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_ROUGH_JADE_SLAB = HELPER.createBlock("smooth_rough_jade_slab", () -> new SlabBlock(Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ROUGH_JADE_TILE_SLAB = HELPER.createBlock("rough_jade_tile_slab", () -> new SlabBlock(Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_BRICK_SLAB = HELPER.createBlock("polished_jade_brick_slab", () -> new SlabBlock(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMALL_POLISHED_JADE_BRICK_SLAB = HELPER.createBlock("small_polished_jade_brick_slab", () -> new SlabBlock(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_SHINGLE_SLAB = HELPER.createBlock("polished_jade_shingle_slab", () -> new SlabBlock(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_TILE_SLAB = HELPER.createBlock("polished_jade_tile_slab", () -> new SlabBlock(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

    //Jade Vertical Slabs
    public static final RegistryObject<Block> RAW_JADE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "raw_jade_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMOOTH_RAW_JADE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "smooth_raw_jade_vertical_slab", () -> new VerticalSlabBlock(Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> RAW_JADE_TILE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "raw_jade_tile_vertical_slab", () -> new VerticalSlabBlock(Properties.ROUGH_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_jade_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SMALL_POLISHED_JADE_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "small_polished_jade_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_SHINGLE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_jade_shingle_vertical_slab", () -> new VerticalSlabBlock(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_JADE_TILE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_jade_tile_vertical_slab", () -> new VerticalSlabBlock(Properties.POLISHED_JADE_BLOCKS), CreativeModeTab.TAB_BUILDING_BLOCKS);

    //Copper Buttons
    public static final RegistryObject<Block> COPPER_BUTTON = HELPER.createBlock("copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.UNAFFECTED, Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> EXPOSED_COPPER_BUTTON = HELPER.createBlock("exposed_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.EXPOSED, Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WEATHERED_COPPER_BUTTON = HELPER.createBlock("weathered_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.WEATHERED, Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> OXIDIZED_COPPER_BUTTON = HELPER.createBlock("oxidized_copper_button", () -> new WeatheringCopperButtonBlock(WeatheringCopper.WeatherState.OXIDIZED, Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_COPPER_BUTTON = HELPER.createBlock("waxed_copper_button", () -> new CopperButtonBlock(Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BUTTON = HELPER.createBlock("waxed_exposed_copper_button", () -> new CopperButtonBlock(Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BUTTON = HELPER.createBlock("waxed_weathered_copper_button", () -> new CopperButtonBlock(Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BUTTON = HELPER.createBlock("waxed_oxidized_copper_button", () -> new CopperButtonBlock(Properties.COPPER_BUTTONS), CreativeModeTab.TAB_REDSTONE);

    //Misc
    public static final RegistryObject<Block> TORTOISE_EGG = HELPER.createBlock("tortoise_egg", () -> new TortoiseEggBlock(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG)), CreativeModeTab.TAB_MISC);

    public static final class Properties {
        public static final BlockBehaviour.Properties JADE_ORE = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F);
        public static final BlockBehaviour.Properties DEEPSLATE_JADE_ORE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE);
        public static final BlockBehaviour.Properties ROUGH_JADE_BLOCKS = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(5.0F, 6.0F);
        public static final BlockBehaviour.Properties POLISHED_JADE_BLOCKS = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.EMERALD).requiresCorrectToolForDrops().strength(5.0F, 6.0F);
        public static final BlockBehaviour.Properties COPPER_BUTTONS = BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F);
    }
}
