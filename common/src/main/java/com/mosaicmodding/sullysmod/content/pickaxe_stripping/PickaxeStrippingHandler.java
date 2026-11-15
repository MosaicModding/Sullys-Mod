package com.mosaicmodding.sullysmod.content.pickaxe_stripping;

import com.mosaicmodding.sullysmod.SullysMod;
import com.mosaicmodding.sullysmod.core.config.SMCommonConfig;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Function;

public class PickaxeStrippingHandler {

    public static InteractionResult handleInteraction(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        if (!SMCommonConfig.isEnabled(SMCommonConfig.PICKAXE_STRIPPING.getSecond())) return InteractionResult.PASS;
        var itemInHand = player.getItemInHand(hand);
        if (itemInHand.isEmpty()) return InteractionResult.PASS;
        if (!(itemInHand.getItem() instanceof PickaxeItem)) return InteractionResult.PASS;

        var pos = hitResult.getBlockPos();
        var originalState = level.getBlockState(pos);

        var strippedBlockResult = SullysMod.PLAT_HELPER.getPickaxeStrippableResult(originalState.getBlock());
        if (strippedBlockResult == null || strippedBlockResult.equals(originalState.getBlock())) return InteractionResult.PASS;

        var strippedState = strippedBlockResult.withPropertiesOf(originalState);

        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemInHand);
        }

        level.setBlock(pos, strippedState, Block.UPDATE_ALL_IMMEDIATE);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, strippedState));
        level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F); //TODO add pickaxe strip sound event & sound

        itemInHand.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}
