package com.example.desafiomobi7.controller

import com.example.desafiomobi7.dto.PontoDeInteresseDTO
import com.example.desafiomobi7.dto.toEntity
import com.example.desafiomobi7.model.PontoDeInteresse
import com.example.desafiomobi7.service.PontoDeInteresseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pontosDeInteresse")
class PontoDeInteresseController() {

    @Autowired
    lateinit var pontoDeInteresseService: PontoDeInteresseService

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun listarTodos(): List<PontoDeInteresse>? {
        return this.pontoDeInteresseService.listarTodosPontosDeInteresse()
    }

    @PostMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun cadastrarPontoDeInteresse(@RequestBody @Validated pontoDeInteresseDTO: PontoDeInteresseDTO): PontoDeInteresseDTO {
        val pontoDeInteresse = pontoDeInteresseDTO.toEntity()
        return pontoDeInteresseService.criarNovoPontoDeInteresse(pontoDeInteresse)
    }
}