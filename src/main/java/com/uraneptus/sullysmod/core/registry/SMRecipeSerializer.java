package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.recipes.GrindstonePolishingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SMRecipeSerializer {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, SullysMod.MOD_ID);

    public static final Supplier<RecipeSerializer<GrindstonePolishingRecipe>> GRINDSTONE_POLISHING_SERIALIZER = SERIALIZERS.register(GrindstonePolishingRecipe.NAME, GrindstonePolishingRecipe.Serializer::new);
}
