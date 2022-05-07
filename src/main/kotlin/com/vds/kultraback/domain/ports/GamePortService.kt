package com.vds.kultraback.domain.ports

import com.vds.kultraback.domain.model.Game
import java.util.UUID

interface GamePortService {
    fun findBy(id: UUID): Game?
    fun save(game: Game): Game
    fun findAll(): List<Game>
    fun findByName(name: String): Game?
}