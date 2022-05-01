package com.vds.kultraback.adapters.repository

import com.vds.kultraback.domain.model.Game
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID.randomUUID

data class GameDTO(
    val id: String?,
    val title: String,
    val price: BigDecimal,
    val publisher: Long,
    val tags: String,
    val releaseDate: LocalDate?
) {
    fun toDomain() = Game(
        id = randomUUID(),
        title = title,
        price = price,
        publisher = publisher,
        tags = tags,
        releaseDate = releaseDate
    )
}