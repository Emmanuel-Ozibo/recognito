package com.example.user.recognito.LastFmApiWapper.LastFmModels.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 12/29/2017.
 */

public class LAlbum implements Parcelable{
    @SerializedName("artist")
    public String artist;
    @SerializedName("title")
    public String title;
    @SerializedName("image")
    public List<LImage> imageList;

    protected LAlbum(Parcel in) {
        artist = in.readString();
        title = in.readString();
        imageList = in.createTypedArrayList(LImage.CREATOR);
    }

    public static final Creator<LAlbum> CREATOR = new Creator<LAlbum>() {
        @Override
        public LAlbum createFromParcel(Parcel in) {
            return new LAlbum(in);
        }

        @Override
        public LAlbum[] newArray(int size) {
            return new LAlbum[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(artist);
        parcel.writeString(title);
        parcel.writeTypedList(imageList);
    }
}
