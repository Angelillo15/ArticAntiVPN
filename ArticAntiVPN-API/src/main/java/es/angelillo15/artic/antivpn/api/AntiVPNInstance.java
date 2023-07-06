package es.angelillo15.artic.antivpn.api;

import es.angelillo15.artic.antivpn.api.exceptions.PluginNotLoadedException;
import es.angelillo15.artic.antivpn.api.utils.ILogger;
import es.angelillo15.artic.antivpn.api.utils.ServerUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;

public interface AntiVPNInstance<P> {
    static AntiVPNInstance<JavaPlugin> getInstance() {
        AntiVPNInstance<JavaPlugin> instance = (AntiVPNInstance<JavaPlugin>) Bukkit.getPluginManager().getPlugin("ArticAntiVPN");

        if (instance == null) {
            throw new PluginNotLoadedException();
        }

        return instance;
    }

    static AntiVPNInstance<Plugin> getBungeeInstance() {
        AntiVPNInstance<Plugin> instance = (AntiVPNInstance<Plugin>) ProxyServer.getInstance().getPluginManager().getPlugin("ArticAntiVPN");

        if (instance == null) {
            throw new PluginNotLoadedException();
        }

        return instance;
    }

    public static ILogger getLogger() {
        if (ServerUtils.getServerType() == ServerUtils.ServerType.BUKKIT) {
            return getInstance().getPLogger();
        } else {
            return getBungeeInstance().getPLogger();
        }
    }

    public boolean isDebug();
    public void drawLogo();
    public void loadConfig();
    public void registerCommands();
    public void registerListeners();
    public void loadDatabase();
    public void unregisterCommands();
    public void unregisterListeners();
    public void unloadDatabase();
    public void reload();
    public P getPluginInstance();
    public ILogger getPLogger();
    public File getPluginDataFolder();
    public InputStream getPluginResourceAsStream(String path);
    public void setDebug(boolean debug);
}
