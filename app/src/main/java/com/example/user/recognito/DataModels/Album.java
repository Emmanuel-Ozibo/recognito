package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emmanuel on 12/19/2017.
 */

public class Album implements Parcelable{

    public String id;

    protected Album(Parcel in) {
        id = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
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
