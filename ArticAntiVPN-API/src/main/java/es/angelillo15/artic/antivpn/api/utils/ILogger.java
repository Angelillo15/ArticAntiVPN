package es.angelillo15.artic.antivpn.api.utils;

public interface ILogger {
    /**
     * Log a message to the console.
     * @param message The message to log.
     */
    void info(String message);

    /**
     * Log a warning to the console.
     * @param message
     */
    void warn(String message);

    /**
     * Log an error to the console.
     * @param message
     */
    void error(String message);

    /**
     * Log a debug message to the console.
     * @param message
     */
    void debug(String message);
}
