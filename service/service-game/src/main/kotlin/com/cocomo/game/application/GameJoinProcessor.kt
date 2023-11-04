package com.cocomo.game.application

import com.cocomo.game.domain.GameId

interface GameJoinProcessor {
    fun process(command: GameJoinCommand): GameId
}

data class GameJoinCommand(
    val id: GameId,
)