package es.angelillo15.artic.antivpn.api;

public class TextUtils {
    /**
     * Colorize a message.
     * @param message The message to colorize.
     * @return The colorized message.
     */
    public static String colorize(String message) {
        return message.replace("&", "§");
    }
}
