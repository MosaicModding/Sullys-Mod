package com.uraneptus.sullysmod.core.integration.jei;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.recipes.GrindstonePolishingRecipe;
import com.uraneptus.sullysmod.core.integration.jei.category.SMPolishingCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.Nonnull;

@JEI
public class JEIPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_ID = SullysMod.modPrefix("jei_plugin");

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new SMPolishingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel level = Minecraft.getInstance().level;
        registration.addRecipes(GrindstonePolishingRecipe.getRecipes(level), JEIRecipeTypes.GRINDSTONE_POLISHING.getUid());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(Items.GRINDSTONE), JEIRecipeTypes.GRINDSTONE_POLISHING);
    }

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }
}
