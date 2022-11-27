package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.UUID;

public class Rascal extends PathfinderMob implements IAnimatable {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Rascal.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> LOOKED_AT = SynchedEntityData.defineId(Rascal.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> TIMES_LOOKED_AT = SynchedEntityData.defineId(Rascal.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> LOOKED_AT_COOLDOWN = SynchedEntityData.defineId(Rascal.class, EntityDataSerializers.INT);
    int timesLookedAt;
    int lookedAtCooldown;

    @Nullable
    private UUID trackingTarget;

    Player player;

    private static final int IS_LOOKED_AT_FLAG = 1;
    private static final int FADE_FLAG = 4;

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public Rascal(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.LAVA, -1.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(0, new RascalLookForDarkPlaceToFade(this, 1.25D));
        //this.goalSelector.addGoal(0, new RascalLookForPlayerGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.4F)
                .add(Attributes.MAX_HEALTH, 30.0D);
    }

    @Override
    public void setTarget(@Nullable LivingEntity pLivingEntity) {
        if (pLivingEntity == null) {
            this.setLookedAt(false);
            this.setLookedAtCooldown(0);
            this.setTimesLookedAt(0);
        }
        super.setTarget(pLivingEntity);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TIMES_LOOKED_AT, timesLookedAt);
        this.entityData.define(LOOKED_AT_COOLDOWN, lookedAtCooldown);
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);

    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("TimesLookedAt", getTimesLookedAt());
        nbt.putInt("LookedAtCooldown", getLookedAtCooldown());

        nbt.putBoolean("LookedAt", this.isLookedAt());
        nbt.putBoolean("Fading", this.isFading());

    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setTimesLookedAt(nbt.getInt("TimesLookedAt"));
        this.setLookedAtCooldown(nbt.getInt("LookedAtCooldown"));

        this.setLookedAt(nbt.getBoolean("LookedAt"));
        this.setFading(nbt.getBoolean("Fading"));

    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        super.onSyncedDataUpdated(pKey);

    }

    boolean isLookingAtMe() {
        player = this.level.getNearestPlayer(TargetingConditions.forNonCombat().range(256), this);
        if (player != null) {
            Vec3 vec3 = player.getViewVector(1.0F).normalize();
            Vec3 vec31 = new Vec3(this.getX() - player.getX(), this.getEyeY() - player.getEyeY(), this.getZ() - player.getZ());
            double d0 = vec31.length();
            vec31 = vec31.normalize();
            double d1 = vec3.dot(vec31);
            return d1 > 1.0D - 0.025D / d0 && player.hasLineOfSight(this);
        }
        return false;
    }


    public void aiStep() {
        super.aiStep();

        if (getTimesLookedAt() <= 3) {

        }
        SullysMod.LOGGER.info("Times looked at {}", getTimesLookedAt());
        SullysMod.LOGGER.info("Has been looked at {}", isLookedAt());
        System.out.println("Cooldown: " + getLookedAtCooldown());





    }

    public void tick() {
        super.tick();

        if (!this.level.isClientSide()) {
            if (this.getLookedAtCooldown() > 0) {
                this.entityData.set(LOOKED_AT_COOLDOWN, this.getLookedAtCooldown() - 1);

            }

            if (this.getLookedAtCooldown() == 50) {
                this.setLookedAt(false);
            }

            if (this.getLookedAtCooldown() == 0 && this.isLookingAtMe() && this.getTimesLookedAt() < 3) {
                this.entityData.set(TIMES_LOOKED_AT, this.getTimesLookedAt() + 1);
                this.setLookedAtCooldown(60);
                this.setLookedAt(true);
                this.setTarget(player);
            }

        }
    }


