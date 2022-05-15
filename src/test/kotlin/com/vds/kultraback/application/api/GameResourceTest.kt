package com.vds.kultraback.application.api

import assertk.assertAll
import com.ninjasquad.springmockk.MockkBean
import com.vds.kultraback.application.model.Game
import com.vds.kultraback.application.services.GameService
import com.vds.kultraback.application.utils.Util.emptyGame
import io.mockk.every
import io.mockk.verify
import java.math.BigDecimal
import java.time.LocalDate
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(GameResource::class)
internal class GameResourceTest {

    companion object {
        val GAMES = listOf(
            Game(1, "game 1", BigDecimal.valueOf(50.95), 1500, listOf("fps"), LocalDate.of(2022, 1, 1)),
            Game(2, "game 2", BigDecimal.valueOf(55.95), 1600, listOf("moba"), LocalDate.of(2021, 12, 31)),
            Game(3, "game 3", BigDecimal.valueOf(59.95), 1700, listOf("survival"), LocalDate.of(2002, 5, 27))
        )

        val GAME = Game(10, "game 10", BigDecimal.valueOf(70), 2000, listOf("tps", "fps"), LocalDate.now())
    }

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var gameService: GameService

    @Test
    internal fun `should return all games when calling endpoint`() {
        every { gameService.findAll() } returns GAMES

        mockMvc.perform(MockMvcRequestBuilders
            .get("/game/find/all")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id", CoreMatchers.`is`(1)))
            .andExpect(jsonPath("$[1].title", CoreMatchers.`is`("game 2")))
            .andExpect(jsonPath("$[2].releaseDate", CoreMatchers.`is`("2002-05-27")))

        assertAll { verify(exactly = 1) { gameService.findAll() } }
    }

    @Test
    internal fun `should return game identified by id when calling endpoint`() {
        val gameId: Long = 10

        every { gameService.findById(gameId) } returns GAME

        mockMvc.perform(MockMvcRequestBuilders
            .get("/game/find/$gameId")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id", CoreMatchers.`is`(10)))
            .andExpect(jsonPath("$.title", CoreMatchers.`is`("game 10")))
            .andExpect(jsonPath("$.price", CoreMatchers.`is`(70)))

        assertAll { verify(exactly = 1) { gameService.findById(gameId) } }
    }

    @Test
    internal fun `should return empty game giving wrong id`() {
        val gameId: Long = 25

        every { gameService.findById(gameId) } returns emptyGame

        mockMvc.perform(MockMvcRequestBuilders
            .get("/game/find/$gameId")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id", CoreMatchers.`is`(-1)))
            .andExpect(jsonPath("$.title", CoreMatchers.not("value")))
            .andExpect(jsonPath("$.releaseDate", CoreMatchers.nullValue()))

        assertAll { verify(exactly = 1) { gameService.findById(gameId) } }
    }
}