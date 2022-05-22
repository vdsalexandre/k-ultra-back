package com.vds.kultraback.application.services

import com.vds.kultraback.application.model.Game
import com.vds.kultraback.application.repository.GameRepository
import com.vds.kultraback.application.repository.initDB
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

@Service
class GameService(val gameRepository: GameRepository, environment: Environment) {

    init {
        initDB(environment)
    }

    fun findAll(): List<Game> {
        return gameRepository.findAll()
    }

    fun findById(id: Long): Game {
        return gameRepository.findById(id)
    }

    fun add(game: Game): Game {
        return gameRepository.addGame(game)
    }

    fun delete(id: Long) {
        return gameRepository.deleteGame(id)
    }

    fun update(game: Game): Game {
        return gameRepository.updateGame(game)
    }
}
