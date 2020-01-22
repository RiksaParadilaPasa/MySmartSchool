package com.example.mysmartschool.api;

import com.example.mysmartschool.Config;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {

    private static Retrofit retrofit = null;
    private static OkHttpClient client = null;

    public static Retrofit getClient(OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Config.SERVER_SCHEME + "://" + Config.SERVER_URL + ":" + Config.SERVER_PORT + "/" + Config.SERVER_SUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
