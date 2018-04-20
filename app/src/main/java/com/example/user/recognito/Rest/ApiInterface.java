package com.example.user.recognito.Rest;

import com.example.user.recognito.DataModels.SpotifyData.AccessToken;
import com.example.user.recognito.DataModels.SpotifyData.Album;
import com.example.user.recognito.DataModels.SpotifyData.Request;

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

    @GET("authorize")
    Call<Album> getAccessAlbum(@Query("client_id") String clientId,
                                     @Query("response_type") String responseType,
                                     @Query("redirect_uri") String redirectUri);

    @GET("v1/albums/{vid}")
    Call<Album> getAlbum(@Header("access_token") String accessToken,
                         @Path("vid") String albumId);

    @POST("/api/token")
    Call<AccessToken> getAccessToken(@Body Request request);
}
