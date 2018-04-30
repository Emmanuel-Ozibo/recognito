package com.example.user.recognito.Rest;

import com.example.user.recognito.DataModels.SpotifyData.AccessToken;
import com.example.user.recognito.DataModels.SpotifyData.Album;
import com.example.user.recognito.DataModels.SpotifyData.Request;
import com.example.user.recognito.DataModels.SpotifyData.TrackWapper;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by emmanuel on 12/20/2017.
 */

public interface ApiInterface{
    @GET("v1/artists/{id}/top-tracks")
    Call<TrackWapper> getTopTracks(@Path("id") String id, @Query("country") String country);
}
