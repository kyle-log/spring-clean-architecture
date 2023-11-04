import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.spring") version "1.9.10"

    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
}

allprojects {
    group = "com.cocomo"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("base")
        plugin("kotlin")
        plugin("kotlin-spring")
        plugin("io.spring.dependency-management")
        plugin("idea")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.1.3")
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        implementation("org.springframework:spring-context")
        implementation("org.springframework:spring-context-support")
    }


    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project("service-game") {
}

project("data-game") {
    dependencies {
        implementation(project(":service-game"))
    }
}

project("app-api") {
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation(project(":service-game"))
        implementation(project(":data-game"))
        implementation("org.springframework.boot:spring-boot-starter-web")
    }

    springBoot {
        buildInfo()
    }

    tasks.jar {
        enabled = true
    }
    tasks.bootJar {
        enabled = true
    }
}

tasks.jar {
    enabled = false
}
tasks.bootJar {
    enabled = false
}