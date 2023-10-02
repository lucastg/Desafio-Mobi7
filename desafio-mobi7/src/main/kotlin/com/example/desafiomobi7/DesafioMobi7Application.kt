package com.example.desafiomobi7

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "Desafio Mobi7", version = "1.0", description = "Localiza/Mobi7"))
@EntityScan
class DesafioMobi7Application

fun main(args: Array<String>) {
	runApplication<DesafioMobi7Application>(*args)
}
