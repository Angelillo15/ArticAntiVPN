package es.angelillo15.artic.antivpn.utils;

import es.angelillo15.artic.antivpn.AntiVPN;
import es.angelillo15.artic.antivpn.api.libs.LibsManager;
import net.byteflux.libby.BungeeLibraryManager;

public class LibsLoader {
    public static void loadLibs() {
        BungeeLibraryManager manager = new BungeeLibraryManager(AntiVPN.getInstance());

        manager.addMavenCentral();
        manager.addJitPack();

        LibsManager.load();
        LibsManager.getLibs().forEach(manager::loadLibrary);
    }
}
