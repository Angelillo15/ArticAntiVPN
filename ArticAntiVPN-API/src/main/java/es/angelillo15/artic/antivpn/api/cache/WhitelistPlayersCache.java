package es.angelillo15.artic.antivpn.api.cache;

import com.craftmend.storm.Storm;
import com.craftmend.storm.api.enums.Where;
import es.angelillo15.artic.antivpn.api.database.PluginConnection;
import es.angelillo15.artic.antivpn.api.models.WhiteListPlayers;
import lombok.Getter;

import java.sql.SQLException;
import java.util.ArrayList;

public class WhitelistPlayersCache {
    @Getter
    private static ArrayList<String> whitelistPlayers = new ArrayList<>();

    public static void add(String key) {
        whitelistPlayers.add(key);

        new Thread(() -> {
            Storm storm = PluginConnection.getStorm();

            WhiteListPlayers whiteListPlayers = new WhiteListPlayers();
            whiteListPlayers.setKey(key);

            try {
                storm.save(whiteListPlayers);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void remove(String key) {
        whitelistPlayers.remove(key);

        new Thread(() -> {
            Storm storm = PluginConnection.getStorm();

            try {
                storm.buildQuery(WhiteListPlayers.class)
                        .where("key", Where.EQUAL, key)
                        .execute()
                        .thenAccept(query -> {
                            for (WhiteListPlayers whiteListPlayers : query) {
                                try {
                                    storm.delete(whiteListPlayers);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public boolean contains(String key) {
        return whitelistPlayers.contains(key);
    }

    public static void loadWhitelistPlayers() {
        new Thread(() -> {
            Storm storm = PluginConnection.getStorm();

            try {
                storm.buildQuery(WhiteListPlayers.class)
                        .execute()
                        .thenAccept(query -> {
                            for (WhiteListPlayers whitelistPlayersCache : query) {
                                whitelistPlayers.add(whitelistPlayersCache.getKey());
                            }
                        });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
