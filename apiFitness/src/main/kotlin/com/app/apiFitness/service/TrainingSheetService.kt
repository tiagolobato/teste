package com.app.apiFitness.service

import com.app.apiFitness.controller.dto.request.ChangeTrainingSheetRequestDTO
import com.app.apiFitness.controller.dto.request.TrainingSheetCreateRequestDTO
import com.app.apiFitness.controller.dto.response.SearchTrainingSheetResponseDTO

interface TrainingSheetService {
    fun searchAllTrainingSheets(id: Long) : SearchTrainingSheetResponseDTO
    fun create(trainingSheetDTO: TrainingSheetCreateRequestDTO)

    fun change(changeTrainingSheetRequestDTO: ChangeTrainingSheetRequestDTO)
}