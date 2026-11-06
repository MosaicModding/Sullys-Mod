package com.mosaicmodding.sullysmod;

import com.mosaicmodding.sullysmod.builtin.SMBuiltinEntriesProvider;
import com.mosaicmodding.sullysmod.client.SMBlockStateGen;
import com.mosaicmodding.sullysmod.client.SMItemModelGen;
import com.mosaicmodding.sullysmod.client.SMLanguageGen;
import com.mosaicmodding.sullysmod.client.SMSoundDefinitionsGen;
import com.mosaicmodding.sullysmod.fabricated_conditions.FabricatedConditionRegistry;
import com.mosaicmodding.sullysmod.loot.SMLootTableGen;
import com.mosaicmodding.sullysmod.recipes.SMRecipeGen;
import com.mosaicmodding.sullysmod.tags.SMBiomeTagGen;
import com.mosaicmodding.sullysmod.tags.SMBlockTagGen;
import com.mosaicmodding.sullysmod.tags.SMEntityTypeTagGen;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = SullysMod.ID)
@Mod(SullysMod.ID)
public class SullysModDatagen {

    public SullysModDatagen(IEventBus bus) {
        FabricatedConditionRegistry.API.registerAPI(bus);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //Client
        var languageGen = new SMLanguageGen(output);
        var itemModelGen = new SMItemModelGen(output, fileHelper);
        var blockstateGen = new SMBlockStateGen(output, fileHelper, itemModelGen);
        var soundGen = new SMSoundDefinitionsGen(output, fileHelper);

        //Server
        var datapackEntriesProvider = new SMBuiltinEntriesProvider(output, lookupProvider);
        var modRegistryProvider = datapackEntriesProvider.getRegistryProvider(); //use this instead of lookupProvider, so we have access to our own registry set builder

        var blockTagDatagen = new SMBlockTagGen(output, modRegistryProvider, fileHelper);
        var entityTypeTagDatagen = new SMEntityTypeTagGen(output, modRegistryProvider, fileHelper);
        var biomeTagDatagen = new SMBiomeTagGen(output, modRegistryProvider, fileHelper);

        var lootTableDatagen = new SMLootTableGen(output, modRegistryProvider);

        var recipeDatagen = new SMRecipeGen(output, modRegistryProvider);

        //Generator additions
        generator.addProvider(includeClient, languageGen);
        generator.addProvider(includeClient, itemModelGen);
        generator.addProvider(includeClient, blockstateGen);
        generator.addProvider(includeClient, soundGen);

        generator.addProvider(includeServer, datapackEntriesProvider);

        generator.addProvider(includeServer, blockTagDatagen);
        generator.addProvider(includeServer, entityTypeTagDatagen);
        generator.addProvider(includeServer, biomeTagDatagen);

        generator.addProvider(includeServer, lootTableDatagen);

        generator.addProvider(includeServer, recipeDatagen);

    }
}
