package com.example.user.recognito.LastFmApiWapper.LastFmModels.SimilarTrack;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/30/2017.
 */

public class SImage implements Parcelable{

    @SerializedName("#text")
    public String imageUrl;

    protected SImage(Parcel in) {
        imageUrl = in.readString();
    }

    public static final Creator<SImage> CREATOR = new Creator<SImage>() {
        @Override
        public SImage createFromParcel(Parcel in) {
            return new SImage(in);
        }

        @Override
        public SImage[] newArray(int size) {
            return new SImage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
    }
}
