package com.app.apiFitness.controller.dto.request

import com.app.apiFitness.model.UserProfileModel

data class TeacherProfileRequestDTO (
        val user: UserProfileModel,
        val CREF: String? = null
)