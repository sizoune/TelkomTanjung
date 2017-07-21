package com.kp.mwi.telkomtanjung.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mwi on 7/20/17.
 */

public class OTB implements Parcelable{
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

    protected OTB(Parcel in) {
        odf = in.readString();
        panel = in.readString();
        port = in.readString();
        core = in.readString();
        kap = in.readString();
    }

    public static final Creator<OTB> CREATOR = new Creator<OTB>() {
        @Override
        public OTB createFromParcel(Parcel in) {
            return new OTB(in);
        }

        @Override
        public OTB[] newArray(int size) {
            return new OTB[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(odf);
        dest.writeString(panel);
        dest.writeString(port);
        dest.writeString(core);
        dest.writeString(kap);
    }
}
