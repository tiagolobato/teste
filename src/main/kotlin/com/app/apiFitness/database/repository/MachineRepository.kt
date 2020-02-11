package com.app.apiFitness.database.repository

import com.app.apiFitness.database.repository.entity.MachineEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MachineRepository: JpaRepository<MachineEntity, Long>