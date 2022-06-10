package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.CopperGolemEntity;
import com.uraneptus.sullysmod.common.entities.LanternfishEntity;
import com.uraneptus.sullysmod.common.entities.TortoiseEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMEntityTypes {
    public static final EntitySubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getEntitySubHelper();

    public static final RegistryObject<EntityType<CopperGolemEntity>> COPPER_GOLEM = HELPER.createLivingEntity("copper_golem", CopperGolemEntity::new, MobCategory.MISC, 1.0F, 1.0F);
    public static final RegistryObject<EntityType<LanternfishEntity>> LANTERNFISH = HELPER.createLivingEntity("lanternfish", LanternfishEntity::new, MobCategory.WATER_AMBIENT, 0.5F, 0.3F);
    public static final RegistryObject<EntityType<TortoiseEntity>> TORTOISE = HELPER.createLivingEntity("tortoise", TortoiseEntity::new, MobCategory.CREATURE, 0.9F, 1.0F);
}
