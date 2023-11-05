package com.cocomo.game.local

import com.cocomo.game.domain.Game
import com.cocomo.game.domain.GameId
import com.cocomo.game.domain.GameRepository

class LocalGameRepository : GameRepository {

    private val games = mutableMapOf<GameId, Game>()
    private var id = 0L
    private fun generateId() = GameId((++id))

    override fun save(model: Game): Game = when (model.id) {
        null -> insert(model)
        else -> update(model)
    }

    private fun insert(model: Game): Game {
        val newModel = model.copy(id = generateId())
        games[newModel.requireId()] = newModel
        return newModel
    }

    private fun update(model: Game): Game {
        games[model.requireId()] = model
        return model
    }

    override fun findById(id: GameId): Game? {
        return games[id]
    }
}