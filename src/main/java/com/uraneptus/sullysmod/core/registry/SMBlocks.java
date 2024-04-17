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
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import com.uraneptus.sullysmod.core.other.SMTextUtil;
import com.uraneptus.sullysmod.core.registry.util.SMBlockSubRegistryHelper;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
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

    //Misc
    public static final RegistryObject<Block> TORTOISE_EGG = HELPER.createBlock("tortoise_egg", () -> new TortoiseEggBlock(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG)));
    public static final RegistryObject<Block> ITEM_STAND = HELPER.createBlock("item_stand", () -> new ItemStandBlock(SMProperties.Blocks.ITEM_STAND));

    //Ancient Skulls
    public static List<Supplier<Block>> ANCIENT_SKULLS = new ArrayList<>();
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> CRACKED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.CRACKED, "The head of a giant ancient creature, it has a noticeable amount of cracks", NoteBlockInstrument.ZOMBIE, 43); //TODO add custom sounds
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> CRESTED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.CRESTED, "The large head of a now extinct animal, the beak seems more hollow than others", NoteBlockInstrument.ZOMBIE, 40);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> FLATBILLED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.FLATBILLED, "The head of an animal that went extinct long ago", NoteBlockInstrument.ZOMBIE, 37);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> GIGANTIC_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.GIGANTIC, "The gigantic head of an ancient creature, it feels familiar", NoteBlockInstrument.ZOMBIE, 43);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> HORNED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.HORNED, "The head of an extinct creature with a broken off horn on its head", NoteBlockInstrument.ZOMBIE, 36);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> LONG_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.LONG, "A long head from an extinct animals, it has a large overbite", NoteBlockInstrument.ZOMBIE, 34);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> TINY_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.TINY, "The head of a small chicken sized critter that lived long ago", NoteBlockInstrument.ZOMBIE, 30);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> WIDE_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.WIDE, "The head of an extinct animal with a strangely wide head and giant eye sockets", NoteBlockInstrument.ZOMBIE, 37);

    public static Pair<RegistryObject<Block>, RegistryObject<Block>> registerAncientSkull(AncientSkullBlock.Types type, String description, NoteBlockInstrument skullSound, int price) {
        String typeName = SMTextUtil.convertSkullTypeToString(type);
        String skullName = typeName + "_ancient_skull";
        RegistryObject<Block> skull = HELPER.createBlockNoItem(skullName, () -> new AncientSkullBlock(type, SMProperties.Blocks.ancientSkulls(skullSound)));
        RegistryObject<Block> wallSkull = HELPER.createBlockNoItem(typeName + "_ancient_wall_skull", () -> new AncientWallSkullBlock(type, SMProperties.Blocks.ancientSkulls(skullSound).lootFrom(skull)));
        ANCIENT_SKULLS.add(skull);
        RegistryObject<Item> skullItem = SMItems.HELPER.createItem(skullName, () -> new StandingAndWallBlockItem(skull.get(), wallSkull.get(), SMProperties.Items.artifacts(), Direction.DOWN));
        SMItems.ARTIFACT_DESC_MAP.put(skullItem, SMTextUtil.addSMTranslatable("artifact." + skullName + ".desc", description).withStyle(SMTextDefinitions.ARTIFACT_DESC_STYLE));
        SMItems.TRADES.put(skullItem, price);

        return Pair.of(skull, wallSkull);
    }

    private static PetrifiedLog log(Supplier<Block> strippedBlock, MapColor pTopMapColor, MapColor pSideMapColor) {
        return new PetrifiedLog(strippedBlock, SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor((blockState) ->
                blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor));
    }

    private static RotatedPillarBlock strippedLog(MapColor pTopMapColor, MapColor pSideMapColor) {
        return new RotatedPillarBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor((blockState) ->
                blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor));
    }
}
