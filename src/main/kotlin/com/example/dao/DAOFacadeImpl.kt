package com.example.dao

import com.example.dao.DatabaseSingleton.dbQuery
import com.example.models.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToWeather(row: ResultRow) = Weather(
        id = row[Weathers.id],
        address = row[Weathers.address],
        temp = row[Weathers.temp],
        feelslike = row[Weathers.feelslike],
    )

    override suspend fun allWeathers(): List<Weather> = dbQuery {
        Weathers.selectAll().map(::resultRowToWeather)
    }

    override suspend fun weather(address: String): Weather? = dbQuery {
      Weathers
          .select { Weathers.address eq address }
          .map(::resultRowToWeather)
          .singleOrNull()
    }
    
    override suspend fun addNewWeather(address: String, temp: Double, feelslike: Double): Weather? = dbQuery {
      val insertStatement = Weathers.insert {
          it[Weathers.address] = address
          it[Weathers.temp] = temp
          it[Weathers.feelslike] = feelslike
      }
      insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToWeather)
    }

    override suspend fun deleteWeather(id: Int): Boolean = dbQuery {
        Weathers.deleteWhere { Weathers.id eq id } > 0
    }
}

val dao: DAOFacade = DAOFacadeImpl().apply {
  runBlocking {
      if(allWeathers().isEmpty()) {
          addNewWeather("london", 5.6, 8.0)
      }
  }
}