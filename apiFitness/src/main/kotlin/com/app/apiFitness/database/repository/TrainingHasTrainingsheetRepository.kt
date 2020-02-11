package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.TrainingHasTrainingsheetEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainingHasTrainingsheetRepository: JpaRepository<TrainingHasTrainingsheetEntity, Long>{
    fun findAllByTrainingSheetId(trainingSheetId: Int?): List<TrainingHasTrainingsheetEntity>
}
