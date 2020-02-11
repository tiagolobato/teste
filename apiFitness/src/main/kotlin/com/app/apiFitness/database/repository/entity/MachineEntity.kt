package com.app.apiFitness.database.repository.entity

import javax.persistence.*

@Entity
@Table(name = "machine", schema = "db_apifitness", catalog = "")
open class MachineEntity {
    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    @get:Basic
    @get:Column(name = "name", nullable = true)
    var name: String? = null

    @get:OneToMany(mappedBy = "refMachineEntity")
    var refGymHasMachineEntities: List<GymHasMachineEntity>? = null
    @get:OneToMany(mappedBy = "refMachineEntity")
    var refTrainingEntities: List<TrainingEntity>? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "name = $name " +
                    ")"

}

