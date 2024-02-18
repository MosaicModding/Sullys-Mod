package com.uraneptus.sullysmod.core.other;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SMItemUtil {
    public static void damageItem(Player player, ItemStack stack, InteractionHand hand) {
        if (notCreative(player)) {
            stack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(hand));
        }
    }

    public static void triggerItemUsedOnBlock(Player player, ItemStack stack, BlockPos pos) {
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
        }
    }

    public static void nonCreativeShrinkStack(Player player, ItemStack stack) {
        if (notCreative(player)) {
            stack.shrink(1);
        }
    }

    public static void nonCreativeAddItems(Player player, ItemStack stack) {
        if (notCreative(player)) {
            if (!player.getInventory().add(stack)) {
                player.drop(stack, false);
            }
        }

    }

    public static boolean notCreative(Player player) {
        return !player.isCreative() && !player.getAbilities().instabuild;
    }
}
