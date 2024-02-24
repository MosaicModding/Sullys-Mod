package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.core.other.SMItemUtil;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
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

    default void addWorkstationSaveData(@NotNull CompoundTag nbt) {
        CompoundTag compoundTag = new CompoundTag();
        this.getAppliedWorkstation().save(compoundTag);
        nbt.put("AppliedWorkstation", compoundTag);
    }

    default void readWorkstationSaveData(@NotNull CompoundTag nbt) {
        this.setAppliedWorkstation(ItemStack.of(nbt.getCompound("AppliedWorkstation")));
    }

    InteractionResult customInteraction(Player pPlayer, @NotNull InteractionHand pHand);

    //It was hell to handle this code for both entities. It works now, and I never want to touch this shit again
    default InteractionResult workstationInteraction(Player pPlayer, @NotNull InteractionHand pHand, Entity entity) {
        boolean flag = false;
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);
        if (hasAppliedWorkstation()) {
            if (pPlayer.isShiftKeyDown()) {
                if (itemInHand.is(ItemTags.AXES)) {
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
                    //handle disc actions here
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
}
