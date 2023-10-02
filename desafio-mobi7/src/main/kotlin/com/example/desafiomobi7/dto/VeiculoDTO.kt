package com.example.desafiomobi7.dto

import com.example.desafiomobi7.model.Veiculo
import java.time.LocalDateTime

data class PosicoesVeiculoDTO(
    val placa: String,
    val data: LocalDateTime,
    val velocidade: Int,
    val latitude: Double,
    val longitude: Double,
    val veiculoLigado: Boolean
)


fun PosicoesVeiculoDTO.toEntity(): Veiculo {
    return Veiculo(
        data = this.data,
        velocidade = this.velocidade,
        latitude = this.latitude,
        longitude = this.longitude,
        veiculoLigado = this.veiculoLigado
    )
}