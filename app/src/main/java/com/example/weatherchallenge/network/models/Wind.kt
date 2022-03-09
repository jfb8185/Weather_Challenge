package com.example.weatherchallenge.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)