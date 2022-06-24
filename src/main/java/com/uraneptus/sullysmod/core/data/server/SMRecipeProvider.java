package com.uraneptus.sullysmod.core.data.server;

import com.teamabnormals.blueprint.core.api.conditions.QuarkFlagRecipeCondition;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.data.server.builder.GrindstonePolishingRecipeBuilder;
import com.uraneptus.sullysmod.core.data.SMDatagenUtil;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.ConditionalRecipe;

import java.util.function.Consumer;

public class SMRecipeProvider extends RecipeProvider {

    public SMRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        //Cooking
        cookingRecipes(SMItems.RAW_LANTERNFISH.get(), SMItems.COOKED_LANTERNFISH.get(), 0.35F, consumer);

        //Smelting
        basicSmeltingRecipes(SMBlocks.ROUGH_JADE_BLOCK.get(), SMBlocks.SMOOTHED_ROUGH_JADE.get(), 1.0F, consumer);

        //Crafting
        packableBlockRecipes(SMItems.ROUGH_JADE.get(), SMBlocks.ROUGH_JADE_BLOCK.get(), consumer);
        packableBlockRecipes(SMItems.POLISHED_JADE.get(), SMBlocks.POLISHED_JADE_BLOCK.get(), consumer);

        tilingBlockRecipes(SMBlocks.ROUGH_JADE_BLOCK.get(), SMBlocks.ROUGH_JADE_BRICKS.get(), consumer);
        tilingBlockRecipes(SMBlocks.ROUGH_JADE_BRICKS.get(), SMBlocks.ROUGH_JADE_TILES.get(), consumer);
        tilingBlockRecipes(SMBlocks.POLISHED_JADE_BLOCK.get(), SMBlocks.POLISHED_JADE_BRICKS.get(), consumer);
        tilingBlockRecipes(SMBlocks.POLISHED_JADE_BRICKS.get(), SMBlocks.POLISHED_JADE_TILES.get(), consumer);
        tilingBlockRecipes(SMBlocks.POLISHED_JADE_TILES.get(), SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(), consumer);
        tilingBlockRecipes(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(), SMBlocks.POLISHED_JADE_SHINGLES.get(), consumer);

        stairRecipes(SMBlocks.ROUGH_JADE_BRICKS.get(), SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.ROUGH_JADE_TILES.get(), SMBlocks.ROUGH_JADE_TILE_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.SMOOTHED_ROUGH_JADE.get(), SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.POLISHED_JADE_BRICKS.get(), SMBlocks.POLISHED_JADE_BRICK_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.POLISHED_JADE_TILES.get(), SMBlocks.POLISHED_JADE_TILE_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(), SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get(), consumer);
        stairRecipes(SMBlocks.POLISHED_JADE_SHINGLES.get(), SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get(), consumer);

        slabRecipes(SMBlocks.ROUGH_JADE_BRICKS.get(), SMBlocks.ROUGH_JADE_BRICK_SLAB.get(), consumer);
        slabRecipes(SMBlocks.ROUGH_JADE_TILES.get(), SMBlocks.ROUGH_JADE_TILE_SLAB.get(), consumer);
        slabRecipes(SMBlocks.SMOOTHED_ROUGH_JADE.get(), SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get(), consumer);
        slabRecipes(SMBlocks.POLISHED_JADE_BRICKS.get(), SMBlocks.POLISHED_JADE_BRICK_SLAB.get(), consumer);
        slabRecipes(SMBlocks.POLISHED_JADE_TILES.get(), SMBlocks.POLISHED_JADE_TILE_SLAB.get(), consumer);
        slabRecipes(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(), SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get(), consumer);
        slabRecipes(SMBlocks.POLISHED_JADE_SHINGLES.get(), SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get(), consumer);

