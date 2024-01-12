package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class WeatherRouteTest {
    @Test
    fun testGetWeather() = testApplication {
        val response = client.get("/weather/London")
        assertEquals(
            """{"id":"1","address":"London","days":[{"datetime":"2024-01-12","temp":5.4,"feelslike":3.7},{"datetime":"2024-01-11","temp":7.4,"feelslike":10.1}]}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

}
