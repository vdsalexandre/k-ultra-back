package com.vds.kultraback.api

import com.vds.kultraback.model.Game
import com.vds.kultraback.services.GameService
import com.vds.kultraback.utils.Util.emptyGame
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/game"], produces = ["application/json"])
class GameResource(val gameService: GameService) {

    private val logger = LoggerFactory.getLogger(GameResource::class.java)

    @GetMapping("/find/{id}")
    fun fetchGame(@PathVariable id: Long): ResponseEntity<Game> {
        return gameService.retrieveById(id)
            .also { logger.info("fetchGame> $it") }
            ?.let { ok(it) } ?: ResponseEntity(emptyGame, HttpStatus.NOT_FOUND)
    }

    @PostMapping("/add")
    fun addGame(@RequestBody game: Game): Game {
        return gameService
            .add(game)
            .also { logger.info("addGame> $it") }
    }

    @GetMapping("/find/all")
    fun fetchAllGames(): ResponseEntity<List<Game>> {
        return gameService
            .findAll()
            .also { logger.info("fetchAllGames> games to retrieve: ${it.size}") }
            .let { ok(it) }
    }

    @DeleteMapping("/del/{id}")
    fun removeGame(@PathVariable id: Long) {
        return gameService
            .delete(id)
            .also {
                logger.info("removeGame> $id")
            }
    }

    @PutMapping("/update")
    fun updateGame(@RequestBody game: Game): ResponseEntity<Game> {
        return gameService.update(game)
            .also { logger.info("updateGame> $it") }
            ?.let { ok(it) } ?: notFound().build()
    }
}