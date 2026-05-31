package com.uraneptus.sullysmod.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.*;
import com.uraneptus.sullysmod.common.blocks.utilities.SMDirectionalBlock;
import com.uraneptus.sullysmod.core.other.SMProperties;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SMBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SullysMod.MOD_ID);
    public static List<DeferredBlock<? extends Block>> AUTO_TRANSLATE = new ArrayList<>();

    //Jade
    public static final DeferredBlock<Block> JADE_ORE = createBlock("jade_ore", () -> new DropExperienceBlock(ConstantInt.of(0), SMProperties.Blocks.JADE_ORE));
    public static final DeferredBlock<Block> DEEPSLATE_JADE_ORE = createBlock("deepslate_jade_ore", () -> new DropExperienceBlock(ConstantInt.of(0), SMProperties.Blocks.DEEPSLATE_JADE_ORE));
    public static final DeferredBlock<Block> ROUGH_JADE_BLOCK = createBlockNoLang("rough_jade_block", () -> new Block(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final DeferredBlock<Block> ROUGH_JADE_BRICKS = createBlock("rough_jade_bricks", () -> new Block(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final DeferredBlock<Block> JADE_BLOCK = createBlockNoLang("jade_block", () -> new Block(SMProperties.Blocks.JADE_BLOCKS));
    public static final DeferredBlock<Block> JADE_BRICKS = createBlock("jade_bricks", () -> new Block(SMProperties.Blocks.JADE_BLOCKS));
    public static final DeferredBlock<Block> CHISELED_JADE = createBlock("chiseled_jade", () -> new Block(SMProperties.Blocks.JADE_BLOCKS));
    public static final DeferredBlock<Block> JADE_TOTEM = createBlock("jade_totem", () -> new SMDirectionalBlock(SMProperties.Blocks.JADE_BLOCKS));
    public static final DeferredBlock<Block> JADE_FLINGER_TOTEM = createBlock("jade_flinger_totem", () -> new FlingerTotem(SMProperties.Blocks.FLINGER_TOTEM));
    public static final DeferredBlock<Block> JADE_PILLAR = createBlock("jade_pillar", () -> new RotatedPillarBlock(SMProperties.Blocks.JADE_BLOCKS));

    //Jade Stairs
    public static final DeferredBlock<Block> ROUGH_JADE_BRICK_STAIRS = createBlock("rough_jade_brick_stairs", () -> new StairBlock(ROUGH_JADE_BRICKS.get().defaultBlockState(), SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final DeferredBlock<Block> JADE_BRICK_STAIRS = createBlock("jade_brick_stairs", () -> new StairBlock(JADE_BRICKS.get().defaultBlockState(), SMProperties.Blocks.JADE_BLOCKS));

    //Jade Slabs
    public static final DeferredBlock<Block> ROUGH_JADE_BRICK_SLAB = createBlock("rough_jade_brick_slab", () -> new SlabBlock(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final DeferredBlock<Block> JADE_BRICK_SLAB = createBlock("jade_brick_slab", () -> new SlabBlock(SMProperties.Blocks.JADE_BLOCKS));

    //Jade Walls
    public static final DeferredBlock<Block> ROUGH_JADE_BRICK_WALL = createBlock("rough_jade_brick_wall", () -> new WallBlock(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final DeferredBlock<Block> JADE_BRICK_WALL = createBlock("jade_brick_wall", () -> new WallBlock(SMProperties.Blocks.JADE_BLOCKS));

    //Copper Buttons
    public static final DeferredBlock<Block> COPPER_BUTTON = createBlock("copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 10, true, WeatheringCopper.WeatherState.UNAFFECTED));
    public static final DeferredBlock<Block> EXPOSED_COPPER_BUTTON = createBlock("exposed_copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 20, true, WeatheringCopper.WeatherState.EXPOSED));
    public static final DeferredBlock<Block> WEATHERED_COPPER_BUTTON = createBlock("weathered_copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 30, true, WeatheringCopper.WeatherState.WEATHERED));
    public static final DeferredBlock<Block> OXIDIZED_COPPER_BUTTON = createBlock("oxidized_copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 40, true, WeatheringCopper.WeatherState.OXIDIZED));
    public static final DeferredBlock<Block> WAXED_COPPER_BUTTON = createBlock("waxed_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 10, true));
    public static final DeferredBlock<Block> WAXED_EXPOSED_COPPER_BUTTON = createBlock("waxed_exposed_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 20, true));
    public static final DeferredBlock<Block> WAXED_WEATHERED_COPPER_BUTTON = createBlock("waxed_weathered_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 30, true));
    public static final DeferredBlock<Block> WAXED_OXIDIZED_COPPER_BUTTON = createBlock("waxed_oxidized_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 40, true));

    //Amber
    public static final DeferredBlock<Block> AMBER = createBlock("amber", () -> new AmberBlock(SMProperties.Blocks.AMBER));
    public static final DeferredBlock<Block> AMBER_SOLID = createBlock("amber_solid", () -> new AmberBlockSolid(SMProperties.Blocks.AMBER_SOLID));

    public static final DeferredBlock<Block> AMBER_BRICKS = createBlock("amber_bricks", () -> new AmberDecorationBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final DeferredBlock<Block> AMBER_BRICK_STAIRS = createBlock("amber_brick_stairs", () -> new AmberStairBlock(() -> AMBER_BRICKS.get().defaultBlockState(), SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final DeferredBlock<Block> AMBER_BRICK_SLAB = createBlock("amber_brick_slab", () -> new AmberSlabBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final DeferredBlock<Block> AMBER_BRICK_WALL = createBlock("amber_brick_wall", () -> new AmberWallBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final DeferredBlock<Block> ROUGH_AMBER = createBlock("rough_amber", () -> new AmberDecorationBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final DeferredBlock<Block> CHISELED_AMBER = createBlock("chiseled_amber", () -> new AmberDecorationBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final DeferredBlock<Block> AMBER_PILLAR = createBlock("amber_pillar", () -> new AmberRotatedPillarBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final DeferredBlock<Block> AMBER_CAULDRON = createBlockNoItem("amber_cauldron", () -> new AmberLayeredCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)));

    //Gem Lanterns
    public static final DeferredBlock<Block> AMBER_LANTERN = createBlock("amber_lantern", () -> new AmberDecorationBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS.lightLevel(state -> 15)));
    public static final DeferredBlock<Block> JADE_LANTERN = createBlock("jade_lantern", () -> new Block(SMProperties.Blocks.JADE_BLOCKS.lightLevel(state -> 15)));
    public static final DeferredBlock<Block> DIAMOND_LANTERN = createBlock("diamond_lantern", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).lightLevel(state -> 15)));
    public static final DeferredBlock<Block> EMERALD_LANTERN = createBlock("emerald_lantern", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK).lightLevel(state -> 15)));
    public static final DeferredBlock<Block> LAPIS_LANTERN = createBlock("lapis_lantern", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK).lightLevel(state -> 15)));
    public static final DeferredBlock<Block> AMETHYST_LANTERN = createBlock("amethyst_lantern", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).lightLevel(state -> 15)));
    public static final DeferredBlock<Block> QUARTZ_LANTERN = createBlock("quartz_lantern", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).lightLevel(state -> 15)));

    //Petrified Wood
    public static final DeferredBlock<Block> PETRIFIED_PLANKS = createBlock("petrified_planks", () -> new Block(SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> STRIPPED_PETRIFIED_LOG = createBlock("stripped_petrified_log", () -> strippedLog(MapColor.TERRACOTTA_ORANGE, MapColor.TERRACOTTA_ORANGE));
    public static final DeferredBlock<Block> PETRIFIED_LOG = createBlock("petrified_log", () -> log(STRIPPED_PETRIFIED_LOG, MapColor.TERRACOTTA_ORANGE, MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> STRIPPED_PETRIFIED_WOOD = createBlock("stripped_petrified_wood", () -> new RotatedPillarBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final DeferredBlock<Block> PETRIFIED_WOOD = createBlock("petrified_wood", () -> new PetrifiedLog(STRIPPED_PETRIFIED_WOOD, SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor(MapColor.COLOR_BROWN)));
    public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> PETRIFIED_SIGN = createSignBlock("petrified", SMBlocksetTypes.PETRIFIED_WOOD_TYPE.get(), SMProperties.Blocks.PETRIFIED_SIGN);
    public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> PETRIFIED_HANGING_SIGN = createHangingSignBlock("petrified", SMBlocksetTypes.PETRIFIED_WOOD_TYPE.get(), SMProperties.Blocks.PETRIFIED_HANGING_SIGN);
    public static final DeferredBlock<Block> PETRIFIED_PRESSURE_PLATE = createBlock("petrified_pressure_plate", () -> new PressurePlateBlock(SMBlocksetTypes.PETRIFIED_BLOCKSET.get(), SMProperties.Blocks.PETRIFIED_PRESSURE_PLATE));
    public static final DeferredBlock<Block> PETRIFIED_TRAPDOOR = createBlock("petrified_trapdoor", () -> new TrapDoorBlock(SMBlocksetTypes.PETRIFIED_BLOCKSET.get(), SMProperties.Blocks.PETRIFIED_TRAPDOOR));
    public static final DeferredBlock<Block> PETRIFIED_STAIRS = createBlock("petrified_stairs", () -> new StairBlock(PETRIFIED_PLANKS.get().defaultBlockState(), SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> PETRIFIED_SLAB = createBlock("petrified_slab", () -> new SlabBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> PETRIFIED_BUTTON = createBlock("petrified_button", () -> new ButtonBlock(SMBlocksetTypes.PETRIFIED_BLOCKSET.get(), 20, SMProperties.Blocks.PETRIFIED_BUTTON));
    public static final DeferredBlock<Block> PETRIFIED_FENCE_GATE = createBlock("petrified_fence_gate", () -> new FenceGateBlock(SMBlocksetTypes.PETRIFIED_WOOD_TYPE.get(), SMProperties.Blocks.petrified().requiresCorrectToolForDrops().forceSolidOn()));
    public static final DeferredBlock<Block> PETRIFIED_FENCE = createBlock("petrified_fence", () -> new FenceBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> PETRIFIED_DOOR = createBlock("petrified_door", () -> new DoorBlock(SMBlocksetTypes.PETRIFIED_BLOCKSET.get(), SMProperties.Blocks.petrified().requiresCorrectToolForDrops().noOcclusion().strength(0.35F)));
    public static final DeferredBlock<Block> PETRIFIED_SAPLING = createBlock("petrified_sapling", () -> new PetrifiedSapling(SMProperties.Blocks.PETRIFIED_SAPLING));
    public static final DeferredBlock<Block> POTTED_PETRIFIED_SAPLING = createBlockNoItem("potted_petrified_sapling", () -> new FlowerPotBlock(PETRIFIED_SAPLING.get(), SMProperties.Blocks.flowerPot()));

    //Misc
    public static final DeferredBlock<Block> TORTOISE_EGG = createBlock("tortoise_egg", () -> new TortoiseEggBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TURTLE_EGG)));
    public static final DeferredBlock<Block> ITEM_STAND = createBlock("item_stand", () -> new ItemStandBlock(SMProperties.Blocks.ITEM_STAND));
    public static final DeferredBlock<Block> FIXED_BOWL = createBlock("fixed_bowl", () -> new BowlBlock(SMProperties.Blocks.flowerPot()));
    public static final DeferredBlock<Block> FIXED_CUP = createBlock("fixed_cup", () -> new CupBlock(SMProperties.Blocks.flowerPot()));
    public static final DeferredBlock<Block> FIXED_VASE = createBlock("fixed_vase", () -> new VaseBlock(SMProperties.Blocks.flowerPot()));

    private static PetrifiedLog log(Supplier<Block> strippedBlock, MapColor pTopMapColor, MapColor pSideMapColor) {
        return new PetrifiedLog(strippedBlock, SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor((blockState) ->
                blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor));
    }

    private static RotatedPillarBlock strippedLog(MapColor pTopMapColor, MapColor pSideMapColor) {
        return new RotatedPillarBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor((blockState) ->
                blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor));
    }

    public static Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> createSignBlock(String name, WoodType woodType, Block.Properties properties) {
        DeferredBlock<BlueprintStandingSignBlock> standing = createBlockNoItem(name + "_sign", () -> new BlueprintStandingSignBlock(properties, woodType));
        DeferredBlock<BlueprintWallSignBlock> wall = createBlockNoItemNoLang(name + "_wall_sign", () -> new BlueprintWallSignBlock(properties.lootFrom(standing), woodType));
        SMItems.ITEMS.register(name + "_sign", () -> new SignItem(new Item.Properties(), standing.get(), wall.get()));
        return Pair.of(standing, wall);
    }

    public static Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> createHangingSignBlock(String name, WoodType woodType, Block.Properties properties) {
        DeferredBlock<BlueprintCeilingHangingSignBlock> ceiling = createBlockNoItem(name + "_hanging_sign", () -> new BlueprintCeilingHangingSignBlock(properties, woodType));
        DeferredBlock<BlueprintWallHangingSignBlock> wall = createBlockNoItemNoLang(name + "_wall_hanging_sign", () -> new BlueprintWallHangingSignBlock(properties.lootFrom(ceiling), woodType));
        SMItems.ITEMS.register(name + "_hanging_sign", () -> new HangingSignItem(ceiling.get(), wall.get(), new Item.Properties()));
        return Pair.of(ceiling, wall);
    }

    public static <B extends Block> DeferredBlock<B> createBlockNoItemNoLang(String name, Supplier<? extends B> supplier) {
        return BLOCKS.register(name, supplier);
    }

    public static <B extends Block> DeferredBlock<B> createBlockNoItem(String name, Supplier<? extends B> supplier) {
        DeferredBlock<B> block = BLOCKS.register(name, supplier);
        AUTO_TRANSLATE.add(block);
        return block;
    }
    
    public static <B extends Block> DeferredBlock<B> createBlockNoLang(String name, Supplier<? extends B> supplier) {
        DeferredBlock<B> block = BLOCKS.register(name, supplier);
        SMItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    public static <B extends Block> DeferredBlock<B> createBlock(String name, Supplier<? extends B> supplier) {
        DeferredBlock<B> block = BLOCKS.register(name, supplier);
        SMItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        AUTO_TRANSLATE.add(block);
        return block;
    }
}
