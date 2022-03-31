package com.uraneptus.sullysmod;

import com.mojang.logging.LogUtils;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.uraneptus.sullysmod.common.entities.CopperGolemEntity;
import com.uraneptus.sullysmod.common.entities.LanternfishEntity;
import com.uraneptus.sullysmod.core.data.client.BlockStates;
import com.uraneptus.sullysmod.core.data.client.ItemModels;
import com.uraneptus.sullysmod.core.data.client.LangProvider;
import com.uraneptus.sullysmod.core.data.server.tags.BlockTags;
import com.uraneptus.sullysmod.core.data.server.tags.EntityTags;
import com.uraneptus.sullysmod.core.data.server.tags.ItemTags;
import com.uraneptus.sullysmod.core.registry.SMEntityType;
import com.uraneptus.sullysmod.core.registry.SMFeatures;
import com.uraneptus.sullysmod.core.registry.SMParticleTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import org.slf4j.Logger;

@Mod(SullysMod.MOD_ID)
@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SullysMod {
    public static final String MOD_ID = "sullysmod";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);
    public static final Logger LOGGER = LogUtils.getLogger();

    public SullysMod() {
        IEventBus event_bus = FMLJavaModLoadingContext.get().getModEventBus();
        event_bus.addListener(this::setup);
        event_bus.addListener(this::gatherData);

        REGISTRY_HELPER.register(event_bus);
        SMParticleTypes.PARTICLES.register(event_bus);

        MinecraftForge.EVENT_BUS.addListener(SMFeatures::onBiomeLoad);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        SMFeatures.registerFeatures();
    }

    @SubscribeEvent
    public static void addEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(SMEntityType.COPPER_GOLEM.get(), CopperGolemEntity.createAttributes().build());
        event.put(SMEntityType.LANTERNFISH.get(), LanternfishEntity.createAttributes().build());
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(new BlockStates(generator, fileHelper));
            generator.addProvider(new ItemModels(generator, fileHelper));
            generator.addProvider(new LangProvider(generator));
        }
        if (event.includeServer()) {
            BlockTags blockTagProvider = new BlockTags(generator, fileHelper);

            generator.addProvider(new EntityTags(generator, fileHelper));
            generator.addProvider(blockTagProvider);
            generator.addProvider(new ItemTags(generator, blockTagProvider, fileHelper));
        }

    }
}
