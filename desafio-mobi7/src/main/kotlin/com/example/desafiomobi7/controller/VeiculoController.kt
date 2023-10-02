package com.example.desafiomobi7.controller

import com.example.desafiomobi7.dto.VeiculoDTO
import com.example.desafiomobi7.dto.TempoPontoDeInteresseDTO
import com.example.desafiomobi7.dto.TempoPontoDeInteresseResponseDTO
import com.example.desafiomobi7.dto.toEntity
import com.example.desafiomobi7.model.Veiculo
import com.example.desafiomobi7.service.VeiculoService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

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
    fun cadastrarPosicaoVeiculo(@Valid @RequestBody veiculoDTO: VeiculoDTO): VeiculoDTO {
        val veiculo = veiculoDTO.toEntity()
        return veiculoService.criarNovaPosicaoVeiculo(veiculo)
    }

    @GetMapping("/tempo")
    @ResponseStatus(HttpStatus.OK)
    fun listarTempoemPontoDeInteresse(@RequestParam(value = "placa") placa: String?, @RequestParam(value = "data") data: LocalDate?): List<TempoPontoDeInteresseResponseDTO> {
        return veiculoService.listarTempoemPontoDeInteresse(TempoPontoDeInteresseDTO(placa, data))
    }
}