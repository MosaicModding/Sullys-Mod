package com.mosaicmodding.sullysmod.platform.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.MixinIntrinsics;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Rarity.class)
public enum RarityMixin {
    SULLYSMOD_ANCIENT(MixinIntrinsics.currentEnumOrdinal(), "ancient", ChatFormatting.GOLD);

    @Shadow
    RarityMixin(final int id, final String name, final ChatFormatting color) {}
}
