package com.uraneptus.sullysmod.core.integration.jei;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.integration.jei.other.SMPseudoPolishingRecipe;
import mezz.jei.api.recipe.RecipeType;

public class SMRecipeTypes {
    public static final RecipeType<SMPseudoPolishingRecipe> POLISHING = RecipeType.create(SullysMod.MOD_ID, "polishing", SMPseudoPolishingRecipe.class);
}
