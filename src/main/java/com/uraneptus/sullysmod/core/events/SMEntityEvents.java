package com.uraneptus.sullysmod.core.events;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.JadeFlingerTotem;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import com.uraneptus.sullysmod.common.entities.goals.GenericMobAttackTortoiseEggGoal;
import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import com.uraneptus.sullysmod.core.other.tags.SMEntityTags;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMParticleTypes;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SMEntityEvents {

    @SubscribeEvent
    public static void onProjectileHitsBlock(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        Level level = event.getEntity().getLevel();
        HitResult hitResult = event.getRayTraceResult();
        Vec3 vec3 = projectile.getDeltaMovement();
        float velocity = (float) vec3.length();
        boolean flingerFlag = false;


        if (hitResult instanceof BlockHitResult blockHitResult) {
            BlockPos pos = blockHitResult.getBlockPos();
            BlockState blockState = level.getBlockState(pos);
            Direction direction = blockHitResult.getDirection();

            if (blockState.is(SMBlockTags.PROJECTILES_BOUNCE_ON)) {
                if (!(projectile.getType().is(SMEntityTags.CANNOT_BE_FLUNG))) {
                    if (blockState.getBlock() instanceof JadeFlingerTotem) {
                        Direction front = blockState.getValue(JadeFlingerTotem.FACING);
                        if (!direction.equals(front)) {
                            flingerFlag = true;
                        }
                    }
                }
                if (flingerFlag) {
                    event.setCanceled(true);
                    Direction front = blockState.getValue(JadeFlingerTotem.FACING);

                    Projectile newProjectile = projectile;
                    projectile = (Projectile) projectile.getType().create(level);
                    if (projectile == null) {
                        return;
                    }
                    newProjectile.setRemoved(Entity.RemovalReason.DISCARDED);

                    CompoundTag compoundtag = newProjectile.saveWithoutId(new CompoundTag());
                    compoundtag.remove("Motion");
                    projectile.load(compoundtag);

                    projectile.moveTo(pos.getX() + 0.5 + front.getStepX(), pos.getY() + 0.5F + front.getStepY(), pos.getZ() + 0.5 + front.getStepZ());
                    projectile.shoot(front.getStepX(), front.getStepY(), front.getStepZ(), velocity, 0.0F);
                    level.addFreshEntity(projectile);
                    level.playSound(null, pos, SMSounds.FLINGER_FLINGS.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    projectile.gameEvent(GameEvent.PROJECTILE_SHOOT);
                }
                else if (!(projectile.getType().is(SMEntityTags.CANNOT_BOUNCE))) {
                    event.setCanceled(true);

                    if (direction.getAxis() == Direction.Axis.X) {
                        projectile.shoot(vec3.reverse().x, vec3.y, vec3.z, calculateBounceVelocity(velocity), 0.0F);

                    } else if (direction.getAxis() == Direction.Axis.Y) {
                        projectile.shoot(vec3.x, vec3.reverse().y, vec3.z, calculateBounceVelocity(velocity), 0.0F);

                    } else if (direction.getAxis() == Direction.Axis.Z) {
                        projectile.shoot(vec3.x, vec3.y, vec3.reverse().z, calculateBounceVelocity(velocity), 0.0F);
                    }
                    projectile.gameEvent(GameEvent.PROJECTILE_SHOOT);
                    level.addParticle(SMParticleTypes.RICOCHET.get(), projectile.getX(), projectile.getY(), projectile.getZ(), 0, 0, 0);
                    level.playLocalSound(projectile.getX(), projectile.getY(), projectile.getZ(), SMSounds.JADE_RICOCHET.get(), SoundSource.BLOCKS, 1.0F, 0.0F, false);
                }
            }
        }
    }

    //TODO This could be improved
    @SubscribeEvent
    public static void onShieldBlockEvent(ShieldBlockEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getEntity().getLevel();
        DamageSource source = event.getDamageSource();

        if (entity instanceof Player player) {
            Direction direction = player.getDirection();
            //Level level1 = player.getLevel();

            if (player.getUseItem().is(SMItems.JADE_SHIELD.get())) {
                Entity damageEntity = source.getDirectEntity();

                if (damageEntity instanceof Projectile projectile) {
                    Vec3 vec3 = projectile.getDeltaMovement();
                    float velocity = (float) vec3.length();

                    if (!(projectile.getType().is(SMEntityTags.CANNOT_BOUNCE))) {

                        Projectile newProjectile = projectile;
                        projectile = (Projectile) projectile.getType().create(level);
                        if (projectile == null) {
                            return;
                        }
                        Vec3 vec31 = newProjectile.getDeltaMovement();
                        double x1 = newProjectile.getX();
                        double y1 = newProjectile.getY();
                        double z1 = newProjectile.getZ();


                        newProjectile.setRemoved(Entity.RemovalReason.DISCARDED);

                        CompoundTag compoundtag = newProjectile.saveWithoutId(new CompoundTag());
                        compoundtag.remove("Motion");
                        projectile.load(compoundtag);

                        projectile.moveTo(newProjectile.getX() - 0.2, newProjectile.getY(), newProjectile.getZ() - 0.2);


                        if (direction.getAxis() == Direction.Axis.X) {
                            projectile.shoot(vec31.reverse().x, vec31.y, vec31.z, calculateBounceVelocity(velocity), 0.0F);

                        } else if (direction.getAxis() == Direction.Axis.Y) {
                            projectile.shoot(vec31.x, vec31.reverse().y, vec31.z, calculateBounceVelocity(velocity), 0.0F);

                        } else if (direction.getAxis() == Direction.Axis.Z) {
                            projectile.shoot(vec31.x, vec31.y, vec31.reverse().z, calculateBounceVelocity(velocity), 0.0F);
                        }
                        level.addFreshEntity(projectile);

                        level.playSound(null, player.getX(), player.getY(), player.getZ(), SMSounds.JADE_RICOCHET.get(), player.getSoundSource(),  1.0F, 0.0F);
                        ((ServerLevel) level).sendParticles(SMParticleTypes.RICOCHET.get(), x1, y1, z1, 1, 0.0D, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();

        if (entity.getType().is(SMEntityTags.ATTACKS_BABY_TORTOISES) && entity instanceof Mob mob) {
            if (mob instanceof Ocelot ocelot) {
                ocelot.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(ocelot, Tortoise.class, 10, false, false, Turtle.BABY_ON_LAND_SELECTOR));
            }
            else if (mob instanceof TamableAnimal tamable) {
                if (!tamable.isTame()) {
                    tamable.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(tamable, Tortoise.class, false, Turtle.BABY_ON_LAND_SELECTOR));
                }
            }
            else mob.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(mob, Tortoise.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
        }

        if (entity instanceof Zombie zombie) {
            zombie.goalSelector.addGoal(4, new GenericMobAttackTortoiseEggGoal(zombie, 1.0D, 3));
        }
    }

    private static float calculateBounceVelocity(float velocity) {
        if (SMConfig.ENABLE_DYNAMIC_VELOCITY.get() && velocity * 0.8F >= 0.5F) {
            return velocity * 0.8F;
        }
        else return 0.5F;
    }
}
