package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.common.entities.JungleSpider;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.ForgeRegistries;

public class VialItem extends Item {
    public VialItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof JungleSpider jungleSpider && hand.equals(InteractionHand.MAIN_HAND)) {
            ItemStack vialStack = new ItemStack(SMItems.VENOM_VIAL.get());
            vialStack.getOrCreateTag().putString("beneficialEffect", ForgeRegistries.MOB_EFFECTS.getKey(jungleSpider.getBeneficialVenomEffect()).toString());
            vialStack.getOrCreateTag().putString("harmfulEffect", ForgeRegistries.MOB_EFFECTS.getKey(jungleSpider.getHarmfulVenomEffect()).toString());

            this.turnVialIntoItem(stack, player, vialStack);
            return InteractionResult.sidedSuccess(player.level().isClientSide);
        }

        return super.interactLivingEntity(stack, player, target, hand);
    }

    protected void turnVialIntoItem(ItemStack vialStack, Player player, ItemStack filledVial) {
        player.awardStat(Stats.ITEM_USED.get(this));
        ItemUtils.createFilledResult(vialStack, player, filledVial, true);
    }
}
