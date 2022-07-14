package com.vds.kultraback.utils

import com.vds.kultraback.model.Game
import java.math.BigDecimal.valueOf

object Util {
    val emptyGame = Game(
        id = -1,
        title = "",
        price = valueOf(-1),
        publisher = -1,
        tags = listOf(),
        releaseDate = null
    )
}