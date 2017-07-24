package com.kp.mwi.telkomtanjung.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mwi on 7/24/17.
 */

public class ETRANS implements Parcelable{
    private String panel, port;

    public ETRANS() {
    }

    public ETRANS(String panel, String port) {
        this.panel = panel;
        this.port = port;
    }

    protected ETRANS(Parcel in) {
        panel = in.readString();
        port = in.readString();
    }

    public static final Creator<ETRANS> CREATOR = new Creator<ETRANS>() {
        @Override
        public ETRANS createFromParcel(Parcel in) {
            return new ETRANS(in);
        }

        @Override
        public ETRANS[] newArray(int size) {
            return new ETRANS[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(panel);
        dest.writeString(port);
    }
}
