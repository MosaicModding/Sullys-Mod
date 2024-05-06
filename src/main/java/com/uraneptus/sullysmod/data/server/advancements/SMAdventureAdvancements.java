package com.uraneptus.sullysmod.data.server.advancements;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.SMTextDefinitions;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class SMAdventureAdvancements implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {

        //JADE
        Advancement JADE_GRINDSET = Advancement.Builder.advancement().display(SMItems.ROUGH_JADE.get(), SMTextDefinitions.JADE_GRINDSET_ADV.getLeft(), SMTextDefinitions.JADE_GRINDSET_ADV.getRight(),null, FrameType.TASK, true, true, false).parent(new ResourceLocation("adventure/root")).addCriterion("rough_jade", new Criterion(InventoryChangeTrigger.TriggerInstance.hasItems(SMItems.ROUGH_JADE.get()))).requirements(RequirementsStrategy.OR).save(saver, SullysMod.modPrefix("adventure/" + SMTextDefinitions.JADE_GRINDSET_ADV_NAME), existingFileHelper);
        Advancement POLISH_JADE = Advancement.Builder.advancement().display(SMItems.JADE.get(), SMTextDefinitions.POLISH_JADE_ADV.getLeft(), SMTextDefinitions.POLISH_JADE_ADV.getRight(), null, FrameType.TASK, true, true, false).parent(JADE_GRINDSET).addCriterion("polished_jade", new Criterion(InventoryChangeTrigger.TriggerInstance.hasItems(SMItems.JADE.get()))).requirements(RequirementsStrategy.OR).save(saver, SullysMod.modPrefix("adventure/" + SMTextDefinitions.POLISH_JADE_ADV_NAME), existingFileHelper);

        //TORTOISE SHELL
        Advancement SHELL_HIT = Advancement.Builder.advancement().display(SMItems.TORTOISE_SHELL.get(), SMTextDefinitions.SHELL_HIT_ADV.getLeft(), SMTextDefinitions.SHELL_HIT_ADV.getRight(), null, FrameType.TASK, true, true, false).parent(new ResourceLocation("adventure/root")).addCriterion("tortoise_shell", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(DamagePredicate.Builder.damageInstance().type(DamageSourcePredicate.Builder.damageType().direct(EntityPredicate.Builder.entity().of(SMEntityTypes.TORTOISE_SHELL.get()))))).save(saver, SullysMod.modPrefix("adventure/" + SMTextDefinitions.SHELL_HIT_ADV_NAME), existingFileHelper);
        Advancement SHELL_HIT_RAVAGER = Advancement.Builder.advancement().display(SMItems.TORTOISE_SHELL.get(), SMTextDefinitions.SHELL_HIT_RAVAGER_ADV.getLeft(), SMTextDefinitions.SHELL_HIT_RAVAGER_ADV.getRight(), null, FrameType.CHALLENGE, true, true, false).parent(SHELL_HIT).addCriterion("tortoise_shell", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(DamagePredicate.Builder.damageInstance().type(DamageSourcePredicate.Builder.damageType().direct(EntityPredicate.Builder.entity().of(SMEntityTypes.TORTOISE_SHELL.get()))), EntityPredicate.Builder.entity().of(EntityType.RAVAGER).build())).save(saver, SullysMod.modPrefix("adventure/" + SMTextDefinitions.SHELL_HIT_RAVAGER_ADV_NAME), existingFileHelper);

        //JUNGLE SPIDER
        Advancement FILL_VIAL_JUNGLE_SPIDER = Advancement.Builder.advancement().display(SMItems.GLASS_VIAL.get(), SMTextDefinitions.FILL_VIAL_JUNGLE_SPIDER_ADV.getLeft(), SMTextDefinitions.FILL_VIAL_JUNGLE_SPIDER_ADV.getRight(), null, FrameType.TASK, true, true, false).parent(new ResourceLocation("adventure/root")).addCriterion("harvest_venom", InventoryChangeTrigger.TriggerInstance.hasItems(SMItems.VENOM_VIAL.get())).requirements(RequirementsStrategy.OR).save(saver, SullysMod.modPrefix("adventure/" + SMTextDefinitions.FILL_VIAL_JUNGLE_SPIDER_ADV_NAME), existingFileHelper);

    }
}
