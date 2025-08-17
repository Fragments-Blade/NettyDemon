package gg.noob.netty.server.connection;

import java.net.InetSocketAddress;
import java.util.UUID;

public class User {

    public UUID uuid;
    public String name;
    public InetSocketAddress address;

    public User(UUID uuid, String name, InetSocketAddress address) {
        this.uuid = uuid;
        this.name = name;
        this.address = address;
    }

}
