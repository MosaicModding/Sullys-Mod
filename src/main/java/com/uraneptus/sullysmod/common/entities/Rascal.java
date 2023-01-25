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
import net.minecraft.world.level.LevelReader;
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

//Highly Work in Progress
public class Rascal extends PathfinderMob implements IAnimatable {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Rascal.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> LOOKED_AT = SynchedEntityData.defineId(Rascal.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> TIMES_LOOKED_AT = SynchedEntityData.defineId(Rascal.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> LOOKED_AT_COOLDOWN = SynchedEntityData.defineId(Rascal.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> ALPHA_TICK = SynchedEntityData.defineId(Rascal.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> OLD_ALPHA_TICK = SynchedEntityData.defineId(Rascal.class, EntityDataSerializers.FLOAT);
    int timesLookedAt;
    int lookedAtCooldown;
    public float alphaTick;
    public float alphaTickOld;
    public boolean shouldTeleport;

    @Nullable
    private UUID trackingTarget;

    Player player;

    private static final int IS_LOOKED_AT_FLAG = 1;
    private static final int FADE_FLAG = 4;

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public Rascal(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.LAVA, -1.0F);
        //alphaTickOld = alphaTick;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new RascalLookForDarkPlaceToFade(this, 1.25D));
        //this.goalSelector.addGoal(0, new RascalLookForPlayerGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.4F)
                .add(Attributes.MAX_HEALTH, 30.0D);
    }



    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TIMES_LOOKED_AT, timesLookedAt);
        this.entityData.define(LOOKED_AT_COOLDOWN, lookedAtCooldown);
        this.entityData.define(ALPHA_TICK, alphaTick);
        this.entityData.define(OLD_ALPHA_TICK, alphaTickOld);
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);

    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("TimesLookedAt", getTimesLookedAt());
        nbt.putInt("LookedAtCooldown", getLookedAtCooldown());
        nbt.putFloat("AlphaTick", getAlphaTick());
        nbt.putFloat("OldAlphaTick", getOldAlphaTick());

        nbt.putBoolean("LookedAt", this.isLookedAt());
        nbt.putBoolean("Fading", this.isFading());

    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setTimesLookedAt(nbt.getInt("TimesLookedAt"));
        this.setLookedAtCooldown(nbt.getInt("LookedAtCooldown"));
        this.setAlphaTick(nbt.getFloat("AlphaTick"));
        this.setOldAlphaTick(nbt.getFloat("OldAlphaTick"));

        this.setLookedAt(nbt.getBoolean("LookedAt"));
        this.setFading(nbt.getBoolean("Fading"));

    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        super.onSyncedDataUpdated(pKey);
        lookedAtCooldown = getLookedAtCooldown();
        alphaTickOld = getOldAlphaTick();
        alphaTick = getAlphaTick();
    }

    public boolean isLookingAtMe() {
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

    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return pLevel.getBrightness(LightLayer.BLOCK, this.blockPosition()) == 0 ? 10.0F : super.getWalkTargetValue(pPos, pLevel);
    }


    public void aiStep() {
        super.aiStep();

        if (getTimesLookedAt() <= 3) {

        }
        SullysMod.LOGGER.info("Times looked at {}", getTimesLookedAt());
        SullysMod.LOGGER.info("Has been looked at {}", isLookedAt());
        System.out.println("Cooldown: " + getLookedAtCooldown());
        System.out.println("Old Alpha tick: " + getOldAlphaTick());
        System.out.println("Alpha tick: " + getAlphaTick());






    }

