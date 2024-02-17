package com.uraneptus.sullysmod.common.items;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.uraneptus.sullysmod.common.entities.ThrownThrowingKnife;
import com.uraneptus.sullysmod.core.other.SMItemUtil;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ThrowingKnifeItem extends Item {
    //TODO add dispense behavior

    public ThrowingKnifeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.CUSTOM;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {

            private static final HumanoidModel.ArmPose THROWING_KNIFE_POSE = HumanoidModel.ArmPose.create("THROWING_KNIFE", false, (model, entity, arm) -> {
                if (arm == HumanoidArm.RIGHT) {
                    model.rightArm.xRot = (model.rightArm.xRot - 2.8F) + (model.head.xRot + 0.5F);
                    model.rightArm.yRot = 0.0F + (model.head.yRot + 0.13F);
                } else {
                    model.leftArm.xRot = (model.leftArm.xRot - 2.5F) + (model.head.xRot + 0.5F);
                    model.leftArm.yRot = 0.0F + (model.head.yRot + 0.13F);
                }
            });

            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                if (!itemStack.isEmpty()) {
                    if (entityLiving.getUsedItemHand() == hand && entityLiving.getUseItemRemainingTicks() > 0) {
                        return THROWING_KNIFE_POSE;
                    }
                }
                return null;
            }

            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                if (player.getUseItem() != itemInHand) {
                    return false;
                }
                float i = arm == HumanoidArm.RIGHT ? 1.0F : -1.0F;
                poseStack.translate(i * 0.56F, -0.52F + equipProcess * -0.6F, -0.72F);
                if (player.isUsingItem()) {
                    ItemStack stack = player.getUseItem();
                    poseStack.translate(i * -0.33F, 0.55F, 0.07F);
                    poseStack.mulPose(Axis.XP.rotationDegrees(23.0F));
                    poseStack.mulPose(Axis.YP.rotationDegrees(i * 48.0F));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(i * 13.0F));
                    //Rest is copied from trident item rendenderer so don't ask me what all the 'f' variables mean! Idk either
                    float drawDuration = (float)stack.getUseDuration() - ((float)player.getUseItemRemainingTicks() - partialTick + 1.0F);
                    float f11 = drawDuration / 10.0F;
                    if (f11 > 1.0F) {
                        f11 = 1.0F;
                    }

                    if (f11 > 0.1F) {
                        float f14 = Mth.sin((drawDuration - 0.1F) * 1.3F);
                        float f17 = f11 - 0.1F;
                        float f19 = f14 * f17;
                        poseStack.translate(f19 * 0.0F, f19 * 0.004F, f19 * 0.0F);
                    }

                    poseStack.translate(0.0F, 0.0F, f11 * 0.2F);
                    poseStack.scale(1.0F, 1.0F, 1.0F + f11 * 0.2F);
                    poseStack.mulPose(Axis.YN.rotationDegrees(i * 45.0F));
                }
                return true;
            }
        });
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            int i = this.getUseDuration(pStack) - pTimeLeft;
            if (i >= 7) {
                if (!pLevel.isClientSide) {
                    ThrownThrowingKnife thrownThrowingKnife = new ThrownThrowingKnife(pLevel, player, pStack);
                    thrownThrowingKnife.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 0.0F);
                    if (player.getAbilities().instabuild) {
                        thrownThrowingKnife.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }
                    pLevel.addFreshEntity(thrownThrowingKnife);
                    pLevel.playSound(null, thrownThrowingKnife, SMSounds.THROWING_KNIFE_THROW.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    SMItemUtil.nonCreativeShrinkStack(player, pStack);
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pHand));
    }
}
