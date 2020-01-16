package com.example.mysmartschool.dataclass


import com.google.gson.annotations.SerializedName

data class LoginRespone(
        @SerializedName("token_type") val tokenType: String,
        @SerializedName("access_token") val token: String,
        @SerializedName("expires_in") val expire: String

)