package com.example.user.recognito.Rest;


import com.example.user.recognito.DataModels.YouTubeData.YouTubeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by emmanuel on 12/20/2017.
 */



public interface ApiInterface{

    @GET("v3/search")
    Call<YouTubeResponse> getYouTubeVideo(@Query("q") String searchItem,
                                           @Query("maxResults") Integer maxResult,
                                           @Query("part") String part,
                                          @Query("key") String apiKey);

}
