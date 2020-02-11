package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.AppointmentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AppointmentRepository : JpaRepository<AppointmentEntity, Long>