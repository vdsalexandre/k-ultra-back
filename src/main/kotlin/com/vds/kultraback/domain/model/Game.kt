package com.vds.kultraback.domain.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.date

object Games : LongIdTable(name = "game") {
    val gameId = long(name = "gameId").uniqueIndex()
    val title = varchar(name = "title", length = 255)
    val price = decimal(name = "price", precision = 10, scale = 2)
    //val publisher = reference(name = "publisher", refColumn = Publisher.publisherId)
    val publisher = long(name = "publisher")
    val tags = varchar(name = "tags", length = 255)
    val releaseDate = date(name = "releaseDate")
}

class Game(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Game>(Games)

    var gameId by Games.gameId
    var title by Games.title
    var price by Games.price
    var publisher by Games.publisher
    var tags by Games.tags
    val releaseDate by Games.releaseDate
}