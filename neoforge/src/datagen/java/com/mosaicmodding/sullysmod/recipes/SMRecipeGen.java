package com.mosaicmodding.sullysmod.recipes;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import com.mosaicmodding.sullysmod.fabricated_conditions.DatagenConfigFlagCondition;
import com.mosaicmodding.sullysmod.registry.SMItems;
import com.mosaicmodding.tessera.datagen.providers.TesseraRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.AndCondition;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.mosaicmodding.sullysmod.registry.SMRegUtils.path;

public class SMRecipeGen extends TesseraRecipeProvider {

    public SMRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(SullysMod.ID, output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        cookingRecipes(SMItems.LANTERNFISH.get(), SMItems.COOKED_LANTERNFISH.get(), 0.35F, withFeature(SMCommonConfig.LANTERNFISH.getSecond(), recipeOutput));
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
