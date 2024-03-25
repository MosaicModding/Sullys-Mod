package com.uraneptus.sullysmod.data.server.builder;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMRecipeSerializer;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings({"unused", "deprecation"})
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GrindstonePolishingRecipeBuilder {
    private final RecipeCategory category;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Item result;
    private final int experience;
    private final int count;
    private final RecipeSerializer<?> serializer;
    private String recipeGroup;

    private GrindstonePolishingRecipeBuilder(RecipeCategory pCategory, ItemLike result, int count, int experience, RecipeSerializer<?> serializer) {
        this.category = pCategory;
        this.result = result.asItem();
        this.count = count;
        this.experience = experience;
        this.serializer = serializer;
    }

    public static GrindstonePolishingRecipeBuilder grindstonePolishing(RecipeCategory pCategory, ItemLike result) {
        return grindstonePolishing(pCategory, result, 1, 0);
    }

    public static GrindstonePolishingRecipeBuilder grindstonePolishingWithoutExperience(RecipeCategory pCategory, ItemLike result, int count) {
        return grindstonePolishing(pCategory, result, count, 0);
    }

    public static GrindstonePolishingRecipeBuilder grindstonePolishingWithoutCount(RecipeCategory pCategory, ItemLike result, int experience) {
        return grindstonePolishing(pCategory, result, 1, experience);
    }

    public static GrindstonePolishingRecipeBuilder grindstonePolishing(RecipeCategory pCategory, ItemLike result, int count, int experience) {
        return new GrindstonePolishingRecipeBuilder(pCategory, result, count, experience, SMRecipeSerializer.GRINDSTONE_POLISHING_SERIALIZER.get());
    }

    public GrindstonePolishingRecipeBuilder requires(TagKey<Item> pTag) {
        return this.requires(Ingredient.of(pTag));
    }

    public GrindstonePolishingRecipeBuilder requires(ItemLike pItem) {
        return this.requires(pItem, 1);
    }

    public GrindstonePolishingRecipeBuilder requires(ItemLike pItem, int pQuantity) {
        for(int i = 0; i < pQuantity; ++i) {
            this.requires(Ingredient.of(pItem));
        }

        return this;
    }

    public GrindstonePolishingRecipeBuilder requires(Ingredient pIngredient) {
        return this.requires(pIngredient, 1);
    }

    public GrindstonePolishingRecipeBuilder requires(Ingredient pIngredient, int pQuantity) {
        for(int i = 0; i < pQuantity; ++i) {
            this.ingredients.add(pIngredient);
        }

        return this;
    }

    public GrindstonePolishingRecipeBuilder group(String pGroupName) {
        this.recipeGroup = pGroupName;
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer) {
        save(consumer, "");
    }

    public void save(Consumer<FinishedRecipe> consumer, String suffix) {
        ResourceLocation resultLocation = ForgeRegistries.ITEMS.getKey(this.result);
        if (resultLocation != null) {
            consumer.accept(new GrindstonePolishingRecipeBuilder.Result(new ResourceLocation(SullysMod.MOD_ID + ":grindstone_polishing/" + resultLocation.getPath() + suffix), this.serializer, this.recipeGroup == null ? "" : this.recipeGroup, this.ingredients, this.result, this.count, this.experience));
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final RecipeSerializer<?> serializer;
        private final String group;
        private final List<Ingredient> ingredients;
        private final Item result;
        private final int count;
        private final int experience;

        public Result(ResourceLocation pId, RecipeSerializer<?> serializer, String pGroup, List<Ingredient> pIngredients, Item pResult, int count, int experience) {
            this.id = pId;
            this.serializer = serializer;
            this.group = pGroup;
            this.ingredients = pIngredients;
            this.result = pResult;
            this.count = count;
            this.experience = experience;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }

            JsonArray jsonarray = new JsonArray();
            for(Ingredient ingredient : this.ingredients) {
                jsonarray.add(ingredient.toJson());
            }
            pJson.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }
            pJson.add("result", jsonobject);
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
