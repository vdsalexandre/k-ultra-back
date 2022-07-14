package com.vds.kultraback.repository

import com.vds.kultraback.model.Games
import com.vds.kultraback.model.Publishers
import java.math.BigDecimal
import java.time.LocalDate
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
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
        SchemaUtils.create(Publishers, Games)
        val publisherId = Publishers.insertAndGetId { publisher ->
            publisher[name] = "Ubisoft"
            publisher[siret] = 123456789
            publisher[phone] = "+33101020304"
        }
        val otherPublisherId = Publishers.insertAndGetId { publisher ->
            publisher[name] = "Riot Games"
            publisher[siret] = 987654321
            publisher[phone] = "+33109080706"
        }
        Games.insert { game ->
            game[title] = "League of Legends"
            game[price] = BigDecimal.valueOf(50.50)
            game[publisher] = publisherId
            game[tags] = "moba"
            game[releaseDate] = LocalDate.of(2022, 1, 1)
        }
        Games.insert { game ->
            game[title] = "Left 4 Dead 2"
            game[price] = BigDecimal.valueOf(45.75)
            game[publisher] = otherPublisherId
            game[tags] = "survival,horror"
            game[releaseDate] = LocalDate.of(2010, 10, 25)
        }
    }
}