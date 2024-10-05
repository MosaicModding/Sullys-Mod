package com.uraneptus.sullysmod.core.events;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.AmberBlock;
import com.uraneptus.sullysmod.common.blocks.AmberLayeredCauldronBlock;
import com.uraneptus.sullysmod.common.blocks.PickaxeStrippable;
import com.uraneptus.sullysmod.common.recipes.GrindstonePolishingRecipe;
import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.other.SMItemUtil;
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import com.uraneptus.sullysmod.core.other.tags.SMBiomeTags;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMParticleTypes;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID)
@SuppressWarnings("unused")
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
                        Direction face = event.getFace();
                        if (face != null) {
                            ParticleUtils.spawnParticlesOnBlockFace(level, pos, ParticleTypes.CRIT, UniformInt.of(1, 4), face, () -> new Vec3(player.getLookAngle().x() + Mth.nextDouble(random, -0.5, 0.5), 0.8D, player.getLookAngle().z() + Mth.nextDouble(random, -0.5, 0.5)), 0.55D);
                            ParticleUtils.spawnParticlesOnBlockFace(level, pos, new ItemParticleOption(ParticleTypes.ITEM, itemInHand), UniformInt.of(1, 2), face, () -> new Vec3(Mth.nextDouble(random, -0.05D, 0.05D), 0, Mth.nextDouble(random, -0.05D, 0.05D)), 0.55D);
                        }
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
        if (block instanceof CauldronBlock cauldron && itemInHand.is(SMItems.MOLTEN_AMBER_BUCKET.get())) {
            event.setCancellationResult(CauldronInteraction.emptyBucket(level, pos, player, hand, new ItemStack(SMItems.MOLTEN_AMBER_BUCKET.get()),
                    SMBlocks.AMBER_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3), SoundEvents.BUCKET_EMPTY));
            event.setCanceled(true);
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
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        ItemStack itemInHand = event.getItemStack();
        Player player = event.getEntity();
        Level level = event.getLevel();
        if (itemInHand.is(SMItems.LOST_RECIPE_BOOK.get()) && level.getServer() != null && player instanceof ServerPlayer serverPlayer) {
            List<Recipe<?>> unknownRecipes = new ArrayList<>();

            for (Recipe<?> recipe : level.getServer().getRecipeManager().getRecipes()) {
                if (!serverPlayer.getRecipeBook().contains(recipe)) {
                    unknownRecipes.add(recipe);
                }
            }

            Recipe<?> recipe = unknownRecipes.get(player.getRandom().nextInt(unknownRecipes.size()));
            serverPlayer.awardRecipes(List.of(recipe));
            level.playSound(null, player.blockPosition(), SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS);
            if (!player.getAbilities().instabuild) {
                player.setItemInHand(event.getHand(), ItemStack.EMPTY);
            }
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        Player player = event.getEntity();
        ItemStack itemstack = event.getItemStack();
        SMItems.ARTIFACT_DESC_MAP.forEach((item, desc) -> {
            if (itemstack.is(item.get())) {
                event.getToolTip().add(desc);
            }
        });

        if (itemstack.is(SMItems.JADE_SHIELD.get()) || (itemstack.is(SMItemTags.ARTIFACTS) && !itemstack.is(SMItems.BROKEN_BOTTLE.get()) && !itemstack.is(SMItems.PRIMITIVE_KNIFE.get()))) { //This also hides damage values of artifacts
            if (FMLEnvironment.production) {
                itemstack.hideTooltipPart(ItemStack.TooltipPart.MODIFIERS);
            }
        }

        if (player != null) {
            if (SMConfig.ENABLE_POLISHABLE_TOOLTIP.get()) {
                ArrayList<GrindstonePolishingRecipe> recipes = new ArrayList<>(GrindstonePolishingRecipe.getRecipes(player.level()));
                for (GrindstonePolishingRecipe polishingRecipe : recipes) {
                    for (ItemStack polishableItems : polishingRecipe.getIngredients().iterator().next().getItems()) {
                        if (itemstack.is(polishableItems.getItem())) {
                            event.getToolTip().add(SMTextDefinitions.POLISHABLE);
                        }
                    }
                }
            }
            if (itemstack.is(SMItems.THROWING_KNIFE.get())) {
                event.getToolTip().add(CommonComponents.EMPTY);
                event.getToolTip().add(SMTextDefinitions.WHEN_THROWN_TOOLTIP);
                event.getToolTip().add(SMTextDefinitions.THROWING_KNIFE_DAMAGE);
                event.getToolTip().add(SMTextDefinitions.THROWING_KNIFE_AIR_DAMAGE);
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
                int randX = random.nextInt(20);
                int randZ = random.nextInt(20);
                int chance = random.nextInt(750);
                if (chance == 1) {
                    player.level().addParticle(SMParticleTypes.BLOT_EYES.get(), player.getBlockX() + randX, (player.getBlockY() - 3) - randY, player.getBlockZ() + randZ, 0D, 0D, 0D);
                }
            }
        }
        if (level.dimensionTypeId() == BuiltinDimensionTypes.END) {
            if (player.getBlockY() <= 60) {
                int randY = random.nextInt(5);
                int randX = random.nextInt(20);
                int randZ = random.nextInt(20);
                int chance = random.nextInt(1500);
                if (chance == 1) {
                    BlockPos blockPos = new BlockPos(player.getBlockX() + randX, (player.getBlockY() - 3) - randY, player.getBlockZ() + randZ);
                    Block block = level.getBlockState(blockPos).getBlock();
                    if (block == Blocks.AIR || block == Blocks.VOID_AIR) {
                        player.level().addParticle(SMParticleTypes.BLOT_EYES.get(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0D, 0D, 0D);
                    }
                }
            }
        }
        if (level.getBiome(new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ())).is(SMBiomeTags.SNOWY_MOUNTAINS)) {
            if (player.getBlockY() > 100) {
                int randX = random.nextInt(5);
                int randZ = random.nextInt(5);
                int chance = random.nextInt(25000);
                if (chance == 1) {
                    if (player.getDirection().getAxisDirection() == Direction.AxisDirection.POSITIVE) {
                        level.playSound(player, new BlockPos((player.getBlockX() - 5) - randX, player.getBlockY(), (player.getBlockZ() - 5) - randZ), SMSounds.MOUNTAIN_CALLS.get(), SoundSource.AMBIENT, 0.1F, 1F);
                    } else if (player.getDirection().getAxisDirection() == Direction.AxisDirection.NEGATIVE) {
                        level.playSound(player, new BlockPos((player.getBlockX() + 5) + randX, player.getBlockY(), (player.getBlockZ() + 5) + randZ), SMSounds.MOUNTAIN_CALLS.get(), SoundSource.AMBIENT, 0.1F, 1F);
                    }
                }
            }

        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer serverPlayer && SMCommonForgeEvents.SEND_BLOCK_REMOVAL_NOTIFY) {
            Component component = Component.literal("Important: Sully's Mod removed some jade blocks!\nTo not break things we replaced them with other jade blocks.")
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.RED));
            serverPlayer.sendSystemMessage(component);
        }
    }

    @SubscribeEvent
    public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
        Level level = event.getEntity().level();
        BlockState state = event.getState();
        if (state.is(SMBlocks.AMBER.get())) {
            System.out.println(event.getOriginalSpeed());
            float breakSpeed = state.getValue(AmberBlock.IS_MELTING) ? 2F : 6F;
            event.setNewSpeed(breakSpeed);
        }
    }
}