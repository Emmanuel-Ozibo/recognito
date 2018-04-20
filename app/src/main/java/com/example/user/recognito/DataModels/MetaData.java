package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by emmanuel on 12/19/2017.
 */

public class MetaData implements Parcelable{

    public int played_duration;
    public List<Music> music;
    public String timestamp_utc;

    protected MetaData(Parcel in) {
        played_duration = in.readInt();
        timestamp_utc = in.readString();
    }

    public static final Creator<MetaData> CREATOR = new Creator<MetaData>() {
        @Override
        public MetaData createFromParcel(Parcel in) {
            return new MetaData(in);
        }

        @Override
        public MetaData[] newArray(int size) {
            return new MetaData[size];
        }
    };

    public int getPlayed_duration() {
        return played_duration;
    }

    public List<Music> getMusic() {
        return music;
    }

    public String getTimestamp_utc() {
        return timestamp_utc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(played_duration);
        parcel.writeString(timestamp_utc);
    }
}
