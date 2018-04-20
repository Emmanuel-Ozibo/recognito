package com.example.user.recognito.LastFmApiWapper.LastFmModels.SimilarTrack;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emmanuel on 12/30/2017.
 */

public class SimilarTrackResponse implements Parcelable{

    @SerializedName("similartracks")
    public SimilarTrack similarTracks;

    protected SimilarTrackResponse(Parcel in) {
        similarTracks = in.readParcelable(SimilarTrack.class.getClassLoader());
    }

    public static final Creator<SimilarTrackResponse> CREATOR = new Creator<SimilarTrackResponse>() {
        @Override
        public SimilarTrackResponse createFromParcel(Parcel in) {
            return new SimilarTrackResponse(in);
        }

        @Override
        public SimilarTrackResponse[] newArray(int size) {
            return new SimilarTrackResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(similarTracks, i);
    }
}
