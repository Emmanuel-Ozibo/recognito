package com.example.user.recognito.LastFmApiWapper.LastFmModels.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/29/2017.
 */

public class LImage implements Parcelable{
    @SerializedName("#text")
    public String imageurl;
    @SerializedName("size")
    public String size;

    protected LImage(Parcel in) {
        imageurl = in.readString();
        size = in.readString();
    }

    public static final Creator<LImage> CREATOR = new Creator<LImage>() {
        @Override
        public LImage createFromParcel(Parcel in) {
            return new LImage(in);
        }

        @Override
        public LImage[] newArray(int size) {
            return new LImage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageurl);
        parcel.writeString(size);
    }
}
