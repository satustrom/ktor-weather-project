package com.example.dao

import com.example.models.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*

object DatabaseSingleton {
    fun init() {
        val driverClassName = "org.h2.Driver" // TODO: update to env vars
        val jdbcURL = "jdbc:h2:file:./build/db" // TODO: update to env vars
        val database = Database.connect(jdbcURL, driverClassName)

        transaction(database) {
          SchemaUtils.create(Weathers)
      }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}