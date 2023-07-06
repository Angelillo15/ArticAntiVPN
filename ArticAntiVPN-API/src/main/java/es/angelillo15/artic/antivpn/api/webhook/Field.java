package es.angelillo15.artic.antivpn.api.webhook;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Field {
    public String name;

    public String value;

    public boolean inline;
}
