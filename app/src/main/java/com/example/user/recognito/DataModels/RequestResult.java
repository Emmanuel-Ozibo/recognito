package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emmanueel on 12/13/2017.
 */

public class RequestResult implements Parcelable{

    private String imagePath;

    public RequestResult(String imagePath) {
        this.imagePath = imagePath;
    }

    protected RequestResult(Parcel in) {
        imagePath = in.readString();
    }

    public static final Creator<RequestResult> CREATOR = new Creator<RequestResult>() {
        @Override
        public RequestResult createFromParcel(Parcel in) {
            return new RequestResult(in);
        }

        @Override
        public RequestResult[] newArray(int size) {
            return new RequestResult[size];
        }
    };

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imagePath);
    }
}
