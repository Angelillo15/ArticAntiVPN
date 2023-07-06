package es.angelillo15.artic.antivpn.api.database;

import com.craftmend.storm.Storm;
import com.craftmend.storm.connection.hikaricp.HikariDriver;
import com.craftmend.storm.connection.sqlite.SqliteFileDriver;
import com.craftmend.storm.dialect.mariadb.MariaDialect;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import es.angelillo15.artic.antivpn.api.AntiVPNInstance;
import es.angelillo15.artic.antivpn.api.TextUtils;
import es.angelillo15.artic.antivpn.api.models.IPModel;
import es.angelillo15.artic.antivpn.api.models.WhiteListPlayers;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PluginConnection {
    @Getter
    private static Connection connection;
    @Getter
    private static DataProvider dataProvider;
    private static HikariConfig config;
    @Getter
    private static Storm storm;
    private static HikariDataSource dataSource;
    @Getter
    private static PluginConnection instance;
    @Getter
    private Connection conn;

    @SneakyThrows
    public PluginConnection(String host, int port, String database, String user, String password){
        dataProvider = DataProvider.MYSQL;
        config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useUnicode=yes");
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(20);
        config.setConnectionTimeout(30000);
        config.setLeakDetectionThreshold(0);
        dataSource = new HikariDataSource(config);

        storm = new Storm(new PluginDriver(dataSource));

        try {
            connection = dataSource.getConnection();
            conn = connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        instance = this;

        migrate();
    }

    public PluginConnection(String pluginPath){
        dataProvider = DataProvider.SQLITE;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            AntiVPNInstance.getLogger().error((TextUtils.colorize("&c┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓")));
            AntiVPNInstance.getLogger().error((TextUtils.colorize("&c┃ The SQLite driver couldn't be found!                                     ┃")));
            AntiVPNInstance.getLogger().error((TextUtils.colorize("&c┃                                                                          ┃")));
            AntiVPNInstance.getLogger().error((TextUtils.colorize("&c┃ Please, join our Discord server to get support:                          ┃")));
            AntiVPNInstance.getLogger().error((TextUtils.colorize("&c┃ https://discord.nookure.com                                              ┃")));
            AntiVPNInstance.getLogger().error((TextUtils.colorize("&c┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛")));
        }
        try {
            String dataPath = pluginPath + "/database.db";
            String url = "jdbc:sqlite:" + dataPath;
            connection = DriverManager.getConnection(url);
            conn = connection;

            storm = new Storm(new SqliteFileDriver(new File(dataPath)));

        } catch (SQLException e) {
            AntiVPNInstance.getLogger().error("An error ocurred while trying to connect to the SQLite database: " + e.getMessage());
        }

        instance = this;

        migrate();
    }

    @SneakyThrows
    public void migrate() {
        Storm storm = PluginConnection.getStorm();

        storm.registerModel(new IPModel());
        storm.runMigrations();
        storm.registerModel(new WhiteListPlayers());
        storm.runMigrations();
    }

    public static boolean tableExists(String table) {
        try {
            return connection.getMetaData().getTables(null, null, table, null).next();
        } catch (SQLException e) {
            AntiVPNInstance.getLogger().error("An error ocurred while trying to check if the table " + table + " exists: " + e.getMessage());
            return false;
        }
    }
}
