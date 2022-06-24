package com.uraneptus.sullysmod.core.data.server.builder;

import com.google.gson.JsonObject;
import com.uraneptus.sullysmod.common.recipes.GrindstonePolishingRecipe;
import com.uraneptus.sullysmod.core.registry.SMRecipeSerializer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class GrindstonePolishingRecipeBuilder implements RecipeBuilder {

    private final Item ingredient;
    private final Item result;
    private final int experience;
    private final int resultCount;
    private final RecipeSerializer<?> serializer;
    private String recipeGroup;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

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
        return new GrindstonePolishingRecipeBuilder(ingredient, result, resultCount, experience, SMRecipeSerializer.POLISHING_SERIALIZER.get());
    }

    //Recipe popup doesn't really work rn
    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
       this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
       return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        this.recipeGroup = pGroupName;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.ensureValid(pRecipeId);
        this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new GrindstonePolishingRecipeBuilder.Result(pRecipeId, this.serializer, this.recipeGroup == null ? "" : this.recipeGroup, this.ingredient, this.result, this.resultCount, this.experience, this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath())));

    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final RecipeSerializer<?> serializer;
        private final String group;
        private final Item ingredient;
        private final Item result;
        private final int resultCount;
        private final int experience;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, RecipeSerializer<?> serializer, String pGroup, Item pIngredient, Item pResult, int resultCount, int experience, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
            this.id = pId;
            this.serializer = serializer;
            this.group = pGroup;
            this.ingredient = pIngredient;
            this.result = pResult;
            this.resultCount = resultCount;
            this.experience = experience;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }

            pJson.addProperty("ingredient", Registry.ITEM.getKey(this.ingredient).toString());
            pJson.addProperty("result", Registry.ITEM.getKey(this.result).toString());
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
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
