package com.example.routes

import com.example.models.*
import com.example.dao.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.weatherRouting() {
    route("/") {
        get {
            call.respond(dao.allWeathers())
        }
        
        delete("{id}") {
            val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest)
            dao.deleteWeather(id);
            call.respond(true);
        }

        post {
            val address = call.parameters["address"] ?: return@post call.respondText(
                "Missing address",
                status = HttpStatusCode.BadRequest
            )
            val temp = call.parameters["temp"]?.toDouble() ?: return@post call.respondText(
                "Missing temperature",
                status = HttpStatusCode.BadRequest
            )
            val feelslike = call.parameters["feelslike"]?.toDouble() ?: return@post call.respondText(
                "Missing feels like",
                status = HttpStatusCode.BadRequest
            )


            dao.addNewWeather(address, temp, feelslike)
            call.respondText("Weather stored correctly", status = HttpStatusCode.Created)
        }

        get("{address?}") {
            val address = call.parameters["address"] ?: return@get call.respondText(
                "Missing address",
                status = HttpStatusCode.BadRequest
            )

            val weather =
                dao.weather(address) ?: return@get call.respondText(
                    "No weather found in $address",
                    status = HttpStatusCode.NotFound
                )
            
            call.respond(weather)    
        }
    }
}