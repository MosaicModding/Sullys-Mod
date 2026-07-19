package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.util.SMRarityBuilder;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public class SMRarities {
    public static final EnumProxy<Rarity> ANCIENT_RARITY_PROXY = new SMRarityBuilder(SullysMod.modPrefix("ancient")).withColor(15107584).build();
}
