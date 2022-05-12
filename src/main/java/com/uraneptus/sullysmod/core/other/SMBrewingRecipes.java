package com.uraneptus.sullysmod.core.other;

import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;

public class SMBrewingRecipes {

    public static void register() {
        PotionBrewing.addMix(Potions.AWKWARD, SMItems.JADE.get(), Potions.LUCK);
        PotionBrewing.addMix(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, SMPotions.UNLUCK.get());
    }
}
