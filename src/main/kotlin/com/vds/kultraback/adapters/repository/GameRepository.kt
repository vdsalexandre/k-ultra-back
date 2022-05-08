package com.vds.kultraback.adapters.repository

import com.vds.kultraback.domain.model.Game
import com.vds.kultraback.domain.model.Games
import com.vds.kultraback.domain.ports.GamePortService
import java.math.BigDecimal
import java.time.LocalDate
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class GameRepository(dbConnect: DbConnect) : GamePortService {

    private val connection = dbConnect.getConnection()

    init {
        initGames()
    }

    override fun findById(id: Long): Game? {
        TODO("Not yet implemented")
    }

    override fun save(game: Game): Game {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Game> {
        return transaction {
            Game.all().toList()
        }
    }

    override fun findByName(name: String): Game? {
        TODO("Not yet implemented")
    }

    private fun initGames() {
        transaction {
            SchemaUtils.create(Games)
            Games.insert { game ->
                game[gameId] = 1500
                game[title] = "League of Legends"
                game[price] = BigDecimal.valueOf(50.50)
                game[publisher] = 1000
                game[tags] = "moba"
                game[releaseDate] = LocalDate.of(2022, 1, 1)
            }
        }
    }
}