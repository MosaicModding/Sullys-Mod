package com.uraneptus.sullysmod.common.network;

import com.uraneptus.sullysmod.common.entities.Tortoise;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record CraftingMenuFromTortoiseMessage(int entityId, int windowId) {

    public static void serialize(CraftingMenuFromTortoiseMessage message, FriendlyByteBuf buffer) {
        buffer.writeVarInt(message.entityId);
        buffer.writeVarInt(message.windowId);
    }

    public static CraftingMenuFromTortoiseMessage deserialize(FriendlyByteBuf buffer) {
        return new CraftingMenuFromTortoiseMessage(buffer.readVarInt(), buffer.readVarInt());
    }

    public static void handle(CraftingMenuFromTortoiseMessage message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;

            if (player != null) {
                Level world = player.level();
                Entity entity = world.getEntity(message.entityId());

                if (entity instanceof Tortoise) {
                    CraftingMenu container = new CraftingMenu(message.windowId(), player.getInventory());
                    player.containerMenu = container;
                    Minecraft.getInstance().setScreen(new CraftingScreen(container, player.getInventory(), Component.translatable("container.crafting")));
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int entityId() {
        return entityId;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int windowId() {
        return windowId;
    }
}
