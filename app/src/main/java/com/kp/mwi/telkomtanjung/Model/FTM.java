package com.kp.mwi.telkomtanjung.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mwi on 7/24/17.
 */

public class FTM implements Parcelable {
    private ESIDE eside;
    private ETRANS etrans;
    private OSIDE oside;

    public FTM() {
        eside = new ESIDE();
        etrans = new ETRANS();
        oside = new OSIDE();
    }

    public FTM(ESIDE eside, ETRANS etrans, OSIDE oside) {
        this.eside = eside;
        this.etrans = etrans;
        this.oside = oside;
    }

    protected FTM(Parcel in) {
        eside = in.readParcelable(ESIDE.class.getClassLoader());
        etrans = in.readParcelable(ETRANS.class.getClassLoader());
        oside = in.readParcelable(OSIDE.class.getClassLoader());
    }

    public static final Creator<FTM> CREATOR = new Creator<FTM>() {
        @Override
        public FTM createFromParcel(Parcel in) {
            return new FTM(in);
        }

        @Override
        public FTM[] newArray(int size) {
            return new FTM[size];
        }
    };

    public ESIDE getEside() {
        return eside;
    }

    public void setEside(ESIDE eside) {
        this.eside = eside;
    }

    public ETRANS getEtrans() {
        return etrans;
    }

    public void setEtrans(ETRANS etrans) {
        this.etrans = etrans;
    }

    public OSIDE getOside() {
        return oside;
    }

    public void setOside(OSIDE oside) {
        this.oside = oside;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(eside, flags);
        dest.writeParcelable(etrans, flags);
        dest.writeParcelable(oside, flags);
    }
}
