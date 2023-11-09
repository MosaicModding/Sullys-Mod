package com.uraneptus.sullysmod;

import com.mojang.logging.LogUtils;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.uraneptus.sullysmod.common.entities.*;
import com.uraneptus.sullysmod.core.SMConfig;
import com.uraneptus.sullysmod.core.data.client.*;
import com.uraneptus.sullysmod.core.data.server.SMDatapackBuiltinEntriesProvider;
import com.uraneptus.sullysmod.core.data.server.SMRecipeProvider;
import com.uraneptus.sullysmod.core.data.server.advancements.SMAdvancementProvider;
import com.uraneptus.sullysmod.core.data.server.loot.SMLootTableProvider;
import com.uraneptus.sullysmod.core.data.server.modifiers.SMAdvancementModifiersProvider;
import com.uraneptus.sullysmod.core.data.server.modifiers.SMLootModifierProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMBiomeTagsProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMBlockTagsProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMEntityTagsProvider;
import com.uraneptus.sullysmod.core.data.server.tags.SMItemTagsProvider;
import com.uraneptus.sullysmod.core.integration.fd.FDCompat;
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import com.uraneptus.sullysmod.core.registry.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(SullysMod.MOD_ID)
@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SullysMod {
    public static final String MOD_ID = "sullysmod";
    public static final String BLUEPRINT_MOD_ID = "blueprint";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);
    public static final Logger LOGGER = LogUtils.getLogger();

    public SullysMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::gatherData);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SMConfig.CLIENT);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SMConfig.COMMON);

        SMTextDefinitions.init();

        REGISTRY_HELPER.register(bus);
        SMParticleTypes.PARTICLES.register(bus);
        SMPotions.POTIONS.register(bus);
        SMRecipeTypes.RECIPE_TYPES.register(bus);
        SMRecipeSerializer.SERIALIZERS.register(bus);

        FDCompat.register();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> SMItems::buildCreativeTabContents);
        if (FDCompat.IS_LOADED) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> SMItems::buildFDCreativeTabContents);
        }


        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation modPrefix(String path) {
        return new ResourceLocation(SullysMod.MOD_ID, path);
    }

    public static ResourceLocation blueprintPrefix(String path) {
        return new ResourceLocation(SullysMod.BLUEPRINT_MOD_ID, path);
    }

    @SubscribeEvent
    public static void addEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(SMEntityTypes.COPPER_GOLEM.get(), CopperGolem.createAttributes().build());
        event.put(SMEntityTypes.LANTERNFISH.get(), Lanternfish.createAttributes().build());
        event.put(SMEntityTypes.TORTOISE.get(), Tortoise.createAttributes().build());
        event.put(SMEntityTypes.BOULDERING_ZOMBIE.get(), BoulderingZombie.createAttributes().build());
        event.put(SMEntityTypes.JUNGLE_SPIDER.get(), JungleSpider.createAttributes().build());
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(SMBrewingRecipes::register);
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(includeClient, new SMBlockStateProvider(packOutput, fileHelper));
        generator.addProvider(includeClient, new SMItemModelProvider(packOutput, fileHelper));
        generator.addProvider(includeClient, new SMSoundDefinitionsProvider(packOutput, fileHelper));
        generator.addProvider(includeClient, new SMLangProvider(packOutput));
        generator.addProvider(includeClient, new SMSpriteSourceProvider(packOutput, fileHelper));

        SMBlockTagsProvider blockTagProvider = new SMBlockTagsProvider(packOutput, lookupProvider, fileHelper);
        generator.addProvider(includeServer, new SMEntityTagsProvider(packOutput, lookupProvider, fileHelper));
        generator.addProvider(includeServer, blockTagProvider);
        generator.addProvider(includeServer, new SMItemTagsProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), fileHelper));
        generator.addProvider(includeServer, new SMBiomeTagsProvider(packOutput, lookupProvider, fileHelper));
        generator.addProvider(includeServer, new SMAdvancementModifiersProvider(packOutput, lookupProvider));
        generator.addProvider(includeServer, new SMLootTableProvider(packOutput));
        generator.addProvider(includeServer, new SMAdvancementProvider(packOutput, lookupProvider, fileHelper));
        generator.addProvider(includeServer, new SMRecipeProvider(packOutput));
        generator.addProvider(includeServer, new SMLootModifierProvider(packOutput, lookupProvider));
        generator.addProvider(includeServer, new SMDatapackBuiltinEntriesProvider(packOutput, lookupProvider));
    }
}