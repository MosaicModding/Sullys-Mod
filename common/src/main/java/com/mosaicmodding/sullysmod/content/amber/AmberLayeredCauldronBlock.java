package com.mosaicmodding.sullysmod.content.amber;

import com.mosaicmodding.sullysmod.registry.SMItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;

public class AmberLayeredCauldronBlock extends LayeredCauldronBlock {
    public static final IntegerProperty LEVEL = LayeredCauldronBlock.LEVEL;

    public AmberLayeredCauldronBlock(Properties pProperties) {
        super(Biome.Precipitation.NONE, null, pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 1));
    }

    //TODO codec?

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (this.isFull(state) && itemstack.is(Items.BUCKET)) {
            return CauldronInteraction.fillBucket(state, level, pos, player, hand, itemstack, new ItemStack(SMItems.MOLTEN_AMBER_BUCKET.get()), s -> s.getValue(AmberLayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL);
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LEVEL);
    }

    @Override
    protected boolean canReceiveStalactiteDrip(Fluid pFluid) {
        return false;
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        //NO-OP
    }

    @Override
    public void handlePrecipitation(BlockState pState, Level pLevel, BlockPos pPos, Biome.Precipitation pPrecipitation) {}
}
