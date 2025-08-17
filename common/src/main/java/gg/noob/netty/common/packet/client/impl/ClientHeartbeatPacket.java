package gg.noob.netty.common.packet.client.impl;

import gg.noob.netty.common.packet.client.ClientPacket;
import gg.noob.netty.common.packet.client.ClientPacketType;
import io.netty.buffer.ByteBuf;

public class ClientHeartbeatPacket extends ClientPacket {

    public long time = System.currentTimeMillis();

    public ClientHeartbeatPacket() {
        super(ClientPacketType.HEARTBEAT);
    }

    @Override
    public void encode(ByteBuf out) {
        out.writeLong(time);
    }

    public static ClientHeartbeatPacket decode(ByteBuf in) {
        ClientHeartbeatPacket packet = new ClientHeartbeatPacket();

        packet.time = in.readLong();

        return packet;
    }
}
