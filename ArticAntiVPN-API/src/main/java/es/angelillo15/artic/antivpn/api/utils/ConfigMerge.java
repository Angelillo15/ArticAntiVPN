package es.angelillo15.artic.antivpn.api.utils;

import lombok.SneakyThrows;
import ru.vyarus.yaml.updater.YamlUpdater;

import java.io.File;
import java.io.InputStream;

public class ConfigMerge {
    public static void loggerSetup() {
        System.setProperty("org.slf4j.simpleLogger.log.ru.vyarus", "none");
    }

    @SneakyThrows
    /**
     * Merges the InputStream into the File
     * @param file The file to merge
     * @param Input The InputStream to merge
     */
    public static void merge(File current, InputStream update) {
        YamlUpdater.create(current, update)
                .update();
    }
}