        verticalSlabRecipes(SMBlocks.ROUGH_JADE_BRICK_SLAB.get(), SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipes(SMBlocks.ROUGH_JADE_TILE_SLAB.get(), SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipes(SMBlocks.SMOOTH_ROUGH_JADE_SLAB.get(), SMBlocks.SMOOTH_ROUGH_JADE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipes(SMBlocks.POLISHED_JADE_BRICK_SLAB.get(), SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipes(SMBlocks.POLISHED_JADE_TILE_SLAB.get(), SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipes(SMBlocks.SMALL_POLISHED_JADE_BRICK_SLAB.get(), SMBlocks.SMALL_POLISHED_JADE_BRICK_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipes(SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get(), SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB.get(), consumer);

        pillarRecipes(SMBlocks.POLISHED_JADE_BLOCK.get(), SMBlocks.POLISHED_JADE_PILLAR.get(), consumer);

        chiseledRecipes(SMBlocks.POLISHED_JADE_BRICK_SLAB.get(), SMBlocks.POLISHED_CHISELED_JADE.get(), consumer);

        buttonRecipes(Blocks.CUT_COPPER, SMBlocks.COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.EXPOSED_CUT_COPPER, SMBlocks.EXPOSED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.WEATHERED_CUT_COPPER, SMBlocks.WEATHERED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.OXIDIZED_CUT_COPPER, SMBlocks.OXIDIZED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.WAXED_CUT_COPPER, SMBlocks.WAXED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.WAXED_EXPOSED_CUT_COPPER, SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.WAXED_WEATHERED_CUT_COPPER, SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), consumer);
        buttonRecipes(Blocks.WAXED_OXIDIZED_CUT_COPPER, SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), consumer);

        waxButtonRecipes(SMBlocks.COPPER_BUTTON.get(), SMBlocks.WAXED_COPPER_BUTTON.get(), consumer);
        waxButtonRecipes(SMBlocks.EXPOSED_COPPER_BUTTON.get(), SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), consumer);
        waxButtonRecipes(SMBlocks.WEATHERED_COPPER_BUTTON.get(), SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), consumer);
        waxButtonRecipes(SMBlocks.OXIDIZED_COPPER_BUTTON.get(), SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), consumer);

        ShapedRecipeBuilder.shaped(SMBlocks.JADE_TOTEM.get()).define('#', SMBlocks.POLISHED_JADE_SHINGLES.get()).pattern("###").pattern("# #").pattern("###").unlockedBy(getHasName(SMBlocks.POLISHED_JADE_SHINGLES.get()), has(SMBlocks.POLISHED_JADE_SHINGLES.get())).save(consumer);
        ShapedRecipeBuilder.shaped(SMBlocks.JADE_FLINGER_TOTEM.get()).define('#', SMBlocks.POLISHED_JADE_SHINGLES.get()).define('-', Items.TRIPWIRE_HOOK).pattern("###").pattern("#-#").pattern("###").unlockedBy(getHasName(Items.TRIPWIRE_HOOK), has(Items.TRIPWIRE_HOOK)).unlockedBy(getHasName(SMBlocks.POLISHED_JADE_SHINGLES.get()), has(SMBlocks.POLISHED_JADE_SHINGLES.get())).save(consumer);

        stonecutterRecipes(SMBlocks.ROUGH_JADE_BLOCK.get(), SMBlocks.ROUGH_JADE_BRICKS.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.ROUGH_JADE_BRICKS.get(), SMBlocks.ROUGH_JADE_TILES.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.ROUGH_JADE_BRICKS.get(), SMBlocks.ROUGH_JADE_BRICK_SLAB.get(), 2, consumer);
        stonecutterRecipes(SMBlocks.ROUGH_JADE_BRICKS.get(), SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.ROUGH_JADE_TILES.get(), SMBlocks.ROUGH_JADE_TILE_SLAB.get(), 2, consumer);
        stonecutterRecipes(SMBlocks.ROUGH_JADE_TILES.get(), SMBlocks.ROUGH_JADE_TILE_STAIRS.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.SMOOTHED_ROUGH_JADE.get(), SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get(), 2, consumer);
        stonecutterRecipes(SMBlocks.SMOOTHED_ROUGH_JADE.get(), SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_JADE_BLOCK.get(), SMBlocks.POLISHED_JADE_BRICKS.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_JADE_BLOCK.get(), SMBlocks.POLISHED_JADE_PILLAR.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_JADE_BRICKS.get(), SMBlocks.POLISHED_JADE_TILES.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_JADE_BRICKS.get(), SMBlocks.POLISHED_JADE_BRICK_SLAB.get(), 2, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_JADE_BRICKS.get(), SMBlocks.POLISHED_JADE_BRICK_STAIRS.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_JADE_BRICKS.get(), SMBlocks.POLISHED_CHISELED_JADE.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_JADE_TILES.get(), SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_JADE_TILES.get(), SMBlocks.POLISHED_JADE_TILE_SLAB.get(), 2, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_JADE_TILES.get(), SMBlocks.POLISHED_JADE_TILE_STAIRS.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(), SMBlocks.POLISHED_JADE_SHINGLES.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(), SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get(), 2, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(), SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get(), 1, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_JADE_SHINGLES.get(), SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get(), 2, consumer);
        stonecutterRecipes(SMBlocks.POLISHED_JADE_SHINGLES.get(), SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get(), 1, consumer);

        grindstonePolishingRecipes(SMItems.ROUGH_JADE.get(), SMItems.POLISHED_JADE.get(), 1, 1, consumer);

        SullysMod.LOGGER.info("RECIPE GENERATION COMPLETE");
    }

