package com.example.user.recognito.LastFmApiWapper.LastFmModels.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/29/2017.
 */

public class LArtist implements Parcelable{
    @SerializedName("name")
    public String name;
    @SerializedName("url")
    public String url;

    protected LArtist(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<LArtist> CREATOR = new Creator<LArtist>() {
        @Override
        public LArtist createFromParcel(Parcel in) {
            return new LArtist(in);
        }

        @Override
        public LArtist[] newArray(int size) {
            return new LArtist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(url);
    }
}
