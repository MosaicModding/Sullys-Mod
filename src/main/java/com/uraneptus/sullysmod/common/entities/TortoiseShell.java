package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

import java.util.List;

public class TortoiseShell extends Entity {
    private static final EntityDataAccessor<Integer> DATA_ID_HURT = SynchedEntityData.defineId(TortoiseShell.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_HURTDIR = SynchedEntityData.defineId(TortoiseShell.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DATA_ID_DAMAGE = SynchedEntityData.defineId(TortoiseShell.class, EntityDataSerializers.FLOAT);
    public int spinTicks = 0;


    public TortoiseShell(EntityType<? extends TortoiseShell> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.blocksBuilding = true;
    }

    public TortoiseShell(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(SMEntityTypes.TORTOISE_SHELL.get(), level);
    }

    @Override
    protected float getEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_ID_HURT, 0);
        this.entityData.define(DATA_ID_HURTDIR, 1);
        this.entityData.define(DATA_ID_DAMAGE, 0.0F);
    }

    @Override
    public boolean canBeCollidedWith() {
        return this.isAlive();
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    //This prevents the entity from moving when the player is sprinting and hits the entity
    @Override
    public void push(double pX, double pY, double pZ) {}

    @Override
    protected Vec3 getRelativePortalPosition(Direction.Axis pAxis, BlockUtil.FoundRectangle pPortal) {
        return LivingEntity.resetForwardDirectionOfRelativePortalPosition(super.getRelativePortalPosition(pAxis, pPortal));
    }

    public void setSpinTimer() {
        this.spinTicks = 18;
    }

    @Override
    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
        double yLookAnglePlayer = pPlayer.getLookAngle().get(Direction.Axis.Y);
        double y = this.getDeltaMovement().get(Direction.Axis.Y);
        double x = this.getX() - pPlayer.getX();
        double z = this.getZ() - pPlayer.getZ();
        if (y == -0.0 && !this.isInFluidType() && (yLookAnglePlayer > -0.6D && yLookAnglePlayer < 0.1)) {
            double d2 = Math.max(x * x + z * z, 0.001D);
            this.setDeltaMovement(x / d2 * 2.1D, 0.05D, z / d2 * 2.1D);
            setSpinTimer();
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource == DamageSource.CACTUS || pSource == DamageSource.ON_FIRE || pSource == DamageSource.IN_FIRE) {
            if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                this.spawnAtLocation(this.getDropItem());
            }
            this.discard();
        }
        if (pSource == DamageSource.LAVA) {
            this.discard();
        }

        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else if (!this.level.isClientSide && !this.isRemoved()) {
            this.setHurtDir(-this.getHurtDir());
            this.setHurtTime(10);
            this.setDamage(this.getDamage() + pAmount * 10.0F);
            this.markHurt();
            this.gameEvent(GameEvent.ENTITY_DAMAGE, pSource.getEntity());
            if (pSource.getEntity() instanceof Player player) {
                boolean flag = player.getAbilities().instabuild;
                if (flag || this.getDamage() > 40.0F) {
                    if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS) && pSource != DamageSource.LAVA && pSource != DamageSource.CACTUS && pSource != DamageSource.IN_FIRE && pSource != DamageSource.ON_FIRE) {
                        this.spawnAtLocation(this.getDropItem());
                    }
                    this.discard();
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public Item getDropItem() {
        return SMItems.TORTOISE_SHELL.get();
    }

    @Override
    public void animateHurt() {
        this.setHurtDir(-this.getHurtDir());
        this.setHurtTime(10);
        this.setDamage(this.getDamage() * 11.0F);
    }

    @Override
    protected AABB getBoundingBoxForPose(Pose pPose) {
        return super.getBoundingBoxForPose(pPose);
    }

    @Override
    public AABB getBoundingBoxForCulling() {
        return super.getBoundingBoxForCulling();
    }

    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }

    private void hurtEntity(List<Entity> pEntities) {
        for(Entity entity : pEntities) {
            if (entity instanceof LivingEntity) {
                entity.hurt(DamageSource.GENERIC, 4);
            }
        }

    }

