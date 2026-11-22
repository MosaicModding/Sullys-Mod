package com.mosaicmodding.sullysmod.content.artifacts;

import com.google.common.collect.ImmutableList;
import com.mosaicmodding.sullysmod.registry.properties.SMItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.List;

public class DeathWhistleItem extends Item {
    private final List<SoundEvent> DEATH_SOUNDS;
    private final int soundAmount;

    public DeathWhistleItem() {
        super(SMItemProperties.ARTIFACT());

        DEATH_SOUNDS = ImmutableList.copyOf(
                BuiltInRegistries.SOUND_EVENT.stream()
                        .filter(soundEvent -> soundEvent.getLocation().getPath().contains("death"))
                        .toList()
        );
        soundAmount = DEATH_SOUNDS.size();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        if (!level.isClientSide()) {
            SoundEvent soundevent = DEATH_SOUNDS.get(player.getRandom().nextInt(this.soundAmount));
            level.playSound(null, player, soundevent, SoundSource.RECORDS, 16.0F, 1.0F);
        }
        level.gameEvent(GameEvent.INSTRUMENT_PLAY, player.position(), GameEvent.Context.of(player));
        player.getCooldowns().addCooldown(this, 60);
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.consume(itemstack);
    }
}
