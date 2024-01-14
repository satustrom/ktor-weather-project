package com.example

import com.example.plugins.*
import com.example.dao.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(CORS) {
        anyHost()
    }
    DatabaseSingleton.init()
    configureSerialization()
    configureRouting()
}
