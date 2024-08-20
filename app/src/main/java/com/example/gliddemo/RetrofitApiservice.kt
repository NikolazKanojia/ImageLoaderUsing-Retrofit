package com.example.gliddemo

import android.telecom.Call
import retrofit2.http.GET

interface RetrofitApiservice {

    @GET("list")
    fun getimage(): retrofit2.Call<List<RandomImagesItem>>
}