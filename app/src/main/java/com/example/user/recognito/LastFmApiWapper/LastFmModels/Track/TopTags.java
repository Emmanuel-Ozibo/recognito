package com.example.user.recognito.LastFmApiWapper.LastFmModels.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 12/29/2017.
 */

public class TopTags implements Parcelable{

    @SerializedName("tag")
    public List<Tags> tagsList;

    protected TopTags(Parcel in) {
        tagsList = in.createTypedArrayList(Tags.CREATOR);
    }

    public static final Creator<TopTags> CREATOR = new Creator<TopTags>() {
        @Override
        public TopTags createFromParcel(Parcel in) {
            return new TopTags(in);
        }

        @Override
        public TopTags[] newArray(int size) {
            return new TopTags[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(tagsList);
    }
}
