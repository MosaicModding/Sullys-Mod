package com.uraneptus.sullysmod.core.events;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.JadeFlingerTotem;
import com.uraneptus.sullysmod.common.entities.TortoiseEntity;
import com.uraneptus.sullysmod.common.entities.goals.GenericMobAttackTortoiseEggGoal;
import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import com.uraneptus.sullysmod.core.other.tags.SMEntityTags;
import com.uraneptus.sullysmod.core.registry.SMParticleTypes;
import com.uraneptus.sullysmod.core.registry.SMSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
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

        if (hitResult instanceof BlockHitResult blockHitResult) {
            BlockPos pos = blockHitResult.getBlockPos();
            BlockState blockState = level.getBlockState(pos);
            Direction direction = blockHitResult.getDirection();

            if (!(projectile.getType().is(SMEntityTags.CANNOT_BOUNCE))) {
                if (blockState.is(SMBlockTags.PROJECTILES_BOUNCE_ON)) {
                    event.setCanceled(true);

                    if (direction.getAxis().isHorizontal()) {
                        projectile.shoot(vec3.reverse().x, vec3.reverse().y, vec3.reverse().z, 0.5F, 0.0F);

                    } else {
                        projectile.shoot(vec3.scale(1.0D).x + vec3.scale(1.5D).x, vec3.scale(-2.5D).y, vec3.scale(1.0D).z + vec3.scale(1.5D).z, 0.5F, 0.0F);
                    }
                    level.addParticle(SMParticleTypes.RICOCHET.get(), projectile.getX(), projectile.getY(), projectile.getZ(), 0, 0, 0);
                    level.playLocalSound(projectile.getX(), projectile.getY(), projectile.getZ(), SMSounds.JADE_RICOCHET.get(), SoundSource.BLOCKS, 1.0F, 0.0F, false);
                }
            }
            if (!(projectile.getType().is(SMEntityTags.CANNOT_BE_FLUNG))) {
                if (blockState.getBlock() instanceof JadeFlingerTotem) {
                    Direction front = blockState.getValue(JadeFlingerTotem.FACING);

                    if (!direction.equals(front)) {
                        event.setCanceled(true);

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
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();

        if (entity.getType().is(SMEntityTags.ATTACKS_BABY_TORTOISES) && entity instanceof Mob mob) {
            if (mob instanceof Ocelot ocelot) {
                ocelot.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(ocelot, TortoiseEntity.class, 10, false, false, Turtle.BABY_ON_LAND_SELECTOR));
            }
            else if (mob instanceof TamableAnimal tamable) {
                if (!tamable.isTame()) {
                    tamable.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(tamable, TortoiseEntity.class, false, Turtle.BABY_ON_LAND_SELECTOR));
                }
            }
            else mob.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(mob, TortoiseEntity.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
        }

        if (entity instanceof Zombie zombie) {
            zombie.goalSelector.addGoal(4, new GenericMobAttackTortoiseEggGoal(zombie, 1.0D, 3));
        }
    }
}
