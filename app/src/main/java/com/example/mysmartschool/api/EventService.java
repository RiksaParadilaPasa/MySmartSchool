package com.example.mysmartschool.api;

import com.example.mysmartschool.dataclass.LoginRespone;
import com.example.mysmartschool.dataclass.Respone;
import com.example.mysmartschool.dataclass.SiswaProfileRespone;

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
