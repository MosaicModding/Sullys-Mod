package com.uraneptus.sullysmod.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.CopperGolemEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMEntityType {
    public static final EntitySubRegistryHelper HELPER = SullysMod.REGISTRY_HELPER.getEntitySubHelper();


    public static final RegistryObject<EntityType<CopperGolemEntity>> COPPER_GOLEM = HELPER.createLivingEntity("copper_golem", CopperGolemEntity::new, MobCategory.MISC, 1.0F, 1.0F);
}
