package com.uraneptus.sullysmod.common.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.uraneptus.sullysmod.core.registry.SMRecipeSerializer;
import com.uraneptus.sullysmod.core.registry.SMRecipeTypes;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GrindstonePolishingRecipe implements Recipe<Container> {
    public static final String NAME = "grindstone_polishing";
    private final ResourceLocation id;
    private final String recipeGroup;
    private final NonNullList<Ingredient> ingredients;
    public final ItemStack result;
    private final int experience;

    public GrindstonePolishingRecipe(ResourceLocation id, String recipeGroup, NonNullList<Ingredient> pIngredients, ItemStack result, int experience) {
        this.id = id;
        this.recipeGroup = recipeGroup;
        this.ingredients = pIngredients;
        this.result = result;
        this.experience = experience;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return true;
    }

    @Override
    public ItemStack assemble(Container pContainer, RegistryAccess pRegistryAccess) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return result;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getResultCount() {
        return this.result.getCount();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String getGroup() {
        return this.recipeGroup;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeType<?> getType() {
        return SMRecipeTypes.GRINDSTONE_POLISHING.get();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SMRecipeSerializer.GRINDSTONE_POLISHING_SERIALIZER.get();
    }

    public static List<GrindstonePolishingRecipe> getRecipes(Level level) {
        return level.getRecipeManager().getAllRecipesFor(SMRecipeTypes.GRINDSTONE_POLISHING.get());
    }

    public static class Serializer implements RecipeSerializer<GrindstonePolishingRecipe> {

        @Override
        public GrindstonePolishingRecipe fromJson(ResourceLocation pRecipeId, JsonObject jsonObject) {
            String group = GsonHelper.getAsString(jsonObject, "group", "");

            NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(jsonObject, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for polishing recipe");
            }
            if (!jsonObject.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            ItemStack result;
            if (jsonObject.get("result").isJsonObject()) {
                result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            }
            else {
                String resultItem = GsonHelper.getAsString(jsonObject, "result");
                ResourceLocation resourcelocation = new ResourceLocation(resultItem);
                result = new ItemStack(ForgeRegistries.ITEMS.getDelegate(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + resultItem + " does not exist")));
            }
            int experience = GsonHelper.getAsInt(jsonObject, "experience", 0);

            return new GrindstonePolishingRecipe(pRecipeId, group, nonnulllist, result, experience);
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray pIngredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();
            int ingredientSize = pIngredientArray.size();
            for(int i = 0; i < ingredientSize; ++i) {
                Ingredient ingredient = Ingredient.fromJson(pIngredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        @Nullable
        @Override
        public GrindstonePolishingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            String group = pBuffer.readUtf();
            int i = pBuffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            for (int j = 0; j < i; j++) {
                nonnulllist.set(j, Ingredient.fromNetwork(pBuffer));
            }
            ItemStack result = pBuffer.readItem();
            int experience = pBuffer.readInt();

            return new GrindstonePolishingRecipe(pRecipeId, group, nonnulllist, result, experience);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, GrindstonePolishingRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.recipeGroup);
            pBuffer.writeVarInt(pRecipe.ingredients.size());
            for(Ingredient ingredient : pRecipe.ingredients) {
                ingredient.toNetwork(pBuffer);
            }
            pBuffer.writeItem(pRecipe.result);
            pBuffer.writeInt(pRecipe.experience);
        }
    }
}
