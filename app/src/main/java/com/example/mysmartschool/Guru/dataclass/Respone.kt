package com.example.mysmartschool.Guru.dataclass

import com.google.gson.annotations.SerializedName

data class Respone<T>(
        @SerializedName("message") var message: String,
        @SerializedName("code") var code: Int,
        @SerializedName("data") var data: T
)