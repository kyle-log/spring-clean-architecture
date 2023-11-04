package com.cocomo.game.application

import com.cocomo.game.domain.GameId

interface GameLeaveProcessor {
    fun process(command: GameLeaveCommand): GameId
}

data class GameLeaveCommand(
    val id: GameId,
)