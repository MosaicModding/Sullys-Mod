package com.uraneptus.sullysmod.data.server.advancements;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.uraneptus.sullysmod.core.other.SMTextDefinitions.*;
import static com.uraneptus.sullysmod.data.SMDatagenUtil.craftingPath;
import static com.uraneptus.sullysmod.data.SMDatagenUtil.name;

public class SMAdventureAdvancements implements ForgeAdvancementProvider.AdvancementGenerator {
    ResourceLocation adventureParent = new ResourceLocation("adventure/root");
    public static List<Item> ARTIFACTS = SMItems.ARTIFACT_DESC_MAP.keySet().stream().map(RegistryObject::get).collect(Collectors.toList());
    public static List<Item> BIBLIOPHILE_ITEMS = List.of(SMItems.TORN_MANUSCRIPT.get(), SMItems.LOST_JOURNAL.get(), SMItems.LOST_SKETCHBOOK.get(), SMItems.LOST_RECIPE_BOOK.get());
    public static List<Item> ALL_SKULL_ITEMS = SMBlocks.ANCIENT_SKULLS.stream().map(Supplier::get).map(Block::asItem).collect(Collectors.toList());
    public static List<Item> ALL_FOSSIL_ITEMS = List.of(SMItems.FOSSILISED_BONE.get(), SMItems.FOSSILISED_FISH.get(), SMItems.FOSSILISED_FOOTSTEP.get(), SMItems.FOSSILISED_SHELLS.get());

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        //JADE
        Advancement JADE_GRINDSET = createAdv(JADE_GRINDSET_ADV_NAME, null, FrameType.TASK, SMItems.ROUGH_JADE.get(), JADE_GRINDSET_ADV, addCriterion("rough_jade", InventoryChangeTrigger.TriggerInstance.hasItems(SMItems.ROUGH_JADE.get())), null, saver, existingFileHelper);
        Advancement POLISH_JADE = createAdv(POLISH_JADE_ADV_NAME, JADE_GRINDSET, FrameType.TASK, SMItems.JADE.get(), POLISH_JADE_ADV, addCriterion("polished_jade", InventoryChangeTrigger.TriggerInstance.hasItems(SMItems.JADE.get())), null, saver, existingFileHelper);

        //TORTOISE SHELL
        Advancement SHELL_HIT = createAdv(SHELL_HIT_ADV_NAME, null, FrameType.TASK, SMItems.TORTOISE_SHELL.get(), SHELL_HIT_ADV, addCriterion("tortoise_shell", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(DamagePredicate.Builder.damageInstance().type(DamageSourcePredicate.Builder.damageType().direct(EntityPredicate.Builder.entity().of(SMEntityTypes.TORTOISE_SHELL.get()))))), null, saver, existingFileHelper);
        Advancement SHELL_HIT_RAVAGER = createAdv(SHELL_HIT_RAVAGER_ADV_NAME, SHELL_HIT, FrameType.CHALLENGE, SMItems.TORTOISE_SHELL.get(), SHELL_HIT_RAVAGER_ADV, addCriterion("tortoise_shell", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(DamagePredicate.Builder.damageInstance().type(DamageSourcePredicate.Builder.damageType().direct(EntityPredicate.Builder.entity().of(SMEntityTypes.TORTOISE_SHELL.get()))), EntityPredicate.Builder.entity().of(EntityType.RAVAGER).build())), AdvancementRewards.Builder.experience(50), saver, existingFileHelper);

        //JUNGLE SPIDER
        Advancement FILL_VIAL_JUNGLE_SPIDER = createAdv(FILL_VIAL_JUNGLE_SPIDER_ADV_NAME, null, FrameType.TASK, SMItems.GLASS_VIAL.get(), FILL_VIAL_JUNGLE_SPIDER_ADV, addCriterion("harvest_venom", InventoryChangeTrigger.TriggerInstance.hasItems(SMItems.VENOM_VIAL.get())), null, saver, existingFileHelper);

