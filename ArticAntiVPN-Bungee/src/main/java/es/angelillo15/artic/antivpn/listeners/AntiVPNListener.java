package es.angelillo15.artic.antivpn.listeners;


import es.angelillo15.artic.antivpn.AntiVPN;
import es.angelillo15.artic.antivpn.api.AntiVPNInstance;
import es.angelillo15.artic.antivpn.api.cache.IPCache;
import es.angelillo15.artic.antivpn.api.config.Config;
import es.angelillo15.artic.antivpn.api.models.IPModel;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.net.InetSocketAddress;

public class AntiVPNListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PreLoginEvent event) {
        event.registerIntent(AntiVPN.getInstance());

        long start = System.currentTimeMillis();

        PendingConnection player = event.getConnection();

        InetSocketAddress address = (InetSocketAddress) player.getSocketAddress();

        AntiVPNInstance.getLogger().debug("Player " + player.getName() + " is trying to join with IP " + address.getAddress().getHostAddress());

        IPModel ip = IPCache.get(address.getAddress().getHostAddress());

        if (ip == null) {
            AntiVPNInstance.getLogger().debug("Player " + player.getName() + " has not been found");
            event.completeIntent(AntiVPN.getInstance());
            return;
        }

        if (ip.getProxy()) {
            event.setCancelReason(new TextComponent(
                    Config.Messages.KickMessages.vpnDetected(
                            ip.getIp(),
                            ip.getCountry(),
                            ip.getCountryCode(),
                            ip.getCity(),
                            ip.getIsp()
                    )
            ));
        }

        long end = System.currentTimeMillis();

        AntiVPNInstance.getLogger().debug("Player " + player.getName() + " took " + (end - start) + "ms to check if he is using a VPN.");

        event.completeIntent(AntiVPN.getInstance());
    }
}
