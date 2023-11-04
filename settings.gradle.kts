pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "spring-clean-architecture"

// service
include("service-game")
project(":service-game").projectDir = file("service/service-game")

// data
include("data-game")
project(":data-game").projectDir = file("data/data-game")

// app
include("app-api")
project(":app-api").projectDir = file("app/app-api")