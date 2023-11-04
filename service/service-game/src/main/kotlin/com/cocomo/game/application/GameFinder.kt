package com.cocomo.game.application

import com.cocomo.game.domain.Game
import com.cocomo.game.domain.GameId

interface GameFinder {
    fun find(id: GameId): Game?
}