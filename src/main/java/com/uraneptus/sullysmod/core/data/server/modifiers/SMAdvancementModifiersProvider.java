package com.uraneptus.sullysmod.core.data.server.modifiers;

import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.tags.SMBlockTags;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class SMAdvancementModifiersProvider extends AdvancementModifierProvider {
    public SMAdvancementModifiersProvider(DataGenerator dataGenerator) {
        super(dataGenerator, SullysMod.MOD_ID);
    }

    @Override
    protected void registerEntries() {
        //Balanced Diet Setup
        CriteriaModifier.Builder constructBalancedDiet = CriteriaModifier.builder(this.modId);
        Collection<RegistryObject<Item>> items = SMItems.HELPER.getDeferredRegister().getEntries();

        items.forEach((item) -> {
            if (item.get().isEdible()) {
                constructBalancedDiet.addCriterion(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item.get()));
            }
        });

        //Modifiers
        this.entry("husbandry/wax_on").selects("husbandry/wax_on").addModifier(CriteriaModifier.builder(this.modId).addCriterion("wax_on", ItemInteractWithBlockTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(SMBlockTags.WAXABLE_COPPER_BLOCKS).build()), ItemPredicate.Builder.item().of(Items.HONEYCOMB))).addIndexedRequirements(0, false, "wax_on").build());
        this.entry("husbandry/wax_off").selects("husbandry/wax_off").addModifier(CriteriaModifier.builder(this.modId).addCriterion("wax_off", ItemInteractWithBlockTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(SMBlockTags.WAXED_COPPER_BLOCKS).build()), ItemPredicate.Builder.item().of(SMItemTags.AXE_ITEMS))).addIndexedRequirements(0, false, "wax_off").build());
        this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(constructBalancedDiet.requirements(RequirementsStrategy.AND).build());
        this.entry("husbandry/tactical_fishing").selects("husbandry/tactical_fishing").addModifier(CriteriaModifier.builder(this.modId).addCriterion("lanternfish_bucket", FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(SMItems.LANTERNFISH_BUCKET.get()).build())).addIndexedRequirements(0, false, "lanternfish_bucket").build());
        this.entry("husbandry/bred_all_animals").selects("husbandry/bred_all_animals").addModifier(CriteriaModifier.builder(this.modId).addCriterion("tortoise", BredAnimalsTrigger.TriggerInstance.bredAnimals(EntityPredicate.Builder.entity().of(SMEntityTypes.TORTOISE.get()))).requirements(RequirementsStrategy.AND).build());
    }
}
