package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.common.entities.ThrownThrowingKnife;
import com.uraneptus.sullysmod.common.entities.TortoiseShell;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import org.jetbrains.annotations.NotNull;

public class SMDispenseBehaviors {

    public static void register() {
        DispenserBlock.registerBehavior(SMItems.TORTOISE_SHELL.get(), new OptionalDispenseItemBehavior() {
            @NotNull
            protected ItemStack execute(BlockSource source, ItemStack stack) {
                this.setSuccess(true);
                Level level = source.getLevel();
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                BlockPos blockPos = source.getPos().relative(direction);

                if (level.getBlockState(blockPos).isAir()) {
                    TortoiseShell shell = SMEntityTypes.TORTOISE_SHELL.get().create(level);
                    if (shell != null) {
                        shell.moveTo(blockPos.getX() + 0.5F, blockPos.getY(), blockPos.getZ() + 0.5F , 0F, 0.0F);
                        shell.shoot(direction.getStepX(), (float)direction.getStepY() + 0.1F, direction.getStepZ(), 0.65F, 0F);
                        level.playSound(null, source.x(), source.y(), source.z(), SMSounds.TORTOISE_SHELL_PLACE.get(), SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
                        shell.setSpinTimer();
                        level.addFreshEntity(shell);
                        stack.shrink(1);
                    }

                } else {
                    this.setSuccess(false);
                }
                return stack;
            }
        });

        DispenserBlock.registerBehavior(SMItems.THROWING_KNIFE.get(), new AbstractProjectileDispenseBehavior() {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                ThrownThrowingKnife thrownThrowingKnife = new ThrownThrowingKnife(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
                thrownThrowingKnife.pickup = AbstractArrow.Pickup.ALLOWED;
                return thrownThrowingKnife;
            }
        });

        SMBlocks.ANCIENT_SKULLS.forEach(block -> DispenserBlock.registerBehavior(block.get().asItem(), new OptionalDispenseItemBehavior() {
            @Override
            protected ItemStack execute(BlockSource pSource, ItemStack pStack) {
                this.setSuccess(ArmorItem.dispenseArmor(pSource, pStack));
                return pStack;
            }
        }));
    }
}
