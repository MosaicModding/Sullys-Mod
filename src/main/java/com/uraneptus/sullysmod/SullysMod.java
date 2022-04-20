package com.uraneptus.sullysmod;

import com.mojang.logging.LogUtils;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.uraneptus.sullysmod.common.entities.CopperGolemEntity;
import com.uraneptus.sullysmod.common.entities.LanternfishEntity;
import com.uraneptus.sullysmod.core.data.server.SMLootTableProvider;
import com.uraneptus.sullysmod.core.data.client.SMBlockStateProvider;
import com.uraneptus.sullysmod.core.data.client.SMItemModelProvider;
import com.uraneptus.sullysmod.core.data.client.SMLangProvider;
import com.uraneptus.sullysmod.core.data.server.SMRecipeProvider;
import com.uraneptus.sullysmod.core.data.server.modifiers.ModAdvancementModifiersProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMBlockTagsProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMEntityTagsProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMItemTagsProvider;
import com.uraneptus.sullysmod.core.other.SMBrewingRecipes;
import com.uraneptus.sullysmod.core.registry.*;
import net.minecraft.data.DataGenerator;
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
        SMPotions.POTIONS.register(event_bus);

        MinecraftForge.EVENT_BUS.addListener(SMFeatures::onBiomeLoad);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SMFeatures.registerFeatures();
            SMSpawnPlacements.register();
            SMBrewingRecipes.register();
        });
    }

    @SubscribeEvent
    public static void addEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(SMEntityTypes.COPPER_GOLEM.get(), CopperGolemEntity.createAttributes().build());
        event.put(SMEntityTypes.LANTERNFISH.get(), LanternfishEntity.createAttributes().build());
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(new SMBlockStateProvider(generator, fileHelper));
            generator.addProvider(new SMItemModelProvider(generator, fileHelper));
            generator.addProvider(new SMLangProvider(generator));
        }
        if (event.includeServer()) {
            SMBlockTagsProvider blockTagProvider = new SMBlockTagsProvider(generator, fileHelper);

            generator.addProvider(new SMEntityTagsProvider(generator, fileHelper));
            generator.addProvider(blockTagProvider);
            generator.addProvider(new SMItemTagsProvider(generator, blockTagProvider, fileHelper));
            generator.addProvider(new ModAdvancementModifiersProvider(generator));
            generator.addProvider(new SMLootTableProvider(generator));
            generator.addProvider(new SMRecipeProvider(generator));
        }
    }
}
