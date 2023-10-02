package com.example.desafiomobi7.model

import com.example.desafiomobi7.dto.PosicoesVeiculoDTO
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*


@Entity
data class Veiculo(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    var id: UUID? = UUID.randomUUID(),

    @Column
    var placa: String = "",

    @Column
    var data: LocalDateTime,

    @Column
    var velocidade: Int = 0,

    @Column
    var longitude: Double = 0.0,

    @Column
    var latitude: Double = 0.0,

    @Column
    var veiculoLigado: Boolean = false,
) {
    constructor() : this(UUID.randomUUID(), "", LocalDateTime.now(), 0, 0.0, 0.0, false)
}

fun Veiculo.toDto(): PosicoesVeiculoDTO {
    return PosicoesVeiculoDTO(
        placa = this.placa,
        data = this.data,
        velocidade = this.velocidade,
        longitude = this.longitude,
        latitude = this.longitude,
        veiculoLigado = this.veiculoLigado,
    )
}

