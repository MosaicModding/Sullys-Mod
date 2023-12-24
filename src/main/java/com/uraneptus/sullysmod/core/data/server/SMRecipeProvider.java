package com.uraneptus.sullysmod.core.data.server;

import com.uraneptus.sullysmod.core.data.server.builder.GrindstonePolishingRecipeBuilder;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.uraneptus.sullysmod.core.data.SMDatagenUtil.*;

@SuppressWarnings("SameParameterValue")
public class SMRecipeProvider extends RecipeProvider {

    public SMRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        //Cooking, Smelting etc.
        cookingRecipes(SMItems.LANTERNFISH, SMItems.COOKED_LANTERNFISH, 0.35F, consumer);

        basicSmeltingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BLOCK, SMBlocks.SMOOTHED_ROUGH_JADE, 1.0F, consumer);

        oreCookingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.JADE_ORE, SMItems.ROUGH_JADE, 0.7F, consumer);
        oreCookingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.DEEPSLATE_JADE_ORE, SMItems.ROUGH_JADE, 0.7F, consumer);

        //Crafting
        packableBlockRecipes(SMItems.ROUGH_JADE, SMBlocks.ROUGH_JADE_BLOCK, consumer);
        packableBlockRecipes(SMItems.POLISHED_JADE, SMBlocks.POLISHED_JADE_BLOCK, consumer);

        tilingBlockRecipes(SMBlocks.ROUGH_JADE_BLOCK, SMBlocks.ROUGH_JADE_BRICKS, consumer);
        tilingBlockRecipes(SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_TILES, consumer);
        tilingBlockRecipes(SMBlocks.POLISHED_JADE_BLOCK, SMBlocks.POLISHED_JADE_BRICKS, consumer);
        tilingBlockRecipes(SMBlocks.POLISHED_JADE_BRICKS, SMBlocks.POLISHED_JADE_TILES, consumer);
        tilingBlockRecipes(SMBlocks.POLISHED_JADE_TILES, SMBlocks.POLISHED_SMALL_JADE_BRICKS, consumer);
        tilingBlockRecipes(SMBlocks.POLISHED_SMALL_JADE_BRICKS, SMBlocks.POLISHED_JADE_SHINGLES, consumer);

        stairRecipes(SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_BRICK_STAIRS, consumer);
        stairRecipes(SMBlocks.ROUGH_JADE_TILES, SMBlocks.ROUGH_JADE_TILE_STAIRS, consumer);
        stairRecipes(SMBlocks.SMOOTHED_ROUGH_JADE, SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS, consumer);
        stairRecipes(SMBlocks.POLISHED_JADE_BRICKS, SMBlocks.POLISHED_JADE_BRICK_STAIRS, consumer);
        stairRecipes(SMBlocks.POLISHED_JADE_TILES, SMBlocks.POLISHED_JADE_TILE_STAIRS, consumer);
        stairRecipes(SMBlocks.POLISHED_SMALL_JADE_BRICKS, SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS, consumer);
        stairRecipes(SMBlocks.POLISHED_JADE_SHINGLES, SMBlocks.POLISHED_JADE_SHINGLE_STAIRS, consumer);

        slabRecipes(SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_BRICK_SLAB, consumer);
        slabRecipes(SMBlocks.ROUGH_JADE_TILES, SMBlocks.ROUGH_JADE_TILE_SLAB, consumer);
        slabRecipes(SMBlocks.SMOOTHED_ROUGH_JADE, SMBlocks.SMOOTHED_ROUGH_JADE_SLAB, consumer);
        slabRecipes(SMBlocks.POLISHED_JADE_BRICKS, SMBlocks.POLISHED_JADE_BRICK_SLAB, consumer);
        slabRecipes(SMBlocks.POLISHED_JADE_TILES, SMBlocks.POLISHED_JADE_TILE_SLAB, consumer);
        slabRecipes(SMBlocks.POLISHED_SMALL_JADE_BRICKS, SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB, consumer);
        slabRecipes(SMBlocks.POLISHED_JADE_SHINGLES, SMBlocks.POLISHED_JADE_SHINGLE_SLAB, consumer);

        pillarRecipes(SMBlocks.POLISHED_JADE_BLOCK, SMBlocks.POLISHED_JADE_PILLAR, consumer);

        chiseledRecipes(SMBlocks.POLISHED_JADE_BRICK_SLAB, SMBlocks.POLISHED_CHISELED_JADE, consumer);

        buttonRecipes(Blocks.CUT_COPPER, SMBlocks.COPPER_BUTTON, consumer);
        buttonRecipes(Blocks.EXPOSED_CUT_COPPER, SMBlocks.EXPOSED_COPPER_BUTTON, consumer);
        buttonRecipes(Blocks.WEATHERED_CUT_COPPER, SMBlocks.WEATHERED_COPPER_BUTTON, consumer);
        buttonRecipes(Blocks.OXIDIZED_CUT_COPPER, SMBlocks.OXIDIZED_COPPER_BUTTON, consumer);
        buttonRecipes(Blocks.WAXED_CUT_COPPER, SMBlocks.WAXED_COPPER_BUTTON, consumer);
        buttonRecipes(Blocks.WAXED_EXPOSED_CUT_COPPER, SMBlocks.WAXED_EXPOSED_COPPER_BUTTON, consumer);
        buttonRecipes(Blocks.WAXED_WEATHERED_CUT_COPPER, SMBlocks.WAXED_WEATHERED_COPPER_BUTTON, consumer);
        buttonRecipes(Blocks.WAXED_OXIDIZED_CUT_COPPER, SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON, consumer);

        waxButtonRecipes(SMBlocks.COPPER_BUTTON, SMBlocks.WAXED_COPPER_BUTTON, consumer);
        waxButtonRecipes(SMBlocks.EXPOSED_COPPER_BUTTON, SMBlocks.WAXED_EXPOSED_COPPER_BUTTON, consumer);
        waxButtonRecipes(SMBlocks.WEATHERED_COPPER_BUTTON, SMBlocks.WAXED_WEATHERED_COPPER_BUTTON, consumer);
        waxButtonRecipes(SMBlocks.OXIDIZED_COPPER_BUTTON, SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON, consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SMItems.GLASS_VIAL.get(), 3)
                .define('#', Items.GLASS_PANE)
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(consumer, craftingPath(getItemName(SMItems.GLASS_VIAL.get())));

        //Stonecutting
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BLOCK, SMBlocks.ROUGH_JADE_BRICKS, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_TILES, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_BRICK_SLAB, 2, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_BRICK_STAIRS, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_TILES, SMBlocks.ROUGH_JADE_TILE_SLAB, 2, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_TILES, SMBlocks.ROUGH_JADE_TILE_STAIRS, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.SMOOTHED_ROUGH_JADE, SMBlocks.SMOOTHED_ROUGH_JADE_SLAB, 2, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.SMOOTHED_ROUGH_JADE, SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_JADE_BLOCK, SMBlocks.POLISHED_JADE_BRICKS, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_JADE_BLOCK, SMBlocks.POLISHED_JADE_PILLAR, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_JADE_BRICKS, SMBlocks.POLISHED_JADE_TILES, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_JADE_BRICKS, SMBlocks.POLISHED_JADE_BRICK_SLAB, 2, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_JADE_BRICKS, SMBlocks.POLISHED_JADE_BRICK_STAIRS, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_JADE_BRICKS, SMBlocks.POLISHED_CHISELED_JADE, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_JADE_TILES, SMBlocks.POLISHED_SMALL_JADE_BRICKS, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_JADE_TILES, SMBlocks.POLISHED_JADE_TILE_SLAB, 2, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_JADE_TILES, SMBlocks.POLISHED_JADE_TILE_STAIRS, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_SMALL_JADE_BRICKS, SMBlocks.POLISHED_JADE_SHINGLES, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_SMALL_JADE_BRICKS, SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB, 2, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_SMALL_JADE_BRICKS, SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS, 1, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_JADE_SHINGLES, SMBlocks.POLISHED_JADE_SHINGLE_SLAB, 2, consumer);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.POLISHED_JADE_SHINGLES, SMBlocks.POLISHED_JADE_SHINGLE_STAIRS, 1, consumer);

        //Grindstone Polishing
        grindstonePolishingRecipes(RecipeCategory.MISC, SMItems.ROUGH_JADE.get(), SMItems.POLISHED_JADE.get(), 1, consumer);

        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BLOCK.get(), SMBlocks.POLISHED_JADE_BLOCK.get(), 9, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICKS.get(), SMBlocks.POLISHED_JADE_BRICKS.get(), consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICK_SLAB.get(), SMBlocks.POLISHED_JADE_BRICK_SLAB.get(), consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(), SMBlocks.POLISHED_JADE_BRICK_STAIRS.get(), consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_TILES.get(), SMBlocks.POLISHED_JADE_TILES.get(), consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_TILE_SLAB.get(), SMBlocks.POLISHED_JADE_TILE_SLAB.get(), consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_TILE_STAIRS.get(), SMBlocks.POLISHED_JADE_TILE_STAIRS.get(), consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.GRANITE, Blocks.POLISHED_GRANITE, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.GRANITE_SLAB, Blocks.POLISHED_GRANITE_SLAB, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.GRANITE_STAIRS, Blocks.POLISHED_GRANITE_STAIRS, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.DIORITE, Blocks.POLISHED_DIORITE, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.DIORITE_SLAB, Blocks.POLISHED_DIORITE_SLAB, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.DIORITE_STAIRS, Blocks.POLISHED_DIORITE_STAIRS, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.ANDESITE_SLAB, Blocks.POLISHED_ANDESITE_SLAB, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.ANDESITE_STAIRS, Blocks.POLISHED_ANDESITE_STAIRS, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLED_DEEPSLATE, Blocks.POLISHED_DEEPSLATE, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLED_DEEPSLATE_SLAB, Blocks.POLISHED_DEEPSLATE_SLAB, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLED_DEEPSLATE_STAIRS, Blocks.POLISHED_DEEPSLATE_STAIRS, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLED_DEEPSLATE_WALL, Blocks.POLISHED_DEEPSLATE_WALL, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.BASALT, Blocks.POLISHED_BASALT, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.BLACKSTONE, Blocks.POLISHED_BLACKSTONE, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.BLACKSTONE_SLAB, Blocks.POLISHED_BLACKSTONE_SLAB, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.BLACKSTONE_STAIRS, Blocks.POLISHED_BLACKSTONE_STAIRS, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.BLACKSTONE_WALL, Blocks.POLISHED_BLACKSTONE_WALL, consumer);
        grindstonePolishingRecipes(RecipeCategory.MISC, SMBlocks.JADE_ORE.get(), SMItems.POLISHED_JADE.get(), 1, 1, consumer);

        grindstonePolishingRecipes(RecipeCategory.MISC, SMBlocks.DEEPSLATE_JADE_ORE.get(), SMItems.POLISHED_JADE.get(), 3, 1, consumer);

        smithingTemplateRecipes(RecipeCategory.TOOLS, SMItems.JADE_UPGRADE_SMITHING_TEMPLATE.get(), Items.SHIELD, SMItems.POLISHED_JADE.get(), SMItems.JADE_SHIELD.get(), SMBlocks.POLISHED_JADE_BLOCK.get(), consumer);
        smithingRecipes(RecipeCategory.TOOLS, SMItems.JADE_UPGRADE_SMITHING_TEMPLATE.get(), Items.DIAMOND_HORSE_ARMOR, SMItems.POLISHED_JADE.get(), SMItems.JADE_HORSE_ARMOR.get(), consumer);

        //Custom
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SMBlocks.JADE_TOTEM.get()).define('#', SMBlocks.POLISHED_JADE_SHINGLES.get()).pattern("###").pattern("# #").pattern("###").unlockedBy(getHasName(SMBlocks.POLISHED_JADE_SHINGLES.get()), has(SMBlocks.POLISHED_JADE_SHINGLES.get())).save(consumer, craftingPath(getItemName(SMBlocks.JADE_TOTEM.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, SMBlocks.JADE_FLINGER_TOTEM.get()).define('#', SMBlocks.POLISHED_JADE_SHINGLES.get()).define('-', Items.TRIPWIRE_HOOK).pattern("###").pattern("#-#").pattern("###").unlockedBy(getHasName(Items.TRIPWIRE_HOOK), has(Items.TRIPWIRE_HOOK)).unlockedBy(getHasName(SMBlocks.POLISHED_JADE_SHINGLES.get()), has(SMBlocks.POLISHED_JADE_SHINGLES.get())).save(consumer, craftingPath(getItemName(SMBlocks.JADE_FLINGER_TOTEM.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SMItems.TORTOISE_SHELL.get()).define('#', SMItems.TORTOISE_SCUTE.get()).pattern("## ").pattern("## ").pattern("   ").unlockedBy(getHasName(SMItems.TORTOISE_SCUTE.get()), has(SMItems.TORTOISE_SCUTE.get())).save(consumer, craftingPath(getItemName(SMItems.TORTOISE_SHELL.get())));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, SMBlocks.JADE_FLINGER_TOTEM.get()).requires(SMBlocks.JADE_TOTEM.get()).requires(Items.TRIPWIRE_HOOK).unlockedBy(getHasName(Items.TRIPWIRE_HOOK), has(Items.TRIPWIRE_HOOK)).unlockedBy(getHasName(SMBlocks.JADE_TOTEM.get()), has(SMBlocks.JADE_TOTEM.get())).save(consumer, craftingPath(getItemName(SMBlocks.JADE_FLINGER_TOTEM.get()) + "_from_jade_totem"));
    }

    protected static void packableBlockRecipes(Supplier<? extends ItemLike> unpacked, Supplier<? extends ItemLike> packed, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, packed.get()).define('#', unpacked.get()).pattern("###").pattern("###").pattern("###")
                .unlockedBy(getHasName(unpacked.get()), has(unpacked.get())).save(consumer, craftingPath(getItemName(packed.get())));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, unpacked.get(), 9).requires(packed.get())
                .unlockedBy(getHasName(packed.get()), has(packed.get())).save(consumer, craftingPath(getItemName(unpacked.get())));
    }

    protected static void tilingBlockRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 4).define('#', ingredient.get()).pattern("##").pattern("##")
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, craftingPath(getItemName(result.get())));
    }

    protected static void basicSmeltingRecipes(RecipeCategory category, Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, float experience, Consumer<FinishedRecipe> consumer) {
        String resultName = getItemName(result.get());
        String ingredientName = getItemName(ingredient.get());
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient.get()), category, result.get(), experience, 200)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, smeltingPath(resultName + "_from_smelting" + "_" + ingredientName));
    }

    protected static void oreCookingRecipes(RecipeCategory category, Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, float experience, Consumer<FinishedRecipe> consumer) {
        String resultName = getItemName(result.get());
        String ingredientName = getItemName(ingredient.get());

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient.get()), category, result.get(), experience, 200)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, smeltingPath(resultName + "_from_smelting" + "_" + ingredientName));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient.get()), category, result.get(), experience, 100)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, blastingPath(resultName + "_from_blasting" + "_" + ingredientName));
    }

    protected static void cookingRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, float experience, Consumer<FinishedRecipe> consumer) {
        String resultName = getItemName(result.get());

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient.get()), RecipeCategory.FOOD, result.get(), experience, 200)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(consumer, smeltingPath(resultName));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient.get()), RecipeCategory.FOOD, result.get(), experience, 600)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(consumer, campfire_cookingPath(resultName + "_from_campfire_cooking"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient.get()), RecipeCategory.FOOD, result.get(), experience, 100)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get()))
                .save(consumer, smokingPath(resultName + "_from_smoking"));
    }

    protected static void stairRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 4).define('#', ingredient.get()).pattern("#  ").pattern("## ").pattern("###")
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, craftingPath(getItemName(result.get())));
    }

    protected static void slabRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6).define('#', ingredient.get()).pattern("###")
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, craftingPath(getItemName(result.get())));
    }

    protected static void pillarRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer) {
      ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 2).define('#', ingredient.get()).pattern("#").pattern("#")
              .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, craftingPath(getItemName(result.get())));
    }

    protected static void chiseledRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 1).define('#', ingredient.get()).pattern("#").pattern("#")
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, craftingPath(getItemName(result.get())));
    }

    protected static void buttonRecipes(ItemLike ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, result.get()).requires(ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, craftingPath(getItemName(result.get())));
    }

    protected static void waxButtonRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer) {
        String resultName = getItemName(result.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, result.get()).requires(ingredient.get()).requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, craftingPath(resultName + "_from_honeycomb"));
    }

    protected static void stonecutterRecipes(RecipeCategory category, Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, int resultCount, Consumer<FinishedRecipe> consumer) {
        String prefix = getItemName(result.get()) + "_from_" + getItemName(ingredient.get());
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient.get()), category, result.get(), resultCount)
                .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())).save(consumer, stonecuttingPath(prefix + "_stonecutting"));
    }

    protected static void grindstonePolishingRecipes(RecipeCategory category, ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
        grindstonePolishingRecipes(category, ingredient, result, 1, 0, consumer);
    }

    protected static void grindstonePolishingRecipes(RecipeCategory category, ItemLike ingredient, ItemLike result, int experience, Consumer<FinishedRecipe> consumer) {
        grindstonePolishingRecipes(category, ingredient, result, 1, experience, consumer);
    }

    protected static void grindstonePolishingRecipes(RecipeCategory category, ItemLike ingredient, ItemLike result, int count, int experience, Consumer<FinishedRecipe> consumer) {
        String ingredientName = getItemName(ingredient);
        GrindstonePolishingRecipeBuilder.grindstonePolishing(category, result, count, experience).requires(ingredient).save(consumer, "_from_" + ingredientName);
    }

    protected static void grindstonePolishingRecipes(RecipeCategory category, TagKey<Item> ingredient, ItemLike result, int count, int experience, Consumer<FinishedRecipe> consumer) {
        String ingredientName = ingredient.location().getPath();
        GrindstonePolishingRecipeBuilder.grindstonePolishing(category, result, count, experience).requires(ingredient).save(consumer, "_from_" + ingredientName);
    }

    protected static void smithingRecipes(RecipeCategory category, ItemLike templateItem, ItemLike baseItem, ItemLike addition, ItemLike result, Consumer<FinishedRecipe> consumer) {
        String resultName = getItemName(result);
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem), Ingredient.of(baseItem), Ingredient.of(addition), category, result.asItem()).unlocks(getHasName(addition), has(addition)).save(consumer, smithingPath(resultName));
    }

    protected static void smithingTemplateRecipes(RecipeCategory category, ItemLike templateItem, ItemLike baseItem, ItemLike addition, ItemLike result, ItemLike duplicationBase, Consumer<FinishedRecipe> consumer) {
        smithingRecipes(category, templateItem, baseItem, addition, result, consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, templateItem, 2).define('#', Items.DIAMOND).define('C', duplicationBase).define('S', templateItem).pattern("#S#").pattern("#C#").pattern("###").unlockedBy(getHasName(templateItem), has(templateItem)).save(consumer, craftingPath(getItemName(templateItem)));
    }
}
