package es.angelillo15.artic.antivpn.utils;

import es.angelillo15.artic.antivpn.AntiVPN;
import es.angelillo15.artic.antivpn.api.utils.ILogger;

public class BungeeLogger implements ILogger {
    @Override
    public void info(String message) {
        AntiVPN.getInstance().getLogger().info(message);
    }

    @Override
    public void warn(String message) {
        AntiVPN.getInstance().getLogger().warning(message);
    }

    @Override
    public void error(String message) {
        AntiVPN.getInstance().getLogger().severe(message);
    }

    @Override
    public void debug(String message) {
        AntiVPN.getInstance().getLogger().info("[DEBUG] " + message);
    }
}
