package gg.noob.netty.common.packet.client.impl;

import gg.noob.netty.common.packet.client.ClientPacket;
import gg.noob.netty.common.packet.client.ClientPacketType;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ClientHandshakePacket extends ClientPacket {

    public UUID uuid;
    public String username;
    public long time = System.currentTimeMillis();

    public ClientHandshakePacket() {
        super(ClientPacketType.HANDSHAKE);
    }

    public ClientHandshakePacket(UUID uuid, String username) {
        super(ClientPacketType.HANDSHAKE);

        this.uuid = uuid;
        this.username = username;
    }

    public void encode(ByteBuf out) {
        out.writeLong(time);
        byte[] usernameBytes = username.getBytes(StandardCharsets.UTF_8);
        out.writeShort(usernameBytes.length);
        out.writeBytes(usernameBytes);
        out.writeLong(uuid.getMostSignificantBits());
        out.writeLong(uuid.getLeastSignificantBits());
    }

    public static ClientHandshakePacket decode(ByteBuf in) {
        ClientHandshakePacket packet = new ClientHandshakePacket();

        packet.time = in.readLong();
        packet.username = in.readCharSequence(in.readShort(), StandardCharsets.UTF_8).toString();
        packet.uuid = new UUID(in.readLong(), in.readLong());

        return packet;
    }

}
