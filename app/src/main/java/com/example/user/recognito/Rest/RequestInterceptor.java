package com.example.user.recognito.Rest;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by emmanuel on 2018-04-29.
 */

public class RequestInterceptor implements Interceptor{
    private String apiKey;

    public RequestInterceptor(String apiKey){
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException{
        Request httpRequest = chain.request();
        HttpUrl httpUrl = httpRequest.url();
        HttpUrl newHttpUrl = httpUrl.newBuilder()
                .addQueryParameter("key", apiKey)
                .build();
        Request.Builder requestBuilder = httpRequest.newBuilder();
        requestBuilder.url(newHttpUrl);
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }


}
