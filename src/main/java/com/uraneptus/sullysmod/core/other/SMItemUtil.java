package com.uraneptus.sullysmod.core.other;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SMItemUtil {
    public static void damageItem(Player player, ItemStack stack, InteractionHand hand) {
        if (!player.isCreative() && !player.getAbilities().instabuild) {
            stack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(hand));
        }
    }

    public static void triggerItemUsedOnBlock(Player player, ItemStack stack, BlockPos pos) {
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
        }
    }

    public static void nonCreativeShrinkStack(Player player, ItemStack stack) {
        if (!player.isCreative() && !player.getAbilities().instabuild) {
            stack.shrink(1);
        }
    }
}
