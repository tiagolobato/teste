package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.NutritionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface NutritionRepository: JpaRepository<NutritionEntity, Long>