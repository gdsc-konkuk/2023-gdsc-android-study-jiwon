package com.example.week1.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {
    @GET(PHOTO+RANDOM)
    fun requestRandomPhoto(@Query(CLIENT_ID)id: String = SECRET_KEY): Call<ResultPhotosRamdom>


    companion object {
        const val PHOTO = "/photos"
        const val RANDOM = "/random"

        const val CLIENT_ID = "client_id"
        const val SECRET_KEY = "8ovP1T32Vr0urlbjua0df7_92rL908HsukZnsBkj0nQ"

        const val TAG = "로그"
    }
}