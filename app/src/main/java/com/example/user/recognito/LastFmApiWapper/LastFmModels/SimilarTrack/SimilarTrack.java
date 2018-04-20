package com.example.user.recognito.LastFmApiWapper.LastFmModels.SimilarTrack;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 12/30/2017.
 */

public class SimilarTrack implements Parcelable{
    @SerializedName("track")
    public List<STrack> trackList;

    protected SimilarTrack(Parcel in) {
        trackList = in.createTypedArrayList(STrack.CREATOR);
    }

    public static final Creator<SimilarTrack> CREATOR = new Creator<SimilarTrack>() {
        @Override
        public SimilarTrack createFromParcel(Parcel in) {
            return new SimilarTrack(in);
        }

        @Override
        public SimilarTrack[] newArray(int size) {
            return new SimilarTrack[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(trackList);
    }
}
