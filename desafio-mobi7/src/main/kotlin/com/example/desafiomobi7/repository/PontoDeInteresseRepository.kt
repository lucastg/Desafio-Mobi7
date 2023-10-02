package com.example.desafiomobi7.repository

import com.example.desafiomobi7.model.PontoDeInteresse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PontoDeInteresseRepository : JpaRepository<PontoDeInteresse, UUID> {}