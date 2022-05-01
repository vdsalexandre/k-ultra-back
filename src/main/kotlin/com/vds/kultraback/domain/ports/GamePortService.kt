package com.vds.kultraback.domain.ports

import com.vds.kultraback.domain.model.Game
import java.util.UUID
import org.springframework.stereotype.Service

@Service
interface GamePortService {
    fun findBy(id: UUID): Game?
    fun save(game: Game): Game
    fun findAll(): List<Game>
    fun findByName(name: String): Game?
}