package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.TeacherEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository: JpaRepository<TeacherEntity, Long>