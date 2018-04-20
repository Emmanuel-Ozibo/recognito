package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 12/19/2017.
 */

public class AlbumM implements Parcelable{
    public String name;

    protected AlbumM(Parcel in) {
        name = in.readString();
    }

    public static final Creator<AlbumM> CREATOR = new Creator<AlbumM>() {
        @Override
        public AlbumM createFromParcel(Parcel in) {
            return new AlbumM(in);
        }

        @Override
        public AlbumM[] newArray(int size) {
            return new AlbumM[size];
        }
    };

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
