package gg.noob.netty.common.packet.server.impl;

import gg.noob.netty.common.packet.server.ServerPacket;
import gg.noob.netty.common.packet.server.ServerPacketType;
import io.netty.buffer.ByteBuf;

public class ServerHeartbeatPacket extends ServerPacket {

    public long time = System.currentTimeMillis();

    public ServerHeartbeatPacket() {
        super(ServerPacketType.HEARTBEAT);
    }

    @Override
    public void encode(ByteBuf out) {
        out.writeLong(time);
    }

    public static ServerHeartbeatPacket decode(ByteBuf in) {
        ServerHeartbeatPacket packet = new ServerHeartbeatPacket();

        packet.time = in.readLong();

        return packet;
    }
}
