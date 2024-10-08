package com.uraneptus.sullysmod.common.networking;

import com.uraneptus.sullysmod.common.caps.SMEntityCap;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MsgEntityAmberStuck {
    private int entityId;
    private boolean stuckInAmber;

    public MsgEntityAmberStuck() {}

    public MsgEntityAmberStuck(Entity entity, boolean stuckInAmber) {
        this.entityId = entity.getId();
        this.stuckInAmber = stuckInAmber;
    }

    public static void serialize(final MsgEntityAmberStuck msg, final FriendlyByteBuf buf) {
        buf.writeVarInt(msg.entityId);
        buf.writeBoolean(msg.stuckInAmber);
    }

    public static MsgEntityAmberStuck deserialize(final FriendlyByteBuf buf) {
        final MsgEntityAmberStuck msg = new MsgEntityAmberStuck();
        msg.entityId = buf.readVarInt();
        msg.stuckInAmber = buf.readBoolean();
        return msg;
    }

    public static void handle(MsgEntityAmberStuck msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
            context.enqueueWork(() -> {
                Minecraft mc = Minecraft.getInstance();
                if (mc.level != null) {
                    Entity entity = mc.level.getEntity(msg.entityId);
                    if (entity instanceof LivingEntity livingEntity) {
                        SMEntityCap.getCapOptional(livingEntity).ifPresent(cap -> {
                            cap.stuckInAmber = msg.stuckInAmber;
                        });
                    }
                }
            });
        }
        context.setPacketHandled(true);
    }
}
