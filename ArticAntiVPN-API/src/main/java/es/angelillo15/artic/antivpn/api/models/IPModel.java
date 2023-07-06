package es.angelillo15.artic.antivpn.api.models;

import com.craftmend.storm.Storm;
import com.craftmend.storm.api.StormModel;
import com.craftmend.storm.api.enums.Where;
import com.craftmend.storm.api.markers.Column;
import com.craftmend.storm.api.markers.Table;
import es.angelillo15.artic.antivpn.api.database.PluginConnection;
import lombok.Data;

@Data
@Table(name = "articantivpn_ips")
public class IPModel extends StormModel {
    @Column(name = "ip")
    private String ip;

    @Column(name = "proxy")
    private Boolean proxy;

    @Column(name = "country")
    private String country;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "city")
    private String city;

    @Column(name = "isp")
    private String isp;

    public static IPModel get(String ip) {
        Storm storm = PluginConnection.getStorm();

        try {
            return storm.buildQuery(IPModel.class)
                    .where("ip", Where.EQUAL, ip)
                    .limit(1)
                    .execute()
                    .join()
                    .iterator()
                    .next();
        } catch (Exception e) {
            return null;
        }
    }
}
