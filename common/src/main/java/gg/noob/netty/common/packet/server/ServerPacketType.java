package gg.noob.netty.common.packet.server;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ServerPacketType {

    HEARTBEAT(0x00);

    public int id;

}
