package gg.noob.netty.common.packet.client;

import io.netty.buffer.ByteBuf;

public abstract class ClientPacket {

    public ClientPacketType type;

    public ClientPacket(ClientPacketType type) {
        this.type = type;
    }

    public abstract void encode(ByteBuf out);

}
