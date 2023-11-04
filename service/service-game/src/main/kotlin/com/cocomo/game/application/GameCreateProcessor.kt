package com.cocomo.game.application

import com.cocomo.game.domain.GameId

interface GameCreateProcessor {
    fun process(command: GameCreateCommand): GameId
}

data class GameCreateCommand(
    val name: String,
)