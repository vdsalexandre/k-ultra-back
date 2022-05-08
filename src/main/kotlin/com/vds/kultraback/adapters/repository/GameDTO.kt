package com.vds.kultraback.adapters.repository

import java.math.BigDecimal
import java.time.LocalDate

data class GameDTO(
    val gameId: String?,
    val title: String,
    val price: BigDecimal,
    val publisher: Long,
    val tags: List<String>,
    val releaseDate: LocalDate?
)