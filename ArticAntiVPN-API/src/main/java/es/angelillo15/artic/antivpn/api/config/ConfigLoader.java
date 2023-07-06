package es.angelillo15.artic.antivpn.api.config;

import es.angelillo15.artic.antivpn.api.AntiVPNInstance;
import es.angelillo15.configmanager.ConfigManager;
import es.angelillo15.artic.antivpn.api.utils.ConfigMerge;
import lombok.Getter;

import java.io.File;

public class ConfigLoader {
    @Getter
    private static ConfigManager config;
    private AntiVPNInstance<?> plugin;

    public ConfigLoader(AntiVPNInstance<?> plugin) {
        this.plugin = plugin;
    }

    public void load() {
        loadConfig();
    }

    public void loadConfig() {
        ConfigMerge.merge(new File(plugin.getPluginDataFolder().toPath() + File.separator + "config.yml"),
                plugin.getPluginResourceAsStream("config.yml")
        );

        config = new ConfigManager(plugin.getPluginDataFolder().toPath(), "config.yml", "config.yml");
        config.registerConfig();

        plugin.setDebug(config.getConfig().getBoolean("Config.debug"));
    }
}
