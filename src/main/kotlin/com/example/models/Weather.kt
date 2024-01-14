package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class Weather(val id: Int, val address: String, val temp: Double, val feelslike: Double)

object Weathers : Table() {
    val id = integer("id").autoIncrement()
    val address = varchar("address", 128)
    val temp = double("temp")
    val feelslike = double("feelslike")

    override val primaryKey = PrimaryKey(id)
}