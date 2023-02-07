package com.uraneptus.sullysmod.core.events;

import com.mojang.math.Vector3f;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.recipes.GrindstonePolishingRecipe;
import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SMPlayerEvents {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        InteractionHand hand = event.getHand();
        Block block = level.getBlockState(pos).getBlock();
        RandomSource random = level.getRandom();

        if (block instanceof GrindstoneBlock) {
            ArrayList<GrindstonePolishingRecipe> recipes = new ArrayList<>(GrindstonePolishingRecipe.getRecipes(level));
            for (GrindstonePolishingRecipe polishingRecipe : recipes) {
                if (!recipes.isEmpty()) {
                    ItemStack ingredient = polishingRecipe.ingredient;
                    ItemStack itemInHand = player.getItemInHand(hand);
                    ItemStack result = polishingRecipe.getResultItem();
                    int resultCount = polishingRecipe.getResultCount();
                    int xpAmount = polishingRecipe.getExperience();

                    if (itemInHand.is(ingredient.getItem())) {
                        event.setCanceled(true);
                        event.setCancellationResult(InteractionResult.FAIL);
                        ItemStack resultItem = result.copy();
                        if (player.isShiftKeyDown()) {
                            int ingredientCount = itemInHand.getCount();

                            if (!player.getAbilities().instabuild) {
                                itemInHand.shrink(ingredientCount);
                            }
                            if (!player.getInventory().add(new ItemStack(resultItem.getItem(), resultCount * ingredientCount))) {
                                player.drop(new ItemStack(resultItem.getItem(), resultCount * ingredientCount), false);
                            }
                            if (!(xpAmount == 0)) {
                                for (int i = 0; i <= ingredientCount; i++) {
                                    int dropXp = random.nextInt(2);
                                    if (dropXp < 1) {
                                        xpAmount = xpAmount + polishingRecipe.getExperience();
                                    }
                                }
                                level.addFreshEntity(new ExperienceOrb(level, pos.getX(), pos.getY() + 1, pos.getZ(), xpAmount));
                            }
                        } else {
                            resultItem.setCount(resultCount);
                            if (!player.getAbilities().instabuild) {
                                itemInHand.shrink(1);
                            }
                            if (!player.getInventory().add(new ItemStack(resultItem.getItem(), resultCount))) {
                                player.drop(new ItemStack(resultItem.getItem(), resultCount), false);
                            }
                            if (!(xpAmount == 0)) {
                                int canDropXp = random.nextInt(2);

                                if (canDropXp < 1) {
                                    level.addFreshEntity(new ExperienceOrb(level, pos.getX(), pos.getY() + 1, pos.getZ(), xpAmount));
                                }
                            }
                        }
                        player.swing(hand);
                        level.playSound(player, pos, SMSounds.POLISH_JADE.get(), SoundSource.BLOCKS, 0.5F, 0.0F);
                    }
                }
            }
        }
    }

    //TODO Perhaps make this a one time thing? When used grindstone this won't appear
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.getLevel();
        InteractionHand hand = player.getUsedItemHand();
        BlockPos pos = player.blockPosition();
        ArrayList<GrindstonePolishingRecipe> recipes = new ArrayList<>(GrindstonePolishingRecipe.getRecipes(level));
        if (level.isClientSide) {
            if (SMConfig.PARTICLES_AROUND_GRINDSTONE.get()) {
                for (GrindstonePolishingRecipe polishingRecipe : recipes) {
                    if (!recipes.isEmpty()) {
                        ItemStack ingredient = polishingRecipe.ingredient;
                        if (player.getItemInHand(hand).is(ingredient.getItem())) {
                            for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-7, -7, -7), pos.offset(7, 7, 7))) {
                                Block block = level.getBlockState(blockpos).getBlock();
                                if (block instanceof GrindstoneBlock) {
                                    ParticleUtils.spawnParticlesOnBlockFaces(level, blockpos, new DustParticleOptions(new Vector3f(Vec3.fromRGB24(16777215)), 0.4F), UniformInt.of(0, 1));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack item = event.getItemStack();
        if (item.is(SMItems.JADE_SHIELD.get())) {
            item.hideTooltipPart(ItemStack.TooltipPart.MODIFIERS);
        }
    }
}
