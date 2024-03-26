package com.uraneptus.sullysmod.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.*;
import com.uraneptus.sullysmod.core.other.SMProperties;
import com.uraneptus.sullysmod.core.registry.util.SMBlockSubRegistryHelper;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings({"deprecation"})
public class SMBlocks {
    public static final SMBlockSubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getBlockSubHelper();
    public static BlockSetType PETRIFIED_BLOCKSET = new BlockSetType(SullysMod.modPrefix("petrified").toString(), true, SMSounds.PETRIFIED_WOOD, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
    public static WoodType PETRIFIED_WOODSET = WoodType.register(new WoodType("sullysmod:petrified", PETRIFIED_BLOCKSET, SMSounds.PETRIFIED_WOOD, SoundType.HANGING_SIGN, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN));

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
    public static final RegistryObject<Block> COPPER_BUTTON = HELPER.createBlock("copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, BlockSetType.STONE, 10, true, WeatheringCopper.WeatherState.UNAFFECTED)); //TODO consider changing BlockSetType to Iron
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
    public static final RegistryObject<Block> ROUGH_AMBER = HELPER.createBlock("rough_amber", () -> new AmberBlock(SMProperties.Blocks.AMBER));
    public static final RegistryObject<Block> CHISELED_AMBER = HELPER.createBlock("chiseled_amber", () -> new AmberBlock(SMProperties.Blocks.AMBER));
    public static final RegistryObject<Block> AMBER_PILLAR = HELPER.createBlock("amber_pillar", () -> new AmberRotatedPillarBlock(SMProperties.Blocks.AMBER));
    public static final RegistryObject<Block> AMBER_LANTERN = HELPER.createBlock("amber_lantern", () -> new AmberBlock(SMProperties.Blocks.AMBER.lightLevel(state -> 15)));

    //Petrified Wood
    //TODO add recipes
    public static final RegistryObject<Block> PETRIFIED_PLANKS = HELPER.createBlock("petrified_planks", () -> new Block(SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STRIPPED_PETRIFIED_LOG = HELPER.createBlock("stripped_petrified_log", () -> strippedLog(MapColor.TERRACOTTA_ORANGE, MapColor.TERRACOTTA_ORANGE));
    public static final RegistryObject<Block> PETRIFIED_LOG = HELPER.createBlock("petrified_log", () -> log(STRIPPED_PETRIFIED_LOG, MapColor.TERRACOTTA_ORANGE, MapColor.COLOR_BROWN));
    public static final RegistryObject<Block> STRIPPED_PETRIFIED_WOOD = HELPER.createBlock("stripped_petrified_wood", () -> new RotatedPillarBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<Block> PETRIFIED_WOOD = HELPER.createBlock("petrified_wood", () -> new PetrifiedLog(STRIPPED_PETRIFIED_WOOD, SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor(MapColor.COLOR_BROWN)));
    public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> PETRIFIED_SIGN = HELPER.createSignBlock("petrified", PETRIFIED_WOODSET, MapColor.TERRACOTTA_ORANGE);
    public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> PETRIFIED_HANGING_SIGN = HELPER.createHangingSignBlock("petrified", PETRIFIED_WOODSET, MapColor.TERRACOTTA_ORANGE);
    public static final RegistryObject<Block> PETRIFIED_PRESSURE_PLATE = HELPER.createBlock("petrified_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, SMProperties.Blocks.PETRIFIED_PRESSURE_PLATE, PETRIFIED_BLOCKSET));
    public static final RegistryObject<Block> PETRIFIED_TRAPDOOR = HELPER.createBlock("petrified_trapdoor", () -> new TrapDoorBlock(SMProperties.Blocks.PETRIFIED_TRAPDOOR, PETRIFIED_BLOCKSET));
    public static final RegistryObject<Block> PETRIFIED_STAIRS = HELPER.createBlock("petrified_stairs", () -> new StairBlock(() -> PETRIFIED_PLANKS.get().defaultBlockState(), SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PETRIFIED_SLAB = HELPER.createBlock("petrified_slab", () -> new SlabBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PETRIFIED_BUTTON = HELPER.createBlock("petrified_button", () -> new ButtonBlock(SMProperties.Blocks.PETRIFIED_BUTTON, PETRIFIED_BLOCKSET, 20, false));
    public static final RegistryObject<Block> PETRIFIED_FENCE_GATE = HELPER.createBlock("petrified_fence_gate", () -> new FenceGateBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops().forceSolidOn(), PETRIFIED_WOODSET));
    public static final RegistryObject<Block> PETRIFIED_FENCE = HELPER.createBlock("petrified_fence", () -> new FenceBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PETRIFIED_DOOR = HELPER.createBlock("petrified_door", () -> new DoorBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops().noOcclusion().strength(0.35F), PETRIFIED_BLOCKSET));
    public static final RegistryObject<Block> PETRIFIED_SAPLING = HELPER.createBlock("petrified_sapling", () -> new PetrifiedSapling(SMProperties.Blocks.PETRIFIED_SAPLING));
    public static final RegistryObject<Block> POTTED_PETRIFIED_SAPLING = HELPER.createBlockNoItem("potted_petrified_sapling", () -> new FlowerPotBlock(PETRIFIED_SAPLING.get(), PropertyUtil.flowerPot()));

    //ANCIENT SKULLS
    public static final RegistryObject<Block> CRACKED_ANCIENT_SKULL = HELPER.createBlock("cracked_ancient_skull", () -> new AncientSkullBlock(AncientSkullBlock.Types.CRACKED, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)));
    //Misc
    public static final RegistryObject<Block> TORTOISE_EGG = HELPER.createBlock("tortoise_egg", () -> new TortoiseEggBlock(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG)));
    public static final RegistryObject<Block> ITEM_STAND = HELPER.createBlock("item_stand", () -> new ItemStandBlock(SMProperties.Blocks.ITEM_STAND));

    //Ancient Skulls

    private static PetrifiedLog log(Supplier<Block> strippedBlock, MapColor pTopMapColor, MapColor pSideMapColor) {
        return new PetrifiedLog(strippedBlock, SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor((blockState) ->
                blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor));
    }

    private static RotatedPillarBlock strippedLog(MapColor pTopMapColor, MapColor pSideMapColor) {
        return new RotatedPillarBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor((blockState) ->
                blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor));
    }
}
