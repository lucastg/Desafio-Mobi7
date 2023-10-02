package com.example.desafiomobi7.dto

import com.example.desafiomobi7.model.Veiculo
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class VeiculoDTO(

    @field:NotBlank
    val placa: String,
    val data: LocalDateTime,
    val velocidade: Int,
    val latitude: Double,
    val longitude: Double,
    val veiculoLigado: Boolean
)


fun VeiculoDTO.toEntity(): Veiculo {
    return Veiculo(
        placa = this.placa,
        data = this.data,
        velocidade = this.velocidade,
        latitude = this.latitude,
        longitude = this.longitude,
        veiculoLigado = this.veiculoLigado
    )
}