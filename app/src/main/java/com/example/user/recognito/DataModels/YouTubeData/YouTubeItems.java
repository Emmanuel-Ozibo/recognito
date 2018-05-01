package com.example.user.recognito.DataModels.YouTubeData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2018-05-05.
 */

public class YouTubeItems{
    @SerializedName("id")
    public YouTubeId youTubeId;

    public YouTubeId getYouTubeId() {
        return youTubeId;
    }
}
