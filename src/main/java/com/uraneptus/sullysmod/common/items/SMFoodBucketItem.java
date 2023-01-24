package com.uraneptus.sullysmod.common.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class SMFoodBucketItem extends Item {
    public SMFoodBucketItem(Properties pProperties) {
        super(pProperties.craftRemainder(Items.BUCKET));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);
        if (pStack.isEmpty()) {
            return this.getBucket();
        } else {
            if (pEntityLiving instanceof Player player && !player.getAbilities().instabuild) {
                ItemStack itemStack = this.getBucket();
                if (!player.getInventory().add(itemStack)) {
                    player.drop(itemStack, false);
                }
            }
        }
        return pStack;
    }

    public ItemStack getBucket() {
        return new ItemStack(Items.BUCKET);
    }
}
