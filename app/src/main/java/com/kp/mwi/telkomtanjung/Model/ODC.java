package com.kp.mwi.telkomtanjung.Model;

/**
 * Created by mwi on 7/20/17.
 */

public class ODC {
    private String nama, panel, port, spl, kap;

    public ODC(String nama, String panel, String port, String spl, String kap) {
        this.nama = nama;
        this.panel = panel;
        this.port = port;
        this.spl = spl;
        this.kap = kap;
    }

    public ODC() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSpl() {
        return spl;
    }

    public void setSpl(String spl) {
        this.spl = spl;
    }

    public String getKap() {
        return kap;
    }

    public void setKap(String kap) {
        this.kap = kap;
    }
}
