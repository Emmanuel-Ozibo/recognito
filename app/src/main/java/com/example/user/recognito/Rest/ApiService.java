package com.example.user.recognito.Rest;

import android.support.annotation.Nullable;

import com.example.user.recognito.Utils.Base64Converter;
import com.example.user.recognito.Utils.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by emmanuel on 12/20/2017.
 */



public class ApiService{

    private static OkHttpClient getOkhttp(String accessToken){

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new RequestInterceptor(accessToken));
        return builder.build();
    }

    public static Retrofit getRetrofit(String baseUrl, String accessToken){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkhttp(accessToken));
                return builder.build();
    }
}
