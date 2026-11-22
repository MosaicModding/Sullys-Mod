package com.mosaicmodding.sullysmod.registry;

import com.mosaicmodding.sullysmod.content.artifacts.*;
import com.mosaicmodding.sullysmod.registry.properties.SMBlockProperties;
import com.mosaicmodding.sullysmod.registry.properties.SMFoods;
import com.mosaicmodding.sullysmod.registry.properties.SMItemProperties;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class SMArtifacts {
    //We have this here so KubeJS can access it!
    //todo public static final Rarity ANCIENT = Rarity.create("sullysmod:ancient", style -> style.withColor(15107584));

    public static final List<RegSupplier<Item>> ARTIFACT_DESC_MAP = new ArrayList<>();
    public static final Map<Supplier<Item>, Integer> TRADES = new HashMap<>();

    public static final RegSupplier<Item> PRIMITIVE_KNIFE = registerArtifact("primitive_knife", "A small knife made from obsidian", () -> new ArtifactWeaponItem(5, -2.5F, null, SMItemProperties.ARTIFACT_DURABILITY(20)), 15);
    public static final RegSupplier<Item> MINERS_HELMET = registerArtifact("miners_helmet", "Looks like it’s previous owner couldn’t get the candle lit anymore",
            () -> new ArtifactHelmetItem(SMArmorMaterials.MINERS_HELMET, SMItemProperties.ARTIFACT_DURABILITY(9)), 15);
    public static final RegSupplier<Item> SMALL_DENTED_HELMET = registerArtifact("small_dented_helmet", "A small rusty helmet. Barely fits",
            () -> new ArtifactHelmetItem(SMArmorMaterials.SMALL_DENTED_HELMET, SMItemProperties.ARTIFACT_DURABILITY(9)), 22);
    public static final RegSupplier<Item> LOST_CROWN = registerArtifact("lost_crown", "Once belonged to the king of a now fallen kingdom",
            () -> new ArtifactHelmetItem(SMArmorMaterials.LOST_CROWN, SMItemProperties.ARTIFACT_DURABILITY(9)), 30);
    public static final RegSupplier<Item> JADE_AMULET = registerArtifact("jade_amulet", "A creature is carefully sculpted from the stone", 20);
    public static final RegSupplier<Item> PRIMITIVE_RING = registerArtifact("primitive_ring", "A roughly made metal ring", 10);
    public static final RegSupplier<Item> RUSTY_TOOLS = registerArtifact("rusty_tools", "Maybe their owners are still out there", 9);
    public static final RegSupplier<Item> COPPER_COG = registerArtifact("copper_cog", "Said to have been part of living creatures", 23);
    public static final RegSupplier<Item> PETRIFIED_COOKIE = registerArtifact("petrified_cookie", "Petrified food is still food, just extra crisp", () -> new PetrifiedCookieItem(SMItemProperties.ARTIFACT().food(SMFoods.PETRIFIED_COOKIE)), 12);
    public static final RegSupplier<Item> ARROWHEAD = registerArtifact("arrowhead", "The tip of an ancient arrow", 5);
    public static final RegSupplier<Item> DEATH_WHISTLE = registerArtifact("death_whistle", "Screeches horrible noises when blown into", DeathWhistleItem::new, 20);
    public static final RegSupplier<Item> OMINOUS_TABLET = registerArtifact("ominous_tablet", "A dark figure is carved into the stone", 25);
    public static final RegSupplier<Item> MOON_TABLET = registerArtifact("moon_tablet", "Has a carved image of the moon", 27);

    public static final RegSupplier<Item> RED_CAP = registerArtifact("red_cap", "A tiny red cap. It’s too small to wear and the fabric feels strange", 20);
    public static final RegSupplier<Item> METALLIC_SKULL = registerArtifact("metallic_skull", "The a metallic skull attached to broken off bars", 25);
    public static final RegSupplier<Item> LOST_BAG = registerArtifact("lost_bag", "A small lightweight bag sloppily sewn together", 14);
    public static final RegSupplier<Item> MYSTERIOUS_PLATE = registerArtifact("mysterious_plate", "Made from an unknown material", 35);
    public static final RegSupplier<Item> AMBER_ENCASED_BUG = registerArtifact("amber_encased_bug", "A small bug that was covered by tree sap ages ago", 20);
    public static final RegSupplier<Item> FOSSILISED_SHELLS = registerArtifact("fossilised_shells", "Shells from a sea creature that lived long ago", 27);
    public static final RegSupplier<Item> FOSSILISED_BONE = registerArtifact("fossilised_bone", "A large bone of an extinct creature", 27);
    public static final RegSupplier<Item> FOSSILISED_FOOTSTEP = registerArtifact("fossilised_footstep", "An ancient footprint that never faded", 25);
    public static final RegSupplier<Item> FOSSILISED_FISH = registerArtifact("fossilised_fish", "The bones of a small fish from times past", 27);
    public static final RegSupplier<Item> TORN_MANUSCRIPT = registerArtifact("torn_manuscript", "Part of a manuscript with an unknown language", 18);
    public static final RegSupplier<Item> LOST_JOURNAL = registerArtifact("lost_journal", "Waterlogged and left unreadable, it has a few pages ripped out", 17);
    public static final RegSupplier<Item> LOST_SKETCHBOOK = registerArtifact("lost_sketchbook", "A small book with scratchy drawings of an unknown large mouthed biped", 24);
    public static final RegSupplier<Item> LOST_RECIPE_BOOK = registerArtifact("lost_recipe_book", "Mostly ruined and unreadable, but still has some recipes inside", 12);
    public static final RegSupplier<Item> GOLDEN_BELT_BUCKLE = registerArtifact("golden_belt_buckle", "Has a peculiar shape and it’s leather feels strange", 26);
    public static final RegSupplier<Item> DEEPSLATE_VASE = registerArtifact("deepslate_vase", "Who would’ve needed a vase made from deepslate?", 25);
    public static final RegSupplier<Item> SMALL_GEODE = registerArtifact("small_geode", "Kind of cute", 23);
    public static final RegSupplier<Item> TORN_CLOTH = registerArtifact("torn_cloth", "A dirty torn off piece of clothing", 6);
    public static final RegSupplier<Item> EMERALD_EARRING = registerArtifact("emerald_earring", "Besides the beautiful emerald, it looks sloppily put together", 17);
    public static final RegSupplier<Item> BROKEN_BOTTLE = registerArtifact("broken_bottle", "The top half of a bottle", () -> new ArtifactWeaponItem(4, -1.2F, SMSoundEvents.BROKEN_BOTTLE_SHATTERS, SMItemProperties.ARTIFACT_DURABILITY(1)), 5);

    public static final RegSupplier<Item> BROKEN_FANCY_DAGGER = registerArtifact("broken_fancy_dagger", "Must have been used for something sinister", 27);
    public static final RegSupplier<Item> BROKEN_MUG = registerArtifact("broken_mug", "Once powerful, now just a useless old cup", 13);
    public static final RegSupplier<Item> CAVE_CARROT = registerArtifact("cave_carrot", "Very common, but it serves for a decent meal", () -> new Item(SMItemProperties.ARTIFACT().food(SMFoods.CAVE_CARROT)), 3);
    public static final RegSupplier<Item> COPPER_SPOON = registerArtifact("copper_spoon", "An oxidized old cauldron spoon", 7);
    public static final RegSupplier<Item> DARK_TABLET = registerArtifact("dark_tablet", "Etched into the stone are frantic words of an unknown language", 25);
    public static final RegSupplier<Item> EYE_TABLET = registerArtifact("eye_tablet", "An eye is etched into the dark stone", 27);
    public static final RegSupplier<Item> FOSSILISED_BEAK = registerArtifact("fossilised_beak", "A small beak separated from its body", 27);
    public static final RegSupplier<Item> GLOOMY_TABLET = registerArtifact("gloomy_tablet", "Two small eyes are carved into a dark shadow", 26);

    public static final RegSupplier<Item> JADE_RING = registerArtifact("jade_ring", "A delicate stone ring", 23);
    public static final RegSupplier<Item> LOST_BESTIARY = registerArtifact("lost_bestiary", "Shows illustrations of animals", 20);
    public static final RegSupplier<Item> LOST_PICTURE_BOOK = registerArtifact("lost_picture_book", "Shows images of a giant prosperous city", 20);
    public static final RegSupplier<Item> LOST_SHOE = registerArtifact("lost_shoe", "Lonely", 2);
    public static final RegSupplier<Item> PETRIFIED_PILLBUG = registerArtifact("petrified_pillbug", "Don't kick it, it hurts", 14);
    public static final RegSupplier<Item> PRIMITIVE_NECKLACE = registerArtifact("primitive_necklace", "A rope with a small metal bead", 9);
    public static final RegSupplier<Item> SMALL_DIAMOND_GEODE = registerArtifact("small_diamond_geode", "Kind of cute and very shiny", 25);
    public static final RegSupplier<Item> SMALL_EMERALD_GEODE = registerArtifact("small_emerald_geode", "Kind of cute and expensive", 35);
    public static final RegSupplier<Item> SNAPPED_PAINTBRUSH = registerArtifact("snapped_paintbrush", "A dried up brush from an arts & crafter's dream", 17);
    public static final RegSupplier<Item> SOAKED_BOOK = registerArtifact("soaked_book", "A completely destroyed book with all pages ripped out", 4);
    public static final RegSupplier<Item> STONE_MASK = registerArtifact("stone_mask", "A mask with a screaming face",
            () -> new ArtifactHelmetItem(SMArmorMaterials.STONE_MASK, SMItemProperties.ARTIFACT_DURABILITY(9)) , 26);
    public static final RegSupplier<Item> STRANGE_FUR = registerArtifact("strange_fur", "A patch of thick coarse hair", 20);
    public static final RegSupplier<Item> ANCIENT_RELIC = registerArtifact("ancient_relic", "It's unfathomably old", 40);

    //Placeable
    public static final RegSupplier<Block> DRIED_CYAN_FLOWER = registerPlaceable("dried_cyan_flower", "A delicate cyan flower that feels strangely familiar", () -> new ArtifactFlowerBlock(SMBlockProperties.ARTIFACT_FLOWER()), 21);
    public static final RegSupplier<Block> POTTED_DRIED_CYAN_FLOWER = RegHelper.registerBlock(path("potted_dried_cyan_flower"), () -> PlatHelper.newFlowerPot(() -> (FlowerPotBlock) Blocks.FLOWER_POT, DRIED_CYAN_FLOWER, SMBlockProperties.FLOWER_POT()));
    public static final RegSupplier<Block> DRIED_RED_FLOWER = registerPlaceable("dried_red_flower", "A delicate red flower that feels like home", () -> new ArtifactFlowerBlock(SMBlockProperties.ARTIFACT_FLOWER()), 22);
    public static final RegSupplier<Block> POTTED_DRIED_RED_FLOWER = RegHelper.registerBlock(path("potted_dried_red_flower"), () -> PlatHelper.newFlowerPot(() -> (FlowerPotBlock) Blocks.FLOWER_POT, DRIED_RED_FLOWER, SMBlockProperties.FLOWER_POT()));


    public static final RegSupplier<Block> BROKEN_BOWL = registerPlaceable("broken_bowl", "A large crack runs down the edge", () -> new ArtifactBlockWithShape(ArtifactBlockShapes.BOWL(), SMBlockProperties.FLOWER_POT()), 9);
    public static final RegSupplier<Block> BROKEN_VASE = registerPlaceable("broken_vase", "A large piece of the side is missing", () -> new ArtifactBlockWithShape(ArtifactBlockShapes.VASE(), SMBlockProperties.FLOWER_POT()), 10);
    public static final RegSupplier<Block> BROKEN_CUP = registerPlaceable("broken_cup", "A small crack keeps it from retaining any liquid", () -> new ArtifactBlockWithShape(ArtifactBlockShapes.CUP(), SMBlockProperties.FLOWER_POT()), 10);
    public static final RegSupplier<Block> GOLDEN_IDOL = registerPlaceable("golden_idol", "The red rock on top is warm to the touch", () -> new ArtifactBlockWithShape(ArtifactBlockShapes.GOLDEN_IDOL(), SMBlockProperties.GOLDEN_IDOL()), 35);
    public static final RegSupplier<Block> GOLDEN_GOBLET = registerPlaceable("golden_goblet", "An old but beautiful chalice made by a skilled goldsmith", () -> new ArtifactBlockWithShape(ArtifactBlockShapes.GOLDEN_GOBLET(), SMBlockProperties.GOLDEN_GOBLET()), 29);
    public static final RegSupplier<Block> FAMILIAR_CUBE = registerPlaceable("familiar_cube", "Hot to the touch and has strange growths on it", () -> new ArtifactBlockWithShape(ArtifactBlockShapes.FAMILIAR_CUBE(), SMBlockProperties.FAMILIAR_CUBE()), 30);

    public static final RegSupplier<Block> STONE_IDOL = registerPlaceable("stone_idol", "Almost looks alive", () -> new ArtifactDirectionalBlockWithShape(ArtifactBlockShapes.STONE_IDOL(), SMBlockProperties.IDOL(MapColor.STONE)), 20);

    public static final RegSupplier<Block> FROG_IDOL = registerPlaceable("frog_idol", "Everybody likes frogs", () -> new ArtifactDirectionalBlockWithShape(ArtifactBlockShapes.FROG_IDOL(), SMBlockProperties.IDOL(MapColor.COLOR_BROWN)), 29);


    //Ancient Skulls
    /*
    public static List<Supplier<Block>> ANCIENT_SKULLS = new ArrayList<>();
    public static final Pair<RegSupplier<Block>, RegSupplier<Block>> CRACKED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.CRACKED, "The head of a giant ancient creature, it has a noticeable amount of cracks", 43);
    public static final Pair<RegSupplier<Block>, RegSupplier<Block>> CRESTED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.CRESTED, "The large head of a now extinct animal, the beak seems more hollow than others", 40);
    public static final Pair<RegSupplier<Block>, RegSupplier<Block>> FLATBILLED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.FLATBILLED, "The head of an animal that went extinct long ago", 37);
    public static final Pair<RegSupplier<Block>, RegSupplier<Block>> GIGANTIC_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.GIGANTIC, "The gigantic head of an ancient creature, it feels familiar", 43);
    public static final Pair<RegSupplier<Block>, RegSupplier<Block>> HORNED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.HORNED, "The head of an extinct creature with a broken off horn on its head", 36);
    public static final Pair<RegSupplier<Block>, RegSupplier<Block>> LONG_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.LONG, "A long head from an extinct animals, it has a large overbite", 34);
    public static final Pair<RegSupplier<Block>, RegSupplier<Block>> TINY_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.TINY, "The head of a small chicken sized critter that lived long ago", 30);
    public static final Pair<RegSupplier<Block>, RegSupplier<Block>> WIDE_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.WIDE, "The head of an extinct animal with a strangely wide head and giant eye sockets", 37);
    public static final Pair<RegSupplier<Block>, RegSupplier<Block>> RIBBED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.RIBBED, "Has a small ribcage directly attached to the skull", 37);
    public static final Pair<RegSupplier<Block>, RegSupplier<Block>> UNICORN_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.UNICORN, "A normal horse skull, but with a large horn protruding from it", 25);
    public static final Pair<RegSupplier<Block>, RegSupplier<Block>> SNOUTED_ANCIENT_SKULL = registerAncientSkull(AncientSkullBlock.Types.SNOUTED, "Horrifying", 35);


     */

    /*
    public static Pair<RegSupplier<Block>, RegSupplier<Block>> registerAncientSkull(AncientSkullBlock.Types type, String description, int price) {
        String typeName = SMTextUtil.convertSkullTypeToString(type);
        String skullName = typeName + "_ancient_skull";
        RegSupplier<Block> skull = SMBlocks.createBlockNoItemNoLang(skullName, () -> new AncientSkullBlock(type, SMProperties.Blocks.ancientSkulls()));
        RegSupplier<Block> wallSkull = SMBlocks.createBlockNoItemNoLang(typeName + "_ancient_wall_skull", () -> new AncientWallSkullBlock(type, SMProperties.Blocks.ancientSkulls().lootFrom(skull)));
        ANCIENT_SKULLS.add(skull);
        RegSupplier<Item> skullItem = SMItems.createItem(skullName, () -> new StandingAndWallBlockItem(skull.get(), wallSkull.get(), SMProperties.Items.artifacts(), Direction.DOWN));
        ARTIFACT_DESC_MAP.add(skullItem);
        TRADES.put(skullItem, price);
        SMTextUtil.artifactDesc(skullName, description);

        return Pair.of(skull, wallSkull);
    }
     */

    private static RegSupplier<Block> registerPlaceable(String name, String description, Supplier<Block> block, int price) {
        RegSupplier<Block> object = RegHelper.registerBlock(path(name), block);
        registerArtifact(name, description, () -> new BlockItem(object.get(), SMItemProperties.ARTIFACT()), price);
        return object;
    }

    private static RegSupplier<Item> registerArtifact(String name, String description, int price) {
        return registerArtifact(name, description, () -> new Item(SMItemProperties.ARTIFACT()), price);
    }

    private static RegSupplier<Item> registerArtifact(String name, String description, Supplier<Item> item, int price) {
        RegSupplier<Item> object = RegHelper.registerItem(path(name), item);
        //todo SMTextUtil.artifactDesc(name, description);
        ARTIFACT_DESC_MAP.add(object);
        TRADES.put(object, price);
        return object;
    }

    public static void init() {}
}
