package es.angelillo15.artic.antivpn.api.models;

import com.craftmend.storm.api.StormModel;
import com.craftmend.storm.api.markers.Column;
import com.craftmend.storm.api.markers.Table;
import lombok.Data;

@Data
@Table(name = "articantivpn_whitelist_players")
public class WhiteListPlayers extends StormModel {
    @Column(name = "uuid")
    private String key;
}
