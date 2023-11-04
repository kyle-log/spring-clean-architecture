package com.cocomo.game.application

import com.cocomo.game.domain.GameId
import com.cocomo.game.domain.GameRepository

class GameParticipantService(
    val repository: GameRepository,
): GameJoinProcessor, GameLeaveProcessor {
    override fun process(command: GameJoinCommand): GameId {
        TODO("Not yet implemented")
    }

    override fun process(command: GameLeaveCommand): GameId {
        TODO("Not yet implemented")
    }

}