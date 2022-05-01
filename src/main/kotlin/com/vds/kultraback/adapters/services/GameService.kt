package com.vds.kultraback.adapters.services

import com.vds.kultraback.adapters.repository.GameDTO
import com.vds.kultraback.adapters.repository.GameRepository
import com.vds.kultraback.domain.model.Game
import com.vds.kultraback.domain.ports.GamePortService
import com.vds.kultraback.utils.Util.emptyGame
import java.util.UUID
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class GameService(val gameRepository: GameRepository) : GamePortService {

    fun fetchGameById(id: UUID): GameDTO {
        return findBy(id)?.let(toApi()) ?: emptyGame
    }

    fun addGame(game: GameDTO): GameDTO {
        return save(game.toDomain()).let(toApi())
    }

    fun fetchAllGames(): List<GameDTO> {
        return findAll().map(toApi())
    }

    override fun findBy(id: UUID): Game? {
        return gameRepository.findByIdOrNull(id)
    }

    override fun save(game: Game): Game {
        return gameRepository.save(game)
    }

    override fun findAll(): List<Game> {
        return gameRepository.findAll().toList()
    }

    override fun findByName(name: String): Game? {
        return gameRepository.findByName(name)
    }

    fun getEmptyGame() = emptyGame

    private fun toApi(): (Game) -> GameDTO = {
        GameDTO(
            id = it.id.toString(),
            title = it.title,
            price = it.price,
            publisher = it.publisher,
            tags = it.tags,
            releaseDate = it.releaseDate
        )
    }
}
