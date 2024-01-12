package com.example.routes

import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.weatherRouting() {
    route("/weather") {
        get("{address?}") {
            val address = call.parameters["address"] ?: return@get call.respondText(
                "Missing address",
                status = HttpStatusCode.BadRequest
            )
            val weather =
                weatherStorage.find { it.address == address } ?: return@get call.respondText(
                  "No weather in address $address",
                  status = HttpStatusCode.NotFound
                )
            call.respond(weather)
          
        }

    }
}