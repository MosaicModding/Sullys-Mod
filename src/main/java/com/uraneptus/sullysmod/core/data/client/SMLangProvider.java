package com.uraneptus.sullysmod.core.data.client;

import com.uraneptus.sullysmod.core.other.SMTextUtil;
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

import java.util.Objects;
import java.util.function.Supplier;

public class SMLangProvider extends LanguageProvider {

    public SMLangProvider(PackOutput packOutput, String modId) {
        super(packOutput, modId, "en_us");
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
        forBlock(SMBlocks.TORTOISE_EGG);
        forBlock(SMBlocks.AMBER);
        forBlock(SMBlocks.AMBER_BRICKS);
        forBlock(SMBlocks.AMBER_BRICK_STAIRS);
        forBlock(SMBlocks.AMBER_BRICK_SLAB);
        forBlock(SMBlocks.AMBER_BRICK_WALL);

        addBlock(SMBlocks.POLISHED_JADE_BLOCK, "Block of Polished Jade");
        addBlock(SMBlocks.ROUGH_JADE_BLOCK, "Block of Rough Jade");

        //Items
        forItem(SMItems.ROUGH_JADE);
        forItem(SMItems.POLISHED_JADE);
        forItem(SMItems.LANTERNFISH_SPAWN_EGG);
        forItem(SMItems.PIRANHA_SPAWN_EGG);
        forItem(SMItems.COOKED_LANTERNFISH);
        forItem(SMItems.COOKED_PIRANHA);
        forItem(SMItems.TORTOISE_SPAWN_EGG);
        forItem(SMItems.JADE_SHIELD);
        forItem(SMItems.TORTOISE_SCUTE);
        forItem(SMItems.TORTOISE_SHELL);
        forItem(SMItems.BOULDERING_ZOMBIE_SPAWN_EGG);
        forItem(SMItems.JUNGLE_SPIDER_SPAWN_EGG);
        forItem(SMItems.GLASS_VIAL);
        add(SMItems.VENOM_VIAL.get(), "Vial of Jungle Venom");
        forItem(SMItems.JADE_HORSE_ARMOR);
        forItem(SMItems.THROWING_KNIFE);

        add(SMItems.LANTERNFISH_BUCKET.get(), "Bucket of Lanternfish");
        add(SMItems.PIRANHA_BUCKET.get(), "Bucket of Piranha");
        add(SMItems.LANTERNFISH.get(), "Raw Lanternfish");
        add(SMItems.PIRANHA.get(), "Raw Piranha");

        addMusicDisc(SMItems.MUSIC_DISC_SCOUR, "LudoCrypt - scour");

        //Entities
        forEntity(SMEntityTypes.LANTERNFISH);
        forEntity(SMEntityTypes.TORTOISE);
        forEntity(SMEntityTypes.TORTOISE_SHELL);
        forEntity(SMEntityTypes.BOULDERING_ZOMBIE);
        forEntity(SMEntityTypes.JUNGLE_SPIDER);
        forEntity(SMEntityTypes.PIRANHA);

        //Potions
        addPotionsForEffect(SMPotions.UNLUCK, "Bad Luck");

        //Subtitles
        add("subtitles.item.vial.shatter", "Vial shatters");
        add("subtitles.item.vial.fill", "Vial fills");

        add("subtitles.block.grindstone.polish_jade", "Grindstone polishes");
        add("subtitles.block.jade.ricochet", "Projectile ricochets");
        add("subtitles.block.flinger_totem.shoot", "Projectile flung");
        add("subtitles.block.flinger_totem.input_honey", "Projectile absorbed");
        add("subtitles.block.flinger_totem.add_honey", "Honey applied");
        add("subtitles.block.flinger_totem.reduce_honey", "Honey removed");

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

        add("subtitles.entity.bouldering_zombie.ambient", "Bouldering Zombie groans");
        add("subtitles.entity.bouldering_zombie.hurt", "Bouldering Zombie hurts");
        add("subtitles.entity.bouldering_zombie.death", "Bouldering Zombie dies");

        add("subtitles.item.throwing_knife.hit", "Knife hits entity");
        add("subtitles.item.throwing_knife.hit_ground", "Knife hits ground");
        add("subtitles.item.throwing_knife.throw", "Knife flies");

        //Other
        SMTextUtil.TRANSLATABLES.forEach(this::add);
        add("death.attack.tortoise_shell", "%1$s took a Tortoise Shell to the knees");
        add("death.attack.tortoise_shell.player", "%1$s got bonked to death");
        add("death.attack.throwing_knife", "%1$s got pierced from a distance");
        add("death.attack.throwing_knife.player", "%1$s got shanked by %2$s"); //Not quite sure about this one yet?
    }

    protected void addMusicDisc(Supplier<? extends Item> item, String description) {
        String disc = item.get().getDescriptionId();
        add(disc, "Music Disc");
        add(disc + ".desc", description);
    }

    protected void addPotionsForEffect(Supplier<? extends Potion> potionEffect, String name) {
        add(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), potionEffect.get()), "Potion of " + name);
        add(PotionUtils.setPotion(Items.SPLASH_POTION.getDefaultInstance(), potionEffect.get()), "Splash Potion of " + name);
        add(PotionUtils.setPotion(Items.LINGERING_POTION.getDefaultInstance(), potionEffect.get()), "Lingering Potion of " + name);
        add(PotionUtils.setPotion(Items.TIPPED_ARROW.getDefaultInstance(), potionEffect.get()), "Arrow of " + name);
    }

    protected void forItem(Supplier<? extends Item> item) {
        addItem(item, SMTextUtil.createTranslation(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.get())).getPath()));
    }

    protected void forBlock(Supplier<? extends Block> block) {
        addBlock(block, SMTextUtil.createTranslation(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath()));
    }

    protected void forEntity(Supplier<? extends EntityType<?>> entity) {
        addEntityType(entity, SMTextUtil.createTranslation(Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entity.get())).getPath()));
    }
}