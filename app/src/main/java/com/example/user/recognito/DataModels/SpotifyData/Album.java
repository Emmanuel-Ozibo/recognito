package com.example.user.recognito.DataModels.SpotifyData;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by emmanuel on 12/20/2017.
 */

public class Album implements Parcelable{

    @SerializedName("images")
    public List<ImageModel>imageModelList;

    public List<ImageModel> getImageModelList() {
        return imageModelList;
    }

    protected Album(Parcel in) {
        imageModelList = in.createTypedArrayList(ImageModel.CREATOR);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(imageModelList);
    }
}
