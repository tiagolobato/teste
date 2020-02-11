package com.app.apiFitness.controller.dto.response

import com.app.apiFitness.model.TrainingModel
import com.app.apiFitness.model.UserProfileModel

data class SearchTeacherStudentsResponseDTO(
        var users: List<UserProfileModel>? = null
): StandardResponseDTO()