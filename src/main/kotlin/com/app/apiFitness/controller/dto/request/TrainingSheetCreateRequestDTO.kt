package com.app.apiFitness.controller.dto.request

import com.app.apiFitness.model.TrainingModel
import com.app.apiFitness.model.TrainingSheetModel
import com.app.apiFitness.model.UserProfileModel

data class TrainingSheetCreateRequestDTO (
        val trainingSheetModel: TrainingSheetModel
)