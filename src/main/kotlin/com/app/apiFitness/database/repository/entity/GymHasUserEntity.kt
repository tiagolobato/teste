package com.app.apiFitness.database.repository.entity

import javax.persistence.*

@Entity
@Table(name = "gym_has_user", schema = "db_apifitness", catalog = "")
open class GymHasUserEntity {
    @get:Id
    @get:Column(name = "Id", nullable = false)
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    @get:Basic
    @get:Column(name = "gym_id", nullable = false)
    var gymId: Int? = null
    @get:Basic
    @get:Column(name = "user_id", nullable = false)
    var userId: Int? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "gym_id", referencedColumnName = "id", insertable = false,updatable = false)
    var refGymEntity: GymEntity? = null
    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false,updatable = false)
    var refUserEntity: UserEntity? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "gymId = $gymId " +
                    "userId = $userId " +
                    ")"

}

