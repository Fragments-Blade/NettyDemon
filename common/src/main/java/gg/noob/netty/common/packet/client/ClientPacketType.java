package gg.noob.netty.common.packet.client;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ClientPacketType {

    HANDSHAKE(0x00),
    HEARTBEAT(0x01);

    public int id;

}
