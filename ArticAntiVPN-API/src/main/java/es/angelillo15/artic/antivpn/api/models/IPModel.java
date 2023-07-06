package es.angelillo15.artic.antivpn.api.models;

import com.craftmend.storm.api.StormModel;
import com.craftmend.storm.api.markers.Column;
import com.craftmend.storm.api.markers.Table;
import lombok.Data;

@Data
@Table(name = "articantivpn_ips")
public class IPModel extends StormModel {
    @Column(name = "ip")
    private String ip;

    @Column(name = "country")
    private String country;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "city")
    private String city;

    @Column(name = "isp")
    private String isp;
}
