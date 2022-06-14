package com.uraneptus.sullysmod.core.events;

import com.mojang.math.Vector3f;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
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

        if (block instanceof GrindstoneBlock) {
            if (player.getItemInHand(hand).is(SMItems.RAW_JADE.get())) {
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.FAIL);

                if (!player.getAbilities().instabuild) {
                    item.shrink(1);
                }

                if (item.isEmpty()) {
                    player.setItemInHand(hand, new ItemStack(SMItems.POLISHED_JADE.get(), 1));
                } else {
                    ItemStack itemStack = new ItemStack(SMItems.POLISHED_JADE.get(), 1);
                    if (!player.getInventory().add(itemStack)) {
                        player.drop(itemStack, false);
                    }
                }
                player.swing(hand);
                level.playSound(player, pos, SMSounds.POLISH_JADE.get(), SoundSource.BLOCKS, 0.5F, 0.0F);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.getLevel();
        InteractionHand hand = player.getUsedItemHand();
        BlockPos pos = player.blockPosition();
        Vec3 vec3 = player.position();

        if (player.getItemInHand(hand).is(SMItems.RAW_JADE.get())) {
            for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-5, -5, -5), pos.offset(5, 5, 5))) {
                Block block = level.getBlockState(blockpos).getBlock();
                if (block instanceof GrindstoneBlock) {
                    ParticleUtils.spawnParticlesOnBlockFaces(level, blockpos, new DustParticleOptions(new Vector3f(Vec3.fromRGB24(16777215)), 0.4F), UniformInt.of(0, 1));
                }
            }
        }
    }
}
