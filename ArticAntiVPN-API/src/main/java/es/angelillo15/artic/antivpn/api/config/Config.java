package es.angelillo15.artic.antivpn.api.config;

import es.angelillo15.artic.antivpn.api.TextUtils;
import es.angelillo15.artic.antivpn.api.webhook.Field;

import java.util.ArrayList;

public class Config {
    public static String prefix() {
        return getString("Config.prefix");
    }

    public static boolean debug() {
        return ConfigLoader.getConfig().getConfig().getBoolean("Config.debug");
    }

    public static int cacheMaxSize() {
        return getInt("Config.cacheMaxSize");
    }

    public static class Database {
        public static String type() {
            return getString("Database.type");
        }

        public static String host() {
            return getString("Database.host");
        }

        public static int port() {
            return getInt("Database.port");
        }

        public static String database() {
            return getString("Database.database");
        }

        public static String username() {
            return getString("Database.user");
        }

        public static String password() {
            return getString("Database.password");
        }
    }

    public static class Messages {
        public static class Alert {
            public static String vpnDetected() {
                return getPrefixedString("Messages.alert.vpnDetected");
            }

            public static String vpnDetected(String player, String ip, String country, String countryCode, String city, String isp) {
                return vpnDetected()
                        .replace("{ip}", ip)
                        .replace("{country}", country)
                        .replace("{countryCode}", countryCode)
                        .replace("{city}", city)
                        .replace("{isp}", isp)
                        .replace("{player}", player);
            }
        }

        public static class KickMessages {
            public static String vpnDetected() {
                return getPrefixedString("Messages.kickMessage.vpnDetected");
            }

            public static String vpnDetected(String ip, String country, String countryCode, String city, String isp) {
                return vpnDetected()
                        .replace("{ip}", ip)
                        .replace("{country}", country)
                        .replace("{countryCode}", countryCode)
                        .replace("{city}", city)
                        .replace("{isp}", isp);
            }
        }
    }

    public static class Webhook {
        public static boolean enabled() {
            return ConfigLoader.getConfig().getConfig().getBoolean("Webhook.enabled");
        }

        public static String url() {
            return getString("Webhook.url");
        }

        public static String username() {
            return getString("Webhook.username");
        }

        public static String avatar() {
            return getString("Webhook.avatar");
        }

        public static class Embed {
            public static String title() {
                return getString("Webhook.embed.title");
            }

            public static String description() {
                return getString("Webhook.embed.description");
            }

            public static String color() {
                return getString("Webhook.embed.color");
            }

            public static class footer {
                public static String text() {
                    return getString("Webhook.embed.footer.text");
                }

                public static String icon() {
                    return getString("Webhook.embed.footer.icon");
                }
            }

            public static String thumbnail() {
                return getString("Webhook.embed.thumbnail");
            }

            public static ArrayList<Field> getFields() {
                ArrayList<Field> fields = new ArrayList<>();
                for (String key : ConfigLoader.getConfig().getConfig().getConfigurationSection("Webhook.embed.fields").getKeys(false)) {
                    fields.add(new Field(
                            getString("Webhook.embed.fields." + key + ".name"),
                            getString("Webhook.embed.fields." + key + ".value"),
                            ConfigLoader.getConfig().getConfig().getBoolean("Webhook.embed.fields." + key + ".inline")
                    ));
                }

                return fields;
            }
        }
    }

    /**
     * Get a string from the config and replace the prefix
     * @param path The path to the string in the config
     * @return The string
     */
    public static String getPrefixedString(String path) {
        return TextUtils.colorize(getString(path).replace("{prefix}", prefix()));
    }

    /**
     * Get a string from the config
     * @param path The path to the string in the config
     * @return The string
     */
    public static String getString(String path) {
        return ConfigLoader.getConfig().getConfig().getString(path);
    }

    /**
     * Get a string from the config
     * @param path The path to the string in the config
     * @return The int
     */
    public static int getInt(String path) {
        return ConfigLoader.getConfig().getConfig().getInt(path);
    }
}
