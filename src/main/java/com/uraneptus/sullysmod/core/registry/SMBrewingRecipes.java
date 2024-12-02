package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.BrewingRecipe;

public class SMBrewingRecipes {

    public static void register() {
        registerRecipe(Potions.AWKWARD, SMItems.JADE.get(), Potions.LUCK, SMFeatures.JADE);
        registerRecipe(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, SMPotions.UNLUCK.get(), SMFeatures.UNLUCK_POTION);
        registerRecipe(Potions.AWKWARD, SMItems.TORTOISE_SCUTE.get(), SMPotions.RESISTANCE.get(), SMFeatures.RESISTANCE_POTION);
        registerRecipe(SMPotions.RESISTANCE.get(), Items.REDSTONE, SMPotions.LONG_RESISTANCE.get(), SMFeatures.RESISTANCE_POTION);
        registerRecipe(SMPotions.RESISTANCE.get(), Items.GLOWSTONE_DUST, SMPotions.STRONG_RESISTANCE.get(), SMFeatures.RESISTANCE_POTION);
    }

    //Note: It's not possible yet to prevent potion variant recipes (splash, lingering etc), but as long as the base potion can't be made, it's fine
    public static void registerRecipe(Potion input, Item ingredient, Potion result, SMFeatures feature) {
        if (SMFeatures.isEnabled(feature)) {
            PotionBrewing.addMix(input, ingredient, result);
        }
    }
}
