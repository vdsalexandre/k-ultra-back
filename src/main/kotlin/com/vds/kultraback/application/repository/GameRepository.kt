package com.vds.kultraback.application.repository

import com.vds.kultraback.application.model.Game
import com.vds.kultraback.application.model.GameEntity
import com.vds.kultraback.application.utils.Util
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class GameRepository {

    fun findAll() = transaction {
        GameEntity.all().map(GameEntity::toGame)
    }

    fun addGame(game: Game) = transaction {
        GameEntity.new {
            this.title = game.title
            this.price = game.price
            this.publisher = game.publisher
            this.tags = game.tags.joinToString(separator = ",")
            this.releaseDate = game.releaseDate!!
        }.toGame()
    }

    fun deleteGame(gameId: Long) = transaction {
        GameEntity[gameId].delete()
    }

    fun findById(gameId: Long) = transaction {
        GameEntity.findById(gameId)?.toGame() ?: Util.emptyGame
    }
}