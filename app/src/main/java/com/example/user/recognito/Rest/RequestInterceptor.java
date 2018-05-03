package com.example.user.recognito.Rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by emmanuel on 2018-04-29.
 */

public class RequestInterceptor implements Interceptor{
    private String accessToken;

    public RequestInterceptor(String accessToken){
        this.accessToken = accessToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException{
        Request httpRequest = chain.request();
        Request.Builder builder = httpRequest.newBuilder();
        builder.addHeader("Authorization", "Bearer " + accessToken);
        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }


}
