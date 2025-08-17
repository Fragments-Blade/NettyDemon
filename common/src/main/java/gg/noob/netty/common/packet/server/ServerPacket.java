package gg.noob.netty.common.packet.server;

import io.netty.buffer.ByteBuf;

public abstract class ServerPacket {

    public ServerPacketType type;

    public ServerPacket(ServerPacketType type) {
        this.type = type;
    }

    public abstract void encode(ByteBuf out);

}
