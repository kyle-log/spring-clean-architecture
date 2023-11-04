package com.cocomo.game

import com.cocomo.game.local.LocalGameRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GameDataConfiguration {

    @Bean
    fun localGameRepository() = LocalGameRepository()
}