package com.uraneptus.sullysmod.core.integration.jei;

import com.google.common.collect.ImmutableList;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.integration.jei.category.SMPolishingCategory;
import com.uraneptus.sullysmod.core.integration.jei.other.SMPseudoPolishingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.Nonnull;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_ID = SullysMod.modPrefix("jei_plugin");

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new SMPolishingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(SMRecipeTypes.POLISHING, ImmutableList.of(new SMPseudoPolishingRecipe()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(Items.GRINDSTONE), SMRecipeTypes.POLISHING);
    }

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }
}
