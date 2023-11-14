package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.core.object.Color;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VenomVialItem extends Item {
    public VenomVialItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        stack.getOrCreateTag().putString("beneficialEffect", "minecraft:speed");
        stack.getOrCreateTag().putString("harmfulEffect", "minecraft:poison");
        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        Player player = livingEntity instanceof Player ? (Player)livingEntity : null;

        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
        }

        if (!level.isClientSide) {
            MobEffectInstance beneficialInstance = new MobEffectInstance(getBeneficialEffect(stack), 200, 0);
            MobEffectInstance harmfulInstance = new MobEffectInstance(getHarmfulEffect(stack), 200, 0);

            if (getBeneficialEffect(stack).isInstantenous()) {
                beneficialInstance.getEffect().applyInstantenousEffect(player, player, livingEntity, beneficialInstance.getAmplifier(), 1.0D);
            }
            else livingEntity.addEffect(beneficialInstance);

            if (getHarmfulEffect(stack).isInstantenous()) {
                harmfulInstance.getEffect().applyInstantenousEffect(player, player, livingEntity, harmfulInstance.getAmplifier(), 1.0D);
            }
            else livingEntity.addEffect(harmfulInstance);
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                level.playSound(null, livingEntity.getOnPos(), SMSounds.VIAL_SHATTERS.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
            }
        }


        livingEntity.gameEvent(GameEvent.DRINK);
        return stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 24;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        MobEffectInstance beneficialInstance = new MobEffectInstance(getBeneficialEffect(stack), 200, 0);
        MobEffectInstance harmfulInstance = new MobEffectInstance(getHarmfulEffect(stack), 200, 0);
        List<MobEffectInstance> effectList = new ArrayList<>();
        effectList.add(beneficialInstance);
        effectList.add(harmfulInstance);
        PotionUtils.addPotionTooltip(effectList, tooltip, 1.0F);
    }

    private static MobEffect getBeneficialEffect(ItemStack stack) {
        if (stack.getOrCreateTag().get("beneficialEffect") == null) {
            stack.getOrCreateTag().putString("beneficialEffect", "minecraft:speed");
        }

        ResourceLocation effectLocation = new ResourceLocation(stack.getOrCreateTag().getString("beneficialEffect"));

        return Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(effectLocation));
    }

    private static MobEffect getHarmfulEffect(ItemStack stack) {
        if (stack.getOrCreateTag().get("harmfulEffect") == null) {
            stack.getOrCreateTag().putString("harmfulEffect", "minecraft:poison");
        }

        ResourceLocation effectLocation = new ResourceLocation(stack.getOrCreateTag().getString("harmfulEffect"));

        return Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(effectLocation));
    }

    public static int getEffectColours(ItemStack stack, int tintIndex) {
        Color effectColor;
        if (tintIndex == 1) {
            effectColor = Color.ofOpaque(getBeneficialEffect(stack).getColor()).brighter(1.3F);
        }
        else {
            effectColor = Color.ofOpaque(getHarmfulEffect(stack).getColor()).brighter(1.3F);
        }
        return effectColor.getColor();
    }
}
