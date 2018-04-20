package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by emmanuel on 3/5/2018.
 */

public class DataBaseSongModel implements Parcelable{
    private String songTitle, albumTitle, imageUrl;private int duration;private long timeStamp;

    public DataBaseSongModel(String songTitle, String albumTitle, int duration, String imageUrl) {
        this.songTitle = songTitle;
        this.albumTitle = albumTitle;
        this.duration = duration;
        this.imageUrl = imageUrl;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getDuration() {
        return duration;
    }

    protected DataBaseSongModel(Parcel in) {
        songTitle = in.readString();
        albumTitle = in.readString();
        imageUrl = in.readString();
        duration = in.readInt();
        timeStamp= in.readLong();
    }

    public static final Creator<DataBaseSongModel> CREATOR = new Creator<DataBaseSongModel>() {
        @Override
        public DataBaseSongModel createFromParcel(Parcel in) {
            return new DataBaseSongModel(in);
        }

        @Override
        public DataBaseSongModel[] newArray(int size) {
            return new DataBaseSongModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(songTitle);
        parcel.writeString(albumTitle);
        parcel.writeString(imageUrl);
        parcel.writeInt(duration);
        parcel.writeLong(timeStamp);
    }
}
