package com.uraneptus.sullysmod.core.data.server;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.CopperButtonBlock;
import com.uraneptus.sullysmod.core.data.DatagenUtil;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import javax.annotation.Nullable;

public class SMRecipeProvider extends RecipeProvider {

    public SMRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        //Cooking
        cookingRecipes(SMItems.RAW_LANTERNFISH.get(), SMItems.COOKED_LANTERNFISH.get(), 0.35F, consumer);

        //Smelting
        basicSmeltingRecipes(SMBlocks.RAW_JADE_BLOCK.get(), SMBlocks.SMOOTH_RAW_JADE.get(), 1.0F, consumer);

        //Crafting
        packableBlockRecipes(SMItems.RAW_JADE.get(), SMBlocks.RAW_JADE_BLOCK.get(), consumer);
        packableBlockRecipes(SMItems.JADE.get(), SMBlocks.JADE_BLOCK.get(), consumer);

        tilingBlockRecipes(SMBlocks.RAW_JADE_BLOCK.get(), SMBlocks.RAW_JADE_BRICKS.get(), consumer);
        tilingBlockRecipes(SMBlocks.RAW_JADE_BRICKS.get(), SMBlocks.RAW_JADE_TILES.get(), consumer);
        tilingBlockRecipes(SMBlocks.JADE_BLOCK.get(), SMBlocks.JADE_BRICKS.get(), consumer);
        tilingBlockRecipes(SMBlocks.JADE_BRICKS.get(), SMBlocks.JADE_TILES.get(), consumer);
        tilingBlockRecipes(SMBlocks.JADE_TILES.get(), SMBlocks.SMALL_JADE_BRICKS.get(), consumer);
        tilingBlockRecipes(SMBlocks.SMALL_JADE_BRICKS.get(), SMBlocks.JADE_SHINGLES.get(), consumer);

        stairRecipes(SMBlocks.RAW_JADE_BRICKS.get(), SMBlocks.RAW_JADE_BRICK_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.RAW_JADE_TILES.get(), SMBlocks.RAW_JADE_TILE_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.SMOOTH_RAW_JADE.get(), SMBlocks.SMOOTH_RAW_JADE_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.JADE_BRICKS.get(), SMBlocks.JADE_BRICK_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.JADE_TILES.get(), SMBlocks.JADE_TILE_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.SMALL_JADE_BRICKS.get(), SMBlocks.SMALL_JADE_BRICK_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.JADE_SHINGLES.get(), SMBlocks.JADE_SHINGLE_STAIRS.get(), consumer);

        slabRecipes(SMBlocks.RAW_JADE_BRICKS.get(), SMBlocks.RAW_JADE_BRICK_SLAB.get(), consumer);
        slabRecipes(SMBlocks.RAW_JADE_TILES.get(), SMBlocks.RAW_JADE_TILE_SLAB.get(), consumer);
        slabRecipes(SMBlocks.SMOOTH_RAW_JADE.get(), SMBlocks.SMOOTH_RAW_JADE_SLAB.get(), consumer);
        slabRecipes(SMBlocks.JADE_BRICKS.get(), SMBlocks.JADE_BRICK_SLAB.get(), consumer);
        slabRecipes(SMBlocks.JADE_TILES.get(), SMBlocks.JADE_TILE_SLAB.get(), consumer);
        slabRecipes(SMBlocks.SMALL_JADE_BRICKS.get(), SMBlocks.SMALL_JADE_BRICK_SLAB.get(), consumer);
        slabRecipes(SMBlocks.JADE_SHINGLES.get(), SMBlocks.JADE_SHINGLE_SLAB.get(), consumer);

        pillarRecipes(SMBlocks.JADE_BLOCK.get(), SMBlocks.JADE_PILLAR.get(), consumer);

        chiseledRecipes(SMBlocks.JADE_BRICK_SLAB.get(), SMBlocks.CHISELED_JADE.get(), consumer);

        buttonRecipes(Blocks.CUT_COPPER, SMBlocks.COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.EXPOSED_CUT_COPPER, SMBlocks.EXPOSED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.WEATHERED_CUT_COPPER, SMBlocks.WEATHERED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.OXIDIZED_CUT_COPPER, SMBlocks.OXIDIZED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.WAXED_CUT_COPPER, SMBlocks.WAXED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.WAXED_EXPOSED_CUT_COPPER, SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.WAXED_WEATHERED_CUT_COPPER, SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.WAXED_OXIDIZED_CUT_COPPER, SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), consumer);

        waxButtonRecipes(SMBlocks.COPPER_BUTTON.get(), SMBlocks.WAXED_COPPER_BUTTON.get(), consumer, "waxed_copper_button_from_honeycomb"); //TODO: Find a way make the path thing automatic
        waxButtonRecipes(SMBlocks.EXPOSED_COPPER_BUTTON.get(), SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), consumer, "waxed_exposed_copper_button_from_honeycomb");
        waxButtonRecipes(SMBlocks.WEATHERED_COPPER_BUTTON.get(), SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), consumer, "waxed_oxidized_copper_button_from_honeycomb");
        waxButtonRecipes(SMBlocks.OXIDIZED_COPPER_BUTTON.get(), SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), consumer, "waxed_weathered_copper_button_from_honeycomb");


        ShapedRecipeBuilder.shaped(SMBlocks.JADE_TOTEM.get()).define('#', SMBlocks.JADE_SHINGLES.get()).pattern("###").pattern("# #").pattern("###").unlockedBy(getHasName(SMBlocks.JADE_SHINGLES.get()), has(SMBlocks.JADE_SHINGLES.get())).save(consumer);

        SullysMod.LOGGER.info("RECIPE GENERATION COMPLETE");
    }

    private static void cookingRecipes(ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
        String prefix = new ResourceLocation(SullysMod.MOD_ID, result.toString()).toString();
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, experience, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(consumer);
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), result, experience, 600)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(consumer, prefix + "_from_campfire_cooking");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), result, experience, 100)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(consumer, prefix + "_from_smoking");
    }

    private static void packableBlockRecipes(ItemLike unpacked, ItemLike packed, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(packed).define('#', unpacked).pattern("###").pattern("###").pattern("###")
                .unlockedBy(getHasName(unpacked), has(unpacked)).save(consumer);
        ShapelessRecipeBuilder.shapeless(unpacked, 9).requires(packed)
                .unlockedBy(getHasName(packed), has(packed)).save(consumer);
    }

    private static void tilingBlockRecipes(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result, 4).define('#', ingredient).pattern("##").pattern("##")
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer);
    }

    private static void basicSmeltingRecipes(ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, experience, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer);
    }

    private static void stairRecipes(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result, 4).define('#', ingredient).pattern("#  ").pattern("## ").pattern("###")
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer);
    }

    private static void slabRecipes(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result, 6).define('#', ingredient).pattern("###")
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer);
    }

    private static void pillarRecipes(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
      ShapedRecipeBuilder.shaped(result, 2).define('#', ingredient).pattern("#").pattern("#")
              .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer);
    }

    private static void chiseledRecipes(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result, 1).define('#', ingredient).pattern("#").pattern("#")
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer);
    }

    private static void buttonRecipes(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(result).requires(ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer);
    }


    private static void waxButtonRecipes(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer, String path) {
        ShapelessRecipeBuilder.shapeless(result).requires(ingredient).requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, new ResourceLocation(SullysMod.MOD_ID, path));
    }




}
