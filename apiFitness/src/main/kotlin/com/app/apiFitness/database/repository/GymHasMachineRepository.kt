package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.GymHasMachineEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GymHasMachineRepository: JpaRepository<GymHasMachineEntity, Long>