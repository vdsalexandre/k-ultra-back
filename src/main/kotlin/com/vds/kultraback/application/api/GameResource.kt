package com.vds.kultraback.application.api

import com.vds.kultraback.application.model.Game
import com.vds.kultraback.application.services.GameService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/game"], produces = ["application/json"])
class GameResource(val gameService: GameService) {

    private val logger = LoggerFactory.getLogger(GameResource::class.java)

    @GetMapping("/find/{id}")
    fun fetchGame(@PathVariable id: Long): Game {
        return gameService
            .findById(id)
            .also {
                logger.info("fetchGame> $it")
            }
    }

    @PostMapping("/add")
    fun addGame(@RequestBody game: Game): Game {
        return gameService
            .add(game)
            .also {
                logger.info("addGame> $it")
            }
    }

    @GetMapping("/find/all")
    fun fetchAllGames(): List<Game> {
        return gameService
            .findAll()
            .also {
                logger.info("fetchAllGames> games to retrieve: ${it.size}")
            }
    }

    @DeleteMapping("/del/{id}")
    fun removeGame(@PathVariable id: Long) {
        return gameService
            .delete(id)
            .also {
                logger.info("removeGame> $id")
            }
    }
}