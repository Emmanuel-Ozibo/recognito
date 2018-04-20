package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by emmanuel on 12/19/2017.
 */

public class Spotify implements Parcelable{

    public Album album;
    public List<Artist> artists;
    public Track track;

    protected Spotify(Parcel in) {
        album = in.readParcelable(Album.class.getClassLoader());
        artists = in.createTypedArrayList(Artist.CREATOR);
        track = in.readParcelable(Track.class.getClassLoader());
    }

    public static final Creator<Spotify> CREATOR = new Creator<Spotify>() {
        @Override
        public Spotify createFromParcel(Parcel in) {
            return new Spotify(in);
        }

        @Override
        public Spotify[] newArray(int size) {
            return new Spotify[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(album, i);
        parcel.writeTypedList(artists);
        parcel.writeParcelable(track, i);
    }
}
