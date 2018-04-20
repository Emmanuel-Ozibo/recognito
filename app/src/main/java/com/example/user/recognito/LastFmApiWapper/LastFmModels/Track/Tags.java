package com.example.user.recognito.LastFmApiWapper.LastFmModels.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/29/2017.
 */

public class Tags implements Parcelable{
    @SerializedName("name")
    public String tagName;

    protected Tags(Parcel in) {
        tagName = in.readString();
    }

    public static final Creator<Tags> CREATOR = new Creator<Tags>() {
        @Override
        public Tags createFromParcel(Parcel in) {
            return new Tags(in);
        }

        @Override
        public Tags[] newArray(int size) {
            return new Tags[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tagName);
    }
}
