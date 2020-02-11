package com.app.apiFitness.controller.dto.response

import com.app.apiFitness.model.TrainingSheetModel
import com.fasterxml.jackson.annotation.JsonProperty

data class SearchTrainingSheetResponseDTO(
        var size: Long = 0,
        var teacher: String = "",
        @JsonProperty("data")
        var trainingSheets: List<TrainingSheetModel>? = null
): StandardResponseDTO()