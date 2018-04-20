package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emmanuel on 12/10/2017.
 */

public class SongDetails implements Parcelable{

     public Status status;
     public MetaData metadata;
     public int result_type;

    protected SongDetails(Parcel in) {
        status = in.readParcelable(Status.class.getClassLoader());
        result_type = in.readInt();
    }

    public static final Creator<SongDetails> CREATOR = new Creator<SongDetails>() {
        @Override
        public SongDetails createFromParcel(Parcel in) {
            return new SongDetails(in);
        }

        @Override
        public SongDetails[] newArray(int size) {
            return new SongDetails[size];
        }
    };

    public Status getStatus() {
        return status;
    }

    public MetaData getMetadata() {
        return metadata;
    }

    public int getResult_type() {
        return result_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(status, i);
        parcel.writeInt(result_type);
    }
}
