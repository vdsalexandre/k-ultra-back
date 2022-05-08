package com.vds.kultraback.utils

import com.vds.kultraback.adapters.repository.GameDTO
import java.math.BigDecimal

object Util {

    val emptyGame = GameDTO(
        gameId = "",
        title = "",
        price = BigDecimal.ZERO,
        publisher = -1,
        tags = listOf(),
        releaseDate = null
    )
}