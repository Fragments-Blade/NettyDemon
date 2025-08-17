package gg.noob.netty.server.connection;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {

    public Map<UUID, User> userByUUID = new ConcurrentHashMap<>();

    public User getUserByUUID(UUID uuid) {
        return userByUUID.get(uuid);
    }

    public void addUser(User user) {
        userByUUID.put(user.uuid, user);
    }

    public void removeUser(User user) {
        userByUUID.remove(user.uuid);
    }

}
