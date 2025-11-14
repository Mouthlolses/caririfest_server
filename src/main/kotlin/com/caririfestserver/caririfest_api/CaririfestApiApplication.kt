package com.caririfestserver.caririfest_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class CaririfestApiApplication

fun main(args: Array<String>) {
	runApplication<CaririfestApiApplication>(*args)
}
