package com.vds.kultraback.model

import java.math.BigDecimal
import java.time.LocalDate
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.date

object Games : LongIdTable() {
    val title = varchar(name = "title", length = 255)
    val price = decimal(name = "price", precision = 10, scale = 2)
    val publisher = reference("publisher", Publishers)
    val tags = varchar(name = "tags", length = 255)
    val releaseDate = date(name = "releaseDate")
}

class GameEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<GameEntity>(Games)

    var title by Games.title
    var price by Games.price
    var publisher by Games.publisher
    var tags by Games.tags
    var releaseDate by Games.releaseDate

    fun toGame() = Game(id.value, title, price, publisher.value, tags.split(","), releaseDate)
}

data class Game(
    val id: Long,
    val title: String,
    val price: BigDecimal,
    val publisher: Long,
    val tags: List<String>,
    val releaseDate: LocalDate?
)