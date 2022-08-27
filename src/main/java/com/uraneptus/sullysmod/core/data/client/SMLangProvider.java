package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.registry.SMBlocks;
import com.uraneptus.sullysmod.core.registry.SMEntityTypes;
import com.uraneptus.sullysmod.core.registry.SMItems;
import com.uraneptus.sullysmod.core.registry.SMPotions;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.data.LanguageProvider;

public class SMLangProvider extends LanguageProvider {

    public SMLangProvider(DataGenerator gen) {
        super(gen, SullysMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Blocks
        add(SMBlocks.JADE_ORE.get(), "Jade Ore");
        add(SMBlocks.DEEPSLATE_JADE_ORE.get(), "Deepslate Jade Ore");
        add(SMBlocks.ROUGH_JADE_BLOCK.get(), "Block of Rough Jade");
        add(SMBlocks.ROUGH_JADE_BRICKS.get(), "Rough Jade Bricks");
        add(SMBlocks.SMOOTHED_ROUGH_JADE.get(), "Smoothed Rough Jade");
        add(SMBlocks.ROUGH_JADE_TILES.get(), "Rough Jade Tiles");
        add(SMBlocks.POLISHED_JADE_BLOCK.get(), "Block of Polished Jade");
        add(SMBlocks.POLISHED_JADE_BRICKS.get(), "Polished Jade Bricks");
        add(SMBlocks.POLISHED_SMALL_JADE_BRICKS.get(), "Polished Small Jade Bricks");
        add(SMBlocks.POLISHED_JADE_SHINGLES.get(), "Polished Jade Shingles");
        add(SMBlocks.POLISHED_JADE_TILES.get(), "Polished Jade Tiles");
        add(SMBlocks.POLISHED_CHISELED_JADE.get(), "Polished Chiseled Jade");
        add(SMBlocks.POLISHED_JADE_PILLAR.get(), "Polished Jade Pillar");
        add(SMBlocks.JADE_TOTEM.get(), "Jade Totem");
        add(SMBlocks.JADE_FLINGER_TOTEM.get(), "Jade Flinger Totem");
        add(SMBlocks.COPPER_BUTTON.get(), "Copper Button");
        add(SMBlocks.EXPOSED_COPPER_BUTTON.get(), "Exposed Copper Button");
        add(SMBlocks.WEATHERED_COPPER_BUTTON.get(), "Weathered Copper Button");
        add(SMBlocks.OXIDIZED_COPPER_BUTTON.get(), "Oxidized Copper Button");
        add(SMBlocks.WAXED_COPPER_BUTTON.get(), "Waxed Copper Button");
        add(SMBlocks.WAXED_EXPOSED_COPPER_BUTTON.get(), "Waxed Exposed Copper Button");
        add(SMBlocks.WAXED_WEATHERED_COPPER_BUTTON.get(), "Waxed Weathered Copper Button");
        add(SMBlocks.WAXED_OXIDIZED_COPPER_BUTTON.get(), "Waxed Oxidized Copper Button");
        add(SMBlocks.POLISHED_JADE_BRICK_STAIRS.get(), "Polished Jade Brick Stairs");
        add(SMBlocks.POLISHED_SMALL_JADE_BRICK_STAIRS.get(), "Polished Small Jade Brick Stairs");
        add(SMBlocks.POLISHED_JADE_SHINGLE_STAIRS.get(), "Polished Jade Shingle Stairs");
        add(SMBlocks.POLISHED_JADE_TILE_STAIRS.get(), "Polished Jade Tile Stairs");
        add(SMBlocks.POLISHED_JADE_BRICK_SLAB.get(), "Polished Jade Brick Slab");
        add(SMBlocks.POLISHED_SMALL_JADE_BRICK_SLAB.get(), "Polished Small Jade Brick Slab");
        add(SMBlocks.POLISHED_JADE_SHINGLE_SLAB.get(), "Polished Jade Shingle Slab");
        add(SMBlocks.POLISHED_JADE_TILE_SLAB.get(), "Polished Jade Tile Slab");
        add(SMBlocks.ROUGH_JADE_BRICK_STAIRS.get(), "Rough Jade Brick Stairs");
        add(SMBlocks.SMOOTHED_ROUGH_JADE_STAIRS.get(), "Smoothed Rough Jade Stairs");
        add(SMBlocks.ROUGH_JADE_TILE_STAIRS.get(), "Rough Jade Tile Stairs");
        add(SMBlocks.ROUGH_JADE_BRICK_SLAB.get(), "Rough Jade Brick Slab");
        add(SMBlocks.SMOOTHED_ROUGH_JADE_SLAB.get(), "Smoothed Rough Jade Slab");
        add(SMBlocks.ROUGH_JADE_TILE_SLAB.get(), "Rough Jade Tile Slab");
        add(SMBlocks.POLISHED_JADE_BRICK_VERTICAL_SLAB.get(), "Polished Jade Brick Vertical Slab");
        add(SMBlocks.POLISHED_SMALL_JADE_BRICK_VERTICAL_SLAB.get(), "Polished Small Jade Brick Vertical Slab");
        add(SMBlocks.POLISHED_JADE_SHINGLE_VERTICAL_SLAB.get(), "Polished Jade Shingle Vertical Slab");
        add(SMBlocks.POLISHED_JADE_TILE_VERTICAL_SLAB.get(), "Polished Jade Tile Vertical Slab");
        add(SMBlocks.ROUGH_JADE_BRICK_VERTICAL_SLAB.get(), "Rough Jade Brick Vertical Slab");
        add(SMBlocks.SMOOTHED_ROUGH_JADE_VERTICAL_SLAB.get(), "Smoothed Rough Jade Vertical Slab");
        add(SMBlocks.ROUGH_JADE_TILE_VERTICAL_SLAB.get(),  "Rough Jade Tile Vertical Slab");
        add(SMBlocks.TORTOISE_EGG.get(), "Tortoise Egg");

        //Items
        add(SMItems.ROUGH_JADE.get(), "Rough Jade");
        add(SMItems.POLISHED_JADE.get(), "Polished Jade");
        add(SMItems.LANTERNFISH_BUCKET.get(), "Bucket of Lanternfish");
        add(SMItems.LANTERNFISH_SPAWN_EGG.get(), "Lanternfish Spawn Egg");
        add(SMItems.RAW_LANTERNFISH.get(), "Raw Lanternfish");
        add(SMItems.COOKED_LANTERNFISH.get(), "Cooked Lanternfish");
        add(SMItems.TORTOISE_SPAWN_EGG.get(), "Tortoise Spawn Egg");
        add(SMItems.MUSIC_DISC_SCOUR.get(), "Music Disc");
        add("item.sullysmod.music_disc_scour.desc", "LudoCrypt - scour");

        //Entities
        add(SMEntityTypes.LANTERNFISH.get(), "Lanternfish");
        add(SMEntityTypes.TORTOISE.get(), "Tortoise");
        add(SMEntityTypes.COPPER_GOLEM.get(), "Copper Golem");

        //Potions
        add(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), SMPotions.UNLUCK.get()), "Potion of Bad Luck");
        add(PotionUtils.setPotion(Items.SPLASH_POTION.getDefaultInstance(), SMPotions.UNLUCK.get()), "Splash Potion of Bad Luck");
        add(PotionUtils.setPotion(Items.LINGERING_POTION.getDefaultInstance(), SMPotions.UNLUCK.get()), "Lingering Potion of Bad Luck");
        add(PotionUtils.setPotion(Items.TIPPED_ARROW.getDefaultInstance(), SMPotions.UNLUCK.get()), "Arrow of Bad Luck");
        add(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), SMPotions.BLINDNESS.get()), "Potion of Blindness");
        add(PotionUtils.setPotion(Items.SPLASH_POTION.getDefaultInstance(), SMPotions.BLINDNESS.get()), "Splash Potion of Blindness");
        add(PotionUtils.setPotion(Items.LINGERING_POTION.getDefaultInstance(), SMPotions.BLINDNESS.get()), "Lingering Potion of Blindness");
        add(PotionUtils.setPotion(Items.TIPPED_ARROW.getDefaultInstance(), SMPotions.BLINDNESS.get()), "Arrow of Blindness");
        
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

        add("subtitles.entity.zombie.destroy_egg", "Egg stomped");

        add("subtitles.entity.lanternfish.flop", "Lanternfish flops");
        add("subtitles.entity.lanternfish.hurt", "Lanternfish hurts");
        add("subtitles.entity.lanternfish.death", "Lanternfish dies");

        //JEI
        add("sullysmod.jei.grindstone_polishing", "Polishing");
        add("sullysmod.jei.grindstone_polishing.info", "Right-click to polish");

        SullysMod.LOGGER.info("LANGUAGE GENERATION COMPLETE");
    }
}
