package com.example.mysmartschool.Guru.api

import com.example.mysmartschool.Config
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
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

fun resourceClient(): OkHttpClient {
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.readTimeout(Config.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
    builder.connectTimeout(Config.REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
    builder.addInterceptor(logInterceptor)
    builder.addNetworkInterceptor(object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val req = chain.request().newBuilder()
            req.addHeader("Accept", "application/json")
            req.addHeader("Authorization",String.format("Bearer %s", Config.API_TOKEN))
            return chain.proceed(req.build())
        }
    })
    builder.authenticator(object : Authenticator {
        override fun authenticate(route: Route?, response: Response): Request? {
            return response.request.newBuilder().header("Authorization",String.format("Bearer %s", Config.API_TOKEN)).build()
        }
    })
    return builder.build()
}