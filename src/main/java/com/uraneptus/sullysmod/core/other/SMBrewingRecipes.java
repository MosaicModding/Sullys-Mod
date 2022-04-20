package com.uraneptus.sullysmod.core.other;

import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;

public class SMBrewingRecipes {

    public static void register() {
        PotionBrewing.addMix(Potions.AWKWARD, SMItems.JADE.get(), Potions.LUCK);
    }
}
