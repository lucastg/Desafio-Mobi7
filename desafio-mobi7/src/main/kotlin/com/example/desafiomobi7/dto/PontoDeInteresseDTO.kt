package com.example.desafiomobi7.dto

import com.example.desafiomobi7.model.PontoDeInteresse

data class PontoDeInteresseDTO(
    val nome: String,
    val raio: Int,
    val latitude: Double,
    val longitude: Double
)

fun PontoDeInteresseDTO.toEntity(): PontoDeInteresse {
    return PontoDeInteresse(
        nome = this.nome,
        raio = this.raio,
        latitude = this.latitude,
        longitude = this.longitude
    )
}
