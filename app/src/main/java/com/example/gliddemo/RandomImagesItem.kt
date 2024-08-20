package com.example.gliddemo

import com.google.gson.annotations.SerializedName

data class RandomImagesItem(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)