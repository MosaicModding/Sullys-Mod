package com.uraneptus.sullysmod.common.blocks;

import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;

import java.util.HashMap;
import java.util.Map;

public class CopperButtonBlock extends ButtonBlock {

    public CopperButtonBlock(Properties pProperties, BlockSetType pType, int pTicksToStayPressed, boolean pArrowsCanPress) {
        super(pProperties, pType, pTicksToStayPressed, pArrowsCanPress);
    }

    @Override
    protected SoundEvent getSound(boolean pushed) {
        return pushed ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        //Scraping wax away list
        HashMap<Block, Block> WAX_OFF_LIST = new HashMap<>();
        WAX_OFF_LIST.put(SMBlocks.WAXED_COPPER_BUTTON.get(), SMBlocks.COPPER_BUTTON.get());
        WAX_OFF_LIST.put(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), SMBlocks.EXPOSED_COPPER_BUTTON.get());
        WAX_OFF_LIST.put(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), SMBlocks.WEATHERED_COPPER_BUTTON.get());
        WAX_OFF_LIST.put(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), SMBlocks.OXIDIZED_COPPER_BUTTON.get());
        //Applying wax
        HashMap<Block, Block> WAX_ON_LIST = new HashMap<>();
        for (Map.Entry<Block, Block> entry : WAX_OFF_LIST.entrySet()) {
            WAX_ON_LIST.put(entry.getValue(), entry.getKey());
        }
        WAX_ON_LIST.put(SMBlocks.COPPER_BUTTON.get(), SMBlocks.WAXED_COPPER_BUTTON.get());

        //Stuff that makes this work
        ItemStack stack = player.getItemInHand(hand);

        InteractionResult returnResult = InteractionResult.PASS;

        if (stack.getItem() instanceof AxeItem) {
            if (WeatheringCopperButtonBlock.PREVIOUS_BY_BLOCK.get().containsKey(state.getBlock())) {
                this.triggerItemUsed(player, stack, pos);
                level.setBlock(pos, WeatheringCopperButtonBlock.PREVIOUS_BY_BLOCK.get().get(state.getBlock()).withPropertiesOf(state), 11);
                level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3005, pos, 0);

                this.damageItem(player, stack, hand);
                returnResult = InteractionResult.sidedSuccess(true);
            }
            else if (WAX_OFF_LIST.containsKey(state.getBlock())) {
                this.triggerItemUsed(player, stack, pos);
                level.setBlock(pos, WAX_OFF_LIST.get(state.getBlock()).withPropertiesOf(state), 11);
                level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3004, pos, 0);

                this.damageItem(player, stack, hand);
                returnResult = InteractionResult.sidedSuccess(true);
            }
        }
        else if (stack.getItem().equals(Items.HONEYCOMB)) {
            if (WAX_ON_LIST.containsKey(state.getBlock())) {
                this.triggerItemUsed(player, stack, pos);
                level.setBlock(pos, WAX_ON_LIST.get(state.getBlock()).withPropertiesOf(state), 11);
                level.playSound(player, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3003, pos, 0);

                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                returnResult = InteractionResult.sidedSuccess(true);
            }
        }
        else {
            returnResult = super.use(state, level, pos, player, hand, result);
        }
        return returnResult;
    }

    public void damageItem(Player player, ItemStack stack, InteractionHand hand) {
        if (!player.isCreative()) {
            stack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(hand));
        }
    }

    public void triggerItemUsed(Player player, ItemStack stack, BlockPos pos) {
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
        }
    }
}