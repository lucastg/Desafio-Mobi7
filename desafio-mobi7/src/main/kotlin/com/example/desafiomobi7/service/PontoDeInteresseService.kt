package com.example.desafiomobi7.service

import com.example.desafiomobi7.dto.PontoDeInteresseDTO
import com.example.desafiomobi7.model.PontoDeInteresse
import com.example.desafiomobi7.model.toDto
import com.example.desafiomobi7.repository.PontoDeInteresseRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PontoDeInteresseService() {

    @Autowired
    lateinit var pontoDeInteresseRepository: PontoDeInteresseRepository

    @Transactional
    fun listarTodosPontosDeInteresse(): List<PontoDeInteresse> {
        val pontosDeInteresse = pontoDeInteresseRepository.findAll()
        return pontosDeInteresse
    }

    fun criarNovoPontoDeInteresse(pontoDeInteresse: PontoDeInteresse): PontoDeInteresseDTO {
        pontoDeInteresseRepository.save(pontoDeInteresse)
        return pontoDeInteresse.toDto()
    }
}