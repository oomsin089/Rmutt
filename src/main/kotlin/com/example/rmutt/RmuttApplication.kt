package com.example.rmutt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RmuttApplication

fun main(args: Array<String>) {
	runApplication<RmuttApplication>(*args)
}
