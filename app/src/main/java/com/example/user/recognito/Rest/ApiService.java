package com.example.user.recognito.Rest;

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

    private static String authValue = Base64Converter
            .toBase64(Constant.CLIENT_ID,Constant.CLIENT_SECRET);

    private static OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder requestBuilder = request.newBuilder()
                            .header("Authorization", " Basic " +authValue);
                    Request newRequest = requestBuilder.build();
                    return chain.proceed(newRequest);
                }
            })
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS);



    private static OkHttpClient getOkhttp(){
        return okhttpBuilder.build();
    }

    //private static void Add

    public static Retrofit getRetrofit(String baseUrl){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkhttp());
                return builder.build();
    }
}
