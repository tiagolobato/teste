package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.GymEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GymRepository: JpaRepository<GymEntity, Long>