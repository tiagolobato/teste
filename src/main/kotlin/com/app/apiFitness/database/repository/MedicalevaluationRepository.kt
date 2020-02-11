package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.MedicalevaluationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MedicalevaluationRepository: JpaRepository<MedicalevaluationEntity, Long>