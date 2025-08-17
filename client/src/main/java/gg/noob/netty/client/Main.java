package gg.noob.netty.client;

import gg.noob.netty.common.Common;


import gg.noob.netty.common.packet.PacketFactory;
import gg.noob.netty.common.packet.client.impl.ClientHandshakePacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.util.UUID;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 8888;
    public static final String CLIENT_IP = "127.0.0.1";
    public static final int CLIENT_PORT = 9999;

    public static void main(String[] args) {
        Common common = new Common();
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        System.out.printf("Hello and welcome!");

        try {
            Bootstrap bootstrap = new Bootstrap();
            NioEventLoopGroup group = new NioEventLoopGroup();

            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, false)
                    .handler(new ClientHandler());

            Channel channel = bootstrap.bind(CLIENT_PORT).sync().channel();

            PacketFactory.sendPacketByClient(channel, getAddress(), new ClientHandshakePacket(UUID.randomUUID(), "RareMen"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (int i = 1; i <= 5; i++) {
            //TIP 按 <shortcut actionId="Debug"/> 开始调试代码。我们已经设置了一个 <icon src="AllIcons.Debugger.Db_set_breakpoint"/> 断点
            // 但您始终可以通过按 <shortcut actionId="ToggleLineBreakpoint"/> 添加更多断点。
            System.out.println("i = " + i);
        }
    }

    public static InetSocketAddress getAddress() {
        return new InetSocketAddress(SERVER_IP, SERVER_PORT);
    }
}