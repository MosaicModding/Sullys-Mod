package com.uraneptus.sullysmod.common.blocks;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.*;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.lwjgl.system.CallbackI;

import java.util.Random;
import java.util.function.Supplier;

public class JadeFlingerTotem extends SMDirectableBlock {
    public JadeFlingerTotem(Properties properties) {
        super(properties);
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult hitResult, Projectile projectile) {
        BlockPos pos = hitResult.getBlockPos();
        Direction direction = hitResult.getDirection();

        if (!direction.equals(state.getValue(FACING))) {
            SullysMod.LOGGER.debug("Side not Front");


            projectile.remove(Entity.RemovalReason.DISCARDED);
        }

        SullysMod.LOGGER.info("feature unfinished");
    }

    private static final Supplier<Item> DROPPER = Blocks.DROPPER::asItem;
    private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(DROPPER);

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> item) {
        FILLER.fillItem(this.asItem(), tab, item);
    }

}
