package com.example

import com.example.models.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.*

import io.ktor.client.call.*
import io.ktor.client.request.*

class WeatherRouteTest {
    @Test
    fun testGetRandomWeather() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        
        val response = client.get("/random-city-does-not-exist")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Test
    fun testPostWeather() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val response = client.post("/") {
            contentType(ContentType.Application.Json)
            setBody(Weather(1, "dubrovnik", 16.1, 11.1))
        }

        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
}
