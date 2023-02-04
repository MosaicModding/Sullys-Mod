package com.uraneptus.sullysmod.core.other;

import com.uraneptus.sullysmod.core.integration.fd.FDCompat;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.ModList;

@SuppressWarnings("unused")
public class SMProperties {

    public static final class Blocks {
        public static final BlockBehaviour.Properties JADE_ORE = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F);
        public static final BlockBehaviour.Properties DEEPSLATE_JADE_ORE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE);
        public static final BlockBehaviour.Properties ROUGH_JADE_BLOCKS = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(5.0F, 6.0F);
        public static final BlockBehaviour.Properties POLISHED_JADE_BLOCKS = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.EMERALD).requiresCorrectToolForDrops().strength(5.0F, 6.0F);
        public static final BlockBehaviour.Properties COPPER_BUTTONS = BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F);
    }

    public static final class Items {

        public static final Item.Properties MUSIC_DISCS = cannotStack().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE);
        public static final Item.Properties JADE_SHIELD = new Item.Properties().durability(336).tab(CreativeModeTab.TAB_COMBAT);
        public static final Item.Properties TOROTISE_SHELL = new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC);

        public static final Item.Properties MISC_TAB = new Item.Properties().tab(CreativeModeTab.TAB_MISC);
        public static final Item.Properties FOOD_TAB = new Item.Properties().tab(CreativeModeTab.TAB_FOOD);
        public static final Item.Properties REDSTONE_TAB = new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE);
        public static final Item.Properties BUILDING_TAB = new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS);

        public static final Item.Properties FD_COMPAT_TAB = new Item.Properties().tab(!FDCompat.IS_LOADED ? null : FDCompat.FDLoaded.fdTab());

        public static Item.Properties cannotStack() {
            return new Item.Properties().stacksTo(1);
        }
        public static Item.Properties sixteenStack() {
           return new Item.Properties().stacksTo(16);
        }
    }

    public static final class Foods {
        public static final FoodProperties LANTERNFISH_FOOD = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 0.1F).build();
        public static final FoodProperties COOKED_LANTERNFISH_FOOD = new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 0.1F).build();

        public static final FoodProperties LANTERNFISH_SLICE_FOOD = new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 0.1F).fast().build();
        public static final FoodProperties COOKED_LANTERNFISH_SLICE_FOOD = new FoodProperties.Builder().nutrition(3).saturationMod(0.5F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 0.1F).fast().build();
        public static final FoodProperties LANTERNFISH_ROLL_FOOD = new FoodProperties.Builder().nutrition(7).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 0.1F).build();
        public static final FoodProperties CAVE_CHUM_BUCKET_FOOD = new FoodProperties.Builder().nutrition(13).saturationMod(1.0F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 0.1F).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 800), 1.0F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 800), 1.0F).build();
    }
}
