package com.uraneptus.sullysmod.data.client;

import com.uraneptus.sullysmod.SullysMod;
import com.uraneptus.sullysmod.core.other.SMTextUtil;
import com.uraneptus.sullysmod.core.registry.*;
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
        SMBlocks.AUTO_TRANSLATE.forEach(this::forBlock);

        addBlock(SMBlocks.JADE_BLOCK, "Block of Jade");
        addBlock(SMBlocks.ROUGH_JADE_BLOCK, "Block of Rough Jade");

        //Items
        //SMItems.ARTIFACT_DESC_MAP.forEach(this::forItem);
        SMItems.AUTO_TRANSLATE.forEach(this::forItem);
        add(SMItems.VENOM_VIAL.get(), "Vial of Jungle Venom");
        add(SMItems.LANTERNFISH_BUCKET.get(), "Bucket of Lanternfish");
        add(SMItems.PIRANHA_BUCKET.get(), "Bucket of Piranha");
        add(SMItems.LANTERNFISH.get(), "Raw Lanternfish");
        add(SMItems.PIRANHA.get(), "Raw Piranha");

        addMusicDisc(SMItems.MUSIC_DISC_SCOUR, "LudoCrypt - scour");
        addMusicDisc(SMItems.MUSIC_DISC_SUNKEN_PAST, "_TurtleFood_ - Sunken Past");

        //Entities
        forEntity(SMEntityTypes.LANTERNFISH);
        forEntity(SMEntityTypes.TORTOISE);
        forEntity(SMEntityTypes.TORTOISE_SHELL);
        forEntity(SMEntityTypes.BOULDERING_ZOMBIE);
        forEntity(SMEntityTypes.JUNGLE_SPIDER);
        forEntity(SMEntityTypes.PIRANHA);

        //Potions
        SMPotions.POTION_TRANSLATIONS.forEach(this::addPotionsForEffect);

        //Subtitles
        add("subtitles.item.vial.shatter", "Vial shatters");
        add("subtitles.item.vial.fill", "Vial fills");

        add("subtitles.block.grindstone.polish_jade", "Grindstone polishes");
        add("subtitles.block.jade.ricochet", "Projectile ricochets");
        add("subtitles.item.jade_shield.ricochet", "Projectile ricochets");
        add("subtitles.block.flinger_totem.shoot", "Projectile flung");
        add("subtitles.block.flinger_totem.input_honey", "Projectile absorbed");
        add("subtitles.block.flinger_totem.add_honey", "Honey applied");
        add("subtitles.block.flinger_totem.reduce_honey", "Honey removed");
        add("subtitles.block.ancient_skull.crested", "Crested Skull echoes");
        add("subtitles.block.ancient_skull.cracked", "Proper Sound soon...");
        add("subtitles.block.ancient_skull.flatbilled", "Proper Sound soon...");
        add("subtitles.block.ancient_skull.gigantic", "Proper Sound soon...");
        add("subtitles.block.ancient_skull.horned", "Proper Sound soon...");
        add("subtitles.block.ancient_skull.long", "Proper Sound soon...");
        add("subtitles.block.ancient_skull.tiny", "Proper Sound soon...");
        add("subtitles.block.ancient_skull.wide", "Proper Sound soon...");
        add("subtitles.block.ancient_skull.ribbed", "Proper Sound soon...");
        add("subtitles.block.ancient_skull.unicorn", "Proper Sound soon...");
        add("subtitles.block.amber.drip", "Amber drips");

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

        add("subtitles.entity.piranha.flop", "Piranha flops");
        add("subtitles.entity.piranha.hurt", "Piranha hurts");
        add("subtitles.entity.piranha.death", "Piranha dies");

        add("subtitles.entity.bouldering_zombie.ambient", "Bouldering Zombie groans");
        add("subtitles.entity.bouldering_zombie.hurt", "Bouldering Zombie hurts");
        add("subtitles.entity.bouldering_zombie.death", "Bouldering Zombie dies");

        add("subtitles.item.throwing_knife.hit", "Knife hits entity");
        add("subtitles.item.throwing_knife.hit_ground", "Knife hits ground");
        add("subtitles.item.throwing_knife.throw", "Knife flies");
        add("subtitles.ambient.mountain.calls", "Echoes in the distance");
        add("subtitles.item.armor.equip_miners_helmet", "Miners Helmet clanks");
        add("subtitles.item.armor.equip_small_dented_helmet", "Small Dented Helmet clangs");
        add("subtitles.item.armor.equip_lost_crown", "Lost Crown clings");

        //Other
        SMTextUtil.TRANSLATABLES.forEach(this::add);
        SMPaintingVariants.PAINTING_TRANSLATIONS.forEach(this::addPainting);
        add("death.attack.tortoise_shell", "%1$s took a Tortoise Shell to the knees");
        add("death.attack.tortoise_shell.player", "%1$s got bonked to death");
        add("death.attack.throwing_knife", "%1$s got sliced and diced");
        add("death.attack.throwing_knife.player", "%1$s was turned into a dart board by %2$s");

        add("fluid_type.sullysmod.molten_amber_fluid", "Molten Amber");
    }

    protected void addPainting(String name, String author) {
        add("painting." + SullysMod.MOD_ID + "." + name + ".title",  SMTextUtil.createTranslation(name));
        add("painting." + SullysMod.MOD_ID + "." + name + ".author",  author);
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