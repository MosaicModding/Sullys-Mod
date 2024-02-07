package com.uraneptus.sullysmod.core.events;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.AmberBlockEntity;
import com.uraneptus.sullysmod.common.blocks.FlingerTotem;
import com.uraneptus.sullysmod.common.blocks.FlingerTotemBlockEntity;
import com.uraneptus.sullysmod.common.blocks.SMDirectionalBlock;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import com.uraneptus.sullysmod.common.entities.goals.GenericMobAttackTortoiseEggGoal;
import com.uraneptus.sullysmod.common.particletypes.DirectionParticleOptions;
import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import com.uraneptus.sullysmod.core.other.tags.SMEntityTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMParticleTypes;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SMEntityEvents {

    @SubscribeEvent
    public static void onProjectileHitsBlock(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        Level level = event.getEntity().level();
        HitResult hitResult = event.getRayTraceResult();
        Vec3 vec3 = projectile.getDeltaMovement();
        float velocity = (float) vec3.length();

        if (hitResult instanceof BlockHitResult blockHitResult && hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = blockHitResult.getBlockPos();
            BlockState blockState = level.getBlockState(pos);
            Direction direction = blockHitResult.getDirection();

            if (!blockState.is(SMBlockTags.PROJECTILES_BOUNCE_ON)) {
                return;
            }

            if (isFlingerAndFlings(projectile, blockState, direction) && level.getBlockEntity(pos) instanceof FlingerTotemBlockEntity blockEntity && !blockEntity.isFull()) {
                blockEntity.addProjectile(projectile);
            } else if (!(projectile.getType().is(SMEntityTags.CANNOT_BOUNCE))) {

                projectile = replaceProjectile(projectile, level);
                if (projectile == null) return;

                switch (direction.getAxis()) {
                    case X -> projectile.shoot(vec3.reverse().x, vec3.y, vec3.z, calculateBounceVelocity(velocity), 0.0F);
                    case Y -> projectile.shoot(vec3.x, vec3.reverse().y, vec3.z, calculateBounceVelocity(velocity), 0.0F);
                    case Z -> projectile.shoot(vec3.x, vec3.y, vec3.reverse().z, calculateBounceVelocity(velocity), 0.0F);
                }
                level.addFreshEntity(projectile);
                handleParticleAndSound(level, blockHitResult, direction, projectile);
            }
            handleCancellation(event);
        }
        if (hitResult instanceof EntityHitResult entityHitResult && !(projectile.getType().is(SMEntityTags.CANNOT_BOUNCE))) {
            if (entityHitResult.getEntity() instanceof Player player && player.isBlocking() && player.getUseItem().is(SMItems.JADE_SHIELD.get())) {
                handleCancellation(event);
                Direction direction = projectile.getDirection();
                Vec3 angle = player.getLookAngle();

                projectile = replaceProjectile(projectile, level);
                if (projectile == null) return;

                projectile.shoot(angle.x, angle.y, angle.z, calculateBounceVelocity(velocity), 0.0F);
                level.addFreshEntity(projectile);
                player.getUseItem().hurtAndBreak(1, player, e -> e.broadcastBreakEvent(player.getUsedItemHand()));
                handleParticleAndSound(level, entityHitResult, direction, projectile);
            }
            if (entityHitResult.getEntity() instanceof Horse horse && horse.getArmor().is(SMItems.JADE_HORSE_ARMOR.get())) {
                Direction direction = projectile.getDirection();
                handleCancellation(event);

                if (projectile.yo < horse.getBoundingBox().maxY) {
                    projectile = replaceProjectile(projectile, level);
                    if (projectile == null) return;

                    //TODO This should use the direction of the hit side of the bounding box of the horse, but idk where I'd get that from!
                    // The issues are: Projectile bugging when shot from above (workaround is the yo < maxY check) & projectile bugging sometimes when shot weird at the side
                    switch (direction.getAxis()) {
                        case X -> projectile.shoot(vec3.reverse().x, vec3.y, vec3.z, calculateBounceVelocity(velocity), 0.0F);
                        case Y -> projectile.shoot(vec3.x, vec3.reverse().y + 1, vec3.z, calculateBounceVelocity(velocity), 0.0F);
                        case Z -> projectile.shoot(vec3.x, vec3.y, vec3.reverse().z, calculateBounceVelocity(velocity), 0.0F);
                    }
                    level.addFreshEntity(projectile);
                    if (direction == Direction.SOUTH || direction == Direction.NORTH) {
                        direction = direction.getOpposite();
                    }
                    handleParticleAndSound(level, entityHitResult, direction, projectile);
                }
            }
        }
    }

    @Nullable
    public static Projectile replaceProjectile(Projectile projectile, Level level) {
        Projectile oldProjectile = projectile;
        projectile = (Projectile) projectile.getType().create(level);
        if (projectile == null) {
            return null;
        }
        oldProjectile.setRemoved(Entity.RemovalReason.DISCARDED);
        CompoundTag compoundtag = oldProjectile.saveWithoutId(new CompoundTag());
        compoundtag.remove("Motion");
        projectile.load(compoundtag);
        return projectile;
    }

    private static float calculateBounceVelocity(float velocity) {
        return SMConfig.ENABLE_DYNAMIC_VELOCITY.get() && (velocity * 0.8F >= 0.5F) ? velocity * 0.8F : 0.5F;
    }

    public static boolean isFlingerAndFlings(Projectile projectile, BlockState blockState, Direction direction) {
        return !(projectile.getType().is(SMEntityTags.CANNOT_BE_FLUNG)) && blockState.getBlock() instanceof FlingerTotem && !direction.equals(blockState.getValue(SMDirectionalBlock.FACING));
    }

    private static void handleParticleAndSound(Level level, HitResult hitResult, Direction direction, Projectile projectile) {
        Vec3 particlePos = Vec3.ZERO;
        if (hitResult instanceof BlockHitResult blockHitResult) {
            particlePos = new Vec3(blockHitResult.getLocation().x, blockHitResult.getLocation().y, blockHitResult.getLocation().z).relative(direction, 0.1D);
        } else if (hitResult instanceof EntityHitResult entityHitResult) {
            particlePos = new Vec3(projectile.getX(), projectile.getY(), projectile.getZ()).relative(direction, 0.1D);
        }
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(new DirectionParticleOptions(SMParticleTypes.RICOCHET.get(), direction), particlePos.x(), particlePos.y(), particlePos.z(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        }
        level.playSound(null, projectile.getX(), projectile.getY(), projectile.getZ(), SMSounds.JADE_RICOCHET.get(), SoundSource.BLOCKS, 1.0F, 0.0F);
        projectile.gameEvent(GameEvent.PROJECTILE_SHOOT);
    }

    /*
     * This handles a breaking change of Forge in version 47.1.4!
     * With this fix, the mod is compatible with any version above 47.1.0 and not just with versions below 47.1.4!
     */
    @SuppressWarnings("removal")
    private static void handleCancellation(ProjectileImpactEvent event) {
        ArtifactVersion FORGE_VERSION = new DefaultArtifactVersion(FMLLoader.versionInfo().forgeVersion());
        if (new DefaultArtifactVersion("47.1.4").compareTo(FORGE_VERSION) < 0) {
            event.setImpactResult(ProjectileImpactEvent.ImpactResult.STOP_AT_CURRENT_NO_DAMAGE);
        } else {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();

        if (entity.getType().is(SMEntityTags.ATTACKS_BABY_TORTOISES) && entity instanceof Mob mob) {
            if (mob instanceof Ocelot ocelot) {
                ocelot.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(ocelot, Tortoise.class, 10, false, false, Turtle.BABY_ON_LAND_SELECTOR));
            } else if (mob instanceof TamableAnimal tamable && !tamable.isTame()) {
                tamable.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(tamable, Tortoise.class, false, Turtle.BABY_ON_LAND_SELECTOR));
            } else {
                mob.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(mob, Tortoise.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
            }
        }
        if (entity instanceof Zombie zombie) {
            zombie.goalSelector.addGoal(4, new GenericMobAttackTortoiseEggGoal(zombie, 1.0D, 3));
        }
    }

    //AMBER STUFF
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.level();
        BlockState state = entity.getFeetBlockState();
        BlockPos blockPos = new BlockPos(entity.getBlockX(), entity.getBlockY(), entity.getBlockZ());
        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        if (state.is(SMBlocks.AMBER.get())) {
            if (entity instanceof Player) {
                entity.makeStuckInBlock(state, new Vec3((double) 0.8F, 0.1D, (double) 0.8F));
            }
            if (entity instanceof Mob mob) {
                if (blockEntity instanceof AmberBlockEntity amber) {
                    mob.makeStuckInBlock(state, new Vec3((double) 0.0F, 0.1D, (double) 0.0F));
                    if (!amber.isFull()) {
                        if (mob.getBlockStateOn() != SMBlocks.AMBER.get().defaultBlockState()) {
                            amber.makeEntityStuck(mob);
                        }
                    }
                }
            }
            if (level.isClientSide) {
                RandomSource randomsource = level.getRandom();
                boolean flag = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
                if (flag && randomsource.nextBoolean()) {
                    level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, entity.getX(), (double)(blockPos.getY() + 1), entity.getZ(), (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F), (double)0.05F, (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F));
                }
            }
        }
    }
}