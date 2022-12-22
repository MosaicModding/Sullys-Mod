package com.uraneptus.sullysmod;

import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.uraneptus.sullysmod.common.entities.Chameleon;
import com.uraneptus.sullysmod.common.entities.CopperGolem;
import com.uraneptus.sullysmod.common.entities.Lanternfish;
import com.uraneptus.sullysmod.common.entities.Rascal;
import com.uraneptus.sullysmod.common.entities.Tortoise;
import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.data.client.SMBlockStateProvider;
import com.uraneptus.sullysmod.core.data.client.SMItemModelProvider;
import com.uraneptus.sullysmod.core.data.client.SMLangProvider;
import com.uraneptus.sullysmod.core.data.client.SMSoundDefinitionsProvider;
import com.uraneptus.sullysmod.core.data.server.SMAdvancementProvider;
import com.uraneptus.sullysmod.core.data.server.SMDatapackRegistryProviders;
import com.uraneptus.sullysmod.core.data.server.SMLootTableProvider;
import com.uraneptus.sullysmod.core.data.server.SMRecipeProvider;
import com.uraneptus.sullysmod.core.data.server.modifiers.SMAdvancementModifiersProvider;
import com.uraneptus.sullysmod.core.data.server.modifiers.SMLootModifierProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMBiomeTagsProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMBlockTagsProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMEntityTagsProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMItemTagsProvider;
import com.uraneptus.sullysmod.core.integration.fd.FDCompat;
import com.uraneptus.sullysmod.core.registry.*;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SMConfig.CLIENT);

        REGISTRY_HELPER.register(bus);
        SMParticleTypes.PARTICLES.register(bus);
        SMPotions.POTIONS.register(bus);
        SMRecipeTypes.RECIPE_TYPES.register(bus);
        SMRecipeSerializer.SERIALIZERS.register(bus);

        FDCompat.register();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(SMBrewingRecipes::register);
    }

    @SubscribeEvent
    public static void addEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(SMEntityTypes.COPPER_GOLEM.get(), CopperGolem.createAttributes().build());
        event.put(SMEntityTypes.LANTERNFISH.get(), Lanternfish.createAttributes().build());
        event.put(SMEntityTypes.TORTOISE.get(), Tortoise.createAttributes().build());
        event.put(SMEntityTypes.RASCAL.get(), Rascal.createAttributes().build());
        event.put(SMEntityTypes.CHAMELEON.get(), Chameleon.createAttributes().build());
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        RegistryAccess registryAccess = RegistryAccess.builtinCopy();
        RegistryOps<JsonElement> registryOps = RegistryOps.create(JsonOps.INSTANCE, registryAccess);

        generator.addProvider(includeClient, new SMBlockStateProvider(generator, fileHelper));
        generator.addProvider(includeClient, new SMItemModelProvider(generator, fileHelper));
        generator.addProvider(includeClient, new SMSoundDefinitionsProvider(generator, fileHelper));
        generator.addProvider(includeClient, new SMLangProvider(generator));

        SMBlockTagsProvider blockTagProvider = new SMBlockTagsProvider(generator, fileHelper);

        generator.addProvider(includeServer, new SMEntityTagsProvider(generator, fileHelper));
        generator.addProvider(includeServer, blockTagProvider);
        generator.addProvider(includeServer, new SMItemTagsProvider(generator, blockTagProvider, fileHelper));
        generator.addProvider(includeServer, new SMBiomeTagsProvider(generator, fileHelper));
        generator.addProvider(includeServer, new SMAdvancementModifiersProvider(generator));
        generator.addProvider(includeServer, new SMLootTableProvider(generator));
        generator.addProvider(includeServer, new SMAdvancementProvider(generator, fileHelper));
        generator.addProvider(includeServer, new SMRecipeProvider(generator));
        generator.addProvider(includeServer, new SMLootModifierProvider(generator));
        SMDatapackRegistryProviders.registerDatapackProviders(fileHelper, generator, registryOps);
    }
}
