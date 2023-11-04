package com.cocomo.api.service.game

import com.cocomo.game.GameDomainConfiguration
import com.cocomo.game.GameDataConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(
    GameDomainConfiguration::class,
    GameDataConfiguration::class,
)
@Configuration
class GameConfiguration