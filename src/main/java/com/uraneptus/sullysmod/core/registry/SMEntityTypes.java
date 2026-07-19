package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.function.Supplier;

public class SMEntityTypes {
    public static final EntitySubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getEntitySubHelper();

    public static final Supplier<EntityType<Lanternfish>> LANTERNFISH = HELPER.createEntity("lanternfish", Lanternfish::new, MobCategory.UNDERGROUND_WATER_CREATURE, 0.5F, 0.3F);
    public static final Supplier<EntityType<Tortoise>> TORTOISE = HELPER.createEntity("tortoise", Tortoise::new, MobCategory.CREATURE, 1.1F, 1.1F);
    public static final Supplier<EntityType<TortoiseShell>> TORTOISE_SHELL = HELPER.createEntity("tortoise_shell", TortoiseShell::new, TortoiseShell::new, MobCategory.MISC, 1.0F, 0.9F);
    public static final Supplier<EntityType<BoulderingZombie>> BOULDERING_ZOMBIE = HELPER.createEntity("bouldering_zombie", BoulderingZombie::new, MobCategory.MONSTER, 0.6F, 1.95F);
    public static final Supplier<EntityType<JungleSpider>> JUNGLE_SPIDER = HELPER.createEntity("jungle_spider", JungleSpider::new, MobCategory.MONSTER, 0.85F, 0.9F);
    public static final Supplier<EntityType<Piranha>> PIRANHA = HELPER.createEntity("piranha", Piranha::new, MobCategory.WATER_AMBIENT, 0.65F, 0.4F);
    public static final Supplier<EntityType<ThrownThrowingKnife>> THROWN_THROWING_KNIFE = HELPER.createEntity("thrown_throwing_knife", ThrownThrowingKnife::new, ThrownThrowingKnife::new, MobCategory.MISC, 0.5F, 0.6F);
    public static final Supplier<EntityType<Mauled>> MAULED = HELPER.createEntity("mauled", Mauled::new, MobCategory.MONSTER, 0.6F, 1.95F);
}
