package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 12/19/2017.
 */

public class Track implements Parcelable{

    public String id;

    protected Track(Parcel in) {
        id = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
    }
}
