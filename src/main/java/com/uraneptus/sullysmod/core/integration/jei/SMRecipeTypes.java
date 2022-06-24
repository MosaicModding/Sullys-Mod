package com.uraneptus.sullysmod.core.integration.jei;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.recipes.GrindstonePolishingRecipe;
import mezz.jei.api.recipe.RecipeType;

public class SMRecipeTypes {
    public static final RecipeType<GrindstonePolishingRecipe> POLISHING = RecipeType.create(SullysMod.MOD_ID, "polishing", GrindstonePolishingRecipe.class);
}
