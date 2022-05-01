package com.vds.kultraback.utils

import com.vds.kultraback.adapters.repository.GameDTO
import java.math.BigDecimal

object Util {

    val emptyGame = GameDTO(
        id = "",
        title = "",
        price = BigDecimal.ZERO,
        publisher = -1,
        tags = "",
        releaseDate = null
    )
}