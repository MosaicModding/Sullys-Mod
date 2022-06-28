package com.uraneptus.sullysmod;

import com.mojang.logging.LogUtils;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.uraneptus.sullysmod.common.entities.CopperGolem;
import com.uraneptus.sullysmod.common.entities.Lanternfish;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.data.client.SMBlockStateProvider;
import com.uraneptus.sullysmod.core.data.client.SMItemModelProvider;
import com.uraneptus.sullysmod.core.data.client.SMLangProvider;
import com.uraneptus.sullysmod.core.data.server.SMAdvancementProvider;
import com.uraneptus.sullysmod.core.data.server.SMLootTableProvider;
import com.uraneptus.sullysmod.core.data.server.SMRecipeProvider;
import com.uraneptus.sullysmod.core.data.server.modifiers.SMAdvancementModifiersProvider;
import com.uraneptus.sullysmod.core.data.server.modifiers.SMLootModifierProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMBlockTagsProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMEntityTagsProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMItemTagsProvider;
import com.uraneptus.sullysmod.core.registry.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.slf4j.Logger;

@Mod(SullysMod.MOD_ID)
@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SullysMod {
    public static final String MOD_ID = "sullysmod";
    public static final String BLUEPRINT_MOD_ID = "blueprint";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation modPrefix(String path) {
        return new ResourceLocation(SullysMod.MOD_ID, path);
    }

    public static ResourceLocation blueprintPrefix(String path) {
        return new ResourceLocation(SullysMod.BLUEPRINT_MOD_ID, path);
    }

    public SullysMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::gatherData);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SMConfig.SERVER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SMConfig.COMMON);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SMConfig.CLIENT);

        REGISTRY_HELPER.register(bus);
        SMParticleTypes.PARTICLES.register(bus);
        SMPotions.POTIONS.register(bus);
        SMRecipeTypes.RECIPE_TYPES.register(bus);
        SMRecipeSerializer.SERIALIZERS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SMSpawnPlacements.register();
            SMBrewingRecipes.register();
        });
    }

    @SubscribeEvent
    public static void addEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(SMEntityTypes.COPPER_GOLEM.get(), CopperGolem.createAttributes().build());
        event.put(SMEntityTypes.LANTERNFISH.get(), Lanternfish.createAttributes().build());
        event.put(SMEntityTypes.TORTOISE.get(), Tortoise.createAttributes().build());
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(true, new SMBlockStateProvider(generator, fileHelper));
            generator.addProvider(true, new SMItemModelProvider(generator, fileHelper));
            generator.addProvider(true, new SMLangProvider(generator));
        }
        if (event.includeServer()) {
            SMBlockTagsProvider blockTagProvider = new SMBlockTagsProvider(generator, fileHelper);

            generator.addProvider(true, new SMEntityTagsProvider(generator, fileHelper));
            generator.addProvider(true, blockTagProvider);
            generator.addProvider(true, new SMItemTagsProvider(generator, blockTagProvider, fileHelper));
            generator.addProvider(true, new SMAdvancementModifiersProvider(generator));
            generator.addProvider(true, new SMLootTableProvider(generator));
            generator.addProvider(true, new SMAdvancementProvider(generator, fileHelper));
            generator.addProvider(true, new SMRecipeProvider(generator));
            generator.addProvider(true, new SMLootModifierProvider(generator));
        }
    }
}
