package com.marcelogontijo.stocks_backend.core.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StocksBackendApplication

fun main(args: Array<String>) {
	runApplication<StocksBackendApplication>(*args)
}
