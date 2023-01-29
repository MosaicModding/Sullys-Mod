package com.uraneptus.sullysmod.common.items;

import com.teamabnormals.blueprint.common.dispenser.SpawnEggDispenseItemBehavior;
import com.teamabnormals.blueprint.common.item.BlueprintBoatItem;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class TortoiseShellItem extends Item {

    public TortoiseShellItem(Properties pProperties) {
        super(pProperties);
        DispenserBlock.registerBehavior(this, new SpawnEggDispenseItemBehavior());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        BlockHitResult hitresult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.SOURCE_ONLY);
        EntityType<?> entitytype = SMEntityTypes.TORTOISE_SHELL.get();
        BlockPos blockpos = hitresult.getBlockPos();

        if (hitresult.getType() != HitResult.Type.BLOCK || pLevel.getBlockState(blockpos).getBlock() instanceof LiquidBlock) {
            return InteractionResultHolder.pass(itemstack);
        } else if (!(pLevel instanceof ServerLevel)) {
            return InteractionResultHolder.success(itemstack);
        } else {
            if (pLevel.mayInteract(pPlayer, blockpos) && pPlayer.mayUseItemAt(blockpos, hitresult.getDirection(), itemstack)) {
                Entity entity = entitytype.spawn((ServerLevel)pLevel, itemstack, pPlayer, blockpos.above(), MobSpawnType.TRIGGERED, false, false);
                if (entity == null) {
                    return InteractionResultHolder.pass(itemstack);
                } else {
                    if (!pPlayer.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, entity.position());
                    return InteractionResultHolder.consume(itemstack);
                }
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }


}
