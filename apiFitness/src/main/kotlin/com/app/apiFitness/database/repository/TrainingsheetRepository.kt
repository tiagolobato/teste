package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.TrainingsheetEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TrainingsheetRepository: JpaRepository<TrainingsheetEntity, Long>{
    fun findByName(name: String)
}