package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emmanuel on 12/19/2017.
 */

public class Status implements Parcelable{
    public String msg;
    public int code;
    public String version;

    protected Status(Parcel in) {
        msg = in.readString();
        code = in.readInt();
        version = in.readString();
    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel in) {
            return new Status(in);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(msg);
        parcel.writeInt(code);
        parcel.writeString(version);
    }
}
