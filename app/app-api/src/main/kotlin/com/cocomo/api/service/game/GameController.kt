package com.cocomo.api.service.game

import com.cocomo.game.application.*
import com.cocomo.game.domain.GameId
import com.cocomo.game.domain.GameNotFoundException
import org.springframework.web.bind.annotation.*

@RestController("/api/v1/games")
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
        return GameIdResponse(id)
    }

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): GameResponse =
        when (val game = gameFinder.find(GameId(id))) {
            null -> throw GameNotFoundException()
            else -> GameResponse(game.requireId(), game.name)
        }

    @PostMapping("{id}/join")
    fun join(@PathVariable id: Long): GameIdResponse {
        val command = GameJoinCommand(GameId(id))
        val joinedId = gameJoinProcessor.process(command)
        return GameIdResponse(joinedId)
    }

    @DeleteMapping("{id}/leave")
    fun leave(@PathVariable id: Long): GameIdResponse {
        val command = GameLeaveCommand(GameId(id))
        val leftId = gameLeaveProcessor.process(command)
        return GameIdResponse(leftId)
    }
}

data class GameIdResponse(val id: GameId)

data class GameResponse(val id: GameId, val name: String)