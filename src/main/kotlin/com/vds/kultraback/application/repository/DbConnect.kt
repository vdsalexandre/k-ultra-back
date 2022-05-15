package com.vds.kultraback.application.repository

import com.vds.kultraback.application.model.Games
import java.math.BigDecimal
import java.time.LocalDate
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.core.env.Environment

fun initDB(environment: Environment) {
    Database.connect(
        url = environment.getProperty("spring.datasource.url")!!,
        driver = environment.getProperty("spring.datasource.driver-class-name")!!,
        user = environment.getProperty("spring.datasource.username")!!,
        password = environment.getProperty("spring.datasource.password")!!
    )
    insertData()
}

private fun insertData() {
    transaction {
        SchemaUtils.create(Games)
        Games.insert { game ->
            game[title] = "League of Legends"
            game[price] = BigDecimal.valueOf(50.50)
            game[publisher] = 1000
            game[tags] = "moba"
            game[releaseDate] = LocalDate.of(2022, 1, 1)
        }
        Games.insert { game ->
            game[title] = "Left 4 Dead 2"
            game[price] = BigDecimal.valueOf(45.75)
            game[publisher] = 2000
            game[tags] = "survival,horror"
            game[releaseDate] = LocalDate.of(2010, 10, 25)
        }
    }
}