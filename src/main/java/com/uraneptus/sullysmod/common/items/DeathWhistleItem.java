package com.uraneptus.sullysmod.common.items;

import com.google.common.collect.ImmutableList;
import com.uraneptus.sullysmod.core.other.SMProperties;
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
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.stream.Collectors;

public class DeathWhistleItem extends Item {
    private final List<SoundEvent> DEATH_SOUNDS = ImmutableList.copyOf(
            ForgeRegistries.SOUND_EVENTS.getValues().stream()
                    .filter(soundEvent -> soundEvent.getLocation().getPath().contains("death"))
                    .collect(Collectors.toList())
    );

    public DeathWhistleItem() {
        super(SMProperties.Items.artifacts());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        SoundEvent soundevent = DEATH_SOUNDS.get(pPlayer.getRandom().nextInt(DEATH_SOUNDS.size()));
        pLevel.playSound(pPlayer, pPlayer, soundevent, SoundSource.RECORDS, 16.0F, 1.0F);
        pLevel.gameEvent(GameEvent.INSTRUMENT_PLAY, pPlayer.position(), GameEvent.Context.of(pPlayer));
        pPlayer.getCooldowns().addCooldown(this, 60);
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.consume(itemstack);
    }
}
