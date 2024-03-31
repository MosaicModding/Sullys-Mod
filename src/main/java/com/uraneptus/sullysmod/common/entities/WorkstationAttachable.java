package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.client.sound.FollowJukeboxEntitySoundInstance;
import com.uraneptus.sullysmod.core.other.SMItemUtil;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public interface WorkstationAttachable {
    ItemStack getAppliedWorkstation();
    void setAppliedWorkstation(ItemStack itemStack);
    boolean hasAppliedWorkstation();
    default boolean isCraftingTable() {
        return getAppliedWorkstation().is(SMItemTags.CRAFTING_TABLES);
    }
    default boolean isJukebox() {
        return getAppliedWorkstation().is(SMItemTags.JUKEBOXES);
    }
    ItemStack getRecordItem();
    void setRecordItem(ItemStack itemStack);
    FollowJukeboxEntitySoundInstance getSoundInstance();
    void setSoundInstance(FollowJukeboxEntitySoundInstance itemStack);

    default void addSaveData(@NotNull CompoundTag nbt) {
        nbt.put("AppliedWorkstation", this.getAppliedWorkstation().save(new CompoundTag()));
        nbt.put("RecordItem", this.getRecordItem().save(new CompoundTag()));
    }

    default void readSaveData(@NotNull CompoundTag nbt) {
        this.setAppliedWorkstation(ItemStack.of(nbt.getCompound("AppliedWorkstation")));
        this.setRecordItem(ItemStack.of(nbt.getCompound("RecordItem")));
    }

    InteractionResult customInteraction(Player pPlayer, @NotNull InteractionHand pHand);

    //It was hell to handle this code for both entities. It works now, and I never want to touch this shit again
    default InteractionResult workstationInteraction(Player pPlayer, @NotNull InteractionHand pHand, Entity entity) {
        boolean flag = false;
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);
        if (hasAppliedWorkstation()) {
            if (pPlayer.isShiftKeyDown()) {
                if (itemInHand.is(ItemTags.AXES)) {
                    if (isJukebox() && !getRecordItem().isEmpty()) {
                        if (!entity.level().isClientSide()) {
                            pPlayer.addItem(getRecordItem());
                            setRecordItem(ItemStack.EMPTY);
                        } else {
                            Minecraft.getInstance().getSoundManager().stop(getSoundInstance());
                        }
                    }
                    SMItemUtil.nonCreativeAddItems(pPlayer, new ItemStack(this.getAppliedWorkstation().getItem()));
                    setAppliedWorkstation(ItemStack.EMPTY);
                    entity.refreshDimensions();
                    return InteractionResult.sidedSuccess(entity.level().isClientSide());
                }
                flag = true;
            } else {
                if (isCraftingTable()) {
                    if (!entity.level().isClientSide()) {
                        this.openCraftingMenu((ServerPlayer)pPlayer, entity);
                        pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
                        return InteractionResult.CONSUME;
                    }
                    return InteractionResult.SUCCESS;
                } else if (isJukebox()) {
                    if (itemInHand.isEmpty()) {
                        if (!getRecordItem().isEmpty()) {
                            if (!entity.level().isClientSide()) {
                                pPlayer.addItem(getRecordItem());
                                setRecordItem(ItemStack.EMPTY);
                            } else {
                                Minecraft.getInstance().getSoundManager().stop(getSoundInstance());
                            }
                            return InteractionResult.SUCCESS;
                        }
                    } else if (itemInHand.getItem() instanceof RecordItem recordItem) {
                        if (getRecordItem().isEmpty()) {
                            setRecordItem(recordItem.getDefaultInstance());
                            SMItemUtil.nonCreativeShrinkStack(pPlayer, itemInHand);
                            setSoundInstance(new FollowJukeboxEntitySoundInstance(entity, recordItem.getSound()));

                            if (entity.level().isClientSide()) {
                                Minecraft mc = Minecraft.getInstance();
                                mc.gui.setNowPlaying(recordItem.getDisplayName());
                                mc.getSoundManager().queueTickingSound(getSoundInstance());
                            }
                            return InteractionResult.sidedSuccess(entity.level().isClientSide());
                        }
                    }
                    return InteractionResult.sidedSuccess(entity.level().isClientSide());
                }
            }
        } else if (itemInHand.is(SMItemTags.CRAFTING_TABLES) || itemInHand.is(SMItemTags.JUKEBOXES)) {
            setAppliedWorkstation(itemInHand.copy());
            entity.refreshDimensions();
            if (!pPlayer.isCreative()) {
                itemInHand.shrink(1);
                return InteractionResult.sidedSuccess(entity.level().isClientSide());
            }
            return InteractionResult.SUCCESS;
        } else {
            flag = true;
        }

        if (flag) {
            return customInteraction(pPlayer, pHand);
        }

        return InteractionResult.PASS;
    }

    default void openCraftingMenu(ServerPlayer player, Entity entity) {
        if (player.containerMenu != player.inventoryMenu) {
            player.closeContainer();
        }
        NetworkHooks.openScreen(player, new SimpleMenuProvider((id, inventory, mPlayer) -> new CraftingMenu(id, inventory, ContainerLevelAccess.create(entity.level(), entity.blockPosition())) {
            @Override
            public boolean stillValid(Player pPlayer) {
                return true;
            }
        }, entity.getName().copy().append(" Crafting")));
    }

    default void handleServerRemoval(Entity entity) {
        if (this.hasAppliedWorkstation()) {
            entity.spawnAtLocation(new ItemStack(getAppliedWorkstation().getItem()));

            if (!this.getRecordItem().isEmpty()) {
                entity.spawnAtLocation(new ItemStack(getRecordItem().getItem()));
                setRecordItem(ItemStack.EMPTY);
            }
        }
    }
}