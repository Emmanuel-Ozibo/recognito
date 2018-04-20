package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emmanuel on 12/19/2017.
 */

public class ExternalMetaData implements Parcelable{
    public Spotify spotify;
    public YouTube youtube;

    protected ExternalMetaData(Parcel in) {
        spotify = in.readParcelable(Spotify.class.getClassLoader());
        youtube = in.readParcelable(YouTube.class.getClassLoader());
    }

    public static final Creator<ExternalMetaData> CREATOR = new Creator<ExternalMetaData>() {
        @Override
        public ExternalMetaData createFromParcel(Parcel in) {
            return new ExternalMetaData(in);
        }

        @Override
        public ExternalMetaData[] newArray(int size) {
            return new ExternalMetaData[size];
        }
    };

    public Spotify getSpotify() {
        return spotify;
    }

    public YouTube getYoutube() {
        return youtube;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(spotify, i);
        parcel.writeParcelable(youtube, i);
    }
}
