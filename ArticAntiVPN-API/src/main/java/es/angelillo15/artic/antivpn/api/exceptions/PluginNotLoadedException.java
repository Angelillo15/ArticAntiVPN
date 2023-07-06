package es.angelillo15.artic.antivpn.api.exceptions;

public class PluginNotLoadedException extends RuntimeException {
    public PluginNotLoadedException() {
        super("ArticAntiVPN is not loaded!");
    }
}
