package com.uraneptus.sullysmod.mixins;

import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Predicate;

@Mixin(MonsterRoomFeature.class)
public class MonsterRoomFeatureMixin {

    @Inject(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/SpawnerBlockEntity;setEntityId(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/util/RandomSource;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void sullysmod_replaceZombie (FeaturePlaceContext<NoneFeatureConfiguration> pContext, CallbackInfoReturnable<Boolean> cir, Predicate predicate, BlockPos blockpos, RandomSource randomsource, WorldGenLevel worldgenlevel, int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int j2, BlockEntity blockentity, SpawnerBlockEntity spawnerblockentity) {
        Entity entity = spawnerblockentity.getSpawner().getOrCreateDisplayEntity(worldgenlevel.getLevel(), randomsource, blockpos);
        if (entity != null) {
            if (entity.getType() == EntityType.ZOMBIE && SMConfig.DISABLE_DEEPSLATE_ZOMBIE_SPAWNS.get() && blockpos.getY() <= 0) {
                spawnerblockentity.setEntityId(SMEntityTypes.BOULDERING_ZOMBIE.get(), randomsource);
            }
        }

    }
}
