package com.example.user.recognito.LastFmApiWapper.LastFmModels.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/29/2017.
 */

public class Wiki implements Parcelable{

    @SerializedName("published")
    public String published;
    @SerializedName("summary")
    public String summary;
    @SerializedName("content")
    public String content;

    protected Wiki(Parcel in) {
        published = in.readString();
        summary = in.readString();
        content = in.readString();
    }

    public static final Creator<Wiki> CREATOR = new Creator<Wiki>() {
        @Override
        public Wiki createFromParcel(Parcel in) {
            return new Wiki(in);
        }

        @Override
        public Wiki[] newArray(int size) {
            return new Wiki[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(published);
        parcel.writeString(summary);
        parcel.writeString(content);
    }
}
