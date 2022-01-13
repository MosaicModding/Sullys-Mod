package com.uraneptus.sullysmod.core.events;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.blocks.JadeBlock;
import com.uraneptus.sullysmod.common.blocks.JadePillar;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents {

    @SubscribeEvent //Here's still some stuff to fix
    public static void onProjectileHitsBlock(ProjectileImpactEvent event) {
        System.out.println("Event fired");
        Projectile projectile = event.getProjectile();
        Level level = event.getEntity().getLevel();
        HitResult hitResult = event.getRayTraceResult();
        Vec3 vec3 = projectile.getDeltaMovement();

        if (!(projectile instanceof ShulkerBullet || projectile instanceof Fireball)) {
            if (hitResult instanceof BlockHitResult blockHitResult) {
                BlockPos pos = blockHitResult.getBlockPos();
                BlockState block = level.getBlockState(pos);
                System.out.println("Hitresult check");
                if (block.getBlock() instanceof JadeBlock || block.getBlock() instanceof JadePillar) {
                    System.out.println("Jade block ckecksss");

                    event.setCanceled(true);
                    projectile.shoot(vec3.reverse().x, vec3.reverse().y, vec3.reverse().z , 0.4F, 1.0F);


                }
            }
        }
    }
}
