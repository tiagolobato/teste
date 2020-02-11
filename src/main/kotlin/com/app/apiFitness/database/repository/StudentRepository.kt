package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<StudentEntity, Long>