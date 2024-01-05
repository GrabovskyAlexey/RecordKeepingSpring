package ru.grabovsky.recordkeeping.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class CoreApplication{}

fun main(args: Array<String>) {
	runApplication<CoreApplication>(*args)
}
