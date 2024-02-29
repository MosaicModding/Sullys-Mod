package com.uraneptus.sullysmod.core.events;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.PickaxeStrippable;
import com.uraneptus.sullysmod.common.recipes.GrindstonePolishingRecipe;
import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.other.SMItemUtil;
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMParticleTypes;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Random;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SMPlayerEvents {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        InteractionHand hand = event.getHand();
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        RandomSource random = level.getRandom();
        ItemStack itemInHand = player.getItemInHand(hand);

        if (block instanceof GrindstoneBlock) {
            ArrayList<GrindstonePolishingRecipe> recipes = new ArrayList<>(GrindstonePolishingRecipe.getRecipes(level));
            for (GrindstonePolishingRecipe polishingRecipe : recipes) {
                for (ItemStack ingredient : polishingRecipe.getIngredients().iterator().next().getItems()) {
                    ItemStack result = polishingRecipe.result;
                    int resultCount = polishingRecipe.getResultCount();
                    int xpAmount = polishingRecipe.getExperience();

                    if (itemInHand.is(ingredient.getItem())) {
                        event.setCanceled(true);
                        event.setCancellationResult(InteractionResult.FAIL);
                        ItemStack resultItem = result.copy();
                        if (player.isShiftKeyDown()) {
                            int ingredientCount = itemInHand.getCount();
                            shrinkIngredientAddResults(player, itemInHand, resultItem, resultCount, ingredientCount);

                            if (!(xpAmount == 0)) {
                                for (int i = 0; i <= ingredientCount; i++) {
                                    int dropXp = random.nextInt(2);
                                    if (dropXp < 1) {
                                        xpAmount = xpAmount + polishingRecipe.getExperience();
                                    }
                                }
                                level.addFreshEntity(new ExperienceOrb(level, pos.getX(), pos.getY() + 1 , pos.getZ(), xpAmount));
                            }
                        } else {
                            resultItem.setCount(resultCount);
                            shrinkIngredientAddResults(player, itemInHand, resultItem, resultCount, 1);

                            if (!(xpAmount == 0)) {
                                int canDropXp = random.nextInt(2);

                                if (canDropXp < 1) {
                                    level.addFreshEntity(new ExperienceOrb(level, pos.getX(), pos.getY() + 1 , pos.getZ(), xpAmount));
                                }
                            }
                        }
                        player.swing(hand);
                        ParticleUtils.spawnParticlesOnBlockFace(level, pos, ParticleTypes.CRIT, UniformInt.of(1, 4), event.getFace(), () -> new Vec3(player.getLookAngle().x() + Mth.nextDouble(random, -0.5, 0.5), 0.8D, player.getLookAngle().z() + Mth.nextDouble(random, -0.5, 0.5)), 0.55D);
                        ParticleUtils.spawnParticlesOnBlockFace(level, pos, new ItemParticleOption(ParticleTypes.ITEM, itemInHand), UniformInt.of(1, 2), event.getFace(), () -> new Vec3(Mth.nextDouble(random, -0.05D, 0.05D), 0, Mth.nextDouble(random, -0.05D, 0.05D)), 0.55D);
                        level.playSound(player, pos, SMSounds.POLISH_JADE.get(), SoundSource.BLOCKS, 0.5F, 0.0F);
                    }
                }
            }
        }
        if (block instanceof PickaxeStrippable pickaxeStrippableBlock) {
            if (!(itemInHand.getItem() instanceof PickaxeItem)) return;
            level.setBlock(pos, BlockUtil.transferAllBlockStates(blockState, pickaxeStrippableBlock.getStrippedBlock().get().defaultBlockState()), 11);
            SMItemUtil.triggerItemUsedOnBlock(player, itemInHand, pos);
            SMItemUtil.damageItem(player, itemInHand, hand);
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            player.swing(hand);
        }
    }

    private static void shrinkIngredientAddResults(Player player, ItemStack itemInHand, ItemStack resultItem, int resultCount, int ingredientCount) {
        if (!player.getAbilities().instabuild) {
            itemInHand.shrink(ingredientCount);
        }
        if (!player.getInventory().add(new ItemStack(resultItem.getItem(), resultCount * ingredientCount))) {
            player.drop(new ItemStack(resultItem.getItem(), resultCount * ingredientCount), false);
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        Player player = event.getEntity();
        ItemStack itemstack = event.getItemStack();
        SMItems.ARTIFACTS.forEach((item, desc) -> {
            if (itemstack.is(item.get())) {
                event.getToolTip().add(desc);
            }
        });

        if (itemstack.is(SMItems.JADE_SHIELD.get()) || itemstack.is(SMItemTags.ARTIFACTS)) {
            itemstack.hideTooltipPart(ItemStack.TooltipPart.MODIFIERS);
        }

        if (player != null) {
            if (SMConfig.ENABLE_POLISHABLE_TOOLTIP.get()) {
                ArrayList<GrindstonePolishingRecipe> recipes = new ArrayList<>(GrindstonePolishingRecipe.getRecipes(player.level()));
                for (GrindstonePolishingRecipe polishingRecipe : recipes) {
                    for (ItemStack itemStack : polishingRecipe.getIngredients().iterator().next().getItems()) {
                        if (itemstack.is(itemStack.getItem())) {
                            event.getToolTip().add(SMTextDefinitions.POLISHABLE);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level();
        RandomSource random = player.getRandom();
        if (level.dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD) {
            if (player.getBlockY() < -62) {
                int randY = random.nextInt(5);
                int randXAndZ = random.nextInt(30);
                int chance = random.nextInt(500);
                if (chance == 1) {
                    player.level().addParticle(SMParticleTypes.BLOT_EYES.get(), player.getX() + randXAndZ, (player.getBlockY() - 3) - randY, player.getBlockZ() + randXAndZ, 0D, 0D, 0D);
                }
            }
        }
        if (level.dimensionTypeId() == BuiltinDimensionTypes.END) {
            if (player.getBlockY() <= 60) {
                int randY = random.nextInt(10);
                int randXAndZ = random.nextInt(50);
                int chance = random.nextInt(1000);
                if (chance == 1) {
                    BlockPos blockPos = new BlockPos(player.getBlockX() + randXAndZ, (player.getBlockY() - 20) - randY, player.getBlockZ() + randXAndZ);
                    Block block = level.getBlockState(blockPos).getBlock();
                    if (block == Blocks.AIR || block == Blocks.VOID_AIR) {
                        player.level().addParticle(SMParticleTypes.BLOT_EYES.get(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0D, 0D, 0D);
                    }
                }
            }
        }

    }
}