package com.uraneptus.sullysmod.core.data.server;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public class SMAdvancementProvider extends AdvancementProvider {
    public SMAdvancementProvider(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        Advancement JADE_GRINDSET = Advancement.Builder.advancement().display(SMItems.ROUGH_JADE.get(), Component.translatable("advancements.adventure.jade_grindset.title"), Component.translatable("advancements.adventure.jade_grindset.description"),null, FrameType.TASK, true, true, false).parent(new ResourceLocation("adventure/root")).addCriterion("rough_jade", new Criterion(InventoryChangeTrigger.TriggerInstance.hasItems(SMItems.ROUGH_JADE.get()))).requirements(RequirementsStrategy.OR).save(consumer, SullysMod.modPrefix("adventure/jade_grindset"), fileHelper);
        Advancement POLISH_JADE = Advancement.Builder.advancement().display(SMItems.POLISHED_JADE.get(), Component.translatable("advancements.adventure.polish_jade.title"), Component.translatable("advancements.adventure.polish_jade.description"), null, FrameType.TASK, true, true, false).parent(JADE_GRINDSET).addCriterion("polished_jade", new Criterion(InventoryChangeTrigger.TriggerInstance.hasItems(SMItems.POLISHED_JADE.get()))).requirements(RequirementsStrategy.OR).save(consumer, SullysMod.modPrefix("adventure/polish_jade"), fileHelper);
    }
}
