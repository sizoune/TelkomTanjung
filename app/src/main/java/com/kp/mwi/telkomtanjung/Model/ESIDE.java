package com.kp.mwi.telkomtanjung.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mwi on 7/24/17.
 */

public class ESIDE implements Parcelable{
    private String panel, port;

    public ESIDE() {
    }

    public ESIDE(String panel, String port) {
        this.panel = panel;
        this.port = port;
    }

    protected ESIDE(Parcel in) {
        panel = in.readString();
        port = in.readString();
    }

    public static final Creator<ESIDE> CREATOR = new Creator<ESIDE>() {
        @Override
        public ESIDE createFromParcel(Parcel in) {
            return new ESIDE(in);
        }

        @Override
        public ESIDE[] newArray(int size) {
            return new ESIDE[size];
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
