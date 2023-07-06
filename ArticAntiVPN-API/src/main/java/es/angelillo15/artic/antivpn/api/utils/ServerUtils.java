package es.angelillo15.artic.antivpn.api.utils;

public class ServerUtils {
    private static ServerType serverType;
    public static ServerType getServerType() {
        if (serverType != null) {
            return serverType;
        }

        try {
            Class.forName("org.bukkit.Bukkit");
            serverType = ServerType.BUKKIT;
            return ServerType.BUKKIT;
        } catch (ClassNotFoundException e) {
            serverType = ServerType.BUNGEE;
            return ServerType.BUNGEE;
        }
    }
    public enum ServerType {
        BUKKIT,
        BUNGEE
    }
}
