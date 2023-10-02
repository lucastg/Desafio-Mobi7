package com.example.desafiomobi7.controller

import com.example.desafiomobi7.dto.PosicoesVeiculoDTO
import com.example.desafiomobi7.dto.TempoPontoDeInteresseDTO
import com.example.desafiomobi7.dto.TempoPontoDeInteresseResponseDTO
import com.example.desafiomobi7.dto.toEntity
import com.example.desafiomobi7.model.Veiculo
import com.example.desafiomobi7.service.VeiculoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/veiculos")
class VeiculoController() {

    @Autowired
    lateinit var veiculoService: VeiculoService

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun listarTodos(): List<Veiculo>? {
        return this.veiculoService.listarTodasPosicoes()
    }

    @PostMapping("/posicoes")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun cadastrarPosicaoVeiculo(@RequestBody @Validated posicoesVeiculoDTO: PosicoesVeiculoDTO): PosicoesVeiculoDTO {
        val posicoesVeiculo = posicoesVeiculoDTO.toEntity()
        return veiculoService.criarNovaPosicaoVeiculo(posicoesVeiculo)
    }

    @PostMapping("/tempo")
    @ResponseStatus(HttpStatus.OK)
    fun listarTempoemPontoDeInteresse(@RequestBody tempoPontoDeInteresseDTO: TempoPontoDeInteresseDTO): List<TempoPontoDeInteresseResponseDTO> {
        return veiculoService.listarTempoemPontoDeInteresse(tempoPontoDeInteresseDTO)
    }
}