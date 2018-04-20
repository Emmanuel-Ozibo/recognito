package com.example.user.recognito.DataModels.SpotifyData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emmanuel on 12/23/2017.
 */

public class AccessToken{
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("token_type")
    public String tokenType;
    @SerializedName("expires_in")
    public int expires_in;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpires_in() {
        return expires_in;
    }
}