        //ARTIFACTS
        Advancement ITEM_STAND = createAdv(ITEM_STAND_ADV_NAME, null, FrameType.TASK, SMBlocks.ITEM_STAND.get(), ITEM_STAND_ADV, addCriterion("craft_item_stand", RecipeCraftedTrigger.TriggerInstance.craftedItem(craftingPath(name(SMBlocks.ITEM_STAND.get())))), null, saver, existingFileHelper);
        Advancement FIRST_ARTIFACT = createAdv(FIRST_ARTIFACT_ADV_NAME, null, FrameType.TASK, SMItems.BROKEN_VASE.get(), FIRST_ARTIFACT_ADV, addCriterion("first_artifact", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(SMItemTags.ARTIFACTS).build())), null, saver, existingFileHelper);
        Advancement LOST_CROWN = createAdv(LOST_CROWN_ADV_NAME, FIRST_ARTIFACT, FrameType.TASK, SMItems.LOST_CROWN.get(), LOST_CROWN_ADV, addCriterion("lost_crown", InventoryChangeTrigger.TriggerInstance.hasItems(SMItems.LOST_CROWN.get())), null, saver, existingFileHelper);
        Advancement BIBLIOPHILE = addCollectionAdv(BIBLIOPHILE_ITEMS, BIBLIOPHILE_ADV_NAME, createAdvBuilder(FIRST_ARTIFACT, FrameType.GOAL, SMItems.TORN_MANUSCRIPT.get(), BIBLIOPHILE_ADV, null, null), saver, existingFileHelper);
        Advancement FIRST_SKULL = createAdv(FIRST_SKULL_ADV_NAME, FIRST_ARTIFACT, FrameType.TASK, SMBlocks.CRESTED_ANCIENT_SKULL.getFirst().get(), FIRST_SKULL_ADV, addCriterion("first_skull", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(SMItemTags.ANCIENT_SKULLS).build())), null, saver, existingFileHelper);
        Advancement ALL_SKULLS = addCollectionAdv(ALL_SKULL_ITEMS, ALL_SKULLS_ADV_NAME, createAdvBuilder(FIRST_SKULL, FrameType.CHALLENGE, SMBlocks.CRESTED_ANCIENT_SKULL.getFirst().get(), ALL_SKULLS_ADV, null, AdvancementRewards.Builder.experience(100)), saver, existingFileHelper);
        Advancement DRIED_FLOWERS = addCollectionAdv(List.of(SMItems.DRIED_CYAN_FLOWER.get(), SMItems.DRIED_RED_FLOWER.get()), DRIED_FLOWERS_ADV_NAME, createAdvBuilder(FIRST_ARTIFACT, FrameType.GOAL, SMItems.DRIED_CYAN_FLOWER.get(), DRIED_FLOWERS_ADV, null, null), saver, existingFileHelper);
        Advancement FIND_TABLETS = addCollectionAdv(List.of(SMItems.MOON_TABLET.get(), SMItems.OMINOUS_TABLET.get()), FIND_TABLETS_ADV_NAME, createAdvBuilder(FIRST_ARTIFACT, FrameType.GOAL, SMItems.MOON_TABLET.get(), FIND_TABLETS_ADV, null, null), saver, existingFileHelper);
        Advancement ALL_FOSSILS = addCollectionAdv(ALL_FOSSIL_ITEMS, ALL_FOSSILS_ADV_NAME, createAdvBuilder(FIRST_ARTIFACT, FrameType.GOAL, SMItems.FOSSILISED_BONE.get(), ALL_FOSSILS_ADV, null, null), saver, existingFileHelper);
        Advancement ALL_ARTIFACTS = addCollectionAdv(ARTIFACTS, ALL_ARTIFACTS_ADV_NAME, createAdvBuilder(FIRST_ARTIFACT, FrameType.CHALLENGE, SMItems.BROKEN_VASE.get(), ALL_ARTIFACTS_ADV, null, AdvancementRewards.Builder.experience(700)), saver, existingFileHelper);
    }

    public Advancement createAdv(String advName, @Nullable Advancement parentAdvancement, FrameType type, ItemLike displayItem, Pair<Component, Component> titleDescPair, @Nullable Pair<String, CriterionTriggerInstance> criterionTriggerInstancePair, @Nullable AdvancementRewards.Builder reward, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        return createAdvBuilder(parentAdvancement, type, displayItem, titleDescPair, criterionTriggerInstancePair, reward).save(saver, SullysMod.modPrefix("adventure/" + advName), existingFileHelper);
    }
    
    public Advancement.Builder createAdvBuilder(@Nullable Advancement parentAdvancement, FrameType type, ItemLike displayItem, Pair<Component, Component> titleDescPair, @Nullable Pair<String, CriterionTriggerInstance> criterionTriggerInstancePair, @Nullable AdvancementRewards.Builder reward) {
        Advancement.Builder builder = Advancement.Builder.advancement().display(displayItem, titleDescPair.getLeft(), titleDescPair.getRight(), null, type, true, true, false)
                .requirements(RequirementsStrategy.OR);
        if (criterionTriggerInstancePair != null) {
            builder.addCriterion(criterionTriggerInstancePair.getLeft(), criterionTriggerInstancePair.getRight());
        }
        if (reward != null) {
            builder.rewards(reward.build());
        }
        if (parentAdvancement == null) {
            builder.parent(adventureParent);
        } else builder.parent(parentAdvancement);

        return builder;
    }
    
    public Pair<String, CriterionTriggerInstance> addCriterion(String name, CriterionTriggerInstance triggerInstance) {
        return Pair.of(name, triggerInstance);
    }

    private static Advancement addCollectionAdv(List<Item> itemsToCollect, String advName, Advancement.Builder pBuilder, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        for (Item item : itemsToCollect) {
            pBuilder.addCriterion(ForgeRegistries.ITEMS.getKey(item).getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(item));
        }
        pBuilder.requirements(RequirementsStrategy.AND);
        return pBuilder.save(saver, SullysMod.modPrefix("adventure/" + advName), existingFileHelper);
    }
}
