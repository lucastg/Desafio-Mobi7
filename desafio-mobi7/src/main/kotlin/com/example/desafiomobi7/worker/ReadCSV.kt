package com.example.desafiomobi7.worker

import com.example.desafiomobi7.model.PontoDeInteresse
import com.example.desafiomobi7.model.Veiculo
import com.example.desafiomobi7.repository.PontoDeInteresseRepository
import com.example.desafiomobi7.repository.VeiculoRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*

@Service
class ReadCSV() {

    @Autowired
    lateinit var veiculoRepository: VeiculoRepository

    @Autowired
    lateinit var pontoDeInteresseRepository: PontoDeInteresseRepository

    val posicoesCSV =
        "csv/posicoes.csv"

    val pontoDeInteresseCsv =
        "csv/base_pois_def.csv"

    @Transactional
    fun principal() {
        if (veiculoRepository.findAll().isEmpty()) {
            this.readPosicoesCsv()
        }

        if (pontoDeInteresseRepository.findAll().isEmpty()) {
            this.readPontoDeInteresseCsv()
        }
    }

    @Transactional
    fun readPosicoesCsv() {
        try {
            val file = File(posicoesCSV)
            val fileInputStream = FileInputStream(file)
            val reader = fileInputStream.bufferedReader()
            val header = reader.readLine()
            val dateFormat =
                SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'z '(Hora oficial do Brasil)'", Locale.ENGLISH)

            val posicoesVeiculos = reader.lineSequence()
                .filter { it.isNotBlank() }
                .map {
                    val tokens = it.split(',')
                    val placa = tokens[0].trim()
                    val dateString = tokens[1].trim()
                    val date = dateFormat.parse(dateString)
                    val data = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime()
                    val velocidade = tokens[2].trim().toInt()
                    val longitude = tokens[3].trim().toDouble()
                    val latitude = tokens[4].trim().toDouble()
                    val veiculoLigado = tokens[5].trim().toBoolean()

                    Veiculo(
                        placa = placa,
                        data = data,
                        velocidade = velocidade,
                        latitude = latitude,
                        longitude = longitude,
                        veiculoLigado = veiculoLigado
                    )
                }
                .toList()

            veiculoRepository.saveAll(posicoesVeiculos)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Transactional
    fun readPontoDeInteresseCsv() {
        try {
            val file = File(pontoDeInteresseCsv)
            val fileInputStream = FileInputStream(file)
            val reader = fileInputStream.bufferedReader()
            val header = reader.readLine()

            val pontosDeInteresse = reader.lineSequence()
                .filter { it.isNotBlank() }
                .map {
                    val tokens = it.split(',')
                    val nome = tokens[0].trim()
                    val raio = tokens[1].trim().toInt()
                    val latitude = tokens[2].trim().toDouble()
                    val longitude = tokens[3].trim().toDouble()

                    PontoDeInteresse(
                        nome = nome,
                        raio = raio,
                        latitude = latitude,
                        longitude = longitude
                    )
                }
                .toList()

            pontoDeInteresseRepository.saveAll(pontosDeInteresse)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}