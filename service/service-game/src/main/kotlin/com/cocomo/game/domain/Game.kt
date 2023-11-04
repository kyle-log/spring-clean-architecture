package com.cocomo.game.domain

data class Game(
    val id: GameId?,
    val name: String,
) {

    fun requireId() = id ?: throw GameNotFoundException()

    companion object {
        fun create(
            name: String
        ) = Game(
            id = null,
            name = name,
        )
    }
}

@JvmInline
value class GameId(val value: Long)