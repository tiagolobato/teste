package com.app.apiFitness.controller.dto.response

data class SearchAllTrainingSheetsResponseDTO (
        val name: String,
        val description: String? = null,
        val studentName: String
)