package com.kp.mwi.telkomtanjung.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mwi on 7/20/17.
 */

public class ODC implements Parcelable {
    private String nama, panel, port, spl, kap, koordinat;

    public ODC(String nama, String panel, String port, String spl, String kap) {
        this.nama = nama;
        this.panel = panel;
        this.port = port;
        this.spl = spl;
        this.kap = kap;
    }

    public ODC(String nama, String panel, String port, String spl, String kap, String koordinat) {
        this.nama = nama;
        this.panel = panel;
        this.port = port;
        this.spl = spl;
        this.kap = kap;
        this.koordinat = koordinat;
    }

    public ODC() {
    }

    protected ODC(Parcel in) {
        nama = in.readString();
        panel = in.readString();
        port = in.readString();
        spl = in.readString();
        kap = in.readString();
        koordinat = in.readString();
    }

    public static final Creator<ODC> CREATOR = new Creator<ODC>() {
        @Override
        public ODC createFromParcel(Parcel in) {
            return new ODC(in);
        }

        @Override
        public ODC[] newArray(int size) {
            return new ODC[size];
        }
    };

    public String getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(panel);
        dest.writeString(port);
        dest.writeString(spl);
        dest.writeString(kap);
        dest.writeString(koordinat);
    }
}
