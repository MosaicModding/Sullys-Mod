package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.recipes.GrindstonePolishingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SMRecipeTypes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, SullysMod.MOD_ID);

    public static final Supplier<RecipeType<GrindstonePolishingRecipe>> GRINDSTONE_POLISHING = RECIPE_TYPES.register(GrindstonePolishingRecipe.NAME, () -> registerType(GrindstonePolishingRecipe.NAME));

    /**
     * Modified version of {@link RecipeType#register(String)}
     */
    public static <T extends Recipe<?>> RecipeType<T> registerType(final String identifier) {
        return new RecipeType<>() {
            public String toString() {
                return SullysMod.MOD_ID + ":" + identifier;
            }
        };
    }
}
