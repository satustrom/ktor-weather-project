package com.example.dao

import com.example.models.*

interface DAOFacade {
    suspend fun allWeathers(): List<Weather>
    suspend fun weather(address: String): Weather?
    suspend fun addNewWeather(address: String, temp: Double, feelslike: Double): Weather?
    suspend fun deleteWeather(id: Int): Boolean
}