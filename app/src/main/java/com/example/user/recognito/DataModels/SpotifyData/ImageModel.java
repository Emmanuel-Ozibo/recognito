package com.example.user.recognito.DataModels.SpotifyData;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emmanuel on 12/20/2017.
 */

public class ImageModel implements Parcelable{

    private Integer height;
    private String url;
    private Integer width;

    public String getUrl() {
        return url;
    }

    public ImageModel(Integer height, String url, Integer width) {
        this.height = height;
        this.url = url;
        this.width = width;
    }

    protected ImageModel(Parcel in) {
        if (in.readByte() == 0) {
            height = null;
        } else {
            height = in.readInt();
        }
        url = in.readString();
        if (in.readByte() == 0) {
            width = null;
        } else {
            width = in.readInt();
        }
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (height == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(height);
        }
        parcel.writeString(url);
        if (width == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(width);
        }
    }
}
