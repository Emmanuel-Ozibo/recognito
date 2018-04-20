package com.example.user.recognito.DataModels.SpotifyData;

/**
 * Created by emmanuel on 12/29/2017.
 */

public class Request{
    private String grant_type;

    public Request(String grant_type){
        this.grant_type = grant_type;
    }

    public String getGrant_type() {
        return grant_type;
    }
}
