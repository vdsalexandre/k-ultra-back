package com.vds.kultraback.repository

import com.vds.kultraback.model.Game
import com.vds.kultraback.model.GameEntity
import com.vds.kultraback.model.PublisherEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class GameRepository {

    fun findAll() = transaction {
        GameEntity.all().map { it.toGame() }
    }

    fun addGame(game: Game) = transaction {
        GameEntity.new {
            title = game.title
            price = game.price
            publisher = PublisherEntity.findById(game.publisher)!!.id
            tags = game.tags.joinToString(separator = ",")
            releaseDate = game.releaseDate!!
        }.toGame()
    }

    fun updateGame(game: Game) = transaction {
        val gameEntity = GameEntity[game.id]
        gameEntity.title = game.title
        gameEntity.price = game.price
        gameEntity.publisher = PublisherEntity[game.publisher].id
        gameEntity.tags = game.tags.joinToString(separator = ",")
        gameEntity.releaseDate = game.releaseDate!!

        return@transaction findById(game.id)
    }

    fun deleteGame(gameId: Long) = transaction {
        GameEntity[gameId].delete()
    }

    fun findById(gameId: Long): Game? = transaction {
        GameEntity.findById(gameId)?.toGame()
    }
}