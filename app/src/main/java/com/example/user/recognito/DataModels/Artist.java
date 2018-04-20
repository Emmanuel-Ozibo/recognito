package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emmanuel on 12/19/2017.
 */

public class Artist implements Parcelable{
    /**
     * @param vid is the spotify album vid
     * */
    public String id;

    protected Artist(Parcel in) {
        id = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
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
