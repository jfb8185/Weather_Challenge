package com.example.weatherchallenge.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherInfo>,
    val message: Int
)