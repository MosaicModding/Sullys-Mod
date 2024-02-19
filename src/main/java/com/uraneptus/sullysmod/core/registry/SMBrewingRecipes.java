package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;

public class SMBrewingRecipes {

    public static void register() {
        DataUtil.addMix(Potions.AWKWARD, SMItems.POLISHED_JADE.get(), Potions.LUCK);
        DataUtil.addMix(Potions.LUCK, Items.FERMENTED_SPIDER_EYE, SMPotions.UNLUCK.get());
        DataUtil.addMix(Potions.AWKWARD, SMItems.TORTOISE_SCUTE.get(), SMPotions.RESISTANCE.get());
        DataUtil.addMix(SMPotions.RESISTANCE.get(), Items.REDSTONE, SMPotions.LONG_RESISTANCE.get());
        DataUtil.addMix(SMPotions.RESISTANCE.get(), Items.GLOWSTONE_DUST, SMPotions.STRONG_RESISTANCE.get());
    }
}
