package com.example.mysmartschool.retrofit;

import com.example.mysmartschool.Config;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(OkHttpClient okHttpClient) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.SERVER_SCHEME + "://" + Config.SERVER_URL + ":" + Config.SERVER_PORT + "/" + Config.SERVER_SUB_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
