package gg.noob.netty.client;

import gg.noob.netty.common.packet.PacketFactory;
import gg.noob.netty.common.packet.client.impl.ClientHeartbeatPacket;
import gg.noob.netty.common.packet.server.ServerPacket;
import gg.noob.netty.common.packet.server.impl.ServerHeartbeatPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class ClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        ByteBuf byteBuf = packet.content();

        ServerPacket serverPacket = PacketFactory.decodeByServer(byteBuf);

        if (serverPacket instanceof ServerHeartbeatPacket) {
            System.out.println("收到服务器心跳包: " + ((ServerHeartbeatPacket) serverPacket).time);

            PacketFactory.sendPacketByClient(ctx.channel(), Main.getAddress(), new ClientHeartbeatPacket());
        }

    }
}
