package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.GymHasUserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GymHasUserRepository : JpaRepository<GymHasUserEntity, Long>