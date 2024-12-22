package com.uraneptus.sullysmod.common.entities;

import com.uraneptus.sullysmod.client.sound.FollowJukeboxEntitySoundInstance;
import com.uraneptus.sullysmod.core.other.SMItemUtil;
import com.uraneptus.sullysmod.core.other.tags.SMItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
    long getRecordTickCount();
    void setRecordTickCount(long tickCount);
    long getRecordStartedTick();
    void setRecordStartedTick(long startedTick);
    boolean isRecordPlaying();
    void setRecordPlaying(boolean isPlaying);
    int getTicksSinceLastEvent();
    void setTicksSinceLastEvent(int ticksSinceLastEvent);

    default void addSaveData(@NotNull CompoundTag nbt) {
        nbt.put("AppliedWorkstation", this.getAppliedWorkstation().save(new CompoundTag()));
        nbt.put("RecordItem", this.getRecordItem().save(new CompoundTag()));
        nbt.putBoolean("IsPlaying", this.isRecordPlaying());
        nbt.putLong("RecordStartTick", this.getRecordStartedTick());
        nbt.putLong("TickCount", this.getRecordTickCount());
    }

    default void readSaveData(@NotNull CompoundTag nbt) {
        this.setAppliedWorkstation(ItemStack.of(nbt.getCompound("AppliedWorkstation")));
        this.setRecordItem(ItemStack.of(nbt.getCompound("RecordItem")));
        this.setRecordPlaying(nbt.getBoolean("IsPlaying"));
        this.setRecordStartedTick(nbt.getLong("RecordStartTick"));
        this.setRecordTickCount(nbt.getLong("TickCount"));
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
                            this.setRecordPlaying(false);
                            this.setRecordTickCount(0);
                            this.setTicksSinceLastEvent(0);
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
                                this.setRecordPlaying(false);
                                this.setRecordTickCount(0);
                                this.setTicksSinceLastEvent(0);
                            }
                            return InteractionResult.sidedSuccess(entity.level().isClientSide());
                        }
                    } else if (itemInHand.getItem() instanceof RecordItem recordItem) {
                        if (getRecordItem().isEmpty()) {
                            setRecordItem(recordItem.getDefaultInstance());
                            SMItemUtil.nonCreativeShrinkStack(pPlayer, itemInHand);
                            this.setRecordStartedTick(this.getRecordTickCount());
                            this.setRecordPlaying(true);
                            if (entity.level().isClientSide()) {
                                startRecordPlaying(entity, recordItem);
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

    @OnlyIn(Dist.CLIENT)
    default void startRecordPlaying(Entity entity, RecordItem recordItem) {
        Minecraft mc = Minecraft.getInstance();
        mc.getSoundManager().queueTickingSound(new FollowJukeboxEntitySoundInstance(entity, recordItem.getSound()));
        mc.gui.setNowPlaying(recordItem.getDisplayName());
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

    default void handleJukeboxTick(Entity entity, Level level) {
        if (!isJukebox()) return;
        BlockPos pos = entity.blockPosition();
        this.setTicksSinceLastEvent(1 + this.getTicksSinceLastEvent());
        if (!this.getRecordItem().isEmpty() && this.isRecordPlaying()) {
            if (this.getRecordItem().getItem() instanceof RecordItem recorditem) {
                if (this.getRecordTickCount() >= this.getRecordStartedTick() + (long)recorditem.getLengthInTicks() + 20L) {
                    this.setRecordPlaying(false);
                    level.gameEvent(GameEvent.JUKEBOX_STOP_PLAY, pos, GameEvent.Context.of(entity));
                    level.levelEvent(1011, pos, 0);
                } else if (getTicksSinceLastEvent() >= 20) {
                    this.setTicksSinceLastEvent(0);
                    level.gameEvent(GameEvent.JUKEBOX_PLAY, pos, GameEvent.Context.of(entity));
                    if (level instanceof ServerLevel serverlevel) {
                        Vec3 vec3 = Vec3.atBottomCenterOf(pos).add(0.0D, 1.2F, 0.0D);
                        float xOffset = (float)level.getRandom().nextInt(4) / 24.0F;
                        serverlevel.sendParticles(ParticleTypes.NOTE, vec3.x(), vec3.y(), vec3.z(), 0, xOffset, 0.0D, 0.0D, 1.0D);
                    }
                }
            }
        }
        this.setRecordTickCount(1 + getRecordTickCount());
    }

    default void handleServerRemoval(Entity entity) {
        if (this.hasAppliedWorkstation()) {
            entity.spawnAtLocation(new ItemStack(getAppliedWorkstation().getItem()));

            if (!this.getRecordItem().isEmpty()) {
                entity.spawnAtLocation(new ItemStack(getRecordItem().getItem()));
                setRecordItem(ItemStack.EMPTY);
            }
            this.setRecordPlaying(false);
            this.setRecordTickCount(0);
            this.setTicksSinceLastEvent(0);
        }
    }
}