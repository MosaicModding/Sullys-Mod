package com.uraneptus.sullysmod.core.integration.jei;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.recipes.GrindstonePolishingRecipe;
import mezz.jei.api.recipe.RecipeType;

public class JEIRecipeTypes {
    public static final RecipeType<GrindstonePolishingRecipe> GRINDSTONE_POLISHING = RecipeType.create(SullysMod.MOD_ID, GrindstonePolishingRecipe.NAME, GrindstonePolishingRecipe.class);
}
