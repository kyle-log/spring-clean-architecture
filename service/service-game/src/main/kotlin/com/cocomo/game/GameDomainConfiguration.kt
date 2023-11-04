package com.cocomo.game

import com.cocomo.game.application.GameHostService
import com.cocomo.game.application.GameParticipantService
import com.cocomo.game.application.GameQueryService
import com.cocomo.game.domain.GameRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GameDomainConfiguration {

    @Bean
    fun gameHostService(repository: GameRepository) = GameHostService(repository)

    @Bean
    fun gameParticipantService(repository: GameRepository) = GameParticipantService(repository)

    @Bean
    fun gameQueryService(repository: GameRepository) = GameQueryService(repository)
}