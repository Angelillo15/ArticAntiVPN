package es.angelillo15.artic.antivpn;

import es.angelillo15.artic.antivpn.api.AntiVPNInstance;
import es.angelillo15.artic.antivpn.api.Constants;
import es.angelillo15.artic.antivpn.api.TextUtils;
import es.angelillo15.artic.antivpn.api.config.ConfigLoader;
import es.angelillo15.artic.antivpn.api.utils.ILogger;
import es.angelillo15.artic.antivpn.utils.BungeeLogger;
import es.angelillo15.artic.antivpn.utils.LibsLoader;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.InputStream;

public class AntiVPN extends Plugin implements AntiVPNInstance<Plugin> {
    @Getter
    private static AntiVPN instance;

    private static boolean debug = false;
    private static ILogger logger;

    @Override
    public boolean isDebug() {
        return debug;
    }

    @Override
    public void drawLogo() {
        instance = this;
        logger = new BungeeLogger();

        logger.info(TextUtils.colorize("&c&lArtic&f&lAntiVPN&7 v" + Constants.VERSION + " &7by &c&lAngelillo15"));
        logger.info(TextUtils.colorize("&7Build time: &c&l" + Constants.COMMIT_TIME));
        LibsLoader.loadLibs();
    }

    @Override
    public void loadConfig() {
        new ConfigLoader(this).loadConfig();
    }

    @Override
    public void registerCommands() {

    }

    @Override
    public void registerListeners() {

    }

    @Override
    public void loadDatabase() {

    }

    @Override
    public void unregisterCommands() {

    }

    @Override
    public void unregisterListeners() {

    }

    @Override
    public void unloadDatabase() {

    }

    @Override
    public void reload() {

    }

    @Override
    public Plugin getPluginInstance() {
        return this;
    }

    @Override
    public ILogger getPLogger() {
        return logger;
    }

    @Override
    public File getPluginDataFolder() {
        return getDataFolder();
    }

    @Override
    public InputStream getPluginResourceAsStream(String path) {
        return getResourceAsStream(path);
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
