package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 12/19/2017.
 */

public class YouTube implements Parcelable{
    public String vid;

    protected YouTube(Parcel in) {
        vid = in.readString();
    }

    public static final Creator<YouTube> CREATOR = new Creator<YouTube>() {
        @Override
        public YouTube createFromParcel(Parcel in) {
            return new YouTube(in);
        }

        @Override
        public YouTube[] newArray(int size) {
            return new YouTube[size];
        }
    };

    public String getVid() {
        return vid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(vid);
    }
}
