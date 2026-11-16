package com.mosaicmodding.sullysmod.server.recipes;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.registry.SMBlocks;
import com.mosaicmodding.sullysmod.registry.SMItems;
import com.mosaicmodding.sullysmod.registry.registry_sets.SimpleBlockRegistrySet;
import com.mosaicmodding.sullysmod.registry.registry_sets.WoodBlockRegistrySet;
import com.mosaicmodding.sullysmod.server.fabricated_conditions.DatagenConfigFlagCondition;
import com.mosaicmodding.tessera.datagen.providers.TesseraRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.neoforge.common.conditions.AndCondition;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SMRecipeGen extends TesseraRecipeProvider {

    public SMRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(SullysMod.ID, output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        SMBlocks.REG_SETS.forEach(set -> {
            if (set instanceof SimpleBlockRegistrySet blockRegistrySet) {
                createForBlockSet(blockRegistrySet, recipeOutput);
            }
            if (set instanceof WoodBlockRegistrySet woodBlockRegistrySet) {
                createForWoodSet(woodBlockRegistrySet, recipeOutput);
            }
        });

        cookingRecipes(SMItems.LANTERNFISH.get(), SMItems.COOKED_LANTERNFISH.get(), 0.35F, withFeature(SMCommonConfig.LANTERNFISH.getSecond(), recipeOutput));
    }

    private void createForBlockSet(SimpleBlockRegistrySet blockSet, RecipeOutput recipeOutput) {
        var baseIngredient = blockSet.getBaseIngredient().get();
        bricksLikeBlockRecipe(baseIngredient, blockSet.getBricksBlock().get(), withFeature(blockSet.feature, recipeOutput));
        stonecutterRecipes(baseIngredient, blockSet.getBricksBlock().get(), 1, withFeature(blockSet.feature, recipeOutput));

        stairRecipes(blockSet.getBricksBlock().get(), blockSet.getStairsBlock().get(), withFeature(blockSet.feature, recipeOutput));
        stonecutterRecipes(baseIngredient, blockSet.getStairsBlock().get(), 1, withFeature(blockSet.feature, recipeOutput));
        stonecutterRecipes(blockSet.getBricksBlock().get(), blockSet.getStairsBlock().get(), 1, withFeature(blockSet.feature, recipeOutput));

        slabRecipes(blockSet.getBricksBlock().get(), blockSet.getSlabBlock().get(), withFeature(blockSet.feature, recipeOutput));
        stonecutterRecipes(baseIngredient, blockSet.getSlabBlock().get(), 2, withFeature(blockSet.feature, recipeOutput));
        stonecutterRecipes(blockSet.getBricksBlock().get(), blockSet.getSlabBlock().get(), 2, withFeature(blockSet.feature, recipeOutput));

        if (blockSet.getWallBlock().isPresent()) {
            wall(withFeature(blockSet.feature, recipeOutput), RecipeCategory.BUILDING_BLOCKS, blockSet.getWallBlock().get().get(), blockSet.getBricksBlock().get());
            stonecutterRecipes(baseIngredient, blockSet.getWallBlock().get().get(), 1, withFeature(blockSet.feature, recipeOutput));
            stonecutterRecipes(blockSet.getBricksBlock().get(), blockSet.getWallBlock().get().get(), 1, withFeature(blockSet.feature, recipeOutput));
        }

        if (blockSet.getPillarBlock().isPresent()) {
            pillarRecipes(blockSet.getBricksBlock().get(), blockSet.getPillarBlock().get().get(), withFeature(blockSet.feature, recipeOutput));
            stonecutterRecipes(baseIngredient, blockSet.getPillarBlock().get().get(), 1, withFeature(blockSet.feature, recipeOutput));
            stonecutterRecipes(blockSet.getBricksBlock().get(), blockSet.getPillarBlock().get().get(), 1, withFeature(blockSet.feature, recipeOutput));
        }

        if (blockSet.getChiseledBlock().isPresent()) {
            chiseled(withFeature(blockSet.feature, recipeOutput), RecipeCategory.BUILDING_BLOCKS, blockSet.getChiseledBlock().get().get(), blockSet.getBricksBlock().get());
            stonecutterRecipes(baseIngredient, blockSet.getChiseledBlock().get().get(), 1, withFeature(blockSet.feature, recipeOutput));
            stonecutterRecipes(blockSet.getBricksBlock().get(), blockSet.getChiseledBlock().get().get(), 1, withFeature(blockSet.feature, recipeOutput));
        }
    }

    private void createForWoodSet(WoodBlockRegistrySet blockSet, RecipeOutput recipeOutput) {
        var featureOutput = withFeature(blockSet.feature, recipeOutput);
        var planks = blockSet.getPlanks().get();
        planksFromLogsRecipe(blockSet.getLogsTagBundle().itemTag(), planks, featureOutput);
        woodFromLogsRecipe(blockSet.getLog().get(), blockSet.getWood().get(), featureOutput);
        woodFromLogsRecipe(blockSet.getStrippedLog().get(), blockSet.getStrippedWood().get(), featureOutput);
        slabRecipes(planks, blockSet.getSlab().get(), featureOutput);
        stairRecipes(planks, blockSet.getStairs().get(), featureOutput);
        fenceRecipe(planks, blockSet.getFence().get(), featureOutput);

        //TODO by default this uses Redstone category. However woodset buttons should be in BuildingBlocks
        buttonRecipes(planks, blockSet.getButton().get(), featureOutput);
        pressurePlateRecipe(planks, blockSet.getPressurePlate().get(), featureOutput);
        doorRecipe(planks, blockSet.getDoor().get(), featureOutput);
        trapdoorRecipe(planks, blockSet.getTrapdoor().get(), featureOutput);
        fenceGateRecipe(planks, blockSet.getFenceGate().get(), featureOutput);
        signRecipe(planks, blockSet.getSign().getLeft().get(), featureOutput);
        hangingSignRecipe(planks, blockSet.getHangingSign().getLeft().get(), featureOutput);
    }
    
    private RecipeOutput withFeature(String feature, RecipeOutput output) {
        return output.withConditions(new DatagenConfigFlagCondition(feature));
    }

    private RecipeOutput withFeatures(List<String> features, RecipeOutput output) {
        if (features.isEmpty()) return output;
        List<ICondition> list = new ArrayList<>();
        for (var feat : features) {
            list.add(new DatagenConfigFlagCondition(feat));
        }
        return output.withConditions(new AndCondition(list));
    }

}
