package com.vds.kultraback.adapters.services

import com.vds.kultraback.adapters.repository.GameDTO
import com.vds.kultraback.adapters.repository.GameRepository
import com.vds.kultraback.domain.model.Game
import com.vds.kultraback.utils.Util.emptyGame
import org.springframework.stereotype.Service

@Service
class GameService(val gameRepository: GameRepository) {

    fun findAll(): List<GameDTO> {
        return gameRepository.findAll().map(toApi())
    }

    fun getEmptyGame() = emptyGame

    fun toApi(): (Game) -> GameDTO = {
        GameDTO(
            gameId = it.gameId.toString(),
            title = it.title,
            price = it.price,
            publisher = it.publisher,
            tags = it.tags.split(","),
            releaseDate = it.releaseDate
        )
    }
}
