package com.mosaicmodding.sullysmod.registry;

import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.util.function.UnaryOperator;

import static com.mosaicmodding.sullysmod.core.SMUtils.path;

public class SMRaritiesNeo {

    public static final EnumProxy<Rarity> ANCIENT_RARITY_PROXY = new EnumProxy<>(Rarity.class, -1, path("ancient").toString(), (UnaryOperator<Style>) s -> s.withColor(15107584));
}
