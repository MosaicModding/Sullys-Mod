package com.uraneptus.sullysmod.common.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PetrifiedCookieItem extends Item {

    public PetrifiedCookieItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLivingEntity.hurt(pLivingEntity.damageSources().starve(), 1.0F);
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
