package gg.noob.netty.server;

import gg.noob.netty.server.connection.UserManager;
import lombok.Getter;

public class NettyServer {

    @Getter
    private static NettyServer instance;
    @Getter
    private UserManager userManager = new UserManager();

    public NettyServer() {
        instance = this;
    }

}
