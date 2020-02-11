package com.app.apiFitness.database.repository.entity

import com.app.apiFitness.model.TrainingModel
import com.app.apiFitness.model.TrainingSheetModel
import javax.persistence.*

@Entity
@Table(name = "training", schema = "db_apifitness", catalog = "")
    open class  TrainingEntity (){
    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @get:Basic
    @get:Column(name = "name", nullable = true)
    var name: String? = null
    @get:Basic
    @get:Column(name = "weight", nullable = true)
    var weight: Int? = null
    @get:Basic
    @get:Column(name = "series", nullable = true)
    var series: Int? = null
    @get:Basic
    @get:Column(name = "repetition", nullable = true)
    var repetition: Int? = null
    @get:Basic
    @get:Column(name = "obs", nullable = true)
    var obs: String? = null
    @get:Basic
    @get:Column(name = "timeBetweenTraining", nullable = true)
    var timeBetweenTraining: String? = null
    @get:Basic
    @get:Column(name = "eccentric", nullable = true)
    var eccentric: Int? = null
    @get:Basic
    @get:Column(name = "concentric", nullable = true)
    var concentric: String? = null
    @get:Basic
    @get:Column(name = "modality", nullable = true)
    var modality: String? = null
    @get:Basic
    @get:Column(name = "machine_id", nullable = false)
    var machineId: Int? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "machine_id", referencedColumnName = "id", insertable = false,updatable = false)
    var refMachineEntity: MachineEntity? = null
    @get:OneToMany(mappedBy = "refTrainingEntity",cascade = [(javax.persistence.CascadeType.REMOVE)])
    var refTrainingHasTrainingsheetEntities: List<TrainingHasTrainingsheetEntity>? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "name = $name " +
                    "weight = $weight " +
                    "series = $series " +
                    "repetition = $repetition " +
                    "obs = $obs " +
                    "timeBetweenTraining = $timeBetweenTraining " +
                    "eccentric = $eccentric " +
                    "concentric = $concentric " +
                    "modality = $modality " +
                    "machineId = $machineId " +
                    ")"
    constructor(trainingModel: TrainingModel) : this() {
        this.concentric = trainingModel.concentric
        this.eccentric = trainingModel.eccentric
        this.machineId = trainingModel.machineId
        this.modality = trainingModel.modality
        this.name = trainingModel.name
        this.obs = trainingModel.obs
        this.id = trainingModel.id?.toLong()
        this.weight = trainingModel.weight
        this.repetition = trainingModel.repetition
        this.series = trainingModel.series
    }

}

