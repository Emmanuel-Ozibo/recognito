package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emmanuel on 12/19/2017.
 */

public class Artistm implements Parcelable{

    public String name;

    protected Artistm(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Artistm> CREATOR = new Creator<Artistm>() {
        @Override
        public Artistm createFromParcel(Parcel in) {
            return new Artistm(in);
        }

        @Override
        public Artistm[] newArray(int size) {
            return new Artistm[size];
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
