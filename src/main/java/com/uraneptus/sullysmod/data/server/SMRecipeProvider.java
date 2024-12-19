package com.uraneptus.sullysmod.data.server;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.recipes.SMFeatureRecipeCondition;
import com.uraneptus.sullysmod.core.SMFeatures;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.data.server.builder.GrindstonePolishingRecipeBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.uraneptus.sullysmod.data.SMDatagenUtil.*;

@SuppressWarnings("SameParameterValue")
public class SMRecipeProvider extends RecipeProvider {

    public SMRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        //Cooking, Smelting etc.
        cookingRecipes(SMItems.LANTERNFISH, SMItems.COOKED_LANTERNFISH, 0.35F, consumer, SMFeatures.LANTERNFISH);
        cookingRecipes(SMItems.PIRANHA, SMItems.COOKED_PIRANHA, 0.35F, consumer, SMFeatures.PIRANHA);

        oreCookingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.JADE_ORE, SMItems.ROUGH_JADE, 0.7F, consumer, SMFeatures.JADE);
        oreCookingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.DEEPSLATE_JADE_ORE, SMItems.ROUGH_JADE, 0.7F, consumer, SMFeatures.JADE);

        //Crafting
        packableBlockRecipes(SMItems.ROUGH_JADE, SMBlocks.ROUGH_JADE_BLOCK, consumer, SMFeatures.JADE);
        packableBlockRecipes(SMItems.JADE, SMBlocks.JADE_BLOCK, consumer, SMFeatures.JADE);

        tilingBlockRecipes(SMBlocks.ROUGH_JADE_BLOCK, SMBlocks.ROUGH_JADE_BRICKS, consumer, SMFeatures.JADE);
        tilingBlockRecipes(SMBlocks.JADE_BLOCK, SMBlocks.JADE_BRICKS, consumer, SMFeatures.JADE);
        tilingBlockRecipes(SMBlocks.ROUGH_AMBER, SMBlocks.AMBER_BRICKS, consumer, SMFeatures.AMBER);

        stairRecipes(SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_BRICK_STAIRS, consumer, SMFeatures.JADE);
        stairRecipes(SMBlocks.JADE_BRICKS, SMBlocks.JADE_BRICK_STAIRS, consumer, SMFeatures.JADE);
        stairRecipes(SMBlocks.AMBER_BRICKS, SMBlocks.AMBER_BRICK_STAIRS, consumer, SMFeatures.AMBER);

        slabRecipes(SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_BRICK_SLAB, consumer, SMFeatures.JADE);
        slabRecipes(SMBlocks.JADE_BRICKS, SMBlocks.JADE_BRICK_SLAB, consumer, SMFeatures.JADE);
        slabRecipes(SMBlocks.AMBER_BRICKS, SMBlocks.AMBER_BRICK_SLAB, consumer, SMFeatures.AMBER);

        wallRecipes(SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_BRICK_WALL, consumer, SMFeatures.JADE);
        wallRecipes(SMBlocks.JADE_BRICKS, SMBlocks.JADE_BRICK_WALL, consumer, SMFeatures.JADE);
        wallRecipes(SMBlocks.AMBER_BRICKS, SMBlocks.AMBER_BRICK_WALL, consumer, SMFeatures.AMBER);

        pillarRecipes(SMBlocks.JADE_BLOCK, SMBlocks.JADE_PILLAR, consumer, SMFeatures.JADE);
        pillarRecipes(SMBlocks.AMBER_BRICKS, SMBlocks.AMBER_PILLAR, consumer, SMFeatures.AMBER);

        chiseledRecipes(SMBlocks.JADE_BRICK_SLAB, SMBlocks.CHISELED_JADE, consumer, SMFeatures.JADE);
        chiseledRecipes(SMBlocks.AMBER_BRICK_SLAB, SMBlocks.CHISELED_AMBER, consumer, SMFeatures.AMBER);

        buttonRecipes(RecipeCategory.REDSTONE, Blocks.CUT_COPPER, SMBlocks.COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);
        buttonRecipes(RecipeCategory.REDSTONE, Blocks.EXPOSED_CUT_COPPER, SMBlocks.EXPOSED_COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);
        buttonRecipes(RecipeCategory.REDSTONE, Blocks.WEATHERED_CUT_COPPER, SMBlocks.WEATHERED_COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);
        buttonRecipes(RecipeCategory.REDSTONE, Blocks.OXIDIZED_CUT_COPPER, SMBlocks.OXIDIZED_COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);
        buttonRecipes(RecipeCategory.REDSTONE, Blocks.WAXED_CUT_COPPER, SMBlocks.WAXED_COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);
        buttonRecipes(RecipeCategory.REDSTONE, Blocks.WAXED_EXPOSED_CUT_COPPER, SMBlocks.WAXED_EXPOSED_COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);
        buttonRecipes(RecipeCategory.REDSTONE, Blocks.WAXED_WEATHERED_CUT_COPPER, SMBlocks.WAXED_WEATHERED_COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);
        buttonRecipes(RecipeCategory.REDSTONE, Blocks.WAXED_OXIDIZED_CUT_COPPER, SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);

        waxButtonRecipes(SMBlocks.COPPER_BUTTON, SMBlocks.WAXED_COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);
        waxButtonRecipes(SMBlocks.EXPOSED_COPPER_BUTTON, SMBlocks.WAXED_EXPOSED_COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);
        waxButtonRecipes(SMBlocks.WEATHERED_COPPER_BUTTON, SMBlocks.WAXED_WEATHERED_COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);
        waxButtonRecipes(SMBlocks.OXIDIZED_COPPER_BUTTON, SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON, consumer, SMFeatures.COPPER_BUTTONS);

        planksFromLogsRecipe(SMItemTags.PETRIFIED_LOGS, SMBlocks.PETRIFIED_PLANKS, consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        woodFromLogsRecipe(SMBlocks.PETRIFIED_LOG, SMBlocks.PETRIFIED_WOOD, consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        woodFromLogsRecipe(SMBlocks.STRIPPED_PETRIFIED_LOG, SMBlocks.STRIPPED_PETRIFIED_WOOD, consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        slabRecipes(SMBlocks.PETRIFIED_PLANKS, SMBlocks.PETRIFIED_SLAB, consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        fenceRecipe(SMBlocks.PETRIFIED_PLANKS, SMBlocks.PETRIFIED_FENCE, consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        stairRecipes(SMBlocks.PETRIFIED_PLANKS, SMBlocks.PETRIFIED_STAIRS, consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        buttonRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.PETRIFIED_PLANKS.get(), SMBlocks.PETRIFIED_BUTTON, consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        pressurePlateRecipe(SMBlocks.PETRIFIED_PLANKS, SMBlocks.PETRIFIED_PRESSURE_PLATE, consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        doorRecipe(SMBlocks.PETRIFIED_PLANKS, SMBlocks.PETRIFIED_DOOR, consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        trapdoorRecipe(SMBlocks.PETRIFIED_PLANKS, SMBlocks.PETRIFIED_TRAPDOOR, consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        fenceGateRecipe(SMBlocks.PETRIFIED_PLANKS, SMBlocks.PETRIFIED_FENCE_GATE, consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        signRecipe(SMBlocks.PETRIFIED_PLANKS, SMBlocks.PETRIFIED_SIGN.getFirst(), consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);
        hangingSignRecipe(SMBlocks.STRIPPED_PETRIFIED_LOG, SMBlocks.PETRIFIED_HANGING_SIGN.getFirst(), consumer, SMFeatures.PETRIFIED_WOOD, SMFeatures.AMBER);

        gemLanterns(() -> Items.DIAMOND_BLOCK, SMBlocks.DIAMOND_LANTERN, consumer);
        gemLanterns(() -> Items.EMERALD_BLOCK, SMBlocks.EMERALD_LANTERN, consumer);
        gemLanterns(() -> Items.QUARTZ_BLOCK, SMBlocks.QUARTZ_LANTERN, consumer);
        gemLanterns(() -> Items.LAPIS_BLOCK, SMBlocks.LAPIS_LANTERN, consumer);
        gemLanterns(() -> Items.AMETHYST_BLOCK, SMBlocks.AMETHYST_LANTERN, consumer);
        gemLanterns(SMBlocks.JADE_BLOCK, SMBlocks.JADE_LANTERN, consumer, SMFeatures.JADE);
        gemLanterns(SMBlocks.AMBER, SMBlocks.AMBER_LANTERN, consumer, SMFeatures.AMBER);

        //Stonecutting
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BLOCK, SMBlocks.ROUGH_JADE_BRICKS, 1, consumer, SMFeatures.JADE);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_BRICK_SLAB, 2, consumer, SMFeatures.JADE);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_BRICK_STAIRS, 1, consumer, SMFeatures.JADE);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICKS, SMBlocks.ROUGH_JADE_BRICK_WALL, 1, consumer, SMFeatures.JADE);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.JADE_BLOCK, SMBlocks.JADE_PILLAR, 1, consumer, SMFeatures.JADE);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.JADE_BRICKS, SMBlocks.CHISELED_JADE, 1, consumer, SMFeatures.JADE);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.JADE_BLOCK, SMBlocks.JADE_BRICKS, 1, consumer, SMFeatures.JADE);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.JADE_BRICKS, SMBlocks.JADE_BRICK_SLAB, 2, consumer, SMFeatures.JADE);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.JADE_BRICKS, SMBlocks.JADE_BRICK_STAIRS, 1, consumer, SMFeatures.JADE);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.JADE_BRICKS, SMBlocks.JADE_BRICK_WALL, 1, consumer, SMFeatures.JADE);

        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.AMBER_BRICKS, SMBlocks.AMBER_PILLAR, 1, consumer, SMFeatures.AMBER);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.AMBER_BRICKS, SMBlocks.CHISELED_AMBER, 1, consumer, SMFeatures.AMBER);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_AMBER, SMBlocks.AMBER_BRICKS, 1, consumer, SMFeatures.AMBER);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.AMBER_BRICKS, SMBlocks.AMBER_BRICK_SLAB, 2, consumer, SMFeatures.AMBER);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.AMBER_BRICKS, SMBlocks.AMBER_BRICK_STAIRS, 1, consumer, SMFeatures.AMBER);
        stonecutterRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.AMBER_BRICKS, SMBlocks.AMBER_BRICK_WALL, 1, consumer, SMFeatures.AMBER);

        //Grindstone Polishing
        grindstonePolishingRecipes(RecipeCategory.MISC, SMItems.ROUGH_JADE.get(), SMItems.JADE.get(), 1, consumer);

        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BLOCK.get(), SMBlocks.JADE_BLOCK.get(), 9, consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICKS.get(), SMBlocks.JADE_BRICKS.get(), consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICK_SLAB.get(), SMBlocks.JADE_BRICK_SLAB.get(), consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(), SMBlocks.JADE_BRICK_STAIRS.get(), consumer);
        grindstonePolishingRecipes(RecipeCategory.BUILDING_BLOCKS, SMBlocks.ROUGH_JADE_BRICK_WALL.get(), SMBlocks.JADE_BRICK_WALL.get(), consumer);
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
        grindstonePolishingRecipes(RecipeCategory.MISC, SMBlocks.JADE_ORE.get(), SMItems.JADE.get(), 1, 1, consumer);

        grindstonePolishingRecipes(RecipeCategory.MISC, SMBlocks.DEEPSLATE_JADE_ORE.get(), SMItems.JADE.get(), 3, 1, consumer);

        smithingTemplateRecipes(RecipeCategory.TOOLS, SMItems.JADE_UPGRADE_SMITHING_TEMPLATE.get(), Items.SHIELD, SMItems.JADE.get(), SMItems.JADE_SHIELD.get(), SMBlocks.JADE_BLOCK.get(), consumer, SMFeatures.JADE);
        smithingRecipes(RecipeCategory.TOOLS, SMItems.JADE_UPGRADE_SMITHING_TEMPLATE.get(), Items.DIAMOND_HORSE_ARMOR, SMItems.JADE.get(), SMItems.JADE_HORSE_ARMOR.get(), consumer);

        //Custom
        featureConditionRecipe(List.of(SMFeatures.JADE), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SMBlocks.JADE_TOTEM.get())
                        .define('#', SMBlocks.JADE_BRICKS.get())
                        .pattern("###").pattern("# #").pattern("###")
                        .unlockedBy(getHasName(SMBlocks.JADE_BRICKS.get()), has(SMBlocks.JADE_BRICKS.get())),
                craftingPath(getItemName(SMBlocks.JADE_TOTEM.get())), consumer
        );
        featureConditionRecipe(List.of(SMFeatures.JADE), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, SMBlocks.JADE_FLINGER_TOTEM.get())
                        .define('#', SMBlocks.JADE_BRICKS.get()).define('-', Items.TRIPWIRE_HOOK)
                        .pattern("###").pattern("#-#").pattern("###")
                        .unlockedBy(getHasName(Items.TRIPWIRE_HOOK), has(Items.TRIPWIRE_HOOK))
                        .unlockedBy(getHasName(SMBlocks.JADE_BRICKS.get()), has(SMBlocks.JADE_BRICKS.get())),
                craftingPath(getItemName(SMBlocks.JADE_FLINGER_TOTEM.get())), consumer
        );
        featureConditionRecipe(List.of(SMFeatures.TORTOISE), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SMItems.TORTOISE_SHELL.get())
                        .define('#', SMItems.TORTOISE_SCUTE.get())
                        .pattern("## ").pattern("## ").pattern("   ")
                        .unlockedBy(getHasName(SMItems.TORTOISE_SCUTE.get()), has(SMItems.TORTOISE_SCUTE.get())),
                craftingPath(getItemName(SMItems.TORTOISE_SHELL.get())), consumer
        );
        featureConditionRecipe(List.of(SMFeatures.JADE), RecipeCategory.BUILDING_BLOCKS,
                ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, SMBlocks.JADE_FLINGER_TOTEM.get())
                        .requires(SMBlocks.JADE_TOTEM.get()).requires(Items.TRIPWIRE_HOOK)
                        .unlockedBy(getHasName(Items.TRIPWIRE_HOOK), has(Items.TRIPWIRE_HOOK))
                        .unlockedBy(getHasName(SMBlocks.JADE_TOTEM.get()), has(SMBlocks.JADE_TOTEM.get())),
                craftingPath(getItemName(SMBlocks.JADE_FLINGER_TOTEM.get()) + "_from_jade_totem"), consumer
        );
        featureConditionRecipe(List.of(SMFeatures.PIRANHA), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SMItems.THROWING_KNIFE.get())
                        .define('#', Items.STICK).define('X', SMItems.PIRANHA_TOOTH.get())
                        .pattern(" X").pattern("# ")
                        .unlockedBy(getHasName(SMItems.PIRANHA_TOOTH.get()), has(SMItems.PIRANHA_TOOTH.get())),
                craftingPath(getItemName(SMItems.THROWING_KNIFE.get())), consumer
        );
        featureConditionRecipe(List.of(SMFeatures.PIRANHA), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.ARROW, 4)
                        .define('#', Items.STICK).define('X', SMItems.PIRANHA_TOOTH.get()).define('Y', Items.FEATHER)
                        .pattern("X").pattern("#").pattern("Y")
                        .unlockedBy("has_feather", has(Items.FEATHER))
                        .unlockedBy(getHasName(SMItems.PIRANHA_TOOTH.get()), has(SMItems.PIRANHA_TOOTH.get())),
                craftingPath("arrow_from_piranha_tooth"), consumer
        );
        featureConditionRecipe(List.of(SMFeatures.ARTIFACTS), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.ARROW, 4)
                        .define('#', Items.STICK).define('X', SMItems.ARROWHEAD.get()).define('Y', Items.FEATHER)
                        .pattern("X").pattern("#").pattern("Y")
                        .unlockedBy("has_feather", has(Items.FEATHER))
                        .unlockedBy(getHasName(SMItems.ARROWHEAD.get()), has(SMItems.ARROWHEAD.get())),
                craftingPath("arrow_from_arrowhead"), consumer
        );
        featureConditionRecipe(List.of(SMFeatures.JUNGLE_SPIDER), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SMItems.GLASS_VIAL.get(), 3)
                        .define('#', Items.GLASS_PANE)
                        .pattern("# #").pattern(" # ")
                        .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE)),
                craftingPath(getItemName(SMItems.GLASS_VIAL.get())), consumer
        );
        featureConditionRecipe(List.of(SMFeatures.ARTIFACTS), RecipeCategory.BUILDING_BLOCKS,
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.AMETHYST_SHARD, 3)
                        .requires(SMItems.SMALL_GEODE.get())
                        .unlockedBy(getHasName(SMItems.SMALL_GEODE.get()), has(SMItems.SMALL_GEODE.get())),
                craftingPath("amethyst_shard_from_small_geode"), consumer
        );
        featureConditionRecipe(List.of(SMFeatures.ITEM_STAND), RecipeCategory.BUILDING_BLOCKS,
                ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, SMBlocks.ITEM_STAND.get())
                        .requires(Items.SMOOTH_STONE_SLAB).requires(Items.STICK)
                        .unlockedBy(getHasName(Items.SMOOTH_STONE_SLAB), has(Items.SMOOTH_STONE_SLAB))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK)),
                craftingPath(getItemName(SMBlocks.ITEM_STAND.get())), consumer
        );
    }

    protected static void packableBlockRecipes(Supplier<? extends ItemLike> unpacked, Supplier<? extends ItemLike> packed, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, packed.get())
                        .define('#', unpacked.get()).pattern("###").pattern("###").pattern("###")
                        .unlockedBy(getHasName(unpacked.get()), has(unpacked.get())), craftingPath(getItemName(packed.get())), consumer);

        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, unpacked.get(), 9)
                        .requires(packed.get())
                        .unlockedBy(getHasName(packed.get()), has(packed.get())), craftingPath(getItemName(unpacked.get())), consumer);
    }

    protected static void tilingBlockRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                        .define('#', ingredient.get()).pattern("##").pattern("##")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);


    }

    protected static void oreCookingRecipes(RecipeCategory category, Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, float experience, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        String resultName = getItemName(result.get());
        String ingredientName = getItemName(ingredient.get());

        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient.get()), category, result.get(), experience, 200)
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), smeltingPath(resultName + "_from_smelting" + "_" + ingredientName), consumer);

        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient.get()), category, result.get(), experience, 100)
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), blastingPath(resultName + "_from_blasting" + "_" + ingredientName), consumer);
    }

    protected static void cookingRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, float experience, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        String resultName = getItemName(result.get());

        featureConditionRecipe(List.of(features), RecipeCategory.FOOD,
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient.get()), RecipeCategory.FOOD, result.get(), experience, 200)
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), smeltingPath(resultName), consumer);

        featureConditionRecipe(List.of(features), RecipeCategory.FOOD,
                SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient.get()), RecipeCategory.FOOD, result.get(), experience, 600)
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), campfire_cookingPath(resultName + "_from_campfire_cooking"), consumer);

        featureConditionRecipe(List.of(features), RecipeCategory.FOOD,
                SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient.get()), RecipeCategory.FOOD, result.get(), experience, 100)
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), smokingPath(resultName + "_from_smoking"), consumer);
    }

    protected static void stairRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                        .define('#', ingredient.get()).pattern("#  ").pattern("## ").pattern("###")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);


    }

    protected static void slabRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                        .define('#', ingredient.get()).pattern("###")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);


    }

    protected static void wallRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                        .define('#', ingredient.get()).pattern("###").pattern("###")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);
    }

    protected static void pillarRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 2)
                        .define('#', ingredient.get()).pattern("#").pattern("#")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);
    }

    protected static void chiseledRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 1)
                        .define('#', ingredient.get()).pattern("#").pattern("#")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);
    }

    protected static void buttonRecipes(RecipeCategory category, ItemLike ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapelessRecipeBuilder.shapeless(category, result.get())
                        .requires(ingredient)
                        .unlockedBy(getHasName(ingredient), has(ingredient)), craftingPath(getItemName(result.get())), consumer);
    }

    protected static void waxButtonRecipes(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        String resultName = getItemName(result.get());

        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, result.get())
                        .requires(ingredient.get()).requires(Items.HONEYCOMB)
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(resultName + "_from_honeycomb"), consumer);

    }

    protected static void stonecutterRecipes(RecipeCategory category, Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, int resultCount, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        String prefix = getItemName(result.get()) + "_from_" + getItemName(ingredient.get());

        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient.get()), category, result.get(), resultCount)
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), stonecuttingPath(prefix + "_stonecutting"), consumer);
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

    protected static void smithingTemplateRecipes(RecipeCategory category, ItemLike templateItem, ItemLike baseItem, ItemLike addition, ItemLike result, ItemLike duplicationBase, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        smithingRecipes(category, templateItem, baseItem, addition, result, consumer);
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, templateItem, 2)
                        .define('#', Items.DIAMOND).define('C', duplicationBase).define('S', templateItem)
                        .pattern("#S#").pattern("#C#").pattern("###")
                        .unlockedBy(getHasName(templateItem), has(templateItem)), craftingPath(getItemName(templateItem)), consumer);

    }

    private static void planksFromLogsRecipe(TagKey<Item> pLogs, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                        .requires(pLogs)
                        .unlockedBy("has_logs", has(pLogs)), craftingPath(getItemName(result.get())), consumer);
    }

    private static void woodFromLogsRecipe(Supplier<? extends ItemLike> pLog, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 3)
                        .define('#', pLog.get()).pattern("##").pattern("##")
                        .unlockedBy("has_log", has(pLog.get())), craftingPath(getItemName(result.get())), consumer);
    }

    private static void fenceRecipe(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 3)
                        .define('W', ingredient.get()).define('#', Items.STICK)
                        .pattern("W#W").pattern("W#W")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);
    }
    
    private static void pressurePlateRecipe(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get())
                        .define('#', ingredient.get()).pattern("##")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);
    }

    private static void doorRecipe(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 3)
                        .define('#', ingredient.get())
                        .pattern("##").pattern("##").pattern("##")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);
    }

    private static void trapdoorRecipe(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 2)
                        .define('#', ingredient.get())
                        .pattern("###").pattern("###")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);
    }

    private static void fenceGateRecipe(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get())
                        .define('#', Items.STICK).define('W', ingredient.get())
                        .pattern("#W#").pattern("#W#")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);
    }

    private static void signRecipe(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 3)
                        .define('#', ingredient.get()).define('X', Items.STICK)
                        .pattern("###").pattern("###").pattern(" X ")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);
    }

    private static void hangingSignRecipe(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... features) {
        featureConditionRecipe(List.of(features), RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                        .define('#', ingredient.get()).define('X', Items.CHAIN)
                        .pattern("X X").pattern("###").pattern("###")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);
    }

    private static void gemLanterns(Supplier<? extends ItemLike> ingredient, Supplier<? extends ItemLike> result, Consumer<FinishedRecipe> consumer, SMFeatures... additionalFeatures) {
        List<SMFeatures> features = Stream.concat(Arrays.stream(additionalFeatures), Stream.of(SMFeatures.GEM_LANTERNS)).toList();

        featureConditionRecipe(features, RecipeCategory.BUILDING_BLOCKS,
                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                        .define('#', ingredient.get()).define('X', Items.GLOWSTONE_DUST)
                        .pattern(" # ")
                        .pattern("#X#")
                        .pattern(" # ")
                        .unlockedBy(getHasName(ingredient.get()), has(ingredient.get())), craftingPath(getItemName(result.get())), consumer);
    }

    private static void featureConditionRecipe(List<SMFeatures> features, RecipeCategory category, RecipeBuilder recipe, ResourceLocation customPath, Consumer<FinishedRecipe> consumer) {
        conditionalRecipe(new SMFeatureRecipeCondition(features), category, recipe, customPath, consumer);
    }

    private static void conditionalRecipe(ICondition condition, RecipeCategory category, RecipeBuilder recipe, ResourceLocation customPath, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder().addCondition(condition)
                .addRecipe(consumer1 -> recipe.save(consumer1, SullysMod.modPrefix(RecipeBuilder.getDefaultRecipeId(recipe.getResult()).getPath())))
                .generateAdvancement(new ResourceLocation(RecipeBuilder.getDefaultRecipeId(recipe.getResult()).getNamespace(), "recipes/" + category.getFolderName() + "/" + customPath.getPath()))
                .build(consumer, customPath);
    }
}
