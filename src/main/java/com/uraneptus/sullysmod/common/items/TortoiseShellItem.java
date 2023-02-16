package com.uraneptus.sullysmod.common.items;

import com.uraneptus.sullysmod.common.entities.ThrownTortoiseShell;
import net.minecraft.Util;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class TortoiseShellItem extends Item {

    public TortoiseShellItem(Properties pProperties) {
        super(pProperties);
        DispenserBlock.registerBehavior(this, new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return Util.make(new ThrownTortoiseShell(pLevel, pPosition.x(), pPosition.y(), pPosition.z()), (thrownTortoiseShell) -> {
                    thrownTortoiseShell.setItem(pStack);
                });
            }
        });
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            ThrownTortoiseShell shell = new ThrownTortoiseShell(pLevel, pPlayer);
            shell.setItem(itemstack);
            shell.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity(shell);
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

}
