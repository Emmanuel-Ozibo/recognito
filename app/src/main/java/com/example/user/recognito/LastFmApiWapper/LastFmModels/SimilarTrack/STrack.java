package com.example.user.recognito.LastFmApiWapper.LastFmModels.SimilarTrack;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 12/30/2017.
 */


public class STrack implements Parcelable{
    @SerializedName("name")
    public String trackName;
    @SerializedName("image")
    public List<SImage>imageList;

    protected STrack(Parcel in) {
        trackName = in.readString();
        imageList = in.createTypedArrayList(SImage.CREATOR);
    }

    public static final Creator<STrack> CREATOR = new Creator<STrack>() {
        @Override
        public STrack createFromParcel(Parcel in) {
            return new STrack(in);
        }

        @Override
        public STrack[] newArray(int size) {
            return new STrack[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(trackName);
        parcel.writeTypedList(imageList);
    }
}
