package com.cocomo.api

import com.cocomo.api.service.game.GameConfiguration
import org.springframework.boot.Banner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import kotlin.system.exitProcess

@SpringBootApplication
class ApiApplication

fun main(args: Array<String>) {
    try {
        SpringApplicationBuilder()
            .sources(ParentConfiguration::class.java).run {
                web(WebApplicationType.NONE)
                bannerMode(Banner.Mode.OFF)
            }
            .child(GameConfiguration::class.java).run {
                web(WebApplicationType.NONE)
                bannerMode(Banner.Mode.OFF)
            }
            .sibling(ApiApplication::class.java).run {
                web(WebApplicationType.SERVLET)
                bannerMode(Banner.Mode.OFF)
            }
            .run(*args)
    } catch (error: Exception) {
        exitProcess(-1)
    }
}
