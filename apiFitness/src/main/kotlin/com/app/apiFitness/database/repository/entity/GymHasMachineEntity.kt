package com.app.apiFitness.database.repository.entity

import javax.persistence.*

@Entity
@Table(name = "gym_has_machine", schema = "db_apifitness", catalog = "")
open class GymHasMachineEntity {
    @get:Id
    @get:Column(name = "id", nullable = false)
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    @get:Basic
    @get:Column(name = "gym_id", nullable = true)
    var gymId: Int? = null
    @get:Basic
    @get:Column(name = "machine_id", nullable = true)
    var machineId: Int? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "gym_id", referencedColumnName = "id", insertable = false,updatable = false)
    var refGymEntity: GymEntity? = null
    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "machine_id", referencedColumnName = "id", insertable = false,updatable = false)
    var refMachineEntity: MachineEntity? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "gymId = $gymId " +
                    "machineId = $machineId " +
                    ")"

}

