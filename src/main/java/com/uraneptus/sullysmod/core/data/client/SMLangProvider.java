package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMPotions;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class SMLangProvider extends LanguageProvider {

    public SMLangProvider(PackOutput packOutput) {
        super(packOutput, SullysMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Blocks
        forBlock(SMBlocks.JADE_ORE);
        forBlock(SMBlocks.DEEPSLATE_JADE_ORE);
        forBlock(SMBlocks.ROUGH_JADE_BRICKS);
        forBlock(SMBlocks.SMOOTHED_ROUGH_JADE);
        forBlock(SMBlocks.ROUGH_JADE_TILES);
        forBlock(SMBlocks.POLISHED_JADE_BRICKS);
        forBlock(SMBlocks.POLISHED_SMALL_JADE_BRICKS);
        forBlock(SMBlocks.POLISHED_JADE_SHINGLES);
        forBlock(SMBlocks.POLISHED_JADE_TILES);
        forBlock(SMBlocks.POLISHED_CHISELED_JADE);
        forBlock(SMBlocks.POLISHED_JADE_PILLAR);
        forBlock(SMBlocks.JADE_TOTEM);
        forBlock(SMBlocks.JADE_FLINGER_TOTEM);
        forBlock(SMBlocks.COPPER_BUTTON);
        forBlock(SMBlocks.EXPOSED_COPPER_BUTTON);
        forBlock(SMBlocks.WEATHERED_COPPER_BUTTON);
        forBlock(SMBlocks.OXIDIZED_COPPER_BUTTON);
        forBlock(SMBlocks.WAXED_COPPER_BUTTON);
        forBlock(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON);
        forBlock(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON);
        forBlock(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON);
        forBlock(SMBlocks.POLISHED_JADE_BRICK_STAIRS);
        forBlock(SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS);
        forBlock(SMBlocks.POLISHED_JADE_SHINGLE_STAIRS);
        forBlock(SMBlocks.POLISHED_JADE_TILE_STAIRS);
        forBlock(SMBlocks.POLISHED_JADE_BRICK_SLAB);
        forBlock(SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB);
        forBlock(SMBlocks.POLISHED_JADE_SHINGLE_SLAB);
        forBlock(SMBlocks.POLISHED_JADE_TILE_SLAB);
        forBlock(SMBlocks.ROUGH_JADE_BRICK_STAIRS);
        forBlock(SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS);
        forBlock(SMBlocks.ROUGH_JADE_TILE_STAIRS);
        forBlock(SMBlocks.ROUGH_JADE_BRICK_SLAB);
        forBlock(SMBlocks.SMOOTHED_ROUGH_JADE_SLAB);
        forBlock(SMBlocks.ROUGH_JADE_TILE_SLAB);
        /*
        forBlock(SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB);
        forBlock(SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB);
        forBlock(SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB);
        forBlock(SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB);
        forBlock(SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB);
        forBlock(SMBlocks.SMOOTHED_ROUGH_JADE_VERTICAL_SLAB);
        forBlock(SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB);
         */
        forBlock(SMBlocks.TORTOISE_EGG);

        addBlock(SMBlocks.POLISHED_JADE_BLOCK, "Block of Polished Jade");
        addBlock(SMBlocks.ROUGH_JADE_BLOCK, "Block of Rough Jade");

        //Items
        forItem(SMItems.ROUGH_JADE);
        forItem(SMItems.POLISHED_JADE);
        forItem(SMItems.LANTERNFISH_SPAWN_EGG);
        forItem(SMItems.COOKED_LANTERNFISH);
        forItem(SMItems.TORTOISE_SPAWN_EGG);
        forItem(SMItems.JADE_SHIELD);
        forItem(SMItems.COOKED_LANTERNFISH_SLICE);
        forItem(SMItems.LANTERNFISH_ROLL);
        forItem(SMItems.CAVE_CHUM_BUCKET);
        forItem(SMItems.TORTOISE_SCUTE);
        forItem(SMItems.TORTOISE_SHELL);

        add(SMItems.LANTERNFISH_BUCKET.get(), "Bucket of Lanternfish");
        add(SMItems.LANTERNFISH.get(), "Raw Lanternfish");
        add(SMItems.LANTERNFISH_SLICE.get(), "Raw Lanternfish Slice");

        addMusicDisc(SMItems.MUSIC_DISC_SCOUR, "LudoCrypt - scour");

        addSmithingTemplate("jade", "Shield", "Polished Jade", "Add Polished Jade", "Add shield");

        //Entities
        forEntity(SMEntityTypes.LANTERNFISH);
        forEntity(SMEntityTypes.TORTOISE);
        forEntity(SMEntityTypes.COPPER_GOLEM);
        forEntity(SMEntityTypes.TORTOISE_SHELL);

        //Potions
        addPotionsForEffect(SMPotions.UNLUCK, "Bad Luck");

        //Advancements
        add("advancements.adventure.jade_grindset.title", "Jade Grindset");
        add("advancements.adventure.jade_grindset.description", "Acquire Rough Jade from within a jungle biome");

        add("advancements.adventure.polish_jade.title", "Sparkles Like New!");
        add("advancements.adventure.polish_jade.description", "Polish Rough Jade using a Grindstone");

        //Subtitles
        add("subtitles.block.grindstone.polish_jade", "Grindstone polishes");
        add("subtitles.block.jade.ricochet", "Projectile ricochets");
        add("subtitles.block.flinger_totem.shoot", "Jade Flinger Totem flings");

        add("subtitles.entity.tortoise.ambient", "Tortoise chirps");
        add("subtitles.entity.tortoise.death", "Tortoise dies");
        add("subtitles.entity.tortoise.hurt", "Tortoise hurts");
        add("subtitles.entity.tortoise.hide", "Tortoise hides");
        add("subtitles.entity.tortoise.emerge", "Tortoise emerges");
        add("subtitles.entity.tortoise.hurt.hidden", "Tortoise hurts");
        add("subtitles.entity.tortoise.death_baby", "Tortoise baby dies");
        add("subtitles.entity.tortoise.hurt_baby", "Tortoise baby hurts");
        add("subtitles.entity.tortoise.egg_crack", "Tortoise Egg cracks");
        add("subtitles.entity.tortoise.egg_break", "Tortoise Egg breaks");
        add("subtitles.entity.tortoise.egg_hatch", "Tortoise Egg hatches");

        add("subtitles.entity.tortoise_shell.place", "Tortoise Shell placed");

        add("subtitles.entity.zombie.destroy_egg", "Egg stomped");

        add("subtitles.entity.lanternfish.flop", "Lanternfish flops");
        add("subtitles.entity.lanternfish.hurt", "Lanternfish hurts");
        add("subtitles.entity.lanternfish.death", "Lanternfish dies");

        //JEI
        add("sullysmod.jei.grindstone_polishing", "Polishing");
        add("sullysmod.jei.grindstone_polishing.info", "Right click to polish");

        //Other
        add("sullysmod.polishing.tooltip", "Polishable at grindstone");

    }

    public void addMusicDisc(Supplier<? extends Item> item, String description) {
        String disc = item.get().getDescriptionId();
        add(disc, "Music Disc");
        add(disc + ".desc", description);
    }

    public void addPotionsForEffect(Supplier<? extends Potion> potionEffect, String name) {
        add(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), potionEffect.get()), "Potion of " + name);
        add(PotionUtils.setPotion(Items.SPLASH_POTION.getDefaultInstance(), potionEffect.get()), "Splash Potion of " + name);
        add(PotionUtils.setPotion(Items.LINGERING_POTION.getDefaultInstance(), potionEffect.get()), "Lingering Potion of " + name);
        add(PotionUtils.setPotion(Items.TIPPED_ARROW.getDefaultInstance(), potionEffect.get()), "Arrow of " + name);
    }

    public void addSmithingTemplate(String material, String applies_to, String ingredients, String additions_slot_description, String base_slot_description) {
        String key = "item.minecraft.smithing_template." + material + "_upgrade";
        add("upgrade.minecraft." + material + "_upgrade", firstToUpperCase(material) + " Upgrade");
        add(key + ".applies_to", applies_to);
        add(key + ".ingredients", ingredients);
        add(key + ".base_slot_description", base_slot_description);
        add(key + ".additions_slot_description", additions_slot_description);
    }

    public void forItem(Supplier<? extends Item> item) {
        addItem(item, createTranslation(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.get())).getPath()));
    }

    public void forBlock(Supplier<? extends Block> block) {
        addBlock(block, createTranslation(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath()));
    }

    public void forEntity(Supplier<? extends EntityType<?>> entity) {
        addEntityType(entity, createTranslation(Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entity.get())).getPath()));
    }

    public String createTranslation(String path) {
        var translation = "";
        List<String> translationParts = Lists.newArrayList();
        var splitList = path.split("_");
        for (String split : splitList) {
            var capitalized = firstToUpperCase(split);
            translationParts.add(capitalized);
        }
        translation = String.join(" ", translationParts);
        return translation;
    }

    public String firstToUpperCase(String string) {
        var firstLetter = string.charAt(0);
        return string.replaceFirst(String.valueOf(firstLetter), String.valueOf(firstLetter).toUpperCase());
    }
}