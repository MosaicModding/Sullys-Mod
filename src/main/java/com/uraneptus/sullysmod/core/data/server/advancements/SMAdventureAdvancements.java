package com.uraneptus.sullysmod.core.data.server.advancements;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class SMAdventureAdvancements implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {

        //JADE
        Advancement JADE_GRINDSET = Advancement.Builder.advancement().display(SMItems.ROUGH_JADE.get(), Component.translatable("advancements.adventure.jade_grindset.title"), Component.translatable("advancements.adventure.jade_grindset.description"),null, FrameType.TASK, true, true, false).parent(new ResourceLocation("adventure/root")).addCriterion("rough_jade", new Criterion(InventoryChangeTrigger.TriggerInstance.hasItems(SMItems.ROUGH_JADE.get()))).requirements(RequirementsStrategy.OR).save(saver, SullysMod.modPrefix("adventure/jade_grindset"), existingFileHelper);
        Advancement POLISH_JADE = Advancement.Builder.advancement().display(SMItems.POLISHED_JADE.get(), Component.translatable("advancements.adventure.polish_jade.title"), Component.translatable("advancements.adventure.polish_jade.description"), null, FrameType.TASK, true, true, false).parent(JADE_GRINDSET).addCriterion("polished_jade", new Criterion(InventoryChangeTrigger.TriggerInstance.hasItems(SMItems.POLISHED_JADE.get()))).requirements(RequirementsStrategy.OR).save(saver, SullysMod.modPrefix("adventure/polish_jade"), existingFileHelper);

        //TORTOISE SHELL
        //Advancement BONK = Advancement.Builder.advancement().display(SMItems.TORTOISE_SHELL.get(), "Get Bonked!", "Hit an Entity with a Tortoise Shell", null, FrameType.TASK, true, true, false).parent(new ResourceLocation("adventure/root")).addCriterion("tortoise_shell", new Criterion())

    }
}
