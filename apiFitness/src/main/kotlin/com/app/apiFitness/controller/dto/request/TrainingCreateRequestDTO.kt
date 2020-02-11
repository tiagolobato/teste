package com.app.apiFitness.controller.dto.request

import com.app.apiFitness.model.TrainingModel
import com.app.apiFitness.model.TrainingSheetModel

data class TrainingCreateRequestDTO(
    val trainingModel: TrainingModel,
    val trainingSheetId : Int?
)