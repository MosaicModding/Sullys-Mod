package com.uraneptus.sullysmod.core.events;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SMPlayerEvents {

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getPlayer();
        Level level = event.getWorld();
        BlockPos pos = event.getPos();
        ItemStack item = event.getItemStack();
        InteractionHand hand = event.getHand();
        Block block = level.getBlockState(pos).getBlock();

        if (player.isShiftKeyDown()) {
            if (block instanceof GrindstoneBlock) {
                if (player.getItemInHand(hand).is(SMItems.RAW_JADE.get())) {
                    if (!player.getAbilities().instabuild) {
                        item.shrink(1);
                    }

                    if (item.isEmpty()) {
                        player.setItemInHand(hand, new ItemStack(SMItems.JADE.get(), 1));
                    } else {
                        ItemStack itemStack = new ItemStack(SMItems.JADE.get(), 1);
                        if (!player.getInventory().add(itemStack)) {
                            player.drop(itemStack, false);
                        }
                    }
                    level.playSound(player, pos, SMSounds.POLISH_JADE.get(), SoundSource.BLOCKS, 0.5F, 0.0F);
                }
            }
        }
    }
}
