package gg.noob.netty.common.packet;

import gg.noob.netty.common.packet.client.*;
import gg.noob.netty.common.packet.client.impl.*;
import gg.noob.netty.common.packet.server.*;
import gg.noob.netty.common.packet.server.impl.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class PacketFactory {

    public static Map<Integer, ClientPacketType> clientCache = new HashMap<>();
    public static Map<Integer, ServerPacketType> serverCache = new HashMap<>();

    static {
        for (ClientPacketType type : ClientPacketType.values()) {
            clientCache.put(type.id, type);
        }

        for (ServerPacketType type : ServerPacketType.values()) {
            serverCache.put(type.id, type);
        }
    }

    public static <T extends ClientPacket> T decodeByClient(ByteBuf in) {
        ClientPacketType type = clientCache.get(in.readInt());
        return switch (type) {
            case ClientPacketType.HANDSHAKE -> (T) ClientHandshakePacket.decode(in);
            case ClientPacketType.HEARTBEAT -> (T) ClientHeartbeatPacket.decode(in);
        };

    }

    public static <T extends ServerPacket> T decodeByServer(ByteBuf in) {
        ServerPacketType type = serverCache.get(in.readInt());
        return switch (type) {
            case ServerPacketType.HEARTBEAT -> (T) ServerHeartbeatPacket.decode(in);
        };
    }

    public static void sendPacketByClient(Channel channel, InetSocketAddress recipient, ClientPacket packet) {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(packet.type.id);
        packet.encode(buffer);

        DatagramPacket nettyPacket = new DatagramPacket(buffer, recipient);
        channel.writeAndFlush(nettyPacket);
    }

    public static void sendPacketByServer(Channel channel, InetSocketAddress recipient, ServerPacket packet) {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(packet.type.id);
        packet.encode(buffer);

        DatagramPacket nettyPacket = new DatagramPacket(buffer, recipient);
        channel.writeAndFlush(nettyPacket);
    }

}
