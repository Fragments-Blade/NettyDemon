package gg.noob.netty.server;

import gg.noob.netty.common.packet.PacketFactory;
import gg.noob.netty.common.packet.client.ClientPacket;
import gg.noob.netty.common.packet.client.impl.ClientHandshakePacket;
import gg.noob.netty.common.packet.client.impl.ClientHeartbeatPacket;
import gg.noob.netty.common.packet.server.ServerPacket;
import gg.noob.netty.server.connection.User;
import gg.noob.netty.server.connection.UserManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        ByteBuf byteBuf = packet.content();

        ClientPacket clientPacket = PacketFactory.decodeByClient(byteBuf);

        if (clientPacket instanceof ClientHeartbeatPacket) {
            System.out.println("收到客户端心跳包: " + ((ClientHeartbeatPacket) clientPacket).time);
        } else if (clientPacket instanceof ClientHandshakePacket) {
            ClientHandshakePacket handshakePacket = (ClientHandshakePacket) clientPacket;
            System.out.println("收到客户端握手包: " + handshakePacket.username);
            UserManager userManager = NettyServer.getInstance().getUserManager();
            User user = userManager.getUserByUUID(handshakePacket.uuid);

            if (user == null) {
                user = new User(handshakePacket.uuid, handshakePacket.username, packet.sender());

                userManager.addUser(user);
            } else {
                userManager.removeUser(user);
            }
        }
    }

}
