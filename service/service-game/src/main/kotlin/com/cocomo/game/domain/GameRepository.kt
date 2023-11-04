package com.cocomo.game.domain

interface GameRepository {

    fun save(model: Game): Game

    fun findById(id: GameId): Game?
}