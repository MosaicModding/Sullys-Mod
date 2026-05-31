package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.core.SMFeatures;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

public class SMBrewingRecipes {

    @SubscribeEvent // on the game event bus
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        if (SMFeatures.isEnabled(SMFeatures.JADE)) {
            builder.addMix(Potions.AWKWARD, SMItems.JADE.get(), Potions.LUCK);
        }
        if (SMFeatures.isEnabled(SMFeatures.UNLUCK_POTION)) {
            builder.addMix(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, SMPotions.UNLUCK);
        }
        if (SMFeatures.isEnabled(SMFeatures.RESISTANCE_POTION)) {
            builder.addMix(Potions.AWKWARD,  SMItems.TORTOISE_SCUTE.get(), SMPotions.RESISTANCE);
            builder.addMix(SMPotions.RESISTANCE, Items.REDSTONE, SMPotions.LONG_RESISTANCE);
            builder.addMix(SMPotions.RESISTANCE, Items.GLOWSTONE_DUST, SMPotions.STRONG_RESISTANCE);
        }
    }
}
