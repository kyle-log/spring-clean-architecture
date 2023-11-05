package com.cocomo.api.service.game

import com.cocomo.game.application.*
import com.cocomo.game.domain.Game
import com.cocomo.game.domain.GameId
import com.cocomo.game.domain.GameNotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/games")
class GameController(
    val gameCreateProcessor: GameCreateProcessor,
    val gameJoinProcessor: GameJoinProcessor,
    val gameLeaveProcessor: GameLeaveProcessor,
    val gameFinder: GameFinder,
) {

    @PostMapping
    fun create(): GameIdResponse {
        val command = GameCreateCommand("game${System.currentTimeMillis()}")
        val id = gameCreateProcessor.process(command)
        return GameIdResponse.of(id)
    }

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): GameResponse =
        when (val game = gameFinder.find(GameId(id))) {
            null -> throw GameNotFoundException()
            else -> GameResponse.of(game)
        }

    @PostMapping("{id}/join")
    fun join(@PathVariable id: Long): GameIdResponse {
        val command = GameJoinCommand(GameId(id))
        val joinedId = gameJoinProcessor.process(command)
        return GameIdResponse.of(joinedId)
    }

    @DeleteMapping("{id}/leave")
    fun leave(@PathVariable id: Long): GameIdResponse {
        val command = GameLeaveCommand(GameId(id))
        val leftId = gameLeaveProcessor.process(command)
        return GameIdResponse.of(leftId)
    }
}

data class GameIdResponse(val id: Long) {
    companion object {
        fun of(id: GameId) = GameIdResponse(id.value)
    }
}

data class GameResponse(val id: Long, val name: String) {
    companion object {
        fun of(game: Game) = GameResponse(game.requireId().value, game.name)
    }
}