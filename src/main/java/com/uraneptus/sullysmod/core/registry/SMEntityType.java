package com.uraneptus.sullysmod.core.registry;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.common.entities.CopperGolemEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SMEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITIES, SullysMod.MOD_ID);

    public static final RegistryObject<EntityType<CopperGolemEntity>> COPPER_GOLEM = ENTITY_TYPE.register("copper_golem",
            () -> EntityType.Builder.of(CopperGolemEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(10)
                    .build(new ResourceLocation(SullysMod.MOD_ID, "copper_golem").toString()));
}
