package es.angelillo15.artic.antivpn.api.cache;

import com.craftmend.storm.Storm;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import es.angelillo15.artic.antivpn.api.AntiVPNInstance;
import es.angelillo15.artic.antivpn.api.config.Config;
import es.angelillo15.artic.antivpn.api.database.PluginConnection;
import es.angelillo15.artic.antivpn.api.models.IPModel;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.sql.SQLException;

public class IPCache {
    private static final String API_URL = "http://ip-api.com/json/{IP}?fields=country,countryCode,city,isp,proxy";
    private static final Cache<String, IPModel> cache = Caffeine.newBuilder()
            .maximumSize(Config.cacheMaxSize())
            .build();


    /**
     * Get an IPModel from the cache or database or from the API
     * @param ip
     * @return IPModel
     */
    public static IPModel get(String ip) {
        if (cache.getIfPresent(ip) != null) {
            return cache.getIfPresent(ip);
        }

        IPModel ipModel = getIpModelFromDB(ip);

        if (ipModel != null) {
            cache.put(ip, ipModel);
            return ipModel;
        }

        return null;
    }


    public static IPModel getIpModelFromDB(String ip) {
        return IPModel.get(ip);
    }

    public static IPModel getIpModelFromAPI(String ip) {
        HttpResponse<String> response = Unirest.get(API_URL.replace("{IP}", ip)).asString();

        if (response.getStatus() != 200) {
            AntiVPNInstance.getLogger().error("Error getting IPModel from API: " + response.getStatus());
            return null;
        }

        JsonElement jsonElement = JsonParser.parseString(response.getBody());

        if (!jsonElement.isJsonObject()) {
            return null;
        }



        IPModel ipModel = new IPModel();
        ipModel.setIp(ip);

        try {
            ipModel.setCountry(jsonElement.getAsJsonObject().get("country").getAsString());
            ipModel.setCountryCode(jsonElement.getAsJsonObject().get("countryCode").getAsString());
            ipModel.setCity(jsonElement.getAsJsonObject().get("city").getAsString());
            ipModel.setIsp(jsonElement.getAsJsonObject().get("isp").getAsString());
            ipModel.setProxy(jsonElement.getAsJsonObject().get("proxy").getAsBoolean());
        } catch (Exception e) {
            ipModel.setCountry("Localhost");
            ipModel.setCountryCode("Localhost");
            ipModel.setCity("Localhost");
            ipModel.setIsp("Localhost");
            ipModel.setProxy(false);
        }


        Storm storm = PluginConnection.getStorm();

        new Thread(() -> {
            try {
                storm.save(ipModel);
            } catch (SQLException e) {
                return;
            }
        }).start();

        cache.put(ip, ipModel);

        return ipModel;
    }
}
