package com.example.user.recognito.DataModels.YouTubeData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2018-05-05.
 */

public class YouTubeResponse{
    @SerializedName("regionCode")
    public String regionCode;
    @SerializedName("items")
    public List<YouTubeItems>youTubeItemsList;


    public String getRegionCode(){
        return regionCode;
    }

    public List<YouTubeItems> getYouTubeItemsList() {
        return youTubeItemsList;
    }
}
