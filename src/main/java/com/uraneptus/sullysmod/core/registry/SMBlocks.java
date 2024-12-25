package com.uraneptus.sullysmod.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.*;
import com.uraneptus.sullysmod.common.blocks.utilities.SMDirectionalBlock;
import com.uraneptus.sullysmod.core.other.SMProperties;
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import com.uraneptus.sullysmod.core.other.SMTextUtil;
import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SullysMod.MOD_ID);
    public static List<RegistryObject<? extends Block>> AUTO_TRANSLATE = new ArrayList<>();

    //Jade
    public static final RegistryObject<Block> JADE_ORE = createBlock("jade_ore", () -> new DropExperienceBlock(SMProperties.Blocks.JADE_ORE));
    public static final RegistryObject<Block> DEEPSLATE_JADE_ORE = createBlock("deepslate_jade_ore", () -> new DropExperienceBlock(SMProperties.Blocks.DEEPSLATE_JADE_ORE));
    public static final RegistryObject<Block> ROUGH_JADE_BLOCK = createBlockNoLang("rough_jade_block", () -> new Block(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> ROUGH_JADE_BRICKS = createBlock("rough_jade_bricks", () -> new Block(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> JADE_BLOCK = createBlockNoLang("jade_block", () -> new Block(SMProperties.Blocks.JADE_BLOCKS));
    public static final RegistryObject<Block> JADE_BRICKS = createBlock("jade_bricks", () -> new Block(SMProperties.Blocks.JADE_BLOCKS));
    public static final RegistryObject<Block> CHISELED_JADE = createBlock("chiseled_jade", () -> new Block(SMProperties.Blocks.JADE_BLOCKS));
    public static final RegistryObject<Block> JADE_TOTEM = createBlock("jade_totem", () -> new SMDirectionalBlock(SMProperties.Blocks.JADE_BLOCKS));
    public static final RegistryObject<Block> JADE_FLINGER_TOTEM = createBlock("jade_flinger_totem", () -> new FlingerTotem(SMProperties.Blocks.FLINGER_TOTEM));
    public static final RegistryObject<Block> JADE_PILLAR = createBlock("jade_pillar", () -> new RotatedPillarBlock(SMProperties.Blocks.JADE_BLOCKS));

    //Jade Stairs
    public static final RegistryObject<Block> ROUGH_JADE_BRICK_STAIRS = createBlock("rough_jade_brick_stairs", () -> new StairBlock(() -> ROUGH_JADE_BRICKS.get().defaultBlockState(), SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> JADE_BRICK_STAIRS = createBlock("jade_brick_stairs", () -> new StairBlock(() -> JADE_BRICKS.get().defaultBlockState(), SMProperties.Blocks.JADE_BLOCKS));

    //Jade Slabs
    public static final RegistryObject<Block> ROUGH_JADE_BRICK_SLAB = createBlock("rough_jade_brick_slab", () -> new SlabBlock(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> JADE_BRICK_SLAB = createBlock("jade_brick_slab", () -> new SlabBlock(SMProperties.Blocks.JADE_BLOCKS));

    //Jade Walls
    public static final RegistryObject<Block> ROUGH_JADE_BRICK_WALL = createBlock("rough_jade_brick_wall", () -> new WallBlock(SMProperties.Blocks.ROUGH_JADE_BLOCKS));
    public static final RegistryObject<Block> JADE_BRICK_WALL = createBlock("jade_brick_wall", () -> new WallBlock(SMProperties.Blocks.JADE_BLOCKS));

    //Copper Buttons
    public static final RegistryObject<Block> COPPER_BUTTON = createBlock("copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 10, true, WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> EXPOSED_COPPER_BUTTON = createBlock("exposed_copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 20, true, WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WEATHERED_COPPER_BUTTON = createBlock("weathered_copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 30, true, WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> OXIDIZED_COPPER_BUTTON = createBlock("oxidized_copper_button", () -> new WeatheringCopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 40, true, WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_COPPER_BUTTON = createBlock("waxed_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 10, true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BUTTON = createBlock("waxed_exposed_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 20, true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BUTTON = createBlock("waxed_weathered_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 30, true));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BUTTON = createBlock("waxed_oxidized_copper_button", () -> new CopperButtonBlock(SMProperties.Blocks.COPPER_BUTTONS, SMBlocksetTypes.COPPER_BLOCKSET.get(), 40, true));

    //Amber
    public static final RegistryObject<Block> AMBER = createBlock("amber", () -> new AmberBlock(SMProperties.Blocks.AMBER));
    public static final RegistryObject<Block> AMBER_BRICKS = createBlock("amber_bricks", () -> new SolidAmberBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final RegistryObject<Block> AMBER_BRICK_STAIRS = createBlock("amber_brick_stairs", () -> new AmberStairBlock(() -> AMBER_BRICKS.get().defaultBlockState(), SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final RegistryObject<Block> AMBER_BRICK_SLAB = createBlock("amber_brick_slab", () -> new AmberSlabBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final RegistryObject<Block> AMBER_BRICK_WALL = createBlock("amber_brick_wall", () -> new AmberWallBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final RegistryObject<Block> ROUGH_AMBER = createBlock("rough_amber", () -> new SolidAmberBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final RegistryObject<Block> CHISELED_AMBER = createBlock("chiseled_amber", () -> new SolidAmberBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final RegistryObject<Block> AMBER_PILLAR = createBlock("amber_pillar", () -> new AmberRotatedPillarBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS));
    public static final RegistryObject<LiquidBlock> MOLTEN_AMBER_BLOCK = createBlockNoItem("molten_amber_block", () -> new LiquidBlock(SMFluids.SOURCE_MOLTEN_AMBER, BlockBehaviour.Properties.copy(Blocks.LAVA).speedFactor(0.5F).lightLevel(blockState -> 0)));
    public static final RegistryObject<Block> AMBER_CAULDRON = createBlockNoItem("amber_cauldron", () -> new AmberLayeredCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)));

    //Gem Lanterns
    public static final RegistryObject<Block> AMBER_LANTERN = createBlock("amber_lantern", () -> new SolidAmberBlock(SMProperties.Blocks.AMBER_BUILDING_BLOCKS.lightLevel(state -> 15)));
    public static final RegistryObject<Block> JADE_LANTERN = createBlock("jade_lantern", () -> new Block(SMProperties.Blocks.JADE_BLOCKS.lightLevel(state -> 15)));
    public static final RegistryObject<Block> DIAMOND_LANTERN = createBlock("diamond_lantern", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).lightLevel(state -> 15)));
    public static final RegistryObject<Block> EMERALD_LANTERN = createBlock("emerald_lantern", () -> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).lightLevel(state -> 15)));
    public static final RegistryObject<Block> LAPIS_LANTERN = createBlock("lapis_lantern", () -> new Block(BlockBehaviour.Properties.copy(Blocks.LAPIS_BLOCK).lightLevel(state -> 15)));
    public static final RegistryObject<Block> AMETHYST_LANTERN = createBlock("amethyst_lantern", () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).lightLevel(state -> 15)));
    public static final RegistryObject<Block> QUARTZ_LANTERN = createBlock("quartz_lantern", () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).lightLevel(state -> 15)));

    //Petrified Wood
    public static final RegistryObject<Block> PETRIFIED_PLANKS = createBlock("petrified_planks", () -> new Block(SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STRIPPED_PETRIFIED_LOG = createBlock("stripped_petrified_log", () -> strippedLog(MapColor.TERRACOTTA_ORANGE, MapColor.TERRACOTTA_ORANGE));
    public static final RegistryObject<Block> PETRIFIED_LOG = createBlock("petrified_log", () -> log(STRIPPED_PETRIFIED_LOG, MapColor.TERRACOTTA_ORANGE, MapColor.COLOR_BROWN));
    public static final RegistryObject<Block> STRIPPED_PETRIFIED_WOOD = createBlock("stripped_petrified_wood", () -> new RotatedPillarBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<Block> PETRIFIED_WOOD = createBlock("petrified_wood", () -> new PetrifiedLog(STRIPPED_PETRIFIED_WOOD, SMProperties.Blocks.petrified().requiresCorrectToolForDrops().mapColor(MapColor.COLOR_BROWN)));
    public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> PETRIFIED_SIGN = createSignBlock("petrified", SMBlocksetTypes.PETRIFIED_WOOD_TYPE.get(), SMProperties.Blocks.PETRIFIED_SIGNS);
    public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> PETRIFIED_HANGING_SIGN = createHangingSignBlock("petrified", SMBlocksetTypes.PETRIFIED_WOOD_TYPE.get(), SMProperties.Blocks.PETRIFIED_SIGNS);
    public static final RegistryObject<Block> PETRIFIED_PRESSURE_PLATE = createBlock("petrified_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, SMProperties.Blocks.PETRIFIED_PRESSURE_PLATE, SMBlocksetTypes.PETRIFIED_BLOCKSET.get()));
    public static final RegistryObject<Block> PETRIFIED_TRAPDOOR = createBlock("petrified_trapdoor", () -> new TrapDoorBlock(SMProperties.Blocks.PETRIFIED_TRAPDOOR, SMBlocksetTypes.PETRIFIED_BLOCKSET.get()));
    public static final RegistryObject<Block> PETRIFIED_STAIRS = createBlock("petrified_stairs", () -> new StairBlock(() -> PETRIFIED_PLANKS.get().defaultBlockState(), SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PETRIFIED_SLAB = createBlock("petrified_slab", () -> new SlabBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PETRIFIED_BUTTON = createBlock("petrified_button", () -> new ButtonBlock(SMProperties.Blocks.PETRIFIED_BUTTON, SMBlocksetTypes.PETRIFIED_BLOCKSET.get(), 20, false));
    public static final RegistryObject<Block> PETRIFIED_FENCE_GATE = createBlock("petrified_fence_gate", () -> new FenceGateBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops().forceSolidOn(), SMBlocksetTypes.PETRIFIED_WOOD_TYPE.get()));
    public static final RegistryObject<Block> PETRIFIED_FENCE = createBlock("petrified_fence", () -> new FenceBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PETRIFIED_DOOR = createBlock("petrified_door", () -> new DoorBlock(SMProperties.Blocks.petrified().requiresCorrectToolForDrops().noOcclusion().strength(0.35F), SMBlocksetTypes.PETRIFIED_BLOCKSET.get()));
    public static final RegistryObject<Block> PETRIFIED_SAPLING = createBlock("petrified_sapling", () -> new PetrifiedSapling(SMProperties.Blocks.PETRIFIED_SAPLING));
    public static final RegistryObject<Block> POTTED_PETRIFIED_SAPLING = createBlockNoItem("potted_petrified_sapling", () -> new FlowerPotBlock(null, PETRIFIED_SAPLING, PropertyUtil.flowerPot()));

    //Misc
    public static final RegistryObject<Block> TORTOISE_EGG = createBlock("tortoise_egg", () -> new TortoiseEggBlock(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG)));
    public static final RegistryObject<Block> ITEM_STAND = createBlock("item_stand", () -> new ItemStandBlock(SMProperties.Blocks.ITEM_STAND));

    //Ancient Skulls
    public static List<Supplier<Block>> ANCIENT_SKULLS = new ArrayList<>();
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> CRACKED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.CRACKED, "The head of a giant ancient creature, it has a noticeable amount of cracks", 43);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> CRESTED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.CRESTED, "The large head of a now extinct animal, the beak seems more hollow than others", 40);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> FLATBILLED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.FLATBILLED, "The head of an animal that went extinct long ago", 37);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> GIGANTIC_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.GIGANTIC, "The gigantic head of an ancient creature, it feels familiar", 43);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> HORNED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.HORNED, "The head of an extinct creature with a broken off horn on its head", 36);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> LONG_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.LONG, "A long head from an extinct animals, it has a large overbite", 34);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> TINY_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.TINY, "The head of a small chicken sized critter that lived long ago", 30);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> WIDE_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.WIDE, "The head of an extinct animal with a strangely wide head and giant eye sockets", 37);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> RIBBED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.RIBBED, "Has a small ribcage directly attached to the skull", 37);
    public static final Pair<RegistryObject<Block>, RegistryObject<Block>> UNICORN_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.UNICORN, "A normal horse skull, but with a large horn protruding from it", 25);

    public static Pair<RegistryObject<Block>, RegistryObject<Block>> registerAncientSkull(AncientSkullBlock.Types type, String description, int price) {
        String typeName = SMTextUtil.convertSkullTypeToString(type);
        String skullName = typeName + "_ancient_skull";
        RegistryObject<Block> skull = createBlockNoItemNoLang(skullName, () -> new AncientSkullBlock(type, SMProperties.Blocks.ancientSkulls()));
        RegistryObject<Block> wallSkull = createBlockNoItemNoLang(typeName + "_ancient_wall_skull", () -> new AncientWallSkullBlock(type, SMProperties.Blocks.ancientSkulls().lootFrom(skull)));
        ANCIENT_SKULLS.add(skull);
        RegistryObject<Item> skullItem = SMItems.ITEMS.register(skullName, () -> new StandingAndWallBlockItem(skull.get(), wallSkull.get(), SMProperties.Items.artifacts(), Direction.DOWN));
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

    public static Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> createSignBlock(String name, WoodType woodType, Block.Properties properties) {
        RegistryObject<BlueprintStandingSignBlock> standing = createBlockNoItem(name + "_sign", () -> new BlueprintStandingSignBlock(properties, woodType));
        RegistryObject<BlueprintWallSignBlock> wall = createBlockNoItemNoLang(name + "_wall_sign", () -> new BlueprintWallSignBlock(properties.lootFrom(standing), woodType));
        SMItems.ITEMS.register(name + "_sign", () -> new SignItem(new Item.Properties(), standing.get(), wall.get()));
        return Pair.of(standing, wall);
    }

    public static Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> createHangingSignBlock(String name, WoodType woodType, Block.Properties properties) {
        RegistryObject<BlueprintCeilingHangingSignBlock> ceiling = createBlockNoItem(name + "_hanging_sign", () -> new BlueprintCeilingHangingSignBlock(properties, woodType));
        RegistryObject<BlueprintWallHangingSignBlock> wall = createBlockNoItemNoLang(name + "_wall_hanging_sign", () -> new BlueprintWallHangingSignBlock(properties.lootFrom(ceiling), woodType));
        SMItems.ITEMS.register(name + "_hanging_sign", () -> new HangingSignItem(ceiling.get(), wall.get(), new Item.Properties()));
        return Pair.of(ceiling, wall);
    }

    private static <B extends Block> RegistryObject<B> createBlockNoItemNoLang(String name, Supplier<? extends B> supplier) {
        return BLOCKS.register(name, supplier);
    }

    private static <B extends Block> RegistryObject<B> createBlockNoItem(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        System.out.println(block.getId());
        AUTO_TRANSLATE.add(block);
        return block;
    }
    
    private static <B extends Block> RegistryObject<B> createBlockNoLang(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        SMItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        SMItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        AUTO_TRANSLATE.add(block);
        return block;
    }
}
