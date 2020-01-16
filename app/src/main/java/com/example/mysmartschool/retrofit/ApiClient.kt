package com.example.mysmartschool.retrofit

import com.example.mysmartschool.Config
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun loginClient(): OkHttpClient {
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.readTimeout(Config.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
    builder.connectTimeout(Config.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
    builder.addInterceptor(logInterceptor)
    builder.addInterceptor(object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val req = chain.request().newBuilder()
            req.addHeader("Accept", "application/json")
            return chain.proceed(req.build())
        }

    })

    return builder.build()
}

fun requestClient(): OkHttpClient {
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.readTimeout(Config.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
    builder.connectTimeout(Config.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
    builder.addInterceptor(logInterceptor)
    builder.addInterceptor(object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val req = chain.request().newBuilder()
            req.addHeader("Accept", "application/json")
            req.addHeader("Authorization","Bearer ")
            return chain.proceed(req.build())
        }

    })

    return builder.build()
}

inline fun <reified T> retrofitRequest(okHttpClient: OkHttpClient): T {
    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
    val retrofit = Retrofit.Builder()
            .baseUrl("${Config.SERVER_SCHEME}://${Config.SERVER_URL}:${Config.SERVER_PORT}/${Config.SERVER_SUB_URL}")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    return retrofit.create(T::class.java)

}