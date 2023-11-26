package com.example.week1.network

import com.google.gson.annotations.SerializedName

data class ResultPhotosRamdom(
    @SerializedName("urls")
    val urls: URLS
)

data class URLS(
    @SerializedName("raw")
    val url: String
)