    public void tick() {
        super.tick();


        if (!this.level.isClientSide()) {
            if (this.getLookedAtCooldown() > 0) {
                this.entityData.set(LOOKED_AT_COOLDOWN, this.getLookedAtCooldown() - 1);
                this.setOldAlphaTick(getAlphaTick());
                this.entityData.set(ALPHA_TICK, getAlphaTick() - 1);
            }
            if (getAlphaTick() > 0) {


            }

            if (this.getLookedAtCooldown() == 70) {
                setLookedAt(false);

            }

            if (this.getLookedAtCooldown() == 0) {

                if (this.isLookingAtMe() && this.getTimesLookedAt() < 3) {
                    this.entityData.set(TIMES_LOOKED_AT, this.getTimesLookedAt() + 1);
                    this.setLookedAtCooldown(80);
                    this.setLookedAt(true);
                    this.setFading(true);
                    this.setTarget(player);
                }
                setAlphaTick(80.0f);
                setOldAlphaTick(0);

            }
            if (getAlphaTick() == 1.0F) {
                this.remove(RemovalReason.DISCARDED);
            }

            if (this.getLookedAtCooldown() != 0) {

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

    public float getAlphaTick() {
        return this.entityData.get(ALPHA_TICK);
    }

    public void setAlphaTick(float amount) {
        this.entityData.set(ALPHA_TICK, amount);
    }

    public float getOldAlphaTick() {
        return this.entityData.get(OLD_ALPHA_TICK);
    }

    public void setOldAlphaTick(float amount) {
        this.entityData.set(OLD_ALPHA_TICK, amount);
    }

    public boolean isLookedAt() {
        return this.getFlag(1);
    }

    public void setLookedAt(boolean flag) {
        this.setFlag(1, flag);
    }

    public boolean isFading() {
        return this.getFlag(4);
    }

    public void setFading(boolean flag) {
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
        private final Rascal mob;
        private Player player;

        public RascalLookForPlayerGoal(Rascal mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            this.player = this.mob.level.getNearestPlayer(this.mob.TARGETING_CONDITION, this.mob);
            if (this.player != null) {
                if (this.mob.getLookedAtCooldown() == 0) {
                    if (this.mob.getTimesLookedAt() != 3) {
                        return mob.isLookingAtMe();
                    }
                }
            }
            return false;
        }

        public boolean canContinueToUse() {
            return /*this.player != null && this.mob.hasBeenLookedAt() &&this.mob.getTimesLookedAt() != 3 && this.mob.distanceToSqr(this.player) < 256.0D;
        }


        public void start() {
            this.mob.setLookedAt(true);
            //this.mob.setLookedAt(true);
        }

        public void stop() {
            this.player = null;
        }

        public void tick() {


        }
    }*/

    //WTF is happening here?!
    static class RascalLookForDarkPlaceToFade extends Goal {
        private final Rascal mob;
        private double wantedX;
        private double wantedY;
        private double wantedZ;
        private final double speedModifier;

        public RascalLookForDarkPlaceToFade(Rascal mob, double pSpeedModifier) {
            this.mob = mob;
            this.speedModifier = pSpeedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.mob.getTarget();

            if (!this.mob.isLookedAt()) {
                System.out.println("Jaasss");
                return false;
            }

            System.out.println("OUi Oui");
            return this.setWantedPos();

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
            return !this.mob.getNavigation().isDone();
        }

        public void start() {
            this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);

        }

        public void stop() {
            this.mob.getNavigation().stop();
            super.stop();
        }

        @Nullable
        protected Vec3 getHidePos() {
            RandomSource randomsource = this.mob.getRandom();
            BlockPos blockpos = this.mob.blockPosition();

            for(int i = 0; i < 10; ++i) {
                BlockPos blockpos1 = blockpos.offset(randomsource.nextInt(20) - 10, randomsource.nextInt(6) - 3, randomsource.nextInt(20) - 10);
                if (!(this.mob.getLevel().getBrightness(LightLayer.BLOCK, this.mob.blockPosition()) == 0) && (this.mob.getWalkTargetValue(blockpos1) < 10.0F)) {
                    return Vec3.atBottomCenterOf(blockpos1);
                }
            }

            return null;
        }

        @Override
        public void tick() {
            System.out.println("ah yes");
            super.tick();

        }
    }
}