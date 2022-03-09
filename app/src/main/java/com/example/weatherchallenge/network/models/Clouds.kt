package com.example.weatherchallenge.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Clouds(
    val all: Int
)