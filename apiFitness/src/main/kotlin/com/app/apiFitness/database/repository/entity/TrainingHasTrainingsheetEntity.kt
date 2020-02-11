package com.app.apiFitness.database.repository.entity

import javax.persistence.*

@Entity
@Table(name = "training_has_trainingsheet", schema = "db_apifitness", catalog = "")
open class  TrainingHasTrainingsheetEntity {
    @get:Id
    @get:Column(name = "id", nullable = false)
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    @get:Basic
    @get:Column(name = "training_id", nullable = true)
    var trainingId: Int? = null
    @get:Basic
    @get:Column(name = "training_sheet_id", nullable = true)
    var trainingSheetId: Int? = null
    @get:Basic
    @get:Column(name = "order_training", nullable = true)
    var orderTraining: String? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "training_id", referencedColumnName = "id", insertable = false,updatable = false)
    var refTrainingEntity: TrainingEntity? = null
    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "training_sheet_id", referencedColumnName = "id", insertable = false,updatable = false)
    var refTrainingsheetEntity: TrainingsheetEntity? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "trainingId = $trainingId " +
                    "trainingSheetId = $trainingSheetId " +
                    "orderTraining = $orderTraining " +
                    ")"
}

