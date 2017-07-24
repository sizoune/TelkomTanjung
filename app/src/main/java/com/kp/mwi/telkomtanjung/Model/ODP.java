package com.kp.mwi.telkomtanjung.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mwi on 7/20/17.
 */

public class ODP implements Parcelable {
    private String nama, panel, port, core, spl, koordinat, lastupdate, alamat, kap, tipe, portolt, ipolt;
    private ODC odc;
    private OTB otb;
    private FTM ftm;


    public ODP(String nama, String panel, String port, String core, String spl, String koordinat,
               String lastupdate, String alamat, String kap, String tipe, String portolt,
               String ipolt, ODC odc, OTB otb, FTM ftm) {
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
        this.ipolt = ipolt;
        this.odc = odc;
        this.otb = otb;
        this.ftm = ftm;
    }

    public ODP() {
        odc = new ODC();
        otb = new OTB();
        ftm = new FTM();
    }

    public FTM getFtm() {
        return ftm;
    }

    public void setFtm(FTM ftm) {
        this.ftm = ftm;
    }

    public String getIpolt() {
        return ipolt;
    }

    public void setIpolt(String ipolt) {
        this.ipolt = ipolt;
    }

    protected ODP(Parcel in) {
        nama = in.readString();
        panel = in.readString();
        port = in.readString();
        core = in.readString();
        spl = in.readString();
        koordinat = in.readString();
        lastupdate = in.readString();
        alamat = in.readString();
        kap = in.readString();
        tipe = in.readString();
        portolt = in.readString();
        ipolt = in.readString();
        odc = in.readParcelable(ODC.class.getClassLoader());
        otb = in.readParcelable(OTB.class.getClassLoader());
        ftm = in.readParcelable(FTM.class.getClassLoader());
    }

    public static final Creator<ODP> CREATOR = new Creator<ODP>() {
        @Override
        public ODP createFromParcel(Parcel in) {
            return new ODP(in);
        }

        @Override
        public ODP[] newArray(int size) {
            return new ODP[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(panel);
        dest.writeString(port);
        dest.writeString(core);
        dest.writeString(spl);
        dest.writeString(koordinat);
        dest.writeString(lastupdate);
        dest.writeString(alamat);
        dest.writeString(kap);
        dest.writeString(tipe);
        dest.writeString(portolt);
        dest.writeString(ipolt);
        dest.writeParcelable(odc, flags);
        dest.writeParcelable(otb, flags);
        dest.writeParcelable(ftm, flags);
    }
}
