package com.uraneptus.sullysmod.core.integration.jei.category;

import com.google.common.collect.ImmutableList;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.integration.jei.SMRecipeTypes;
import com.uraneptus.sullysmod.core.integration.jei.other.SMPseudoPolishingRecipe;
import com.uraneptus.sullysmod.core.registry.SMItems;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Collections;
import java.util.List;

public class SMPolishingCategory implements IRecipeCategory<SMPseudoPolishingRecipe> {

    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;

    public SMPolishingCategory(IGuiHelper helper) {
        title = new TranslatableComponent(SullysMod.MOD_ID + ".jei." + getUid().getPath());
        background = helper.createDrawable(SullysMod.modPrefix("textures/gui/jei/polishing.png"), 0, 0, 96, 31);
        icon = helper.createDrawableIngredient(new ItemStack(Items.GRINDSTONE));
    }

    @Override
    public Component getTitle() {
        return this.title;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public ResourceLocation getUid() {
        return this.getRecipeType().getUid();
    }

    @Override
    public Class<? extends SMPseudoPolishingRecipe> getRecipeClass() {
        return this.getRecipeType().getRecipeClass();
    }

    @Override
    public RecipeType<SMPseudoPolishingRecipe> getRecipeType() {
        return SMRecipeTypes.POLISHING;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder layout, SMPseudoPolishingRecipe polishingRecipes, IFocusGroup ingredients) {
        layout.addSlot(RecipeIngredientRole.INPUT, 4, 9).addItemStack(new ItemStack(SMItems.ROUGH_JADE.get()));
        layout.addSlot(RecipeIngredientRole.OUTPUT, 76, 9).addItemStack(new ItemStack(SMItems.POLISHED_JADE.get()));
    }

    @Override
    public List<Component> getTooltipStrings(SMPseudoPolishingRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (iconPosition(31, 3, mouseX, mouseY)) {
            return ImmutableList.of(new TranslatableComponent(SullysMod.MOD_ID + ".jei." + getUid().getPath() + ".info"));
        }
        return Collections.emptyList();
    }

    private static boolean iconPosition(int x, int y, double mouseX, double mouseY) {
        final int iconHeight = 25;
        final int iconWidth = 26;
        return x <= mouseX && mouseX < x + iconWidth && y <= mouseY && mouseY < y + iconHeight;
    }
}
