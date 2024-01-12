package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Weather(val id: String, val address: String, val days: List<WeatherDay>)

@Serializable
data class WeatherDay(val datetime: String, val temp: Double, val feelslike: Double)

// TODO, replace this storage with database
val weatherStorage = listOf(Weather(
    "1", "london", listOf(
        WeatherDay("2024-01-12", 5.4, 3.7),
        WeatherDay("2024-01-11", 7.4, 10.1)
    )),
    Weather("2", "espoo", listOf(
        WeatherDay("2024-01-12", 1.4, 1.7),
        WeatherDay("2024-01-11", 3.4, 4.1)
    )),
)