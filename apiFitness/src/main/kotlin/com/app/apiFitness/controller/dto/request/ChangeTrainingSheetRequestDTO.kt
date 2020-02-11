package com.app.apiFitness.controller.dto.request

import com.app.apiFitness.model.TrainingModel
import com.app.apiFitness.model.TrainingSheetModel

data class ChangeTrainingSheetRequestDTO(
        val trainingSheetModel: TrainingSheetModel
)