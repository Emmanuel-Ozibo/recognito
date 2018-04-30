package com.example.user.recognito.DataModels.SpotifyData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by emmanuel on 2018-04-26.
 */

public class TrackWapper{
    @SerializedName("tracks")
    public List<Album> albumList;

    public List<Album> getAlbumList() {
        return albumList;
    }
}