    public <E extends IAnimatable> PlayState setAnimation(AnimationEvent<E> event) {

        if (isLookedAt()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rascal.found"));
            return PlayState.CONTINUE;
        }
        if (/*!((double) animationSpeed < 0.08D) && isOnGround() &&*/ event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rascal.running", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void registerControllers(AnimationData data) {
        data.setResetSpeedInTicks(4);
        data.addAnimationController(new AnimationController(this, "controller", 3, this::setAnimation));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public int getTimesLookedAt() {
        return this.entityData.get(TIMES_LOOKED_AT);
    }

    public void setTimesLookedAt(int amount) {
        this.entityData.set(TIMES_LOOKED_AT, amount);
    }

    public int getLookedAtCooldown() {
        return this.entityData.get(LOOKED_AT_COOLDOWN);
    }

    public void setLookedAtCooldown(int amount) {
        this.entityData.set(LOOKED_AT_COOLDOWN, amount);
    }

    public boolean isLookedAt() {
        return this.getFlag(1);
    }

    void setLookedAt(boolean flag) {
        this.setFlag(1, flag);
    }

    public boolean isFading() {
        return this.getFlag(4);
    }

    void setFading(boolean flag) {
        this.setFlag(4, flag);
    }

    private void setFlag(int flagId, boolean flag) {
        if (flag) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) | flagId));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) & ~flagId));
        }

    }

    private boolean getFlag(int flagId) {
        return (this.entityData.get(DATA_FLAGS_ID) & flagId) != 0;
    }

    /**
     * This Goal handles whether the player is looking at the Rascal and is responsible for the LookedAt amount counting
     * TODO: Add some revertable boolean that should return true if this goal is called for the animation
     *
     */
    /*
    static class RascalLookForPlayerGoal extends Goal {
        private final Rascal rascal;
        private Player player;

        public RascalLookForPlayerGoal(Rascal rascal) {
            this.rascal = rascal;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            this.player = this.rascal.level.getNearestPlayer(this.rascal.TARGETING_CONDITION, this.rascal);
            if (this.player != null) {
                if (this.rascal.getLookedAtCooldown() == 0) {
                    if (this.rascal.getTimesLookedAt() != 3) {
                        return rascal.isLookingAtMe();
                    }
                }
            }
            return false;
        }

        public boolean canContinueToUse() {
            return /*this.player != null && this.rascal.hasBeenLookedAt() &&this.rascal.getTimesLookedAt() != 3 && this.rascal.distanceToSqr(this.player) < 256.0D;
        }


        public void start() {
            this.rascal.setLookedAt(true);
            //this.rascal.setLookedAt(true);
        }

        public void stop() {
            this.player = null;
        }

        public void tick() {


        }
    }*/

        //WTF is happening here?!
    static class RascalLookForDarkPlaceToFade extends Goal {
        private final Rascal rascal;
        private double wantedX;
        private double wantedY;
        private double wantedZ;
        private final double speedModifier;

        public RascalLookForDarkPlaceToFade(Rascal rascal, double pSpeedModifier) {
            this.rascal = rascal;
            this.speedModifier = pSpeedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.rascal.getTarget();

            if (target instanceof Player) {
                if (target.distanceToSqr(this.rascal) < 256.0D) {
                    if (this.rascal.isLookingAtMe()) {
                        //if (this.rascal.timesLookedAt != 3) {
                            return this.setWantedPos();
                       // }
                    }
                }
            }
            return false;
        }

        protected boolean setWantedPos() {
            Vec3 vec3 = this.getHidePos();
            if (vec3 == null) {
                return false;
            } else {
                this.wantedX = vec3.x;
                this.wantedY = vec3.y;
                this.wantedZ = vec3.z;
                return true;
            }
        }

        public boolean canContinueToUse() {
            return !this.rascal.getNavigation().isDone();
        }

        public void start() {
            this.rascal.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
            System.out.println("ah yes");

        }

        @Nullable
        protected Vec3 getHidePos() {
            RandomSource randomsource = this.rascal.getRandom();
            BlockPos blockpos = this.rascal.blockPosition();

            for(int i = 0; i < 10; ++i) {
                BlockPos blockpos1 = blockpos.offset(randomsource.nextInt(20) - 10, randomsource.nextInt(6) - 3, randomsource.nextInt(20) - 10);
                if (/*!(this.rascal.getLevel().getBrightness(LightLayer.BLOCK, this.rascal.blockPosition()) == 0)*/ !this.rascal.level.canSeeSky(blockpos1) && this.rascal.getWalkTargetValue(blockpos1) < 0.0F) {
                    return Vec3.atBottomCenterOf(blockpos1);
                }
            }

            return null;
        }
    }
}