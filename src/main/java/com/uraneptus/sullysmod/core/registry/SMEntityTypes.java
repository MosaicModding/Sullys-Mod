package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMEntityTypes {
    public static final EntitySubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getEntitySubHelper();

    public static final RegistryObject<EntityType<CopperGolem>> COPPER_GOLEM = HELPER.createLivingEntity("copper_golem", CopperGolem::new, MobCategory.CREATURE, 1.0F, 1.0F);
    public static final RegistryObject<EntityType<Lanternfish>> LANTERNFISH = HELPER.createLivingEntity("lanternfish", Lanternfish::new, MobCategory.UNDERGROUND_WATER_CREATURE, 0.5F, 0.3F);
    public static final RegistryObject<EntityType<Tortoise>> TORTOISE = HELPER.createLivingEntity("tortoise", Tortoise::new, MobCategory.CREATURE, 1.0F, 0.9F);
    public static final RegistryObject<EntityType<TortoiseShell>> TORTOISE_SHELL = HELPER.createEntity("tortoise_shell", TortoiseShell::new, TortoiseShell::new, MobCategory.MISC, 1.1F, 0.9F);
    public static final RegistryObject<EntityType<BoulderingZombie>> BOULDERING_ZOMBIE = HELPER.createLivingEntity("bouldering_zombie", BoulderingZombie::new, MobCategory.MONSTER, 0.6F, 1.95F);
    public static final RegistryObject<EntityType<JungleSpider>> JUNGLE_SPIDER = HELPER.createLivingEntity("jungle_spider", JungleSpider::new, MobCategory.MONSTER, 0.85F, 0.9F);
}