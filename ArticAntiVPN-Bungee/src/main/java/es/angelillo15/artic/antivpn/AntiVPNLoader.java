package es.angelillo15.artic.antivpn;

public class AntiVPNLoader extends AntiVPN {
    @Override
    public void onEnable() {
        drawLogo();

        loadConfig();
        loadDatabase();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
