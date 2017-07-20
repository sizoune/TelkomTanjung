package com.kp.mwi.telkomtanjung.Model;

/**
 * Created by mwi on 7/20/17.
 */

public class OTB {
    private String odf, panel, port, core, kap;

    public OTB(String odf, String panel, String port, String core, String kap) {
        this.odf = odf;
        this.panel = panel;
        this.port = port;
        this.core = core;
        this.kap = kap;
    }

    public OTB() {
    }

    public String getOdf() {
        return odf;
    }

    public void setOdf(String odf) {
        this.odf = odf;
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

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getKap() {
        return kap;
    }

    public void setKap(String kap) {
        this.kap = kap;
    }
}
