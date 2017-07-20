package com.kp.mwi.telkomtanjung.Model;

/**
 * Created by mwi on 7/20/17.
 */

public class ODP {
    private String nama, panel, port, core, spl, koordinat, lastupdate, alamat, kap, tipe, portolt;
    private ODC odc;
    private OTB otb;

    public ODP(String nama, String panel, String port, String core, String spl, String koordinat,
               String lastupdate, String alamat, String kap, String tipe, String portolt, ODC odc,
               OTB otb) {
        this.nama = nama;
        this.panel = panel;
        this.port = port;
        this.core = core;
        this.spl = spl;
        this.koordinat = koordinat;
        this.lastupdate = lastupdate;
        this.alamat = alamat;
        this.kap = kap;
        this.tipe = tipe;
        this.portolt = portolt;
        this.odc = odc;
        this.otb = otb;
    }

    public ODP() {
        odc = new ODC();
        otb = new OTB();
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

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getSpl() {
        return spl;
    }

    public void setSpl(String spl) {
        this.spl = spl;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKap() {
        return kap;
    }

    public void setKap(String kap) {
        this.kap = kap;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getPortolt() {
        return portolt;
    }

    public void setPortolt(String portolt) {
        this.portolt = portolt;
    }

    public ODC getOdc() {
        return odc;
    }

    public void setOdc(ODC odc) {
        this.odc = odc;
    }

    public OTB getOtb() {
        return otb;
    }

    public void setOtb(OTB otb) {
        this.otb = otb;
    }
}
