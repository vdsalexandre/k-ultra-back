package com.vds.kultraback.adapters.api

import com.vds.kultraback.adapters.repository.GameDTO
import com.vds.kultraback.adapters.services.GameService
import java.util.UUID
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/game"], produces = ["application/json"])
class GameResource(val gameService: GameService) {

    private val logger = LoggerFactory.getLogger(GameResource::class.java)

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    fun fetchGame(@PathVariable id: UUID): GameDTO {
        logger.info("fetchGame> $id")
        return try {
            gameService.fetchGameById(id)
        } catch (e: Exception) {
            logger.error(e.message)
            gameService.getEmptyGame()
        }
    }

    @PostMapping("/add")
    fun addGame(@RequestBody game: GameDTO): GameDTO {
        logger.info("addGame> $game")
        return gameService.addGame(game)
    }

    @GetMapping("/all")
    fun fetchAllGames(): List<GameDTO> {
        val games = gameService.fetchAllGames()
        logger.info("fetchAllGames> games to retrieve: ${games.size}")
        return games
    }
}