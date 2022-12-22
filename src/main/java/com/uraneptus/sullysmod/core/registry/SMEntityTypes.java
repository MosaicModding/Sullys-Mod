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
    public static final RegistryObject<EntityType<Lanternfish>> LANTERNFISH = HELPER.createLivingEntity("lanternfish", Lanternfish::new, MobCategory.WATER_AMBIENT, 0.5F, 0.3F);
    public static final RegistryObject<EntityType<Tortoise>> TORTOISE = HELPER.createLivingEntity("tortoise", Tortoise::new, MobCategory.CREATURE, 0.9F, 1.0F);
    public static final RegistryObject<EntityType<Rascal>> RASCAL = HELPER.createLivingEntity("rascal", Rascal::new, MobCategory.CREATURE, 0.5F, 1.0F);
    public static final RegistryObject<EntityType<TortoiseShell>> TORTOISE_SHELL = HELPER.createEntity("tortoise_shell", TortoiseShell::new, TortoiseShell::new, MobCategory.MISC, 0.9F, 1.0F);
    public static final RegistryObject<EntityType<ThrownTortoiseShell>> THROWN_TORTOISE_SHELL = HELPER.createEntity("thrown_tortoise_shell", ThrownTortoiseShell::new, ThrownTortoiseShell::new, MobCategory.MISC, 0.25F, 0.25F);
    public static final RegistryObject<EntityType<Chameleon>> CHAMELEON = HELPER.createLivingEntity("chameleon", Chameleon::new, MobCategory.CREATURE, 0.8F, 0.8F);
}