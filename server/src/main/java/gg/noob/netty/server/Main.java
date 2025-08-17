package gg.noob.netty.server;

import gg.noob.netty.common.Common;
import gg.noob.netty.common.packet.PacketFactory;
import gg.noob.netty.common.packet.server.impl.ServerHeartbeatPacket;
import gg.noob.netty.server.connection.User;
import gg.noob.netty.server.connection.UserManager;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        Common common = new Common();
        NettyServer nettyServer = new NettyServer();
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        System.out.printf("Hello and welcome!");

        try {
            Bootstrap b = new Bootstrap();
            NioEventLoopGroup group = new NioEventLoopGroup();

            try {
                b.group(group)
                        .channel(NioDatagramChannel.class)
                        .option(ChannelOption.SO_BROADCAST, true) // 可选：启用广播
                        .handler(new ServerHandler());

                ChannelFuture f = b.bind(8888).sync();
                System.out.println("UDP 服务器启动在端口 8888");
                // 启动心跳定时器
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        for (User user : NettyServer.getInstance().getUserManager().userByUUID.values()) {
                            PacketFactory.sendPacketByServer(f.channel(), user.address, new ServerHeartbeatPacket());
                            System.out.println("心跳包发送至" + user.name);
                        }
                    }
                }, 0, 5000);
                f.channel().closeFuture().await(); // 阻塞直到关闭
            } finally {
                group.shutdownGracefully();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (int i = 1; i <= 5; i++) {
            //TIP 按 <shortcut actionId="Debug"/> 开始调试代码。我们已经设置了一个 <icon src="AllIcons.Debugger.Db_set_breakpoint"/> 断点
            // 但您始终可以通过按 <shortcut actionId="ToggleLineBreakpoint"/> 添加更多断点。
            System.out.println("i = " + i);
        }
    }
}