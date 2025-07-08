package com.uraneptus.sullysmod.core.other;

import com.uraneptus.sullysmod.core.registry.SMArtifacts;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class SMProperties {

    public static final class Blocks {
        public static final BlockBehaviour.Properties JADE_ORE = BlockBehaviour.Properties.of().sound(SMSounds.JADE_ORE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F);
        public static final BlockBehaviour.Properties DEEPSLATE_JADE_ORE = BlockBehaviour.Properties.of().sound(SMSounds.DEEPSLATE_JADE_ORE).mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE);
        public static final BlockBehaviour.Properties ROUGH_JADE_BLOCKS = BlockBehaviour.Properties.of().sound(SMSounds.ROUGH_JADE).mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F);
        public static final BlockBehaviour.Properties JADE_BLOCKS = BlockBehaviour.Properties.of().sound(SMSounds.JADE).mapColor(MapColor.EMERALD).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F);
        public static final BlockBehaviour.Properties FLINGER_TOTEM = BlockBehaviour.Properties.of().sound(SMSounds.FLINGER_TOTEM).mapColor(MapColor.EMERALD).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F);
        public static final BlockBehaviour.Properties COPPER_BUTTONS = BlockBehaviour.Properties.of().noCollission().strength(0.5F);

        public static final BlockBehaviour.Properties AMBER = BlockBehaviour.Properties.of().strength(1.3F, 3F).mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().randomTicks().noOcclusion().dynamicShape().isViewBlocking(SMProperties::always);
        public static final BlockBehaviour.Properties AMBER_BUILDING_BLOCKS = BlockBehaviour.Properties.of().strength(1.3F, 3F).mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().randomTicks().dynamicShape().forceSolidOn()/*.isViewBlocking((pState, pLevel, pPos) -> true)*/;
        public static final BlockBehaviour.Properties ITEM_STAND = BlockBehaviour.Properties.of().strength(2.0F, 6.0F).requiresCorrectToolForDrops().noOcclusion();
        public static BlockBehaviour.Properties ancientSkulls() {
            return BlockBehaviour.Properties.of().strength(1.0F).pushReaction(PushReaction.DESTROY);
        }

        public static BlockBehaviour.Properties petrified() {
            return BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).strength(2.0F, 6.0F).sound(SMSounds.PETRIFIED_WOOD).mapColor(MapColor.TERRACOTTA_ORANGE);
        }

        public static BlockBehaviour.Properties flowerPot() {
            return BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
        }
        public static final BlockBehaviour.Properties PETRIFIED_TRAPDOOR = petrified().requiresCorrectToolForDrops().noOcclusion().isValidSpawn(SMProperties::never).strength(3.0F);
        public static final BlockBehaviour.Properties PETRIFIED_PRESSURE_PLATE = petrified().forceSolidOn().noCollission().pushReaction(PushReaction.DESTROY).strength(0.5F);
        public static final BlockBehaviour.Properties PETRIFIED_BUTTON = petrified().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        //YES sign and hanging sign really need to be separate definitions. idk why tho
        public static final BlockBehaviour.Properties PETRIFIED_SIGN = petrified().forceSolidOn().noCollission().strength(1.0F);
        public static final BlockBehaviour.Properties PETRIFIED_HANGING_SIGN = petrified().forceSolidOn().noCollission().strength(1.0F);
        public static final BlockBehaviour.Properties PETRIFIED_SAPLING = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY);

        public static final BlockBehaviour.Properties ARTIFACT_FLOWER = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY);
        public static final BlockBehaviour.Properties GOLDEN_IDOL = BlockBehaviour.Properties.of().strength(3.0F, 6.0F).sound(SoundType.METAL).mapColor(MapColor.GOLD).emissiveRendering(SMProperties::always).lightLevel(state -> 6);
        public static final BlockBehaviour.Properties GOLDEN_GOBLET = BlockBehaviour.Properties.of().instabreak().pushReaction(PushReaction.DESTROY).sound(SoundType.METAL).mapColor(MapColor.GOLD);
        public static final BlockBehaviour.Properties FAMILIAR_CUBE = BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL);
        public static final BlockBehaviour.Properties STONE_IDOL = BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.5F, 6.0F);
    }

    public static final class Items {
        //Item Specific
        public static final Item.Properties MUSIC_DISCS = singleStack().rarity(Rarity.RARE);
        public static final Item.Properties JADE_SHIELD = new Item.Properties().durability(400);

        public static Item.Properties food(FoodProperties food) {
            return new Item.Properties().food(food);
        }

        public static Item.Properties artifacts() {
            return new Item.Properties().rarity(SMArtifacts.ANCIENT);
        }

        public static Item.Properties sixteenStack() {
            return new Item.Properties().stacksTo(16);
        }

        public static Item.Properties singleStack() {
            return new Item.Properties().stacksTo(1);
        }
    }

    public static final class Foods {
        public static final FoodProperties LANTERNFISH_FOOD = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 1.0F).build();
        public static final FoodProperties COOKED_LANTERNFISH_FOOD = new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 1.0F).build();
        public static final FoodProperties PIRANHA_FOOD = new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).build();
        public static final FoodProperties COOKED_PIRANHA_FOOD = new FoodProperties.Builder().nutrition(7).saturationMod(0.5F).build();
        public static final FoodProperties PETRIFIED_COOKIE = new FoodProperties.Builder().build();
        public static final FoodProperties CAVE_CARROT = new FoodProperties.Builder().nutrition(6).build();
        public static final FoodProperties BUG_MEAT_FOOD = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600), 1.0F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600), 1.0F).meat().build();
        public static final FoodProperties COOKED_BUG_MEAT_FOOD = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 600), 1.0F).meat().build();
    }

    public static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }

    public static boolean never(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entity) {
        return false;
    }

    public static boolean always(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }

    public static boolean always(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entity) {
        return true;
    }
}
