package com.vds.kultraback.domain.ports

import com.vds.kultraback.domain.model.Game

interface GamePortService {
    fun findById(id: Long): Game?
    fun save(game: Game): Game
    fun findAll(): List<Game>
    fun findByName(name: String): Game?
}