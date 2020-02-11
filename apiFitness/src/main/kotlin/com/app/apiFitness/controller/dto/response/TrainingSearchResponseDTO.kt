package com.app.apiFitness.controller.dto.response

import com.app.apiFitness.database.repository.entity.TrainingEntity
import com.app.apiFitness.model.TrainingModel
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class TrainingSearchResponseDTO(
        var trainings: List<TrainingModel>? = null
        ): StandardResponseDTO()