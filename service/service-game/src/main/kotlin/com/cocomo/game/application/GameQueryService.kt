package com.cocomo.game.application

import com.cocomo.game.domain.Game
import com.cocomo.game.domain.GameId
import com.cocomo.game.domain.GameRepository

class GameQueryService(
    val repository: GameRepository
) : GameFinder {

    override fun find(id: GameId): Game? {
        return repository.findById(id)
    }
}