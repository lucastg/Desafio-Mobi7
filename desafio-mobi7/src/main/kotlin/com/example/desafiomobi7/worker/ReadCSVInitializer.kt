package com.example.desafiomobi7.worker

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ReadCSVInitializer() {

    @Autowired
    lateinit var readCSV: ReadCSV

    @PostConstruct
    fun initialize() {
        readCSV.principal()
    }
}