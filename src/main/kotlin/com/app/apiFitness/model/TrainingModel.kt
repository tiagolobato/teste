package com.app.apiFitness.model

import com.app.apiFitness.database.repository.entity.TrainingEntity

data class TrainingModel(
        var id: Int? = null,
        var name: String? = null,
        var concentric: String? = null,
        var timeBetweenTraining: String? = null,
        var obs: String? = null,
        var weight: Int? = null,
        var series: Int? = null,
        var repetition: Int? = null,
        var eccentric: Int? = null,
        var modality: String? = null,
        var machineId: Int? = null

){
    constructor(trainingEntity: TrainingEntity) : this() {
        this.concentric = trainingEntity.concentric
        this.eccentric = trainingEntity.eccentric
        this.machineId = trainingEntity.machineId
        this.modality = trainingEntity.modality
        this.name = trainingEntity.name
        this.obs = trainingEntity.obs
        this.id = trainingEntity.id?.toInt()
        this.weight = trainingEntity.weight
        this.repetition = trainingEntity.repetition
        this.series = trainingEntity.series
    }
}