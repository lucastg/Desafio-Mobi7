package com.example.desafiomobi7.repository

import com.example.desafiomobi7.dto.projection.TempoPontoDeInteresseProjection
import com.example.desafiomobi7.model.Veiculo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface VeiculoRepository : JpaRepository<Veiculo, UUID> {

    @Query(
        nativeQuery = true,
        value = "WITH tempo_dentro_do_poi AS (" +
                "    SELECT" +
                "        v.placa AS placa_do_veiculo," +
                "        p.nome AS nome_do_poi," +
                "        v.data AS entrada_poi," +
                "        LEAD(v.data) OVER (PARTITION BY v.placa ORDER BY v.data) AS saida_poi" +
                "    FROM" +
                "        veiculo v" +
                "    JOIN" +
                "        ponto_de_interesse p" +
                "    ON" +
                "        (v.latitude - p.latitude) * (v.latitude - p.latitude) +" +
                "        (v.longitude - p.longitude) * (v.longitude - p.longitude) <= (p.raio * p.raio)" +
                ")" +
                "SELECT" +
                "    placa_do_veiculo AS placaDoVeiculo," +
                "    nome_do_poi AS nomeDoPoi," +
                "    TO_CHAR(INTERVAL '1 second' * SUM(EXTRACT(EPOCH FROM (saida_poi - entrada_poi))), 'HH24:MI:SS') AS tempoDentroDoPoi" +
                " FROM" +
                "    tempo_dentro_do_poi" +
                " WHERE" +
                "    (:placaDoVeiculo IS NULL OR placa_do_veiculo = :placaDoVeiculo)" +
                "    AND " +
                "   (CAST(:entradaPoi AS DATE) IS NULL OR DATE(entrada_poi) = CAST(:entradaPoi AS DATE))" +
                " GROUP BY" +
                "    placa_do_veiculo, nome_do_poi"
    )
    fun calcularTempoDentroDoPOI(
        @Param("placaDoVeiculo") placaDoVeiculo: String?,
        @Param("entradaPoi") entradaPoi: LocalDate?
    ): List<TempoPontoDeInteresseProjection>

}