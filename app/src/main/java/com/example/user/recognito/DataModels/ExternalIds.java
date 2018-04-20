package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emmanuel on 12/19/2017.
 */

public class ExternalIds implements Parcelable{

    public String isrc;
    public String upc;

    protected ExternalIds(Parcel in) {
        isrc = in.readString();
        upc = in.readString();
    }

    public static final Creator<ExternalIds> CREATOR = new Creator<ExternalIds>() {
        @Override
        public ExternalIds createFromParcel(Parcel in) {
            return new ExternalIds(in);
        }

        @Override
        public ExternalIds[] newArray(int size) {
            return new ExternalIds[size];
        }
    };

    public String getIsrc() {
        return isrc;
    }

    public String getUpc() {
        return upc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(isrc);
        parcel.writeString(upc);
    }
}
