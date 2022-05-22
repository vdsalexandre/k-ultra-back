package com.vds.kultraback.application.repository

import com.vds.kultraback.application.model.Game
import com.vds.kultraback.application.model.Game.Companion.toGame
import com.vds.kultraback.application.model.GameEntity
import com.vds.kultraback.application.model.Games
import com.vds.kultraback.application.model.PublisherEntity
import com.vds.kultraback.application.utils.Util
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository

@Repository
class GameRepository {

    fun findAll() = transaction {
        Games.selectAll().map { toGame(it) }
    }

    fun addGame(game: Game) = transaction {
        val id = Games.insertAndGetId {
            it[title] = game.title
            it[price] = game.price
            it[publisher] = PublisherEntity.findById(game.publisher)!!.id
            it[tags] = game.tags.joinToString(separator = ",")
            it[releaseDate] = game.releaseDate!!
        }
        return@transaction Games.select { Games.id.eq(id) }.limit(1).first().let { toGame(it) }
    }

    fun updateGame(game: Game) = transaction {
        Games.update({Games.id eq game.id}) {
            it[title] = game.title
            it[price] = game.price
            it[publisher] = PublisherEntity.findById(game.publisher)!!.id
            it[tags] = game.tags.joinToString(separator = ",")
            it[releaseDate] = game.releaseDate!!
        }
        return@transaction Games.select { Games.id.eq(game.id) }.limit(1).first().let { toGame(it) }
    }

    fun deleteGame(gameId: Long) = transaction {
        GameEntity[gameId].delete()
    }

    fun findById(gameId: Long) = transaction {
        GameEntity.findById(gameId)?.toGame() ?: Util.emptyGame
    }
}