package com.example.desafiomobi7.model

import com.example.desafiomobi7.dto.PontoDeInteresseDTO
import jakarta.persistence.*
import java.util.*

@Entity
data class PontoDeInteresse(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    var id: UUID? = UUID.randomUUID(),

    @Column
    var nome: String = "",

    @Column
    var raio: Int = 0,

    @Column
    var latitude: Double = 0.0,

    @Column
    var longitude: Double = 0.0
)

fun PontoDeInteresse.toDto(): PontoDeInteresseDTO {
    return PontoDeInteresseDTO(
        nome = this.nome,
        raio = this.raio,
        latitude = this.latitude,
        longitude = this.longitude,
    )
}
