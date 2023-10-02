package com.example.desafiomobi7.model

import com.example.desafiomobi7.dto.VeiculoDTO
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*


@Entity
data class Veiculo(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    var id: UUID? = UUID.randomUUID(),

    @Column(nullable = false)
    var placa: String = "",

    @Column(nullable = false)
    var data: LocalDateTime,

    @Column(nullable = false)
    var velocidade: Int = 0,

    @Column(nullable = false)
    var longitude: Double = 0.0,

    @Column(nullable = false)
    var latitude: Double = 0.0,

    @Column(nullable = false)
    var veiculoLigado: Boolean = false,
) {
    constructor() : this(UUID.randomUUID(), "", LocalDateTime.now(), 0, 0.0, 0.0, false)
}

fun Veiculo.toDto(): VeiculoDTO {
    return VeiculoDTO(
        placa = this.placa,
        data = this.data,
        velocidade = this.velocidade,
        longitude = this.longitude,
        latitude = this.longitude,
        veiculoLigado = this.veiculoLigado,
    )
}

