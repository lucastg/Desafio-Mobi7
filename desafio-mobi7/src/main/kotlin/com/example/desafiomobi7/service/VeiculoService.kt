package com.example.desafiomobi7.service

import com.example.desafiomobi7.dto.VeiculoDTO
import com.example.desafiomobi7.dto.TempoPontoDeInteresseDTO
import com.example.desafiomobi7.dto.TempoPontoDeInteresseResponseDTO
import com.example.desafiomobi7.dto.projection.TempoPontoDeInteresseProjection
import com.example.desafiomobi7.model.Veiculo
import com.example.desafiomobi7.model.toDto
import com.example.desafiomobi7.repository.VeiculoRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class VeiculoService() {

    @Autowired
    lateinit var veiculoRepository: VeiculoRepository

    @Transactional
    fun listarTodasPosicoes(): List<Veiculo> {
        val poscicoes = veiculoRepository.findAll()
        return poscicoes
    }

    fun criarNovaPosicaoVeiculo(veiculo: Veiculo): VeiculoDTO {
        if(veiculo.placa.isBlank() || veiculo.placa.isEmpty()){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
        veiculoRepository.save(veiculo)
        return veiculo.toDto()
    }

    fun listarTempoemPontoDeInteresse(tempoPontoDeInteresseDTO: TempoPontoDeInteresseDTO): List<TempoPontoDeInteresseResponseDTO> {
        val tempoDentroDoPOI = veiculoRepository.calcularTempoDentroDoPOI(tempoPontoDeInteresseDTO.placa, tempoPontoDeInteresseDTO.data)
        val resultado = mapToTempoPontoDeInteresseResponseDTO(tempoDentroDoPOI)
        return resultado
    }

    fun mapToTempoPontoDeInteresseResponseDTO(projections: List<TempoPontoDeInteresseProjection>): List<TempoPontoDeInteresseResponseDTO> {
        return projections.map {
            TempoPontoDeInteresseResponseDTO(
                it.placaDoVeiculo,
                it.nomeDoPoi,
                it.tempoDentroDoPoi
            )
        }
    }

}