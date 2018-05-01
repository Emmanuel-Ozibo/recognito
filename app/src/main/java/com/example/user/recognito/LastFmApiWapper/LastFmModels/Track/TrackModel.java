package com.example.user.recognito.LastFmApiWapper.LastFmModels.Track;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.user.recognito.DataModels.SpotifyData.ImageModel;

import java.util.List;

/**
 * Created by emmanuel on 12/29/2017.
 */

public class TrackModel implements Parcelable{

    public String name;
    public int duration;
    public int popularity;
    public List<ImageModel> imageModelList;
    public List<String> avaliableMarkets;
    public List<String>artists;

    public TrackModel(String name, int duration, int popularity,
                      List<ImageModel> imageModelList, List<String> avaliableMarkets,
                      List<String> artists){
        this.name = name;
        this.artists = artists;
        this.duration = duration;
        this.popularity = popularity;
        this.imageModelList = imageModelList;
        this.avaliableMarkets = avaliableMarkets;
    }


    protected TrackModel(Parcel in) {
        name = in.readString();
        duration = in.readInt();
        popularity = in.readInt();
        imageModelList = in.createTypedArrayList(ImageModel.CREATOR);
        avaliableMarkets = in.createStringArrayList();
        artists = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(duration);
        dest.writeInt(popularity);
        dest.writeTypedList(imageModelList);
        dest.writeStringList(avaliableMarkets);
        dest.writeStringList(artists);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TrackModel> CREATOR = new Creator<TrackModel>() {
        @Override
        public TrackModel createFromParcel(Parcel in) {
            return new TrackModel(in);
        }

        @Override
        public TrackModel[] newArray(int size) {
            return new TrackModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getPopularity() {
        return popularity;
    }

    public List<ImageModel> getImageModelList() {
        return imageModelList;
    }

    public List<String> getAvaliableMarkets() {
        return avaliableMarkets;
    }

    public List<String> getArtists() {
        return artists;
    }
}
