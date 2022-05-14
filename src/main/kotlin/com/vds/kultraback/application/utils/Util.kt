package com.vds.kultraback.application.utils

import com.vds.kultraback.application.model.Game
import java.math.BigDecimal

object Util {
    val emptyGame = Game(-1, "", BigDecimal.valueOf(-1), -1, listOf(), null)
}