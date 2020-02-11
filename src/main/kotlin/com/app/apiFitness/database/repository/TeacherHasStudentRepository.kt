package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.TeacherHasStudentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherHasStudentRepository: JpaRepository<TeacherHasStudentEntity, Long>