    private static void cookingRecipes(ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
        String resultName = SullysMod.modPrefix(result.toString()).toString();
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, experience, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(consumer);
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), result, experience, 600)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(consumer, resultName + "_from_campfire_cooking");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), result, experience, 100)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(consumer, resultName + "_from_smoking");
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

    private static void waxButtonRecipes(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
        String resultName = new ResourceLocation(SullysMod.MOD_ID, result.asItem().toString()).toString();

        ShapelessRecipeBuilder.shapeless(result).requires(ingredient).requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, resultName + "_from_honeycomb");
    }

    private static void stonecutterRecipes(ItemLike ingredient, ItemLike result, int resultCount, Consumer<FinishedRecipe> consumer) {
        String resultName = new ResourceLocation(SullysMod.MOD_ID, result.asItem().toString()).toString();
        String prefix = resultName + "_from_" + ingredient.asItem();
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), result, resultCount)
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, prefix + "_stonecutting");
    }

    private static void grindstonePolishingRecipes(ItemLike ingredient, ItemLike result, int resultCount, int experience, Consumer<FinishedRecipe> consumer) {
        String resultName = SullysMod.modPrefix(result.toString()).toString();
        GrindstonePolishingRecipeBuilder.grindstonePolishing(ingredient, result, resultCount, experience).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, resultName + "_from_grindstone_polishing");
    }

    private static void verticalSlabRecipes(ItemLike slab, ItemLike verticalSlab, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new QuarkFlagRecipeCondition(SMDatagenUtil.QUARK_FLAG, "vertical_slabs"))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(verticalSlab, 3).define('#', slab).pattern("#").pattern("#").pattern("#").unlockedBy(getHasName(slab), has(slab)).save(consumer1, new ResourceLocation(SullysMod.MOD_ID, getItemName(verticalSlab))))
                .build(consumer, new ResourceLocation(SullysMod.MOD_ID, "crafting/" + getItemName(verticalSlab)));

        ConditionalRecipe.builder()
                .addCondition(new QuarkFlagRecipeCondition(SMDatagenUtil.QUARK_FLAG, "vertical_slabs"))
                .addRecipe(consumer1 -> ShapelessRecipeBuilder.shapeless(slab).requires(verticalSlab).unlockedBy(getHasName(verticalSlab), has(verticalSlab)).save(consumer1, new ResourceLocation(SullysMod.MOD_ID, getItemName(verticalSlab) + "_revert")))
                .build(consumer, new ResourceLocation(SullysMod.MOD_ID, "crafting/" + getItemName(verticalSlab) + "_revert"));
    }
}
