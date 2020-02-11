package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.TrainingEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TrainingRepository: JpaRepository<TrainingEntity, Long>