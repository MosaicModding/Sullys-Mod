package com.uraneptus.sullysmod.common.blockentities;

import com.uraneptus.sullysmod.common.blocks.FlingerTotem;
import com.uraneptus.sullysmod.core.registry.SMBlockEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FlingerTotemBE extends BlockEntity {
    private static final List<String> IGNORED_NBT = Arrays.asList("UUID");
    private final List<FlingerTotemBE.ProjectileData> stored = new ObjectArrayList<>();

    public FlingerTotemBE(BlockPos pPos, BlockState pBlockState) {
        super(SMBlockEntityTypes.FLINGER_TOTEM.get(), pPos, pBlockState);
    }

    public void addProjectile(Projectile projectile) {
        if (this.stored.size() < 5) {
            CompoundTag compoundtag = new CompoundTag();
            projectile.save(compoundtag);
            this.storeProjectile(compoundtag, calculateDelayFromHoney());
            if (this.level != null) {
                BlockPos blockpos = this.getBlockPos();
                if (this.getBlockState().getValue(FlingerTotem.HONEY_AMOUNT) != 0) {
                    this.level.playSound(null, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SMSounds.FLINGER_INPUT_HONEY.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                this.level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(projectile, this.getBlockState())); //DO we want this?
            }

            projectile.discard();
            super.setChanged();
        }
    }

    private int calculateDelayFromHoney() {
        int honey = this.getBlockState().getValue(FlingerTotem.HONEY_AMOUNT);
        return 20 * honey;
    }

    public void storeProjectile(CompoundTag pEntityData, int delayTicks) {
        this.stored.add(new FlingerTotemBE.ProjectileData(pEntityData, delayTicks));
    }

    private static boolean releaseOccupant(Level pLevel, BlockPos pPos, BlockState pState, FlingerTotemBE.ProjectileData pData, @Nullable List<Projectile> stored) {
        Direction front = pState.getValue(FlingerTotem.FACING);
        CompoundTag compoundtag = pData.entityData.copy();
        removeIgnoredNBT(compoundtag);
        Direction direction = pState.getValue(FlingerTotem.FACING);
        BlockPos blockpos = pPos.relative(direction);
        if (!pLevel.getBlockState(blockpos).getCollisionShape(pLevel, blockpos).isEmpty()) {
            return false;
        } else {
            Projectile projectile = (Projectile) EntityType.loadEntityRecursive(compoundtag, pLevel, entity -> entity);
            if (projectile != null) {
                if (stored != null) {
                    stored.add(projectile);
                }
                projectile.moveTo(pPos.getX() + 0.5 + front.getStepX(), pPos.getY() + 0.5F + front.getStepY(), pPos.getZ() + 0.5 + front.getStepZ());
                projectile.shoot(front.getStepX(), front.getStepY(), front.getStepZ(), (float) projectile.getDeltaMovement().length(), 0.0F);
                projectile.gameEvent(GameEvent.PROJECTILE_SHOOT);
                pLevel.playSound(null, pPos, SMSounds.FLINGER_FLINGS.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                return pLevel.addFreshEntity(projectile);
            } else {
                return false;
            }
        }
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, FlingerTotemBE pBlockEntity) {
        boolean flag = false;
        FlingerTotemBE.ProjectileData projectileData;
        for(Iterator<FlingerTotemBE.ProjectileData> iterator = pBlockEntity.stored.iterator(); iterator.hasNext(); projectileData.delayTicks--) {
            projectileData = iterator.next();
            if (projectileData.delayTicks <= 0) {
                if (releaseOccupant(pLevel, pPos, pState, projectileData, null)) {
                    flag = true;
                    iterator.remove();
                }

            }
        }

        if (flag) {
            setChanged(pLevel, pPos, pState);
        }
    }

    static void removeIgnoredNBT(CompoundTag pTag) {
        for(String s : IGNORED_NBT) {
            pTag.remove(s);
        }

    }

    public boolean isEmpty() {
        return this.stored.isEmpty();
    }

    public boolean isFull() {
        return this.stored.size() == 5;
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType() {
        return SMBlockEntityTypes.FLINGER_TOTEM.get();
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        ListTag listtag = pTag.getList("Projectiles", 10);

        for(int i = 0; i < listtag.size(); ++i) {
            CompoundTag compoundtag = listtag.getCompound(i);
            FlingerTotemBE.ProjectileData projectileData = new FlingerTotemBE.ProjectileData(compoundtag.getCompound("EntityData"), compoundtag.getInt("DelayTicks"));
            this.stored.add(projectileData);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("Projectiles", this.writeProjectiles());
    }

    public ListTag writeProjectiles() {
        ListTag listtag = new ListTag();

        for(FlingerTotemBE.ProjectileData projectileData : this.stored) {
            CompoundTag compoundtag = projectileData.entityData.copy();
            compoundtag.remove("UUID");
            CompoundTag compoundtag1 = new CompoundTag();
            compoundtag1.put("EntityData", compoundtag);
            compoundtag1.putInt("DelayTicks", projectileData.delayTicks);
            listtag.add(compoundtag1);
        }

        return listtag;
    }

    static class ProjectileData {
        final CompoundTag entityData;
        int delayTicks;

        ProjectileData(CompoundTag pEntityData, int delayTicks) {
            FlingerTotemBE.removeIgnoredNBT(pEntityData);
            this.entityData = pEntityData;
            this.delayTicks = delayTicks;
        }
    }
}
