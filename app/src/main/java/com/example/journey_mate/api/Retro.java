package com.example.journey_mate.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {
    private static final String BASE_URL
            = "http://10.0.2.2:3030/";
    public static final String IMG_URL
            = "http://10.0.2.2:3030/image/";
    public static final String POST_IMG_URL
            = "http://10.0.2.2:3030/image/post/";
    public static String token = "Bearer ";

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
