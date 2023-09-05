package com.uraneptus.sullysmod.core.data.server.builder;

import com.google.gson.JsonObject;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMRecipeSerializer;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

@SuppressWarnings({"unused", "deprecation"})
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GrindstonePolishingRecipeBuilder {

    private final Item ingredient;
    private final Item result;
    private final int experience;
    private final int resultCount;
    private final RecipeSerializer<?> serializer;
    private String recipeGroup;

    private GrindstonePolishingRecipeBuilder(ItemLike ingredient, ItemLike result, int resultCount, int experience, RecipeSerializer<?> serializer) {
        this.ingredient = ingredient.asItem();
        this.result = result.asItem();
        this.resultCount = resultCount;
        this.experience = experience;
        this.serializer = serializer;
    }

    public static GrindstonePolishingRecipeBuilder grindstonePolishing(ItemLike ingredient, ItemLike result) {
        return grindstonePolishing(ingredient, result, 1, 0);
    }

    public static GrindstonePolishingRecipeBuilder grindstonePolishingWithoutExperience(ItemLike ingredient, ItemLike result, int resultCount) {
        return grindstonePolishing(ingredient, result, resultCount, 0);
    }

    public static GrindstonePolishingRecipeBuilder grindstonePolishingWithoutCount(ItemLike ingredient, ItemLike result, int experience) {
        return grindstonePolishing(ingredient, result, 1, experience);
    }

    public static GrindstonePolishingRecipeBuilder grindstonePolishing(ItemLike ingredient, ItemLike result, int resultCount, int experience) {
        return new GrindstonePolishingRecipeBuilder(ingredient, result, resultCount, experience, SMRecipeSerializer.GRINDSTONE_POLISHING_SERIALIZER.get());
    }

    //The save methods here could be improved!
    public void save(Consumer<FinishedRecipe> consumer) {
        ResourceLocation resultLocation = ForgeRegistries.ITEMS.getKey(this.result);
        ResourceLocation ingredientLocation = ForgeRegistries.ITEMS.getKey(this.ingredient);
        if (resultLocation != null && ingredientLocation != null) {
            this.save(consumer, SullysMod.MOD_ID + ":grindstone_polishing/" + resultLocation.getPath() + "_from_polishing" + "_" + ingredientLocation.getPath());
        }
    }

    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, String save) {
        ResourceLocation defaultResourcelocation = ForgeRegistries.ITEMS.getKey(this.result);
        ResourceLocation resourcelocation = new ResourceLocation(save);
        if (resourcelocation.equals(defaultResourcelocation)) {
            throw new IllegalStateException("Polishing Recipe " + save + " should remove its 'save' argument as it is equal to default one");
        } else {
            this.save(pFinishedRecipeConsumer, resourcelocation);
        }
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        consumer.accept(new GrindstonePolishingRecipeBuilder.Result(id, this.serializer, this.recipeGroup == null ? "" : this.recipeGroup, this.ingredient, this.result, this.resultCount, this.experience));
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final RecipeSerializer<?> serializer;
        private final String group;
        private final Item ingredient;
        private final Item result;
        private final int resultCount;
        private final int experience;

        public Result(ResourceLocation pId, RecipeSerializer<?> serializer, String pGroup, Item pIngredient, Item pResult, int resultCount, int experience) {
            this.id = pId;
            this.serializer = serializer;
            this.group = pGroup;
            this.ingredient = pIngredient;
            this.result = pResult;
            this.resultCount = resultCount;
            this.experience = experience;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }

            pJson.addProperty("ingredient", ForgeRegistries.ITEMS.getKey(this.ingredient).toString());
            pJson.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
            pJson.addProperty("resultCount", this.resultCount);
            pJson.addProperty("experience", this.experience);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return this.serializer;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
