package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.recipes.GrindstonePolishingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SMRecipeTypes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE.key(), SullysMod.MOD_ID);

    public static final RegistryObject<RecipeType<GrindstonePolishingRecipe>> GRINDSTONE_POLISHING = RECIPE_TYPES.register(GrindstonePolishingRecipe.NAME, () -> registerType(GrindstonePolishingRecipe.NAME));


    /**
     * Modified version of {@link RecipeType#register(String)}
     */
    public static <T extends Recipe<?>> RecipeType<T> registerType(final String identifier) {
        return new RecipeType<T>() {
            public String toString() {
                return SullysMod.MOD_ID + ":" + identifier;
            }
        };
    }
}
