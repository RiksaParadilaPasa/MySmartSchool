package com.example.mysmartschool.Guru.api;

import com.example.mysmartschool.Guru.dataclass.LoginRespone;
import com.example.mysmartschool.Guru.dataclass.Respone;
import com.example.mysmartschool.Guru.dataclass.SiswaProfileRespone;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface EventService {

    @Multipart
    @POST("auth/login")
    Call<Respone<LoginRespone>> getToken(
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("remember_me") RequestBody remember);

    @POST("v1/user/profile")
    Call<SiswaProfileRespone> getProfile();
}
