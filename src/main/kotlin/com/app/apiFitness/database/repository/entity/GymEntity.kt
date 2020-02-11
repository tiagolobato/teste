package com.app.apiFitness.database.repository.entity

import javax.persistence.*

@Entity
@Table(name = "gym", schema = "db_apifitness", catalog = "")
open class GymEntity {
    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    @get:Basic
    @get:Column(name = "serialNumber", nullable = true)
    var serialNumber: String? = null
    @get:Basic
    @get:Column(name = "name", nullable = true)
    var name: String? = null

    @get:OneToMany(mappedBy = "refGymEntity")
    var refGymHasMachineEntities: List<GymHasMachineEntity>? = null
    @get:OneToMany(mappedBy = "refGymEntity")
    var refGymHasUserEntities: List<GymHasUserEntity>? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "serialNumber = $serialNumber " +
                    "name = $name " +
                    ")"

}

