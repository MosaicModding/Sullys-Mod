package com.uraneptus.sullysmod.common.networking;

import com.uraneptus.sullysmod.SullysMod;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class SMPacketHandler {
    private static SimpleChannel INSTANCE;
    private static int id = 0;

    public static void register() {
        final String protocolVersion = "1";
        INSTANCE = NetworkRegistry.ChannelBuilder.named(SullysMod.modPrefix("messages"))
                .networkProtocolVersion(() -> protocolVersion)
                .clientAcceptedVersions(protocolVersion::equals)
                .serverAcceptedVersions(protocolVersion::equals)
                .simpleChannel();

        INSTANCE.registerMessage(id++, MsgEntityAmberStuck.class, MsgEntityAmberStuck::serialize, MsgEntityAmberStuck::deserialize, MsgEntityAmberStuck::handle);
    }

    public static <MSG> void sendMsg(PacketDistributor.PacketTarget target, MSG message) {
        INSTANCE.send(target, message);
    }
}
