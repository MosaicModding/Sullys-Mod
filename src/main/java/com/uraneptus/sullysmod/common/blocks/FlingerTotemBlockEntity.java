package com.uraneptus.sullysmod.common.blocks;

import com.teamabnormals.blueprint.core.registry.BlueprintBlockEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FlingerTotemBlockEntity extends BlockEntity {
    public static List<UUID> projectiles = Lists.newArrayList();

    public FlingerTotemBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SMBlockEntityTypes.FLINGER_TOTEM.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        for (UUID uuid : projectiles) {
            pTag.putUUID("projectile", uuid);
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        projectiles.add(pTag.getUUID("projectile"));
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, FlingerTotemBlockEntity pBlockEntity) {
        //System.out.println(projectiles.size());
        int honeyState = pState.getValue(FlingerTotem.HONEY_AMOUNT);
        //remember to remove projectiles from list after it's sent back to the event
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType() {
        return SMBlockEntityTypes.FLINGER_TOTEM.get();
    }
}
