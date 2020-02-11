package com.app.apiFitness.controller.dto.request

import com.app.apiFitness.model.TrainingModel

data class CreateTeacherHasStudentRequestDTO(
        val studentId: Int,
        val teacherId : Int
)