    private void knockBack(List<Entity> pEntities) {
        for(Entity entity : pEntities) {
            if (entity instanceof LivingEntity) {
                double x = entity.getX() - this.getX();
                double z = entity.getZ() - this.getZ();
                double shellX = this.getX() - entity.getX();
                double shellZ = this.getZ() - entity.getZ();
                double d2 = Math.max(x * x + z * z, 0.001D);

                this.setDeltaMovement(shellX / d2 * 0.5D, 0.005D, shellZ / d2 * 0.5D);
                entity.push(x / d2 * 0.05D, 0.005D, z / d2 * 0.05D);

            }
        }
    }

    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy) {
        Vec3 vec3 = (new Vec3(pX, pY, pZ)).normalize().add(this.random.triangle(0.0D, 0.0172275D * (double)pInaccuracy), this.random.triangle(0.0D, 0.0172275D * (double)pInaccuracy), this.random.triangle(0.0D, 0.0172275D * (double)pInaccuracy)).scale((double)pVelocity);
        this.setDeltaMovement(vec3);
        double d0 = vec3.horizontalDistance();
        this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
        this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    private void blockKnockBack() {
        BlockPos XP = this.blockPosition().offset(1, 0, 0);
        BlockPos XN = this.blockPosition().offset(-1, 0, 0);
        BlockPos ZP = this.blockPosition().offset(0, 0, 1);
        BlockPos ZN = this.blockPosition().offset(0, 0, -1);
        BlockState blockXP = this.level.getBlockState(XP);
        BlockState blockXN = this.level.getBlockState(XN);
        BlockState blockZP = this.level.getBlockState(ZP);
        BlockState blockZN = this.level.getBlockState(ZN);
        Vec3 vec3 = this.getDeltaMovement();

        if (blockXP.getFluidState().isEmpty() && blockXN.getFluidState().isEmpty() && blockZP.getFluidState().isEmpty() && blockZN.getFluidState().isEmpty()) {
           if (!blockXP.isAir() && blockXP.isCollisionShapeFullBlock(this.getLevel(), XP) && vec3.x() > 0.0) {
               this.shoot(vec3.reverse().x, vec3.y, vec3.z, 0.45F, 0.0F);
           }
           if (!blockXN.isAir() && blockXN.isCollisionShapeFullBlock(this.getLevel(), XN) && vec3.x() < 0.0) {
               this.shoot(vec3.reverse().x, vec3.y, vec3.z, 0.45F, 0.0F);
           }
           if (!blockZP.isAir() && blockZP.isCollisionShapeFullBlock(this.getLevel(), ZP)) {
               this.shoot(vec3.x, vec3.y, vec3.reverse().z, 0.45F, 0.0F);
           }
           if (!blockZN.isAir() && blockZN.isCollisionShapeFullBlock(this.getLevel(), ZN)) {
               this.shoot(vec3.x, vec3.y, vec3.reverse().z, 0.45F, 0.0F);
           }
        }
    }


    @Override
    public void tick() {
        if (spinTicks > 0 ) {
            this.hurtEntity(this.level.getEntities(this, this.getBoundingBox().inflate(0.30D), EntitySelector.NO_CREATIVE_OR_SPECTATOR));
            this.knockBack(this.level.getEntities(this, this.getBoundingBox().inflate(0.30D), EntitySelector.NO_CREATIVE_OR_SPECTATOR));
            blockKnockBack();
            spinTicks--;
        }
        if (this.getHurtTime() > 0) {
            this.setHurtTime(this.getHurtTime() - 1);
        }

        if (this.getDamage() > 0.0F) {
            this.setDamage(this.getDamage() - 1.0F);
        }

        super.tick();

        if (!this.isNoGravity()) {
            double yVelocity = -0.04D;
            FluidType fluidType = this.getEyeInFluidType();

            if (fluidType != ForgeMod.EMPTY_TYPE.get()) {
                yVelocity *= this.getFluidMotionScale(fluidType);
            }

            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, yVelocity, 0.0D));
        }

        Level level = this.getLevel();
        BlockPos bottomPosition = this.getBlockPosBelowThatAffectsMyMovement();
        float friction = this.isOnGround() ? level.getBlockState(bottomPosition).getFriction(level, bottomPosition, this) * 1.55F : 1.55F;
        float defaultFriction = this.level.getBlockState(bottomPosition).getFriction(level, bottomPosition, this);

        double y = this.getDeltaMovement().get(Direction.Axis.Y);
        if (y == -0.04 && !this.isInFluidType() && defaultFriction == 0.6F) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(friction, 0.98D, friction));
        }
        if (this.isInFluidType()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.85, 1, 0.85));
        }


        if (this.getDeltaMovement() != Vec3.ZERO) {
            this.move(MoverType.SELF, this.getDeltaMovement());
        }

        if (this.isInLava()) {
            this.lavaHurt();
            this.fallDistance *= 0.5F;
        }
    }
    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("spinTicks", this.spinTicks);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
    }

    public void setDamage(float pDamageTaken) {
        this.entityData.set(DATA_ID_DAMAGE, pDamageTaken);
    }

    public float getDamage() {
        return this.entityData.get(DATA_ID_DAMAGE);
    }

    public void setHurtTime(int pHurtTime) {
        this.entityData.set(DATA_ID_HURT, pHurtTime);
    }

    public int getHurtTime() {
        return this.entityData.get(DATA_ID_HURT);
    }

    public void setHurtDir(int pHurtDirection) {
        this.entityData.set(DATA_ID_HURTDIR, pHurtDirection);
    }

    public int getHurtDir() {
        return this.entityData.get(DATA_ID_HURTDIR);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(this.getDropItem());
    }
}