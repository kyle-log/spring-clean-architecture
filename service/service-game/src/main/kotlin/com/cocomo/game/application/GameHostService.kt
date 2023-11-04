package com.cocomo.game.application

import com.cocomo.game.domain.Game
import com.cocomo.game.domain.GameId
import com.cocomo.game.domain.GameRepository

class GameHostService(
    val repository: GameRepository,
) : GameCreateProcessor {

    override fun process(command: GameCreateCommand): GameId {
        val game = Game.create(name = command.name)
            .run { repository.save(this) }
        return game.requireId()
    }
}