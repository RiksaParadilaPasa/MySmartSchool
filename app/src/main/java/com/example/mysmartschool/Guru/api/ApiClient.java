package com.example.mysmartschool.Guru.api;

import com.example.mysmartschool.Config;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class ApiClient {
    public OkHttpClient resourceClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(chain -> {
            Request req = chain.request();
            Request newReq;
            newReq = req.newBuilder()
                    .header("Authorization", String.format("Bearer %s", Config.API_TOKEN))
                    .build();
            return chain.proceed(newReq);
        });
//        client.addInterceptor(interceptor);
        return client.build();
    }
}
