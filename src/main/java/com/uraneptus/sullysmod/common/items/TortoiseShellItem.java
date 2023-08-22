package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.common.entities.TortoiseShell;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class TortoiseShellItem extends Item {

    public TortoiseShellItem(Properties pProperties) {
        super(pProperties);
    }
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        ItemStack itemstack = pContext.getItemInHand();
        BlockPos blockpos = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        InteractionHand hand = pContext.getHand();
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SMSounds.TORTOISE_SHELL_PLACE.get(), SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        player.getCooldowns().addCooldown(this, 20);
        player.swing(hand);
        if (!level.isClientSide()) {
            TortoiseShell shell = SMEntityTypes.TORTOISE_SHELL.get().create(level);
            shell.moveTo(blockpos.getX() + 0.5, blockpos.getY() + 1, blockpos.getZ() + 0.5, player.getYRot(), 0.0F);
            shell.setGotThrown(true);
            level.addFreshEntity(shell);
            level.broadcastEntityEvent(shell, (byte) 3);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }


        return InteractionResult.CONSUME;
    }
}
