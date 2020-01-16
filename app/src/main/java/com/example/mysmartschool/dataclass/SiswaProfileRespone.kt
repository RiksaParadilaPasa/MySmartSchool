package com.example.mysmartschool.dataclass

import com.google.gson.annotations.SerializedName

data class SiswaProfileRespone(
        @SerializedName("nama") val nama: String,
        @SerializedName("email") val email: String,
        @SerializedName("nama_lengkap") val namaLengkap: String,
        @SerializedName("nis") val nis: String,
        @SerializedName("kelas") val kelas: String
)