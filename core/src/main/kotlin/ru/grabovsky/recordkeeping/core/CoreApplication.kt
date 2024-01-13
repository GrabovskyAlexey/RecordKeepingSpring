package ru.grabovsky.recordkeeping.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import ru.grabovsky.recordkeeping.core.config.JwtProperties

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(JwtProperties::class)
class CoreApplication{}

fun main(args: Array<String>) {
	runApplication<CoreApplication>(*args)
}
