package com.uraneptus.sullysmod.core.events;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.AmberBlockEntity;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SMBlockEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        LevelAccessor pLevel =  event.getLevel();
        BlockState blockState = event.getState();
        BlockPos blockPos = event.getPos();



        if (blockState == SMBlocks.AMBER.get().defaultBlockState()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(new BlockPos(blockPos));
            assert blockEntity != null;
            if (((AmberBlockEntity) blockEntity).isFull()) {
                CompoundTag compoundtag = ((AmberBlockEntity) blockEntity).getEntityStuck(0);
                AmberBlockEntity.removeIgnoredNBT(compoundtag);
                LivingEntity livingEntity = (LivingEntity) EntityType.loadEntityRecursive(compoundtag, (Level) pLevel, entity -> entity);
                livingEntity.moveTo(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
                pLevel.addFreshEntity(livingEntity);
                System.out.println("BROKE WORKED");
            }
            System.out.println(blockEntity);


            System.out.println("AMBER BROKE");
        }

    }
}
