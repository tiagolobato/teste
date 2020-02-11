package com.app.apiFitness.service

import com.app.apiFitness.controller.dto.request.CreateTeacherHasStudentRequestDTO
import com.app.apiFitness.controller.dto.request.SearchTeacherStudentsRequestDTO
import com.app.apiFitness.controller.dto.request.TeacherProfileRequestDTO
import com.app.apiFitness.database.repository.entity.TeacherEntity
import com.app.apiFitness.database.repository.entity.UserEntity
import com.app.apiFitness.model.UserProfileModel

interface TeacherProfileService {
    fun create(teacherProfileRequestDTO: TeacherProfileRequestDTO)
    fun verifyIfEmailIsValid(email: String): Boolean
    fun createUser(teacherProfileRequestDTO: TeacherProfileRequestDTO) : UserEntity
    fun createTeacher(userId: Long?,CREF: String?) : TeacherEntity
    fun searchTeacherStudents(searchTeacherStudentsRequestDTO: SearchTeacherStudentsRequestDTO): List<UserProfileModel>?
    fun createTeacherHasStudent(createTeacherHasStudentRequestDTO: CreateTeacherHasStudentRequestDTO)
}