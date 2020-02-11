package com.app.apiFitness.controller.dto.request

import com.app.apiFitness.constants.enums.UserStatusEnum
import com.app.apiFitness.model.UserProfileModel
import javax.persistence.*


data class UserRequestDTO (
        val user: UserProfileModel
)